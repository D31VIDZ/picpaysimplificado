package com.example.picpaysimplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.picpaysimplificado.entities.Transaction;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    List<Transaction> findBySenderId(Long Id);

    List<Transaction> findByReceiverId(Long Id);
}
