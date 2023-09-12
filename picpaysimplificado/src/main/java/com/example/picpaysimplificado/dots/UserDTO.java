package com.example.picpaysimplificado.dots;

import java.math.BigDecimal;

import com.example.picpaysimplificado.entities.UserType;

public record UserDTO(String firstName, String lastName, String email,  String document, String password, BigDecimal balance, UserType userType ) {

}
