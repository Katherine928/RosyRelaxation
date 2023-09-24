package com.katherine.rosyrelaxationbackend.admin.user;

import com.katherine.rosyrelaxationcommon.entity.Role;
import com.katherine.rosyrelaxationcommon.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;
    @Test
    void testCreateNewUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class,1);
        User userKatherine = new User("katherine@gmail.com", "123456", "Katherine", "Brooks");
        userKatherine.addRole(roleAdmin);
        User savedUser = repo.save(userKatherine);
        assertThat(savedUser.getId()).isPositive();
    }

    @Test
    void testCreateNewUserWithTwoRole() {
        User userAlex = new User("Alex@gmail.com", "123456", "Alex", "Brooks");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);
        userAlex.addRole(roleEditor);
        userAlex.addRole(roleAssistant);

        User savedUser = repo.save(userAlex);
        assertThat(savedUser.getId()).isPositive();
    }
    @Test
    void testListAllUsers() {
       Iterable<User> listUsers =  repo.findAll();
       listUsers.forEach(System.out::println);
    }

    @Test
    void testGetUserById() {
        User userKatherine = repo.findById(1).get();
        System.out.println(userKatherine);
        assertThat(userKatherine).isNotNull();
    }

    @Test
    void testupdateUserDetails() {
        User userKatherine = repo.findById(1).get();
        userKatherine.setEnabled(true);
        repo.save(userKatherine);
    }

    @Test
    void testUpdateUserRoles() {
        User userAlex = repo.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesperson = new Role(2);
        userAlex.getRolses().remove(roleEditor);
        userAlex.addRole(roleSalesperson);
        repo.save(userAlex);
    }

    @Test
    void testDeleteUser() {
        Integer userId = 2;
        repo.deleteById(userId);
    }
}
