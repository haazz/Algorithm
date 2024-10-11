import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void rotate(int[][] magnetics, int[] cIdxs,  int prevIdx, int r , int dir) {
        if (prevIdx + dir  >= 4 || prevIdx + dir < 0) {
            return;
        }
        // System.out.println(cIdxs[prevIdx] + " " + cIdxs[prevIdx + dir] + " " + dir);
        int nIdx = (cIdxs[prevIdx + dir] + (dir * -2)) % 8;
        int nPrevIdx = (cIdxs[prevIdx] + (dir * 2)) % 8;
        if (nIdx < 0) {
            nIdx = 8 + nIdx;
        }
        if (nPrevIdx < 0) {
            nPrevIdx = 8 + nPrevIdx;
        }
        // for (int i = 0; i < 8; i++) {
        //     System.out.print(magnetics[prevIdx + dir][i] + " ");
        // }
        
        // System.out.println("\n" + prevIdx +  " " + nPrevIdx + " " + nIdx + " " + magnetics[prevIdx][nPrevIdx] + " " +  magnetics[prevIdx + dir][nIdx]);
        if (magnetics[prevIdx][nPrevIdx] != magnetics[prevIdx + dir][nIdx]) {
            rotate(magnetics, cIdxs, prevIdx + dir, r * -1, dir);
            cIdxs[prevIdx + dir] = (cIdxs[prevIdx + dir] + r) % 8;
            cIdxs[prevIdx + dir] = cIdxs[prevIdx + dir] < 0 ? 8 + cIdxs[prevIdx + dir]: cIdxs[prevIdx + dir];
        }
        
        
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] magnetics = new int[4][8];
        int[] cIdxs = new int[4];

        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                magnetics[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }
        // for (int j = 0; j < 4; j++) {
        //     System.out.print(cIdxs[j]);
        // }
        // System.out.println("");

        int k = Integer.parseInt(br.readLine());
        int[][] rotation = new int[k][2];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            rotation[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rotation[i][1] = Integer.parseInt(st.nextToken());
            rotate(magnetics, cIdxs, rotation[i][0], rotation[i][1], 1);
            rotate(magnetics, cIdxs, rotation[i][0], rotation[i][1], -1);
            cIdxs[rotation[i][0]] -= rotation[i][1];
            cIdxs[rotation[i][0]] = cIdxs[rotation[i][0]] < 0 ? 8 + cIdxs[rotation[i][0]]: cIdxs[rotation[i][0]] % 8;

            // for (int j = 0; j < 4; j++) {
            //     System.out.print(cIdxs[j]);
            // }
            // System.out.println("");
        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
            if (magnetics[i][cIdxs[i]] == 1) {
                result += Math.pow(2, i);
            }
        }


        System.out.println(result);
    }
}