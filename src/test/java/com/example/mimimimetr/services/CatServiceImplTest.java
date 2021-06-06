package com.example.mimimimetr.services;

import com.example.mimimimetr.entities.Cat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatServiceImplTest {

    private CatServiceImpl catService;

    @Autowired
    public void setCatRepository(CatServiceImpl catService) {
        this.catService = catService;
    }

    @Test
    public void findCatById() {
        Cat cat = catService.findCatById(1);
        assertEquals(1, cat.getId());
    }

    @Test
    public void getTop10() {
        List<Cat> top10 = catService.getTop10();
        assertEquals(10, top10.size());
    }

    @Test
    public void saveCat() {
    }

    @Test
    public void findAllOrderById() {
        List<Cat> cats = catService.findAllOrderById();
        assertNotNull(cats);
        assertNotEquals(0, cats.size());
        assertEquals(0, cats.size()%2);
    }

    /**
     * По логике программы котов должно всегда быть четное колличество
     */
    @Test
    public void getCatCount() {
        int count = catService.getCatCount();
        int actual = count % 2;
        int expexted = 0;
        assertEquals(expexted, actual);
        assertNotEquals(0, count);
    }
}