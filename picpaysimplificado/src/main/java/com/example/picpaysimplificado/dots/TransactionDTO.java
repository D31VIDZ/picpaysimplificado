package com.example.picpaysimplificado.dots;

import java.math.BigDecimal;

public record TransactionDTO(Long senderId, Long receiverId, BigDecimal amount) {
	
}
