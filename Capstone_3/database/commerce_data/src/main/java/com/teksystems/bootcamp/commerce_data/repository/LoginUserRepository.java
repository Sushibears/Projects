package com.teksystems.bootcamp.commerce_data.repository;

import java.util.Optional;

import com.teksystems.bootcamp.commerce_data.models.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Integer> {
    public Optional<LoginUser> findByUsername(String user_username);
}
