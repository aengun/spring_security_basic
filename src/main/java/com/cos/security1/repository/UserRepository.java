package com.cos.security1.repository;

import com.cos.security1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUE함수를 JpaRepository가 들고있음
// @Repository라는 어노테이션이 없어도 Ioc됨. 이유는 JpaRepository를 상송했기 때문에
public interface UserRepository extends JpaRepository<User, Integer> {
    // findBy규칙 -> Username문법
    // select * from user where username = ?
    public User findByUsername(String username); //Jpa query method 검색해볼 것
}
