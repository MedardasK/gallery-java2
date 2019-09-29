package com.service;


import com.entity.User;

import java.util.List;

public interface IUserService {

    String delete(Long id);

    User findById(Long id);

    User save(User user);

    List<User> findAll();

}
