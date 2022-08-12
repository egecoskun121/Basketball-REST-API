package com.example.basketballteam.mapper;


import com.example.basketballteam.dto.PlayerDTO;
import com.example.basketballteam.entity.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    public Player ToEntity(PlayerDTO playerDTO){
        Player player = new Player();
        player.setFirstName(playerDTO.getName());
        player.setPosition(playerDTO.getPosition());
        player.setLastName(playerDTO.getLastName());

        return player;
    }

    public Player ToDto(Player player){
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName(player.getFirstName());
        playerDTO.setLastName(player.getLastName());
        playerDTO.setPosition(player.getPosition());
        return player;
    }

}
