package adem.example.mvptictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BoardView {
    BoardPresenter presenter;
    TableLayout board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new BoardPresenter(this);
        board = findViewById(R.id.board);
        for (byte row = 0; row < 3; row++) {
            TableRow tablerow = (TableRow) board.getChildAt(row);
            for (byte col = 0; col < 3; col++) {
                Button button = (Button) tablerow.getChildAt(col);
                BoardPresenter.CellClickListener buttonListener = new BoardPresenter.CellClickListener(presenter, row, col);
                button.setOnClickListener(buttonListener);
            }
        }
    }

    @Override
    public void newGame() {
        for (byte row = 0; row < 3; row++) {
            TableRow tablerow = (TableRow) board.getChildAt(row);
            for (byte col = 0; col < 3; col++) {
                Button button = (Button) tablerow.getChildAt(col);
                button.setText("");
                button.setEnabled(true);
            }
        }
        Toast.makeText(this, "NEW GAME STARTED", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void putSymbol(char symbol, byte row, byte col) {
        TableRow tablerow = (TableRow) board.getChildAt(row);
        Button button = (Button) tablerow.getChildAt(col);
        button.setText(Character.toString(symbol));
    }

    @Override
    public void gameEnded(byte winner) {
        for (byte row = 0; row < 3; row++) {
            TableRow tablerow = (TableRow) board.getChildAt(row);
            for (byte col = 0; col < 3; col++) {
                Button button = (Button) tablerow.getChildAt(col);
                // button.setText("");
                //button.setEnabled(false);
            }
        }

        switch (winner) {
            case BoardView.DRAW:
                Toast.makeText(this,"GAME IS DRAW.", Toast.LENGTH_SHORT).show();
                break;
            case BoardView.PLAYER_1_WINNER:
                Toast.makeText(this,"PLAYER-1 WINS.", Toast.LENGTH_SHORT).show();
                break;
            case BoardView.PLAYER_2_WINNER:
                Toast.makeText(this,"PLAYER-2 WINS.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void invalidMove(byte row, byte col) {
        Toast.makeText(this, "INVALID MOVE.", Toast.LENGTH_SHORT).show();
    }
}