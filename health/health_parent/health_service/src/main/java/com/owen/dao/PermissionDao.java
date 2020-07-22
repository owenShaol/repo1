package com.owen.dao;

import com.owen.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    Set<Permission> findPermissionByRid(Integer roleId);
}
