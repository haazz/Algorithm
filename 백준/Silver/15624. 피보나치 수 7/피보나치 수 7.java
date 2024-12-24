import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static long N;
    static final int SIZE = 2;
    static long[][] baseMatrix;

    public static long[][] matrixMul(long[][] matrix1, long[][] matrix2) {
        long[][] nMatrix = new long[2][2];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    nMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                    nMatrix[i][j] %= 1000000007;
                }
            }
        }
        return nMatrix;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        baseMatrix = new long[][] { { 1, 1 }, { 1, 0 } };
        long[][] matrix = new long[][] { { 1, 0 }, { 0, 1 } };

        if (N == 0) {
            System.out.println(0);
            return;
        }
        N--;
        while (N > 0) {
            if (N % 2 == 1) {
                matrix = matrixMul(matrix, baseMatrix);
            }
            baseMatrix = matrixMul(baseMatrix, baseMatrix);
            N /= 2;
        }
        System.out.println(matrix[0][0]);
    }

}
