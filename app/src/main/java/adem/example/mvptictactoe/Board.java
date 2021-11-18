package adem.example.mvptictactoe;

import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Board {
    private byte[][] board = new byte[3][3];
    private static byte PLAYER_1_SYMBOL = 1;
    private static byte PLAYER_2_SYMBOL = 2;
    private static boolean player1turn = true;
    private static int moveCount = 0;

    BoardListener boardListener;

    public Board(BoardListener boardListener) {
        this.boardListener = boardListener;
    }

    public void move(byte row, byte col) {
        byte player = board[row][col];
        if (board[row][col] != 0) {
            boardListener.invalidMove(row, col);
            return;
        }
        if (player1turn) {
            board[row][col] = PLAYER_1_SYMBOL;
            boardListener.playedAt(BoardListener.PLAYER_1, row, col);
            moveCount++;
        } else {
            board[row][col] = PLAYER_2_SYMBOL;
            boardListener.playedAt(BoardListener.PLAYER_2, row, col);
            moveCount++;
        }
        player1turn = !player1turn;

        if (moveCount == 9) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = 0;
                }
            }
            moveCount = 0;
            boardListener.newGame();
        }


        boolean win=true;
        for (int i = 0; i < 3; i++) {
            if (board[i][col] != player) {
                win = false;
                break;
            }
        }
        if (win) {
            boardListener.gameEnded(player);
        }

        win=true;
        for (int i = 0; i < 3; i++) {
            if (board[row][i] != player) {
                win = false;
                break;
            }
        }
        if (win) {
            boardListener.gameEnded(player);
        }

        // for the left to right diagonal of the board
        win = true;
        for (int i = 0; i < 3; i++) {
            if (board[i][i] != player) {
                win = false;
                break;
            }
        }
        if (win)
            boardListener.gameEnded(player);

    }
}
