package com.example.basketballteam.service;

import com.example.basketballteam.dto.PlayerDTO;
import com.example.basketballteam.entity.Player;
import com.example.basketballteam.entity.Position;
import com.example.basketballteam.mapper.PlayerMapper;
import com.example.basketballteam.repo.PlayerRepository;

import com.example.basketballteam.service.PlayerService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {

    @Mock
    private PlayerRepository playerRepository;


    @Mock
    private PlayerMapper playerMapper;

    @InjectMocks
    PlayerService playerService;

    @Test
    void getPlayersAll() {
        List<Player> playerList = getSamplePlayers();

        Mockito.when(playerRepository.findAll()).thenReturn(playerList);

        List<Player> actualList = playerService.getPlayersAll();

        actualList=actualList.stream().sorted(getPlayerComparator()).collect(Collectors.toList());
        playerList=playerList.stream().sorted(getPlayerComparator()).collect(Collectors.toList());

        for(int i=0;i<playerList.size();i++){
            Assert.assertEquals(playerList.get(i).getFirstName(),actualList.get(i).getFirstName());
            Assert.assertEquals(playerList.get(i).getLastName(),actualList.get(i).getLastName());
            Assert.assertEquals(playerList.get(i).getPosition(),actualList.get(i).getPosition());
        }
    }

    @Test
    void getById() {
        Player player = getSamplePlayers().get(0);
        Optional<Player> optionalPlayer = Optional.of(player);

        Mockito.when(playerService.getById(any())).thenReturn(optionalPlayer);

        Optional<Player> actualPlayer = playerService.getById(1L);

        Assert.assertEquals(optionalPlayer.get().getPosition(),actualPlayer.get().getPosition());
        Assert.assertEquals(optionalPlayer.get().getFirstName(),actualPlayer.get().getFirstName());
        Assert.assertEquals(optionalPlayer.get().getLastName(),actualPlayer.get().getLastName());
    }

    @Ignore
    @Test
    void createPlayer() {
        Player expected = getSamplePlayers().get(0);
        expected.setId(null);

        Mockito.when(playerRepository.save(any())).thenReturn(expected);


        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPosition(expected.getPosition());
        playerDTO.setName(expected.getFirstName());
        playerDTO.setLastName(expected.getLastName());


        Mockito.when(playerMapper.ToEntity(playerDTO)).thenReturn(expected);

        Player actualPlayer = playerService.createPlayer(playerDTO);

        verify(playerRepository,times(1)).save(actualPlayer);

        Assert.assertEquals(expected.getLastName(),actualPlayer.getLastName());
        Assert.assertEquals(expected.getFirstName(),actualPlayer.getFirstName());
        Assert.assertEquals(expected.getPosition(),actualPlayer.getPosition());
    }

    @Test
    void deletePlayer() {

        Long playerID = 1L;
        Player player = getSamplePlayers().get(0);


        Mockito.when(playerRepository.findById(playerID)).thenReturn(Optional.of(player));
        doNothing().when(playerRepository).deleteByID(playerID);

        //validate step
        playerService.deletePlayer(1L);

        verify(playerRepository, times(1)).deleteByID(playerID);
    }

    public List<Player> getSamplePlayers(){
        List<Player> playerList = new ArrayList<>();
        Player player1 = new Player(1L,"Lebron","James", Position.PF);
        Player player2 = new Player(2L,"James","Harden", Position.PG);
        Player player3 = new Player(3L,"Chris","Paul", Position.PG);

        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
        return playerList;
    }

    private Comparator<Player> getPlayerComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0)
                return -1;

            if (o1.getId() - o2.getId() == 0)
                return 0;
            return -1;
        };
    }
}