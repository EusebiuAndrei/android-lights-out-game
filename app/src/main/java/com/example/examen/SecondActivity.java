package com.example.examen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

public class SecondActivity extends AppCompatActivity {
    public HashMap<Integer, String> idToPositionMap = new HashMap<>();
    public HashMap<String, Integer> positionToIdMap = new HashMap<>();

    Game game = new Game();
    int nrOfMoves = 0;
    int minNrOfMoves;

    public void initializeIdToPositionMap() {
        idToPositionMap.put(R.id.l1c1, "1_1");
        idToPositionMap.put(R.id.l1c2, "1_2");
        idToPositionMap.put(R.id.l1c3, "1_3");
        idToPositionMap.put(R.id.l2c1, "2_1");
        idToPositionMap.put(R.id.l2c2, "2_2");
        idToPositionMap.put(R.id.l2c3, "2_3");
        idToPositionMap.put(R.id.l3c1, "3_1");
        idToPositionMap.put(R.id.l3c2, "3_2");
        idToPositionMap.put(R.id.l3c3, "3_3");
    }
    public void initializePositionToIdMap() {
        positionToIdMap.put("1_1", R.id.l1c1);
        positionToIdMap.put("1_2", R.id.l1c2);
        positionToIdMap.put("1_3", R.id.l1c3);
        positionToIdMap.put("2_1", R.id.l2c1);
        positionToIdMap.put("2_2", R.id.l2c2);
        positionToIdMap.put("2_3", R.id.l2c3);
        positionToIdMap.put("3_1", R.id.l3c1);
        positionToIdMap.put("3_2", R.id.l3c2);
        positionToIdMap.put("3_3", R.id.l3c3);
    }
    public void initializeGridFromPuzzle() {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (game.puzzle[i][j]) {
                    toggleTile(positionToIdMap.get(getPositionKey(i, j)));
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initializeIdToPositionMap();
        initializePositionToIdMap();
        initializeGridFromPuzzle();

        Log.d("PUZZLE", PuzzleGenerator.toString(game.puzzle));

//        PuzzleSolver puzzleSolver = PuzzleSolver.solve(game);
//        minNrOfMoves = puzzleSolver.mini;
        minNrOfMoves = PuzzleSolver.solve(game.puzzle);
        Log.d("STEPS", Integer.toString(minNrOfMoves));
    }

    public void GoBackHome(View view) {
        Intent i = new Intent(this, MainActivity .class);
        startActivity(i);
    }

    public String getPerformance() {
        if (nrOfMoves == minNrOfMoves) {
            return "You won: S grade";
        } else if (nrOfMoves < 2 * minNrOfMoves) {
            return "You won: A grade";
        } else if (nrOfMoves < 3 * minNrOfMoves) {
            return "You won: B grade";
        }

        return "You won: never give up";
    }

    public String getDialogText() {
        return "You solved the puzzle in " + nrOfMoves +
                "moves. The minimum number of moves was " + minNrOfMoves +
                ".Do you want to play another game?";
    }

    public void HandleToggleTile(View view) {
        if (!game.isSolved()) {
            toggle(view.getId());
            nrOfMoves++;
        }

        if (game.isSolved()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getPerformance());
            builder.setMessage(getDialogText());

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    game = new Game();
                    initializeGridFromPuzzle();
                    nrOfMoves = 0;
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    private void toggleTile(int id) {
        Button Tile = findViewById(id);
        Tile.setSelected(!Tile.isSelected());
    }

    public void toggle(int id) {
        String position = idToPositionMap.get(id);
        int x = position.charAt(0) - "0".charAt(0);
        int y = position.charAt(2) - "0".charAt(0);

        game.toggle(x, y);
        Log.d("PUZZLE", PuzzleGenerator.toString(game.puzzle));

        int k, next_x, next_y;

        toggleTile(id);
        for (k = 0; k < 4; k++) {
            next_x = x + Game.dx[k];
            next_y = y + Game.dy[k];

            if (Game.isInside(next_x, next_y)) {
                toggleTile(positionToIdMap.get(getPositionKey(next_x, next_y)));
            }
        }
    }

    public String getPositionKey(int x, int y) {
        return x + "_" + y;
    }
}