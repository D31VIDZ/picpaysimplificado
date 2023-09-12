package com.example.picpaysimplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.picpaysimplificado.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
