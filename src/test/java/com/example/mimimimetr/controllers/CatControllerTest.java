package com.example.mimimimetr.controllers;

import com.example.mimimimetr.entities.Cat;
import com.example.mimimimetr.entities.User;
import com.example.mimimimetr.services.CatServiceImpl;
import com.example.mimimimetr.services.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatControllerTest {

    @InjectMocks
    private CatController catController;


    private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private CatServiceImpl catService;

    @Mock
    private Model model;

    @Mock
    private Principal principal;

    @Before
    public void setUp() {
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat());
        cats.add(new Cat());
        Mockito.when(catService.findAllOrderById()).thenReturn(cats);
        User user = new User();
        user.setCatOrder("1 2 3 4");
        Mockito.when(userService.findUserByUsername(Mockito.any())).thenReturn(user);
        mockMvc = MockMvcBuilders.standaloneSetup(catController).build();
    }

    @Test
    public void getCats() {
        List<Cat> cats = new ArrayList<>();
        Cat cat1 = new Cat();
        cat1.setId(1);
        cat1.setName("кот1");
        cat1.setPopularity(0);
        Cat cat2 = new Cat();
        cat2.setId(2);
        cat2.setName("кот2");
        cat2.setPopularity(0);
        cats.add(cat1);
        cats.add(cat2);
        Mockito.when(catService.findAllOrderById()).thenReturn(cats);
        User user = new User();
        user.setUsername("user1");
        user.setCatOrder("1 2 3 4 5 6 7 8 9 10");
        user.setQueue(0);
        Mockito.when(principal.getName()).thenReturn("user1");
        Mockito.when(userService.findUserByUsername("user1")).thenReturn(user);
        CatController catController = new CatController();
        catController.setCatService(catService);
        catController.setUserService(userService);
        String actual = catController.getCats(model, principal);
        assertEquals("mimimimetr", actual);
    }

    @Test
    public void getCatsController() throws Exception {
        Mockito.when(catController.getCats(model, principal)).thenReturn("mimimimetr");
        mockMvc.perform(MockMvcRequestBuilders.get("/mimimimetr"))
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andExpect(MockMvcResultMatchers.forwardedUrl("templates\\mimimimetr.html"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("cat1", "cat2"));
        Mockito.verify(catService).findAllOrderById();
    }

    @Test
    public void putCat() {
        Cat cat1 = new Cat();
        cat1.setId(1);
        cat1.setName("кот1");
        Mockito.when(catService.findCatById(1)).thenReturn(cat1);
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat());
        cats.add(new Cat());
        cats.add(new Cat());
        cats.add(new Cat());
        Mockito.when(catService.findAllOrderById()).thenReturn(cats);
        User user = new User();
        user.setUsername("user1");
        user.setCatOrder("1 2 3 4 5 6 7 8 9 10");
        user.setQueue(0);
        Mockito.when(principal.getName()).thenReturn("user1");
        Mockito.when(userService.findUserByUsername("user1")).thenReturn(user);
        CatController catController = new CatController();
        catController.setCatService(catService);
        catController.setUserService(userService);
        String actual = catController.putCat(model, principal, "catInfo=1");
        assertEquals("mimimimetr", actual);
    }
    //Если пользователь посмотрел уже всех котов
    @Test
    public void putCatWhenQueue3() {
        Cat cat1 = new Cat();
        cat1.setId(1);
        cat1.setName("кот1");
        Mockito.when(catService.findCatById(1)).thenReturn(cat1);
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat());
        cats.add(new Cat());
        cats.add(new Cat());
        cats.add(new Cat());
        Mockito.when(catService.findAllOrderById()).thenReturn(cats);
        User user = new User();
        user.setUsername("user1");
        user.setCatOrder("1 2 3 4 5 6 7 8 9 10");
        user.setQueue(3);
        Mockito.when(principal.getName()).thenReturn("user1");
        Mockito.when(userService.findUserByUsername("user1")).thenReturn(user);
        CatController catController = new CatController();
        catController.setCatService(catService);
        catController.setUserService(userService);
        String actual = catController.putCat(model, principal, "catInfo=1");
        assertEquals("top10cats", actual);
    }

    @Test
    public void to10Cats() {
        Mockito.when(catService.getTop10()).thenReturn(new ArrayList<>());
        CatController catController = new CatController();
        catController.setCatService(catService);
        catController.setUserService(userService);
        String actual = catController.getTop10Cats(model, principal);
        assertEquals("top10cats", actual);
    }

    @Test
    public void top10CatsController() {
        Mockito.when(catController.getTop10Cats(model, principal)).thenReturn("/top10Cats");
    }

    @Test
    public void registrationFormGetTest() {
        CatController catController = new CatController();
        String actual = catController.registrationForm(model);
        assertEquals("registration", actual);
    }

    @Test
    public void RegistrationFormPostTest() {
        User user = new User();
        user.setPassword("111");
        CatController catController = new CatController();
        catController.setUserService(userService);
        catController.setCatService(catService);
        String actual = catController.registrationForm(user);
        assertEquals("redirect:/", actual);
    }

    @Test
    public void getOrderCat() {
        Mockito.when(catService.getCatCount()).thenReturn(10);
        CatController catController = new CatController();
        catController.setCatService(catService);
        String[] order = catController.getOrderCat().split(" ");
        assertEquals(10, order.length);
    }
}