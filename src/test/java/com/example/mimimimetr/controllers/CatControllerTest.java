package com.example.mimimimetr.controllers;

import com.example.mimimimetr.entities.User;
import com.example.mimimimetr.services.CatServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CatControllerTest {

    @MockBean
    CatServiceImpl catService;

    @MockBean
    Principal principal;

    @MockBean
    Model model;

    @Before
    public void init() {
        Mockito.when(catService.getCatCount()).thenReturn(10);
    }

    @Test
    void getCats() {
        String actual = new CatController().getCats(model, principal);
        String expected = "mimimimettr";
        assertEquals(expected, actual);
    }

    @Test
    void putCat() {
    }

    @Test
    void to10Cats() {
        String actual = new CatController().to10Cats(model, principal);
        String expected = "top10cats";
        assertEquals(expected, actual);
    }

    @Test
    void registrationFormGet() {
        String actual = new CatController().registrationForm(new User());
        String expected = "registration";
        assertEquals(expected, actual);
    }

    @Test
    void RegistrationFormPost() {
        String actual = new CatController().registrationForm(new User());
        String expected = "redirect:/";
        assertEquals(expected, actual);
    }

    @Test
    void getOrderCat() {
        String order = new CatController().getOrderCat();
        String[] catId = order.split(" ");
        assertEquals(10, catId.length);
    }
}