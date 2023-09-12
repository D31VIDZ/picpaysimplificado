package com.example.picpaysimplificado.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.picpaysimplificado.entities.Transaction;
import com.example.picpaysimplificado.entities.User;
import com.example.picpaysimplificado.entities.UserType;
import com.example.picpaysimplificado.repositories.TransactionRepository;
import com.example.picpaysimplificado.repositories.UserRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository repository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RestTemplate template;
	
	public List<Transaction> getAll(){
		List<Transaction> trans = repository.findAll();
		return trans;
	}
	
	public Transaction createTransaction(Transaction trans) throws Exception {
		
		Optional<User> sender = userRepository.findById(trans.getSenderId());
		
		Optional<User> receiver = userRepository.findById(trans.getReceiverId());;
		
		if(sender.isEmpty()) {
			throw new Exception("sender não encontrado id invalido");
		}else if(receiver.isEmpty()){
			throw new Exception("receiver nao encontrado id invalido");
		}
		
		boolean valid = validTransaction(sender, trans.getAmount());
		
		if(valid == true) {
			User senderUSer = sender.get();
			BigDecimal BalanceSender = senderUSer.getBalance();	
			BigDecimal newBalanceSender = BalanceSender.subtract(trans.getAmount());
			senderUSer.setBalance(newBalanceSender);
			
			User receiverUSer = receiver.get();
			BigDecimal BalanceReceiver = receiverUSer.getBalance();	
			BigDecimal newBalanceRe = BalanceReceiver.add(trans.getAmount());
			receiverUSer.setBalance(newBalanceRe);
		
		trans.setDateTime(LocalDateTime.now());
		userRepository.save(senderUSer);
		userRepository.save(receiverUSer);
		repository.save(trans);
		
		}
		return trans;
	}
	
	public boolean validTransaction(Optional<User> userOp, BigDecimal amount) throws Exception {
		if(userOp.isPresent()) {
			User user = userOp.get();
			
			if(user.getUserType() == UserType.MERCHANT) {	
				throw new Exception("Usuario do tipo merchant não autorizado a realizar transações");
			}
			if(user.getBalance().compareTo(amount) < 0) {	
				throw new Exception("Saldo insulficiente");
			}
			
			ResponseEntity<Map> authorization = template.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
			
			if(authorization.getStatusCode() == HttpStatus.OK && authorization.getBody().get("messege") == "Autorizado") {
				
				return true;
			}
			}
			
		return false;
	}
		
}