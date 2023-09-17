package com.inmar.app.repository;

import com.inmar.app.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select user from User user WHERE user.username = :username")
    User findByUserName(@Param("username") String username);
}
