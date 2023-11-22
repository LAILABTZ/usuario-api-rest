package com.acme.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.acme.usuario.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
}