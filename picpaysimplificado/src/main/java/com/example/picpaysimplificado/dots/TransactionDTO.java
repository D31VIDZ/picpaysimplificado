package com.example.picpaysimplificado.dots;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(Long senderId, Long receiverId, BigDecimal amount) {
	
}
