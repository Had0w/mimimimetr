package com.example.mimimimetr.services;

import com.example.mimimimetr.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findUserByUsername(String username);

    void addUser(User user);

    void setQueue(String userName, int queue);

    void setOrder(String userName, String order);
}
