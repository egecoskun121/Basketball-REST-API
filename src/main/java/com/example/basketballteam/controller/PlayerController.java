package com.example.basketballteam.controller;



import com.example.basketballteam.dto.PlayerDTO;
import com.example.basketballteam.entity.Player;
import com.example.basketballteam.entity.Position;
import com.example.basketballteam.mapper.PlayerMapper;
import com.example.basketballteam.repo.PlayerRepository;
import com.example.basketballteam.service.PlayerService;
import graphql.GraphQLException;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@RestController
public class PlayerController  {


    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    public PlayerController(PlayerService playerService, PlayerMapper playerMapper) {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }


    @QueryMapping
    public List<Player> getPlayersAll(){
        return playerService.getPlayersAll();
    }

    @QueryMapping
    public Optional<Player> getById(@Argument Long id){
        return playerService.getById(id);
    }

    @MutationMapping
    public Player createPlayer(@Argument String firstName,@Argument String lastName,@Argument Position position){
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName(firstName);
        playerDTO.setLastName(lastName);
        playerDTO.setPosition(position);
        return playerService.createPlayer(playerDTO);
    }

    @MutationMapping
    @Transactional
    public boolean deletePlayer(@Argument Long id){
       return playerService.deletePlayer(id);
    }


}
