package com.example.mimimimetr.repositories;

import com.example.mimimimetr.entities.Cat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@DataJpaTest
public class CatRepositoryTest {

    @Autowired
    CatRepository catRepository;

    @Test
    public void findByOrderByPopularityDesc() {
    }

    @Test
    public void findAllOrderById() {
    }

    @Test
    public void getCountOfCat() {
        //before
        Cat cat1 = new Cat("Куся", "1", 10);
        Cat cat2 = new Cat("Филя", "2", 9);
        catRepository.save(cat1);
        catRepository.save(cat2);
        //when
        int countOfCat = catRepository.getCountOfCat();
        //then
        int expected = 2;
        assertEquals(expected, countOfCat);
    }

    @Test
    public void getById() {
    }
}