package com.example.OlympicDatabase.repo;

import com.example.OlympicDatabase.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    String deleteUserByUsername(String userName);

    boolean existsByUsername(String admin);
}