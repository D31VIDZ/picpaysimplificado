package com.example.picpaysimplificado.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.picpaysimplificado.dots.TransactionDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long senderId;
	
	private Long receiverId;
	
	private BigDecimal amount;
	
	private LocalDateTime dateTime;

	public Transaction(TransactionDTO transactionDTO) {
		this.senderId = transactionDTO.senderId();
		this.receiverId = transactionDTO.receiverId();
		this.amount = transactionDTO.amount();
	}
}
