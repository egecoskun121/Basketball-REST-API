package com.example.basketballteam.controller;

import com.example.basketballteam.dto.PlayerDTO;
import com.example.basketballteam.dto.TeamDTO;
import com.example.basketballteam.entity.Player;
import com.example.basketballteam.entity.Team;
import com.example.basketballteam.exception.PlayerNotFoundException;
import com.example.basketballteam.exception.TeamNotFoundException;
import com.example.basketballteam.mapper.TeamMapper;
import com.example.basketballteam.repo.PlayerRepository;
import com.example.basketballteam.repo.TeamRepository;
import com.example.basketballteam.service.PlayerService;
import com.example.basketballteam.service.TeamService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TeamController {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final TeamService teamService;

    public TeamController(TeamRepository teamRepository, TeamMapper teamMapper, TeamService teamService) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
        this.teamService = teamService;
    }

    @QueryMapping
    public Optional<Team> getTeamById(@Argument Long id){
        return teamService.getById(id);
    }

    @MutationMapping
    public Team addPlayerToTeam(@Argument Long playerId, @Argument Long teamId){

        return teamService.addPlayerToTeam(playerId,teamId);
    }

    @MutationMapping
    public Team createTeam(@Argument String teamName){
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamName(teamName);
        return teamRepository.save(teamMapper.ToEntity(teamDTO));
    }


}
