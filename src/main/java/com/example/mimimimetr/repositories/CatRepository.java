package com.example.mimimimetr.repositories;

import com.example.mimimimetr.entities.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

    @Query(value = "SELECT cat FROM Cat cat ORDER BY cat.popularity DESC")
    List<Cat> findByOrderByPopularityDesc();

    @Query("SELECT cat FROM Cat cat ORDER BY cat.queue")
    List<Cat> findByOrderByDirection();
}
