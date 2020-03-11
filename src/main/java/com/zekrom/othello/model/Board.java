package com.zekrom.othello.model;

import java.io.Serializable;

public class Board implements Serializable {

    private Pawn[][] matrice;

    public Board(Pawn[][] matrice) {
        this.matrice = matrice;
    }

    public static Board initBasicGame(int x,int y) {
        Pawn [][]matrice = new Pawn[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrice[i][j] = Pawn.VOID;
            }
        }
        matrice[1][2] = Pawn.BLACK;
        matrice[2][1] = Pawn.BLACK;
        matrice[2][2] = Pawn.WHITE;
        matrice[1][1] = Pawn.WHITE;
        return new Board(matrice);
    }

    public Pawn getPawn(int x,int y){
        return matrice[x][y];
    }
}
