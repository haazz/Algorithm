
// The main method must be in a class named "Main".

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main {
    static int G;
    static int P;

    public static int find(int[] uf, int x) {
        if (x == uf[x]) {
            return x;
        }
        return uf[x] = find(uf, uf[x]);
    }

    public static int union(int[] uf, int x, int y) {
        x = find(uf, x);
        y = find(uf, y);
        if (x < y) {
            return uf[y] = uf[x];
        }
        return uf[x] = uf[y];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        int[] airplanes = new int[P];
        int[] docking = new int[G + 1];

        for (int i = 0; i <= G; i++) {
            docking[i] = i;
        }
        for (int i = 0; i < P; i++) {
            airplanes[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        for (int i = 0; i < P; i++) {
            int findGate = find(docking, airplanes[i]);
            if (findGate == 0) {
                break;
            }
            union(docking, airplanes[i], findGate - 1);
            cnt++;
        }

        // for (int i = 0; i <= G; i++) {
        // System.out.print(docking[i] + " ");
        // }

        System.out.println(cnt);
    }
}