package com.security.jwt.service;


import com.entity.User;

import java.util.List;


public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();

}
