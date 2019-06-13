package com.mesi.histoireHeros.repository;

import com.mesi.histoireHeros.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByLogin(String login);
}
