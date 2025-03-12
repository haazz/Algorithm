/*
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static class Node {
        String s;
        boolean del;
        Node nNode;

        public Node(String s) {
            this.s = s;
            del = false;
            nNode = null;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Node[] heads = new Node[N];
        Node[] tails = new Node[N];
        int resIdx = 0;

        for (int i = 0; i < N; i++) {
            heads[i] = new Node("");
            heads[i].nNode = new Node(br.readLine());
            tails[i] = heads[i].nNode;
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (heads[a].del || heads[b].del) {
                continue;
            }

            heads[b].del = true;
            tails[a].nNode = heads[b];
            tails[a] = tails[b];
            resIdx = a;
        }
        StringBuilder sb = new StringBuilder();

        while (heads[resIdx] != null) {
            sb.append(heads[resIdx].s);
            heads[resIdx] = heads[resIdx].nNode;
        }
        System.out.println(sb);
    }
}