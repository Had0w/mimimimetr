package com.example.mimimimetr.repositories;

import com.example.mimimimetr.entities.Cat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CatRepositoryTest {

    @Autowired
    private CatRepository catRepository;

    @Before
    public void init() {
        Cat cat1 = new Cat("Куся", "1", 10);
        Cat cat2 = new Cat("Филя", "2", 9);
        Cat cat3 = new Cat("Тосик","3", 8);
        catRepository.save(cat1);
        catRepository.save(cat2);
        catRepository.save(cat3);
    }

    @Test
    public void getCatById() {
        Cat cat = catRepository.getOne(1L);
        assertEquals(1L, cat.getId());
    }

    @Test
    public void findByOrderByPopularityDesc() {
        List<Cat> catList = catRepository.findByOrderByPopularityDesc();
        int popularity1 = catList.get(1).getPopularity();
        int popularity2 = catList.get(2).getPopularity();
        assertTrue(popularity1 >= popularity2);
        assertTrue(catList.size() <= 10);
    }

    @Test
    public void findAllOrderById() {
        List<Cat> catList = catRepository.findAllOrderById();
        long id1 = catList.get(1).getId();
        long id2 = catList.get(2).getId();
        assertTrue(id2 > id1);
        catList.forEach(System.out::println);
    }

    @Test
    public void getCountOfCat() {
        int countOfCat = catRepository.getCountOfCat();
        int expected = 3;
        assertEquals(expected, countOfCat);
        catRepository.deleteAll();
        System.out.println(catRepository.findById(1L).isPresent());
    }
}