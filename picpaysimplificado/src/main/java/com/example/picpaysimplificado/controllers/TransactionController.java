package com.example.picpaysimplificado.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.picpaysimplificado.entities.Transaction;
import com.example.picpaysimplificado.services.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	private TransactionService service;
	
	@GetMapping
	public ResponseEntity<List<Transaction>> getAll(){
		List<Transaction> trans = service.getAll();
		return ResponseEntity.ok(trans);
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<Transaction>> getByUser(@PathVariable Long id) throws Exception {

		List<Transaction> transactions = service.getAllTransactionsByIdUser(id);

		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody @Valid Transaction transaction) throws Exception{
		Transaction newTrans = service.createTransaction(transaction);

		return new ResponseEntity<>(newTrans, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Transaction> update(@PathVariable Long id,@RequestBody Transaction transaction) throws Exception {

		Transaction newTransaction = service.update(id, transaction);

		return new ResponseEntity<>(newTransaction, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Transaction> delete(@PathVariable Long id) throws Exception {

		Transaction transaction = service.delete(id);

		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}
}