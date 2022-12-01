package com.example.paginationandsorting.repository;

import com.example.paginationandsorting.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
