package com.example.mimimimetr.controllers;

import com.example.mimimimetr.entities.Cat;
import com.example.mimimimetr.entities.User;
import com.example.mimimimetr.services.CatServiceImpl;
import com.example.mimimimetr.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class CatController {
    private CatServiceImpl catService;
    private UserServiceImpl userService;
    //очередность котов
    private static List<Integer> orderCat = new ArrayList<>();

    static {

        for (int i = 1; i <= 10; i++) {
            orderCat.add(i);
        }
    }

    @Autowired
    public void setCatService(CatServiceImpl catService) {
        this.catService = catService;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getCats(Model model, Principal principal) {

        List<Cat> cats = catService.findAllOrderById();
        if(principal == null) {
            return "mimimimetr";
        }
        String userName = principal.getName();
        User user = userService.findUserByUsername(userName);
        int queue = user.getQueue();
        String[] order = user.getCatOrder().split(" ");
        
        if (queue >= cats.size() - 1) {
            return to10Cats(model, principal);  //если все коты показаны, то уходим на страницу финиш
        }
        
        Cat cat1 = catService.getCatById(Long.parseLong(order[queue]));
        Cat cat2 = catService.getCatById(Long.parseLong(order[queue + 1]));;

        model.addAttribute("cat1", cat1);
        model.addAttribute("cat2", cat2);
        return "mimimimetr";
    }

    @PostMapping
    public String putCat(Model model, Principal principal, @RequestBody String catInfo) {
        System.out.println(catInfo);
        String[] strings = catInfo.split("=");
        if(strings[1] != null) {  //выбранный в прошлой иттерации кот увеличивает свою популярность
            Cat cat = catService.findCatById(Long.parseLong(strings[1]));
            cat.setPopularity(cat.getPopularity() + 1);
            catService.saveCat(cat);
        }

        List<Cat> cats = catService.findAllOrderById();

        String userName = principal.getName();
        User user = userService.findUserByUsername(userName);
        int queue = user.getQueue();

        if (queue >= cats.size() - 1) {
            return to10Cats(model, principal);  //если все коты показаны, то уходим на страницу финиш
        }
        queue += 2;

        userService.setQueue(userName, queue);
        return getCats(model, principal);
    }

    @GetMapping("top10cats")
    public String to10Cats(Model model, Principal principal) {
        List<Cat> top10Cats = catService.getTop10();
        model.addAttribute("top10Cats", top10Cats);

        String userName = principal.getName();
        userService.setQueue(userName, 0); //Очередность возвращается к 0
//        userService.setOrder(userName, getOrderCat()); //Назначаем новый порядок котов

        return "top10cats";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }
    @PostMapping("/registration")
    public String registrationForm(@ModelAttribute(value = "user") User user) {
        String oldPassword = user.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String newPassword = passwordEncoder.encode(oldPassword);
        user.setPassword(newPassword);
        user.setCatOrder(getOrderCat()); // пользователю назначается порядок котов
        userService.addUser(user);
        return "redirect:/";
    }

    /**
     * метод перемешивает порядок котов для каждого отдеьного пользователя
     */
    static String getOrderCat() {
        Collections.shuffle(orderCat);
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer i : orderCat) {
            stringBuilder.append(i).append(" ");
        }
        return stringBuilder.toString().trim();
    }
}
