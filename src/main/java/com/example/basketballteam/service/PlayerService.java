package com.example.basketballteam.service;

import com.example.basketballteam.controller.PlayerController;
import com.example.basketballteam.dto.PlayerDTO;
import com.example.basketballteam.entity.Player;
import com.example.basketballteam.mapper.PlayerMapper;
import com.example.basketballteam.repo.PlayerRepository;
import graphql.GraphQLException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    public List<Player> getPlayersAll(){
        List<Player> allPlayers=playerRepository.findAll();
        return allPlayers;
    }

    public Optional<Player> getById(Long id){
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        return optionalPlayer;
    }


    public Player createPlayer(PlayerDTO playerDTO){
        Player player = playerMapper.ToEntity(playerDTO);
        return playerRepository.save(player);
    }

    public boolean deletePlayer(Long id){
        Optional<Player> player = playerRepository.findById(id);
        if(player.isPresent()){
            playerRepository.deleteByID(id);
            return true;
        }else {
            throw new GraphQLException("That player "+player.get().getFirstName()+" does not exists.");
        }
    }

}
