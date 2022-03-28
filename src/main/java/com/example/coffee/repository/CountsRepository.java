package com.example.coffee.repository;

import com.example.coffee.model.Counts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountsRepository extends JpaRepository<Counts,Long> {

}
