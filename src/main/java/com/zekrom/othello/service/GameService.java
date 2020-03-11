package com.zekrom.othello.service;

import com.zekrom.othello.controller.Game;
import com.zekrom.othello.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;


    public Game save(Game gameAdmin) {
        return gameRepository.save(gameAdmin);
    }

    public List<Game> findAllByUsername(String loggedInUsername) {
        return gameRepository.findByUsername(loggedInUsername);
    }

    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

}
