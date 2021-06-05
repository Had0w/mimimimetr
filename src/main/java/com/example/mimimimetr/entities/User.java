package com.example.mimimimetr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name ="password")
    private String password;

    @Column(name = "catorder")
    private String catOrder;

    @Column(name = "queue")
    private int queue;

    @Transient
    private String role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "ROLE_USER";
    }

    public User() {
        this.role = "ROLE_USER";
    }
}
