package com.zekrom.othello.model;

import java.io.Serializable;

import static com.zekrom.othello.model.Pawn.*;
import static com.zekrom.othello.model.Turn.BLACK_PLAYER;
import static com.zekrom.othello.model.Turn.WHITE_PLAYER;

public class Board implements Serializable {

    private static final String DRAW = "Egalité";
    private static final String BLACK_WIN = "Victoire joueur noir";
    private static final String WHITE_WIN = "Victoire joueur blanc";
    private Pawn[][] matrice;

    private Turn turn;

    private boolean isGameOver = false;
    private boolean blackDown = false;
    private boolean whiteDown = false;
    private String gameStatus = "";

    public Board(Pawn[][] matrice, Turn turn) {
        this.matrice = matrice;
        this.turn = turn;
    }

    public static Board initBasicGame(int x,int y) {
        Pawn[][] matrice = createBoardgame(x, y);
        matrice[3][4] = BLACK;
        matrice[4][3] = BLACK;
        matrice[4][4] = WHITE;
        matrice[3][3] = WHITE;
        return new Board(matrice,Turn.BLACK_PLAYER);
    }

    public static Pawn[][] createBoardgame(int x, int y) {
        Pawn [][]matrice = new Pawn[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrice[i][j] = VOID;
            }
        }
        return matrice;
    }

    public Pawn getPawn(int x,int y){
        return matrice[x][y];
    }

    public Turn getTurn() {
        return turn;
    }

    public boolean cannot(Integer choice) {
        int x = choice / 10;
        int y = choice % 10;

        Pawn foePawn = turn == WHITE_PLAYER ? BLACK : WHITE;
        Pawn currentPawn = turn == WHITE_PLAYER ? WHITE : BLACK;

        boolean result = true;
        if (matrice[x][y]== VOID)
        {
            while (x+2<8 && y+2<8 && matrice[x+1][y+1]==foePawn)
            {
                if(matrice[x+2][y+2]==currentPawn)
                    result=false;
                x++;
                y++;
            }
            while(x+2<8 && matrice[x+1][y]==foePawn)
            {
                if(matrice[x+2][y]==currentPawn)
                    return false;
                x++;
            }
            while (y+2<8 && matrice[x][y+1]==foePawn)
            {
                if (matrice[x][y+2]==currentPawn)
                    result=false;
                y++;
            }
            while (y-2>-1 && matrice[x][y-1]==foePawn)
            {
                if (matrice[x][y-2]==currentPawn)
                    result=false;
                y--;
            }
            while (x-2>-1 && y-2>-1 && matrice[x-1][y-1]==foePawn)
            {
                if (matrice[x-2][x-2]==currentPawn)
                    result=false;
                x--;
                y--;
            }
            while (x-2>-1 && matrice[x-1][y]==foePawn)
            {
                if (matrice[x-2][y]==currentPawn)
                    result=false;
                x--;
            }
            while (x-2>-1 && y+2<8 && matrice[x-1][y+1]==foePawn)
            {
                if(matrice[x-2][y+2]==currentPawn)
                    result=false;
                x--;
                y++;
            }
            while (x+2<8 && y-2>-1 && matrice[x+1][y-1]==foePawn)
            {
                if (matrice[x+2][y-2]==currentPawn)
                    result = false;
                x++;
                y--;
            }
        }
        return result;
    }

    public void apply(Integer choice) {
        int x = choice / 10;
        int y = choice % 10;

        if (WHITE_PLAYER.equals(turn)) {
            putPawn(x, y, WHITE);
            whiteDown = false;
            turn = BLACK_PLAYER;
        } else if (BLACK_PLAYER.equals(turn)) {
            putPawn(x,y, BLACK);
            turn = WHITE_PLAYER;
            blackDown = false;
        }

    }

    private void putPawn(int x, int y, Pawn pawn) {
        matrice[x][y] = pawn;
        flipWholeBoard(pawn,x,y);
    }

    public boolean isGameOver() {
    return isGameOver;
    }

    private void flipWholeBoard(Pawn turn, int x, int y)
    {
        //Check Above
        direction(x, y, turn, 0, -1);
        direction(x, y, turn, 0, 1);
        //check right & right
        direction(x, y, turn, 1,0);
        direction(x, y, turn, -1, 0);
        //check corners
        direction(x, y, turn, 1,1);
        direction(x, y, turn, 1,-1);
        direction(x, y, turn, -1,1);
        direction(x, y, turn, -1,-1);
    }

    private void direction(int row, int column, Pawn color, int colDir, int rowDir)
    {
        int currentRow= row + rowDir;
        int currentCol = column + colDir;
        if (currentRow==8 || currentRow<0 || currentCol==8 || currentCol<0)
        {
            return;
        }
        while (matrice[currentRow][currentCol]==BLACK || matrice[currentRow][currentCol]==WHITE)
        {
            if (matrice[currentRow][currentCol]==color)
            {
                while(!(row==currentRow && column==currentCol))
                {
                    matrice[currentRow][currentCol]=color;
                    currentRow=currentRow-rowDir;
                    currentCol=currentCol-colDir;
                }
                break;
            }else
            {
                currentRow=currentRow + rowDir;
                currentCol=currentCol + colDir;
            }
            if (currentRow<0 || currentCol<0 || currentRow==8 || currentCol==8)
            {
                break;
            }
        }
    }

    public void pass() {
        if (BLACK_PLAYER.equals(turn)) {
            if (blackDown) {
                isGameOver = true;
                gameEnd();
            }
            else {
                blackDown = true;
                turn = WHITE_PLAYER;
            }
        }
        else {
            if (whiteDown) {
                isGameOver = true;
                gameEnd();
            }
            else {
                whiteDown = true;
                turn = BLACK_PLAYER;
            }
        }
    }

    private void gameEnd() {
        int white_count = 0;
        int black_count = 0;
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (BLACK.equals(getPawn(i, j))) {
                    black_count++;
                }
                if (WHITE.equals(getPawn(i, j))) {
                    white_count++;
                }
            }
        }
        gameStatus = black_count > white_count ? BLACK_WIN : white_count > black_count ? WHITE_WIN : DRAW ;
    }

    public String getResult(){
        return gameStatus;
    }
}
