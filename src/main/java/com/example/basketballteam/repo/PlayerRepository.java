package com.example.basketballteam.repo;



import com.example.basketballteam.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    @Modifying
    @Query(value = "DELETE FROM player WHERE id=:playerId",nativeQuery = true)
     void deleteByID(Long playerId);
}
