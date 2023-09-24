package com.katherine.rosyrelaxationbackend.admin.user;

import com.katherine.rosyrelaxationcommon.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

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

    @Test
    void testCreateRestRoles() {
        Role roleSalesperson = new Role("Salesperson", "manage product price, "
                + "customers, shipping, orders and sales report");
        Role roleEditor = new Role("Editor", "manage categories, brands, products, articles and menus");
        Role roleShipper = new Role("Shipper", "view products, view orders and update order status");
        Role roleAssistant = new Role("Assistant", "manage questions and reviews");
        repo.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));
    }

}