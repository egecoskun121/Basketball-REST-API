package com.example.basketballteam.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.function.Supplier;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(String message){
        super(message);
    }
}
