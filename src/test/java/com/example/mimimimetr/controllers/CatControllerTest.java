package com.example.mimimimetr.controllers;

import com.example.mimimimetr.entities.Cat;
import com.example.mimimimetr.entities.User;
import com.example.mimimimetr.services.CatServiceImpl;
import com.example.mimimimetr.services.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CatControllerTest {

    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper;
    @MockBean
    UserServiceImpl userService;
    @MockBean
    CatServiceImpl catService;

    @BeforeEach
    public void setUp() {
        CatController catController = new CatController();
        catController.setCatService(catService);
        catController.setUserService(userService);
        List<Cat> cats = new ArrayList<>();
        when(catService.findAllOrderById()).thenReturn(cats);
        when(catService.getCatById(any())).thenReturn(new Cat());
        when(userService.findUserByUsername(any())).thenReturn(new User());
        mockMvc = MockMvcBuilders.standaloneSetup(catController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getCats() throws Exception {
        mockMvc.perform((RequestBuilder) get("/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getCatsController() throws Exception {

    }

    @Test
    public void putCat() {

    }
    //Если пользователь посмотрел уже всех котов
    @Test
    public void putCatWhenQueue3() {

    }

    @Test
    public void to10Cats() {

    }

    @Test
    public void top10CatsController() {

    }

    @Test
    public void registrationFormGetTest() {

    }

    @Test
    public void RegistrationFormPostTest() {

    }

    @Test
    public void getOrderCat() {

    }
}