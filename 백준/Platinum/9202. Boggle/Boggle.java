import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int W;
    static int B;
    static Node root = new Node();
    static String[][] board;
    static final int[] dy = { 0, 1, 0, -1, 1, 1, -1, -1 };
    static final int[] dx = { 1, 0, -1, 0, -1, 1, -1, 1 };
    static String maxString;
    static int score;
    static int searchWordCnt;
    static int boardNum;

    static class Node {
        Map<Character, Node> nextNodes = new HashMap<>();
        boolean isEnd = false;
        int visitCnt = 0;
    }

    public static void insert(String s) {
        Node node = root;

        for (int i = 0; i < s.length(); i++) {
            if (node.nextNodes.get(s.charAt(i)) == null) {
                Node nNode = new Node();
                node.nextNodes.put(s.charAt(i), nNode);
                node = nNode;
            } else {
                node = node.nextNodes.get(s.charAt(i));
            }
        }
        node.isEnd = true;
    }

    public static void dfs(String[] board, boolean[][] visit, Node node, int cy, int cx, String s) {
        if (node.isEnd && node.visitCnt <= boardNum) {
            // System.out.println(s);
            int ml = maxString.length();
            int sl = s.length();

            if (ml == sl) {
                if (s.compareTo(maxString) < 0) {
                    maxString = s;
                }
            } else if (ml < sl) {
                maxString = s;
            }

            if (sl >= 3 && sl <= 4) {
                score += 1;
            } else if (sl == 5) {
                score += 2;
            } else if (sl == 6) {
                score += 3;
            } else if (sl == 7) {
                score += 5;
            } else if (sl == 8) {
                score += 11;
            }
            searchWordCnt++;
            node.visitCnt = boardNum + 1;
        }

        for (int i = 0; i < 8; i++) {
            int ny = dy[i] + cy;
            int nx = dx[i] + cx;

            if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4 || visit[ny][nx]) {
                continue;
            }
            Node nNode = node.nextNodes.get(board[ny].charAt(nx));
            if (nNode == null) {
                continue;
            }
            visit[ny][nx] = true;
            dfs(board, visit, nNode, ny, nx, s + board[ny].charAt(nx));
            visit[ny][nx] = false;
        }
    }

    public static void boggle(String[] board) {
        boolean[][] visit = new boolean[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Node nNode = root.nextNodes.get(board[i].charAt(j));
                if (nNode == null) {
                    continue;
                }
                visit[i][j] = true;
                dfs(board, visit, nNode, i, j, "" + board[i].charAt(j));
                visit[i][j] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        W = Integer.parseInt(br.readLine());

        for (int i = 0; i < W; i++) {
            insert(br.readLine());
        }
        br.readLine();

        B = Integer.parseInt(br.readLine());
        board = new String[B][4];
        for (int i = 0; i < B; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = br.readLine();
            }
            if (i < B - 1) {
                br.readLine();
            }
        }

        for (int i = 0; i < B; i++) {
            boardNum = i;
            maxString = "";
            score = 0;
            searchWordCnt = 0;
            boggle(board[i]);
            sb.append(score + " ");
            sb.append(maxString + " ");
            sb.append(searchWordCnt);
            sb.append("\n");
        }

        System.out.print(sb);
    }
}