package com.teksystems.bootcamp.commerce_data.repository.DBRepos;

import com.teksystems.bootcamp.commerce_data.models.DBEntities.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, Integer> {
}