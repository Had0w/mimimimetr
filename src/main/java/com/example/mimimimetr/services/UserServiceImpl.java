package com.example.mimimimetr.services;

import com.example.mimimimetr.entities.User;
import com.example.mimimimetr.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findUserByUsername(s);
        if(user == null) {
            System.out.println("Пользователь не найден!");
            return null;
        }
        List<String> roles = new ArrayList<>();
        roles.add(user.getRole());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthority(roles));
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void setQueue(String userName, int queue) {
        userRepository.setQueue(userName, queue);
    }

    @Override
    public void setOrder(String userName, String order) {
//        userRepository.setOrder(userName, order);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthority(Collection<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
