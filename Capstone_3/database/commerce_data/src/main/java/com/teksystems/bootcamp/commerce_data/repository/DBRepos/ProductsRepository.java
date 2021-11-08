package com.teksystems.bootcamp.commerce_data.repository.DBRepos;

import com.teksystems.bootcamp.commerce_data.models.DBEntities.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
}