package com.example.mimimimetr.repositories;

import com.example.mimimimetr.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "update User user set user.queue = :queue where user.username = :username")
    void setQueue(@Param("username") String username, @Param("queue") int queue);

    @Transactional
    @Modifying
    @Query(value = "update User user set user.catOrder = :catOrder where user.username = :username")
    void setOrder(@Param("username") String username, @Param("catOrder") String catOrder);
}
