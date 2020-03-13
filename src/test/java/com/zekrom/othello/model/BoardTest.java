package com.zekrom.othello.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.zekrom.othello.model.Pawn.BLACK;
import static com.zekrom.othello.model.Pawn.WHITE;

class BoardTest {

    @Test
    public void shouldNotAssumeMovingWithoutEatingEast(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(12)).isTrue();
    }

    @Test
    public void shouldNotAssumeMovingWithoutEatingWest(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(32)).isTrue();
    }
    @Test
    public void shouldNotAssumeMovingWithoutEatingNorth(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(23)).isTrue();
    }
    @Test
    public void shouldNotAssumeMovingWithoutEatingNorthWest(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(33)).isTrue();
    }
    @Test
    public void shouldNotAssumeMovingWithoutEatingSouthWest(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(31)).isTrue();
    }
    @Test
    public void shouldNotAssumeMovingWithoutEatingSouth(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(21)).isTrue();
    }
    @Test
    public void shouldNotAssumeMovingWithoutEatingSouthEast(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(11)).isTrue();
    }
    @Test
    public void shouldNotAssumeMovingWithoutEatingNorthEast(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(13)).isTrue();
    }

    @Test
    public void shouldAssumeMovingEatingAPawnEast(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        voidGame[3][2] = WHITE;
        voidGame[4][2] = BLACK;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(12)).isFalse();
    }

    @Test
    public void shouldAssumeMovingEatingAPawnWest(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        voidGame[0][2] = BLACK;
        voidGame[1][2] = WHITE;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(32)).isFalse();
    }
    @Test
    public void shouldAssumeMovingEatingAPawnNorth(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        voidGame[2][1] = WHITE;
        voidGame[2][0] = BLACK;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(23)).isFalse();
    }
    @Test
    public void shouldAssumeMovingEatingAPawnNorthWest(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        voidGame[1][1] = WHITE;
        voidGame[0][0] = BLACK;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(33)).isFalse();
    }
    @Test
    public void shouldAssumeMovingEatingAPawnSouthWest(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        voidGame[1][3] = WHITE;
        voidGame[0][4] = BLACK;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(31)).isFalse();
    }
    @Test
    public void shouldAssumeMovingEatingAPawnSouth(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        voidGame[2][3] = WHITE;
        voidGame[2][4] = BLACK;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(21)).isFalse();
    }
    @Test
    public void shouldAssumeMovingEatingAPawnSouthEast(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        voidGame[3][3] = WHITE;
        voidGame[4][4] = BLACK;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(11)).isFalse();
    }

    @Test
    public void shouldAssumeMovingEatingAPawnNorthEast(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[2][2] = WHITE;
        voidGame[3][1] = WHITE;
        voidGame[4][0] = BLACK;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(13)).isFalse();
    }

    @Test
    public void shouldAssumeBorderline(){
        Pawn[][] voidGame = Board.createBoardgame(8, 8);
        voidGame[1][1] = WHITE;
        voidGame[2][2] = BLACK;
        Board board = new Board(voidGame, Turn.BLACK_PLAYER);

        Assertions.assertThat(board.cannot(00)).isFalse();
    }

}