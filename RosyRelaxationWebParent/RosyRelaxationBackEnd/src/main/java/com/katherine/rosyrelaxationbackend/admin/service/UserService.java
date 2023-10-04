package com.katherine.rosyrelaxationbackend.admin.service;

import com.katherine.rosyrelaxationcommon.entity.Role;
import com.katherine.rosyrelaxationcommon.entity.User;

import java.util.List;

public interface UserService {
    List<User> listAll();
    List<Role> listRoles();
    void save(User user);
    boolean isEmailUnique(String email);
}
