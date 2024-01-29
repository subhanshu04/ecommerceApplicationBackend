package com.example.loginwithmanuallycodedtoken.repositories;

import com.example.loginwithmanuallycodedtoken.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepo extends JpaRepository<Session,Long> {
    Optional<Session> findByToken(String token);
    long deleteByToken(String token);

    Optional<Session> findByUser_Id(Long id);

}
