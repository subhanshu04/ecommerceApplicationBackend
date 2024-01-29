package com.example.loginwithmanuallycodedtoken.repositories;

import com.example.loginwithmanuallycodedtoken.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

}
