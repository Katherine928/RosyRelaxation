package com.katherine.rosyrelaxationbackend.admin.service;


import com.katherine.rosyrelaxationbackend.admin.exception.UserNotFoundException;
import com.katherine.rosyrelaxationcommon.entity.Role;
import com.katherine.rosyrelaxationcommon.entity.User;

import java.util.List;

public interface UserService {
    List<User> listAll();
    List<Role> listRoles();
    void save(User user);
    boolean isEmailUnique(Integer id, String email);
    User get(Integer id) throws UserNotFoundException;
    void delete(Integer id) throws UserNotFoundException;
}
