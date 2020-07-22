package com.owen.dao;

import com.owen.pojo.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> findRolesByUid(Integer userId);
}
