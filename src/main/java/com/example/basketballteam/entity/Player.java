package com.example.basketballteam.entity;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="player")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 15 , name="name")
    private String firstName;
    @Column(length = 15 , name="last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Position position;



}
