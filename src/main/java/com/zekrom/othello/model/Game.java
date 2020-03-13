package com.zekrom.othello.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_game")
    private Long gameId;
    private  String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="boardID")
    private Board board;

    public Game(String username, Board board){
        this.username = username;
        this.board = board;
    }

    public Game() {
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameId, game.gameId) &&
                Objects.equals(username, game.username) &&
                Objects.equals(board, game.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, username, board);
    }
}
