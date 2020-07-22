package com.owen.service;

import com.owen.pojo.User;

public interface UserService {
    public User findByUsername(String name);
}
