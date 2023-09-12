package com.example.picpaysimplificado.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.picpaysimplificado.entities.Transaction;
import com.example.picpaysimplificado.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransaciotnController {

	@Autowired
	TransactionService service;
	
	@GetMapping
	public ResponseEntity<List<Transaction>> getAll(){
		List<Transaction> trans = service.getAll();
		return ResponseEntity.ok(trans);
	}
	
	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody @Valid Transaction trans) throws Exception{
		Transaction newTrans = service.createTransaction(trans);
		
		return new ResponseEntity<>(newTrans, HttpStatus.CREATED);
	}
}
