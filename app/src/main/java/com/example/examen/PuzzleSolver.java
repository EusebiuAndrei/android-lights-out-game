package com.example.examen;

public class PuzzleSolver {
    public int mini = 80;
    public Game game;
    public int[] final_sol_x = new int[85];
    public int[] final_sol_y = new int[85];
    public int[] sol_x = new int[85];
    public int[] sol_y = new int[85];

    PuzzleSolver(Game game) {
        this.game = game;
    }

    public void bkt(int k) {
        if (k > mini) return;
        if (game.isSolved()) {
            mini = k;
            for(int i = 1; i <= mini; i++) final_sol_x[i] = sol_x[i];
            for(int i = 1; i <= mini; i++) final_sol_y[i] = sol_y[i];
        } else {
            int i, j;
            for (i = 1; i <= 3; i++) {
                for (j = 1; j <= 3; j++) {
                    game.toggle(i, j);
                    sol_x[k] = i;
                    sol_y[k] = j;
                    bkt(k + 1);
                    game.toggle(i, j);
                }
            }
        }
    }

    public static PuzzleSolver solve(Game game) {
        PuzzleSolver puzzleSolver = new PuzzleSolver(game);
        puzzleSolver.bkt(0);
        return puzzleSolver;
    }

    public static PuzzleSolver solve(boolean[][] matrix) {
        PuzzleSolver puzzleSolver = new PuzzleSolver(new Game(matrix));
        puzzleSolver.bkt(0);
        return puzzleSolver;
    }

    public String solToString() {
        StringBuilder solString = new StringBuilder();

        if (mini == 0) return "";

        int i;
        for(i = 1; i < mini; i++) {
            solString.append(final_sol_x[i]);
            solString.append(":");
            solString.append(final_sol_y[i]);
            solString.append("|||");
        }
        solString.append(final_sol_x[i]);
        solString.append(":");
        solString.append(final_sol_y[i]);

        solString.append('\n');

        return solString.toString();
    }
}
