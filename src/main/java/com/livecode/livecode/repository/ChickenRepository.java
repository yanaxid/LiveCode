package com.livecode.livecode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livecode.livecode.models.Chicken;



@Repository
public interface ChickenRepository extends JpaRepository<Chicken, Long> {
}
