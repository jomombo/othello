package com.zekrom.othello.repository;

import com.zekrom.othello.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {

    @Override
    List<Game> findAll();

    List<Game> findByUsername(String loggedInUsername);


}
