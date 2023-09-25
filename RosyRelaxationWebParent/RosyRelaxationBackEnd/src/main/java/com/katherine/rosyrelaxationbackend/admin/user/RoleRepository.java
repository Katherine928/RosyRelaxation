package com.katherine.rosyrelaxationbackend.admin.user;

import com.katherine.rosyrelaxationcommon.entity.Role;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Iterable<Role> findAll(Sort sort);
}
