package com.example.SimpleLoginApplication.LoginApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SimpleLoginApplication.LoginApplication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
    public User findByEmailAndPassword(String email, String password);

    public User findByToken(String token);
    
  //  public User findByUser(User user);
}
