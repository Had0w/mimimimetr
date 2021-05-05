package com.example.mimimimetr.controllers;

import com.example.mimimimetr.entities.Cat;
import com.example.mimimimetr.services.CatServiceImpl;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CatController {
    private CatServiceImpl catService;

    @Autowired
    public void setCatService(CatServiceImpl catService) {
        this.catService = catService;
    }

    @GetMapping
    public String getCats(Model model, @RequestParam(value = "queue", required = false) Integer queue,
                          @RequestParam(value = "chosenCatId", required = false) Long chosenCatId,
                            @RequestParam(value = "order", required = false) List<Byte> order){

        //выбранный в прошлой иттерации кот увеличивает свою популярность
        if(chosenCatId != null) {
            Cat chosenCat = catService.findCatById(chosenCatId);
            chosenCat.setPopularity(chosenCat.getPopularity() + 1);
            catService.saveCat(chosenCat);
        }

        List<Cat> cats = catService.findByQueue();
        if (queue == null) {
            queue = 0;      // это очередность пар котов, каждый раз будет увеличиваться на 2
        }
        if (queue >= cats.size() - 1) {
            return to10Cats(model);  //если все коты показаны, то уходим на страницу финиша
        }

        Cat cat1 = cats.get(queue);
        Cat cat2 = cats.get(queue + 1);
        queue += 2;
        model.addAttribute("cat1", cat1);
        model.addAttribute("cat2", cat2);
        model.addAttribute("queue", queue);
        model.addAttribute("order", order);
        return "mimimimetr";
    }

    @GetMapping("top10cats")
    public String to10Cats(Model model) {
        List<Cat> top10Cats = catService.getTop10();
        List<Byte> queue = new ArrayList<>();
        for (byte i = 0; i < 10; i++) {
            queue.add(i);
        }
        Collections.shuffle(queue);

        for (int i = 0; i < queue.size(); i++) {
            Cat cat = top10Cats.get(i);
            cat.setQueue(queue.get(i));
            catService.saveCat(cat);
        }
        model.addAttribute("top10Cats", top10Cats);
        return "top10cats";
    }
}
