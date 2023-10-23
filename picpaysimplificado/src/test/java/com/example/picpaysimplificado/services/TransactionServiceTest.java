package com.example.picpaysimplificado.services;

import com.example.picpaysimplificado.entities.Transaction;
import com.example.picpaysimplificado.entities.User;
import com.example.picpaysimplificado.entities.UserType;
import com.example.picpaysimplificado.repositories.TransactionRepository;
import com.example.picpaysimplificado.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository repository;

    @Mock
    private UserRepository userRepository;

    @Autowired
    @InjectMocks
    TransactionService service;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("retorna todas transações do usuario com sucesso")
    void getAllTransactionsByIdUsersucess() {
    }

    @Test
    @DisplayName("retorna todas transações do usuario com falha")
    void getAllTransactionsByIdUserfailure() {
    }

    @Test
    @DisplayName("Cria transaçõees com sucesso")
    void createTransactionsucess() throws Exception {
        User sender = new User
                (1L, "paulo", "Augusto",
                        "12345678", "pauloAgu@gmail",
                        "123456", new BigDecimal(50),
                        UserType.COMMON);
        User receiver = new User
                (2L, "maria", "silva",
                        "87654321", "mariasilva@gmail",
                        "654321", new BigDecimal(50),
                        UserType.COMMON);

        when(userRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(userRepository.findById(2L)).thenReturn(Optional.of(receiver));

        Transaction transaction = new Transaction(1L,
                1L, 2L, new BigDecimal(10), LocalDateTime.now());
        service.createTransaction(transaction);

        verify(repository, times(2)).save(transaction);


        sender.setBalance(new BigDecimal(40));
        verify(userRepository, times(1)).save(sender);


        receiver.setBalance(new BigDecimal(60));
        verify(userRepository, times(1)).save(receiver);
    }

    @Test
    @DisplayName("Cria transaçõees com falha usuario nao autorizado")
    void createTransactionfailureValidFalse() throws Exception {
        User sender = new User
                (1L, "paulo", "Augusto",
                        "12345678", "pauloAgu@gmail",
                        "123456", new BigDecimal(50),
                        UserType.MERCHANT);
        User receiver = new User
                (2L, "maria", "silva",
                        "87654321", "mariasilva@gmail",
                        "654321", new BigDecimal(50),
                        UserType.COMMON);

        when(userRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(userRepository.findById(2L)).thenReturn(Optional.of(receiver));

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            Transaction transaction = new Transaction(1L,
                    1L, 2L, new BigDecimal(10), LocalDateTime.now());
            service.createTransaction(transaction);
        });

        Assertions.assertEquals("Usuario do tipo merchant não autorizado a realizar transações", thrown.getMessage());
    }

    @Test
    @DisplayName("Cria transaçõees com falha saldo insulficiente")
    void createTransactionfailureBalance() throws Exception {

        User sender = new User
                (1L, "paulo", "Augusto",
                        "12345678", "pauloAgu@gmail",
                        "123456", new BigDecimal(50),
                        UserType.COMMON);
        User receiver = new User
                (2L, "maria", "silva",
                        "87654321", "mariasilva@gmail",
                        "654321", new BigDecimal(50),
                        UserType.COMMON);

        when(userRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(userRepository.findById(2L)).thenReturn(Optional.of(receiver));


        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            Transaction transaction = new Transaction(1L,
                    1L, 2L, new BigDecimal(99), LocalDateTime.now());
            service.createTransaction(transaction);
        });

        Assertions.assertEquals("Saldo insulficiente", thrown.getMessage());

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}