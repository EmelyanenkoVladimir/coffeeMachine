package com.example.coffee.repository;

import com.example.coffee.model.Ordermenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdermenuRepository extends JpaRepository<Ordermenu,Long> {
}
