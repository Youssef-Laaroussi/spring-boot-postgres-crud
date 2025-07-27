package com.youssef.bibliotheque.dao;

import com.youssef.bibliotheque.bean.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClientRepositroy extends JpaRepository<Client, Integer> {

    Optional<Client> findByEmail(String email);




}
