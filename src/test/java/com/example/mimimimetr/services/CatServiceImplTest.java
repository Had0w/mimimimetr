package com.example.mimimimetr.services;

import com.example.mimimimetr.entities.Cat;
import com.example.mimimimetr.repositories.CatRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CatServiceImplTest {

    @Mock
    private CatRepository catRepository;
    private CatServiceImpl underTest;

    @BeforeEach
    public void setUp() {
        underTest = new CatServiceImpl();
        underTest.setCatRepository(catRepository);
    }

    @Test
    void getAllCat() {
        //when
        underTest.getAllCat();
        //then
        verify(catRepository).findAll();
    }

    @Test
    void findCatById() {
        //given
        long id = 1L;
        //when
        underTest.findCatById(id);
        //then
        ArgumentCaptor<Long> idArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(catRepository).getOne(idArgumentCaptor.capture());

        long actualId = idArgumentCaptor.getValue();

        assertThat(id).isEqualTo(actualId);
    }

    @Test
    void getTop10() {
        //when
        underTest.getTop10();
        //then
        verify(catRepository).findByOrderByPopularityDesc();
    }

    @Test
    void saveCat() {
        //given
        Cat cat = new Cat("Филя", "1", 10);
        //when
        underTest.saveCat(cat);
        //then
        ArgumentCaptor<Cat> catArgumentCaptor = ArgumentCaptor.forClass(Cat.class);
        verify(catRepository).save(catArgumentCaptor.capture());

        Cat capturedCat = catArgumentCaptor.getValue();

        assertThat(capturedCat).isEqualTo(cat);
     }

    @Test
    void findAllOrderById() {
        //when
        underTest.findAllOrderById();
        //then
        verify(catRepository).findAllOrderById();
    }

    @Test
    void getCatById() {
        //when
        underTest.getCatById(1);
        //then
        verify(catRepository).getOne(1L);
    }

    @Test
    void getCatCount() {
        //when
        underTest.getCatCount();
        //then
        verify(catRepository).getCountOfCat();
    }
}