package com.example.examen;

import java.util.Random;

public class PuzzleGenerator {
    public static boolean[][] generate() {
        boolean[][] matrix;

        do {
            matrix = generatePuzzle();
//            matrix[1][1] = true; matrix[1][2] = false; matrix[1][3] = true;
//            matrix[2][1] = true; matrix[2][2] = false; matrix[2][3] = true;
//            matrix[3][1] = false; matrix[3][2] = false; matrix[3][3] = false;
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
                rows[i] += matrix[i][j] == true ? 1 : 0; // boolToInt(matrix[i][j]);
                cols[j] += matrix[i][j] == true ? 1 : 0; // boolToInt(matrix[i][j]);
            }
        }

        for (i = 1; i <= 3; i++) {
            if (rows[i] % 2 != 0 || rows[i] == 0)
                return false;
            if (cols[i] % 2 != 0 || cols[i] == 0)
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
