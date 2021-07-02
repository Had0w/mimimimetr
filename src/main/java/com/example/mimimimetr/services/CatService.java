package com.example.mimimimetr.services;

import com.example.mimimimetr.entities.Cat;
import java.util.List;

public interface CatService {

    List<Cat> getAllCat();

    Cat findCatById(long id);

    List<Cat> getTop10();

    void saveCat(Cat cat);

    List<Cat> findAllOrderById();

    Cat getCatById(long id);

    int getCatCount();
}
