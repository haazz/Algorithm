import java.util.*;

class Solution {
    String[][] table;
    List<Integer>[][] merge;
    final int N = 51;
    
    public void updateMerge(int r2, int c2, String value, boolean[][] visit) {
        table[r2][c2] = value;
        visit[r2][c2] = true;
        for (Integer rc: merge[r2][c2]) {
            int r3 = rc / N;
            int c3 = rc % N;
            if (visit[r3][c3]) {
                continue;
            }
            updateMerge(r3, c3, value, visit);
        }
    }

    public void updateUnmerge(int r2, int c2, boolean[][] visit) {
        table[r2][c2] = null;
        visit[r2][c2] = true;
        for (Integer rc: merge[r2][c2]) {
            int r3 = rc / N;
            int c3 = rc % N;
            if (visit[r3][c3]) {
                continue;
            }
            updateUnmerge(r3, c3, visit);
        }
        merge[r2][c2].clear();
    }
        
    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        table = new String[N][N];
        merge = new ArrayList[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                merge[i][j] = new ArrayList<>();
            }
        }
        
        for (int i = 0; i < commands.length; i++) {
            String[] coms = commands[i].split(" ");
            if (coms[0].equals("UPDATE")) {
                if (coms.length >= 4) {
                    updateMerge(Integer.parseInt(coms[1]), Integer.parseInt(coms[2]), coms[3], new boolean[N][N]);
                } else {
                    for (int y = 0; y < N; y++) {
                        for (int x = 0; x < N; x++) {
                            if (table[y][x] != null && table[y][x].equals(coms[1])) {
                                updateMerge(y, x, coms[2], new boolean[N][N]);
                            }
                        }
                    }
                }
            } else if (coms[0].equals("MERGE")) {
                int r1 = Integer.parseInt(coms[1]);
                int c1 = Integer.parseInt(coms[2]);
                int r2 = Integer.parseInt(coms[3]);
                int c2 = Integer.parseInt(coms[4]);
                
                if ((r1 == r2 && c1 == c2) || merge[r1][c1].contains(r2 * N + c2)) {
                    continue;
                }
                
                if (table[r1][c1] != null) {
                    updateMerge(r2, c2, table[r1][c1], new boolean[N][N]);
                } else if (table[r2][c2] != null) {
                    updateMerge(r1, c1, table[r2][c2], new boolean[N][N]);
                }
                merge[r1][c1].add(r2 * N + c2);
                merge[r2][c2].add(r1 * N + c1);
            } else if (coms[0].equals("UNMERGE")) {
                int r1 = Integer.parseInt(coms[1]);
                int c1 = Integer.parseInt(coms[2]);
                String val = table[r1][c1];
                updateUnmerge(r1, c1, new boolean[N][N]);
                table[r1][c1] = val;
            } else if (coms[0].equals("PRINT")) {
                int r1 = Integer.parseInt(coms[1]);
                int c1 = Integer.parseInt(coms[2]);
                if (table[r1][c1] == null) {
                    answer.add("EMPTY");
                } else {
                    answer.add(table[r1][c1]);
                }
            }
            
            // for (int y = 0; y < 5; y++) {
            //     for (int x = 0; x < 5; x++) {
            //         System.out.print(table[y][x] + " ");
            //     }
            //     System.out.println();
            // }
            // System.out.println();
        }
        String[] realAnswer = new String[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            realAnswer[i] = answer.get(i);
        }
        return realAnswer;
    }
}