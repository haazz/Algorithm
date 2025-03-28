
// 트라이
import java.util.*;
import java.io.*;

class Main {
    static int N;
    static Node root;

    public static class Node {
        int ch;
        int idx;
        boolean isEnd;
        Node[] nNode;

        public Node(int ch, int idx) {
            this.nNode = new Node[26];
            this.ch = ch;
            this.idx = idx;
            this.isEnd = false;
        }
    }

    public static int[] insert(String s, int idx) {
        int sl = s.length();
        int sim = 0;
        int simIdx = -1;
        Node node = root;

        for (int i = 0; i < sl; i++) {
            int alp = s.charAt(i) - 'a';

            if (node.nNode[alp] == null) {
                node.nNode[alp] = new Node(alp, idx);
            } else {
                sim++;
                simIdx = node.nNode[alp].idx;
            }
            if (i == sl - 1) {
                node.nNode[alp].isEnd = true;
            }
            node = node.nNode[alp];

        }
        // System.out.println(simIdx + " " + sim);
        if (simIdx == -1) {
            simIdx = node.idx;
        }
        return new int[] { simIdx, sim };
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        root = new Node(0, 0);
        int simMax = 0;
        int[] simIdx = new int[2];
        String[] ss = new String[N];

        for (int i = 0; i < N; i++) {
            ss[i] = br.readLine();
            int[] sim = insert(ss[i], i);
            // System.out.println(sim[0] + " " + sim[1]);
            if (ss[sim[0]].equals(ss[i])) {
                continue;
            }
            if (simMax < sim[1]) {
                simMax = sim[1];
                simIdx[0] = sim[0];
                simIdx[1] = i;
            } else if (simMax == sim[1] && simIdx[0] > sim[0]) {
                simMax = sim[1];
                simIdx[0] = sim[0];
                simIdx[1] = i;
            }
        }
        if (simIdx[1] == 0) {
            System.out.println(ss[0]);
            int i = 1;
            while (i < N - 1) {
                if (!ss[0].equals(ss[i])) {
                    break;
                }
                i++;
            }
            System.out.println(ss[i]);
        } else {
            System.out.println(ss[simIdx[0]]);
            System.out.println(ss[simIdx[1]]);
        }

    }
}