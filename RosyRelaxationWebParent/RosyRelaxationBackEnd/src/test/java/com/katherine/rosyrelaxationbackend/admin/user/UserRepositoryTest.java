package com.katherine.rosyrelaxationbackend.admin.user;

import com.katherine.rosyrelaxationcommon.entity.Role;
import com.katherine.rosyrelaxationcommon.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

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
}
