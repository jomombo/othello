package com.zekrom.othello.repository;

import com.zekrom.othello.controller.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {

    public List<Game> findByUsername(String loggedInUsername);

    public Optional<Game> findById(Long id);

}