package com.example.mimimimetr.controllers;

import com.example.mimimimetr.entities.Cat;
import com.example.mimimimetr.services.CatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class CatController {
    private CatServiceImpl catService;
    //очередность котов
    private static int queue = 0;

    @Autowired
    public void setCatService(CatServiceImpl catService) {
        this.catService = catService;
    }

    @GetMapping
    public String getCats(Model model) {

        List<Cat> cats = catService.findByQueue();

        if (queue >= cats.size() - 1) {
            return to10Cats(model);  //если все коты показаны, то уходим на страницу финиш
        }

        Cat cat1 = cats.get(queue);
        Cat cat2 = cats.get(++queue);
        queue += 1;
        model.addAttribute("cat1", cat1);
        model.addAttribute("cat2", cat2);
        return "mimimimetr";
    }

    @PostMapping
    public String putCat(Model model, @RequestBody String catInfo) {
        System.out.println(catInfo);
        String[] strings = catInfo.split("=");
        if(strings[1] != null) {  //выбранный в прошлой иттерации кот увеличивает свою популярность
            Cat cat = catService.findCatById(Long.parseLong(strings[1]));
            cat.setPopularity(cat.getPopularity() + 1);
            catService.saveCat(cat);
        }
        return getCats(model);
    }

    @GetMapping("top10cats")
    public String to10Cats(Model model) {
        List<Cat> top10Cats = catService.getTop10();
        List<Byte> order = new ArrayList<>();
        for (byte i = 0; i < 10; i++) {
            order.add(i);
        }
        Collections.shuffle(order);

        for (int i = 0; i < order.size(); i++) {
            Cat cat = top10Cats.get(i);
            cat.setQueue(order.get(i));
            catService.saveCat(cat);
        }
        queue = 0;
        model.addAttribute("top10Cats", top10Cats);
        return "top10cats";
    }
}
