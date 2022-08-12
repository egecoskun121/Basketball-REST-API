package com.example.basketballteam.dto;


import com.example.basketballteam.entity.Position;
import lombok.Data;

@Data
public class PlayerDTO {
    private String name;
    private String lastName;
    private Position position;
}
