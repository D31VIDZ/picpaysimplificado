package com.example.picpaysimplificado.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
	private TransactionRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate template;
	
	public List<Transaction> getAll(){
		List<Transaction> transactions = repository.findAll();
		return transactions;
	}

	public List<Transaction> getAllTransactionsByIdUser(Long id) throws Exception {
		Optional<User> user = userRepository.findById(id);

		if(user.isEmpty()) {
			throw new Exception("usuario não encontrado! Id invalido");
		}

		List<Transaction> transactionsse = repository.findBySenderId(id);

		List<Transaction> transactionsre = repository.findByReceiverId(id);

		if(transactionsre.isEmpty() && transactionsse.isEmpty()) {
			throw new Exception("Usuario não tem transações.");
		}

		List<Transaction> transactions = new ArrayList<>();
				transactions.addAll(transactionsse);
				transactions.addAll(transactionsre);

		return transactions;
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
			User receiverUSer = receiver.get();

			this.createtransactionUser(senderUSer, receiverUSer, trans);

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
			
			//ResponseEntity<Map> authorization = template.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
			//if(authorization.getStatusCode() == HttpStatus.OK && authorization.getBody().get("messege") == "Autorizado") {

				return true;

			}
			
		return false;
	}


	public Transaction update(Long id, Transaction transaction) throws Exception {
		Optional<Transaction> optionalTransaction = repository.findById(id);

		if(optionalTransaction.isEmpty()){
			throw new Exception("transação não encontrada");
		}

		Transaction oldTransaction = optionalTransaction.get();

		Optional<User> senderUSer = userRepository.findById(oldTransaction.getSenderId());
		User sender = senderUSer.get();

		Optional<User> receiverUSer = userRepository.findById(oldTransaction.getReceiverId());
		User receiver = receiverUSer.get();

		this.deletetransactionUser(sender, receiver, oldTransaction);

		this.createtransactionUser(sender, receiver, transaction);

		return transaction;
	}
	public Transaction delete(Long id) throws Exception {
		Optional<Transaction> optionalTransaction = repository.findById(id);

		if(optionalTransaction.isEmpty()){
			throw new Exception("transação não encontrada");
		}

		Transaction transaction = optionalTransaction.get();

		BigDecimal amount = transaction.getAmount();

		Optional<User> senderUSer = userRepository.findById(transaction.getSenderId());
		User sender = senderUSer.get();

		Optional<User> receiverUSer = userRepository.findById(transaction.getReceiverId());
		User receiver = receiverUSer.get();

		this.deletetransactionUser(sender, receiver, transaction);

		repository.deleteById(id);

		return transaction;
	}

	private Transaction createtransactionUser(User sender, User receiver, Transaction transaction){

		BigDecimal BalanceSender = sender.getBalance();
		BigDecimal newBalanceSender = BalanceSender.subtract(transaction.getAmount());
		sender.setBalance(newBalanceSender);

		BigDecimal BalanceReceiver = receiver.getBalance();
		BigDecimal newBalanceRe = BalanceReceiver.add(transaction.getAmount());
		receiver.setBalance(newBalanceRe);

		transaction.setDateTime(LocalDateTime.now());
		userRepository.save(sender);
		userRepository.save(receiver);
		repository.save(transaction);

		return transaction;
	}

	private Transaction deletetransactionUser(User sender, User receiver, Transaction transaction){
		BigDecimal balanceSender = sender.getBalance();
		BigDecimal newBalanceSender = balanceSender.add(transaction.getAmount());
		sender.setBalance(newBalanceSender);

		BigDecimal balanceReceiver = receiver.getBalance();
		BigDecimal newBalanceRe = balanceReceiver.subtract(transaction.getAmount());
		receiver.setBalance(newBalanceRe);

		userRepository.save(sender);
		userRepository.save(receiver);

		return transaction;
	}
}