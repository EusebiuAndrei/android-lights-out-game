package com.example.examen;

import java.util.Random;

public class PuzzleGenerator {
    public static boolean[][] generate() {
        boolean[][] matrix;

        do {
            matrix = generatePuzzle();
        } while (!isSolvable(matrix));

        return matrix;
    }

    private static boolean[][] generatePuzzle() {
        Random rand = new Random();
        boolean[][] matrix = new boolean[5][5];
        int i, j;

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                int randomNumber = rand.nextInt(Integer.MAX_VALUE);
                matrix[i][j] = randomNumber % 2 == 1;
            }
        }

        return matrix;
    }

    private static boolean isSolvable(boolean[][] matrix) {
        int i, j;
        int[] rows = new int[] {0, 0, 0, 0, 0};
        int[] cols = new int[] {0, 0, 0, 0, 0};

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                rows[i] += boolToInt(matrix[i][j]);
                cols[j] += boolToInt(matrix[i][j]);
            }
        }

        for (i = 1; i <= 3; i++) {
            if (rows[i] % 2 != 0)
                return false;
            if (cols[i] % 2 != 0)
                return false;
        }

        return true;
    }

    public static String toString(boolean[][] matrix) {
        StringBuilder matrixString = new StringBuilder();
        int i, j;

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                matrixString.append(boolToInt(matrix[i][j]));
            }
            matrixString.append('\n');
        }

        return matrixString.toString();
    }

    public static int boolToInt(boolean x) {
        return x ? 1 : 0;
    }
}
