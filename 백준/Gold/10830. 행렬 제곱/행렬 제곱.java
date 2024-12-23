import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long B;

    public static long[][] matrixMul(long[][] matrix1, long[][] matrix2) {
        long[][] nMatrix = new long[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    nMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                    nMatrix[i][j] %= 1000;
                }
            }
        }
        return nMatrix;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        long[][] baseMatrix = new long[N][N];
        long[][] matrix = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                baseMatrix[i][j] = Long.parseLong(st.nextToken());
                if (i == j) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }

        while (B > 0) {
            if (B % 2 == 1) {
                matrix = matrixMul(matrix, baseMatrix);
            }
            baseMatrix = matrixMul(baseMatrix, baseMatrix);
            B /= 2;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

}
