package ru.academits.biluta.lambdas;

public class FibonacciNumbers {
    final static long[][] BASIC_MATRIX = {
            {1L, 1L},
            {1L, 0L}
    };

    private final static long INDEX_UPPER_LIMIT = 92;

    public static long getFibonacciNumber(long fibonacciNumberIndex) {
        if (fibonacciNumberIndex < 0 || fibonacciNumberIndex > INDEX_UPPER_LIMIT) {
            throw new IndexOutOfBoundsException(String.format("Wrong index %d, should be in range 0..%d", fibonacciNumberIndex, INDEX_UPPER_LIMIT));
        }

        return getMatrixInPower(BASIC_MATRIX, fibonacciNumberIndex)[0][1];
    }

    public static long[][] getMatricesProduct(long[][] matrix1, long[][] matrix2) {
        if (matrix1[0].length != matrix2.length) {
            throw new UnsupportedOperationException("Multiplication of matrices is impossible");
        }

        long[][] matrixProduct = new long[matrix1.length][matrix2[0].length];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix2.length; k++) {
                    matrixProduct[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return matrixProduct;
    }

    public static long[][] getMatrixInPower(long[][] matrix, long power) {
        long[][] matrixInPower;

        if (power == 0) {
            matrixInPower = new long[matrix.length][matrix.length];

            for (int i = 0; i < matrix.length; i++) {
                matrixInPower[i][i] = 1;
            }

            return matrixInPower;
        }

        if (power % 2 == 0) {
            long[][] temporaryMatrix = getMatrixInPower(matrix, power / 2);
            matrixInPower = getMatricesProduct(temporaryMatrix, temporaryMatrix);
            return matrixInPower;
        }

        long[][] temporaryMatrix = getMatrixInPower(matrix, (power - 1) / 2);
        matrixInPower = getMatricesProduct(matrix, getMatricesProduct(temporaryMatrix, temporaryMatrix));

        return matrixInPower;
    }
}