package com.example.examen;

public class PuzzleSolver {
    public int mini = 5;
    public Game game;

    PuzzleSolver(Game game) {
        this.game = game;
    }

    public void bkt(int k) {
        if (k > mini) return;
        if (game.isSolved()) {
            if (k < mini)
                mini = k;
        } else {
            int i, j;
            for (i = 1; i <= 3; i++) {
                for (j = 1; j <= 3; j++) {
                    game.toggle(i, j);
                    bkt(k + 1);
                    game.toggle(i, j);
                }
            }
        }
    }

    public static int solve(Game game) {
        PuzzleSolver puzzleSolver = new PuzzleSolver(game);
        puzzleSolver.bkt(0);
        return puzzleSolver.mini;
    }

    public static int solve(boolean[][] matrix) {
        PuzzleSolver puzzleSolver = new PuzzleSolver(new Game(matrix));
        puzzleSolver.bkt(0);
        return puzzleSolver.mini;
    }
}
