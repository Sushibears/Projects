package com.teksystems.bootcamp.commerce_data.repository.DBRepos;

import com.teksystems.bootcamp.commerce_data.models.DBEntities.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminsRepository extends JpaRepository<Admins, Integer> {
}