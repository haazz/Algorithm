import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static final int SIZE = 2;

    public static BigInteger[][] mulMatrix(BigInteger[][] matrix1, BigInteger[][] matrix2) {
        BigInteger[][] nMatrix = { { BigInteger.ZERO, BigInteger.ZERO }, { BigInteger.ZERO, BigInteger.ZERO } };
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    nMatrix[i][j] = nMatrix[i][j].add(matrix1[i][k].multiply(matrix2[k][j]));
                }
            }
        }
        return nMatrix;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        BigInteger[][] baseMatrix = { { BigInteger.ONE, BigInteger.ONE }, { BigInteger.ONE, BigInteger.ZERO } };
        BigInteger[][] matrix = { { BigInteger.ONE, BigInteger.ZERO }, { BigInteger.ZERO, BigInteger.ONE } };

        if (N == 0) {
            System.out.println(0);
            return;
        }

        N--;
        while (N > 0) {
            if (N % 2 == 1) {
                matrix = mulMatrix(baseMatrix, matrix);
            }
            baseMatrix = mulMatrix(baseMatrix, baseMatrix);
            N /= 2;
        }
        System.out.println(matrix[0][0]);
    }
}