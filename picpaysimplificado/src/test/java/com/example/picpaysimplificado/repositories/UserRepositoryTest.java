package com.example.picpaysimplificado.repositories;

import com.example.picpaysimplificado.dots.UserDTO;
import com.example.picpaysimplificado.entities.User;
import com.example.picpaysimplificado.entities.UserType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Autowired
    EntityManager entityManager;


    @Test
    @DisplayName("deve buscar um user pelo documento com sucesso")
    void findUserByDocumentsucess() {
        String document = "12345687";
        UserDTO userDTO = new UserDTO("first","test", "firsttest@gmail", document, "45123",new BigDecimal(50), UserType.COMMON);
        this.createUser(userDTO);

        Optional<User> optionalUser = this.repository.findUserByDocument(document);

        assertThat(optionalUser.isPresent());
    }

    @Test
    @DisplayName("deve buscar um user pelo documento com erro")
    void findUserByDocumentfailure() {
        String document = "12345659";
        Optional<User> optionalUser = this.repository.findUserByDocument(document);

        assertThat(optionalUser.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("deve buscar um user pelo id com sucesso")
    void findUserByIdsucess() {
        Long id = 1L;
        UserDTO userDTO = new UserDTO("first","test", "firsttest@gmail", "12345678", "45123",new BigDecimal(50), UserType.COMMON);
        this.createUser(userDTO);
        Optional<User> optionalUser = this.repository.findById(id);

        assertThat(optionalUser.isPresent());
    }

    @Test
    @DisplayName("deve buscar um user pelo id com erro")
    void findUserByIdfailure() {
        Long id = 8L;
        Optional<User> optionalUser = this.repository.findById(id);

        assertThat(optionalUser.isEmpty()).isTrue();
    }




    private User createUser(UserDTO userDTO){
        User newUser = new User(userDTO);
        this.entityManager.persist(newUser);
        return newUser;
    }
}