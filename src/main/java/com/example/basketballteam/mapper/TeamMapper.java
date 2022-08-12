package com.example.basketballteam.mapper;

import com.example.basketballteam.dto.PlayerDTO;
import com.example.basketballteam.dto.TeamDTO;
import com.example.basketballteam.entity.Player;
import com.example.basketballteam.entity.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public Team ToEntity(TeamDTO teamDTO){
        Team team = new Team();
        team.setTeamName(teamDTO.getTeamName());


        return team;
    }

    public TeamDTO ToDto(Team team){
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamName(team.getTeamName());


        return teamDTO;
    }
}
