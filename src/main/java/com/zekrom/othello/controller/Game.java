package com.zekrom.othello.controller;

import javax.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "game_gen")
    @SequenceGenerator(name = "game_gen", sequenceName = "id_game_seq", allocationSize = 1)
    @Column(name = "id_game")
    private Long gameId;
}
