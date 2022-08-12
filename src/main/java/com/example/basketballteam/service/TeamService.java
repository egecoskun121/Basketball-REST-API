package com.example.basketballteam.service;

import com.example.basketballteam.entity.Player;
import com.example.basketballteam.entity.Team;
import com.example.basketballteam.mapper.TeamMapper;
import com.example.basketballteam.repo.TeamRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final PlayerService playerService;

    public TeamService(TeamRepository teamRepository, TeamMapper teamMapper, PlayerService playerService) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
        this.playerService = playerService;
    }


    public List<Team> getAllTeams(){
        List<Team> allTeams=teamRepository.findAll();
        return allTeams;
    }

    public Optional<Team> getById(Long id){
        Optional<Team> optionalTeam = teamRepository.findById(id);
        return optionalTeam;
    }

    public Team addPlayerToTeam(Long playerId,Long teamId){

        Player player = playerService.getById(playerId).orElseThrow();
        Team team = teamRepository.findById(teamId).orElseThrow();
        List<Player> playerList = team.getPlayers();
        if(team.getPlayers().size()<5){
            playerList.add(player);
        }
        team.setPlayers(playerList);

        return team;
    }
}
