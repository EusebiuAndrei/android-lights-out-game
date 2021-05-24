package com.example.examen;

public class Game {
    public boolean[][] puzzle;
    public static final int[] dx = new int[] {-1, 0, 1, 0};
    public static final int[] dy = new int[] {0, 1, 0, -1};

    Game() {
        puzzle = PuzzleGenerator.generate();
    }

    Game(boolean[][] puzzle) {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                this.puzzle[i][j] = puzzle[i][j];
            }
        }
    }

    public boolean isSolved() {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (puzzle[i][j])
                    return false;
            }
        }

        return true;
    }

    public void toggleTile(int x, int y) {
        puzzle[x][y] = !puzzle[x][y];
    }

    public void toggle(int x, int y) {
        int k, next_x, next_y;

        toggleTile(x, y);
        for (k = 0; k < 4; k++) {
            next_x = x + dx[k];
            next_y = y + dy[k];

            if (isInside(next_x, next_y)) {
                toggleTile(next_x, next_y);
            }
        }
    }

    public static boolean isInside(int x, int y) {
        return 1 <= x && x <= 3 && 1 <= y && y <= 3;
    }
}
