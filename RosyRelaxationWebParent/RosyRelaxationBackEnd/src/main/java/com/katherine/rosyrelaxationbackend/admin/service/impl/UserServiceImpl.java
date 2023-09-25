package com.katherine.rosyrelaxationbackend.admin.service.impl;

import com.katherine.rosyrelaxationbackend.admin.service.UserService;
import com.katherine.rosyrelaxationbackend.admin.user.RoleRepository;
import com.katherine.rosyrelaxationbackend.admin.user.UserRepository;
import com.katherine.rosyrelaxationcommon.entity.Role;
import com.katherine.rosyrelaxationcommon.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    Sort sort = Sort.by(Sort.Order.asc("id"));

    @Override
    public List<User> listAll() {
        return (List<User>) userRepo.findAll(sort);
    }

    @Override
    public List<Role> listRoles() {
        return (List<Role>) roleRepo.findAll(sort);
    }

    @Override
    public void save(User user) {
        encodePassword(user);
        userRepo.save(user);
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}
