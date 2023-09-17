package com.inmar.app.service;

import com.inmar.app.dto.request.UserRequest;
import com.inmar.app.dto.response.UserResponse;
import com.inmar.app.jpa.model.User;
import com.inmar.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class MyUserDetailsService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * This method used for load the user details
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       logger.info("entered into loadUserByUsername() ");
        User user = userRepository.findByUserName(userName);
        if (Objects.isNull(user)) {
            logger.error("username not found  for the given input");
            throw new UsernameNotFoundException("username Not found" + userName);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    /**
     * this method us used for signup
     * @param request
     * @return
     */
    public ResponseEntity signup(UserRequest request) {
        if (userRepository.findByUserName(request.getUsername()) != null) {
            logger.info("user already signin with their credentials");
            return new ResponseEntity(new UserResponse("User already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setId(request.getId());
        userRepository.save(user);
        logger.info("new user data  saved successfully");
        return ResponseEntity.ok(new UserResponse("User registered successfully"));
    }
}