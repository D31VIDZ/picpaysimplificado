package com.example.picpaysimplificado.services;

import com.example.picpaysimplificado.entities.User;
import com.example.picpaysimplificado.entities.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AuthorizationService {

    @Autowired
    private RestTemplate template;


    public boolean validTransaction(User userOp, BigDecimal amount) throws Exception {

            if(userOp.getUserType() == UserType.MERCHANT) {
                throw new Exception("Usuario do tipo merchant não autorizado a realizar transações");
            }
            if(userOp.getBalance().compareTo(amount) < 0) {
                throw new Exception("Saldo insulficiente");
            }

            //ResponseEntity<Map> authorization = template.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);
            //if(authorization.getStatusCode() == HttpStatus.OK && authorization.getBody().get("messege") == "Autorizado") {

            return true;
    }
}