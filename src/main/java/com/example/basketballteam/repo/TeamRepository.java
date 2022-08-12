package com.example.basketballteam.repo;

import com.example.basketballteam.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
