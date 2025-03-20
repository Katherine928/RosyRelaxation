package com.katherine.rosyrelaxationbackend.admin.service.impl;

import com.katherine.rosyrelaxationbackend.admin.service.UserService;
import com.katherine.rosyrelaxationbackend.admin.user.RoleRepository;
import com.katherine.rosyrelaxationbackend.admin.exception.UserNotFoundException;
import com.katherine.rosyrelaxationbackend.admin.user.UserRepository;
import com.katherine.rosyrelaxationcommon.entity.Role;
import com.katherine.rosyrelaxationcommon.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
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
        boolean isUpdatingUser = (user.getId() != null);
        if(isUpdatingUser) {
            Optional<User> optionalExistingUser = userRepo.findById(user.getId());
            if(optionalExistingUser.isPresent()) {
                User existingUser = optionalExistingUser.get();
                if(existingUser.getPassword().isEmpty()) {
                    user.setPassword(existingUser.getPassword());
                } else {
                    encodePassword(user);
                }
            }
        }else {
            encodePassword(user);
        }
        userRepo.save(user);
    }

    @Override
    public boolean isEmailUnique(Integer id, String email) {
        User userByEmail = userRepo.getUserByEmail(email);
        if(userByEmail == null) return true;
        boolean isCreatingNew = (id == null);
        if(isCreatingNew) {
            return false;
        } else {
            return Objects.equals(userByEmail.getId(), id);
        }
    }

    @Override
    public User get(Integer id) throws UserNotFoundException {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Could not find any user with id " + id));
    }

    @Override
    public void delete(Integer id) throws UserNotFoundException {
        Long countById = userRepo.countById(id);
        if(countById == null || countById == 0) {
            throw new UserNotFoundException("Could not find any user with ID " + id);
        }
        userRepo.deleteById(id);
    }

    @Override
    public void updateUserEnabledStatus(Integer id, boolean enabled) {
        userRepo.updateEnabledStatus(id, enabled);
    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}
