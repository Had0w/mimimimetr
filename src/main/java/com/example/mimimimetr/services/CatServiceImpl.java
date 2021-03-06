package com.example.mimimimetr.services;

import com.example.mimimimetr.entities.Cat;
import com.example.mimimimetr.repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CatServiceImpl implements CatService{
    private CatRepository catRepository;

    @Autowired
    public void setCatRepository(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Override
    public List<Cat> getAllCat() {
        return catRepository.findAll();
    }

    @Override
    public Cat findCatById(long id) {
        return catRepository.getOne(id);
    }

    @Override
    public List<Cat> getTop10() {
        List<Cat> cats = catRepository.findByOrderByPopularityDesc();
        if(cats.size() > 10) {
            return cats.subList(0, 10);
        }
        else {
            return cats;
        }
    }

    @Override
    public void saveCat(Cat cat) {
        catRepository.save(cat);
    }

    @Override
    public List<Cat> findAllOrderById() {
        return catRepository.findAllOrderById();
    }

    @Override
    public Cat getCatById(long id) {
        return catRepository.getOne(id);
    }

    @Override
    public int getCatCount() {
        return catRepository.getCountOfCat();
    }
}
