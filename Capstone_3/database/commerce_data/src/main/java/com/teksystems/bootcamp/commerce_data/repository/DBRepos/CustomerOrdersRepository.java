package com.teksystems.bootcamp.commerce_data.repository.DBRepos;

import com.teksystems.bootcamp.commerce_data.models.DBEntities.CustomerOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrdersRepository extends JpaRepository<CustomerOrders, Integer> {
}