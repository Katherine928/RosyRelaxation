package com.katherine.rosyrelaxationbackend.admin.user;

import com.katherine.rosyrelaxationcommon.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class RoleRepositoryTest {

    @Autowired
    private RoleRepository repo;

    @Test
    void testCreateFirstRole() {
        Role roleAdmin = new Role("admin","manager everything");
        Role savedRole = repo.save(roleAdmin);
        assertThat(savedRole.getId()).isPositive();
    }
}