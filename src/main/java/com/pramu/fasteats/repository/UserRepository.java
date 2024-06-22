package com.pramu.fasteats.repository;

import com.pramu.fasteats.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    public User findByEmail(String username);
}
