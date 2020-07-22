package com.owen.dao;

import com.owen.pojo.User;

public interface UserDao {
    User findByUsername(String name);
}
