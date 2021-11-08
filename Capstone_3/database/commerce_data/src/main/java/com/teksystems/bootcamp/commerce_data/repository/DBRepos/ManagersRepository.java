package com.teksystems.bootcamp.commerce_data.repository.DBRepos;

import com.teksystems.bootcamp.commerce_data.models.DBEntities.Managers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagersRepository extends JpaRepository<Managers, Integer> {
}