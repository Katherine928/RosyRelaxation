package com.katherine.rosyrelaxationbackend.admin.user;

import com.katherine.rosyrelaxationcommon.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
