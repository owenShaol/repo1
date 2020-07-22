package com.owen.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.owen.dao.PermissionDao;
import com.owen.dao.RoleDao;
import com.owen.dao.UserDao;
import com.owen.pojo.Permission;
import com.owen.pojo.Role;
import com.owen.pojo.User;
import com.owen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    PermissionDao permissionDao;
    @Override
    public User findByUsername(String name) {
        User user=userDao.findByUsername(name);
        if (user==null){
            return null;
        }
        Set<Role> roles=roleDao.findRolesByUid(user.getId());
        for (Role role : roles) {
            Set<Permission> permissions=permissionDao.findPermissionByRid(role.getId());
            role.setPermissions(permissions);
        }
        user.setRoles(roles);

        return user;
    }
}
