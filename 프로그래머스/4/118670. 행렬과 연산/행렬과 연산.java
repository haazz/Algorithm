import java.util.*;

class Solution {
    int N;
    int M;
    
    
    public int[][] solution(int[][] rc, String[] operations) {
        N = rc.length;
        M = rc[0].length;
        int[][] answer = new int[N][M];
        Deque<Integer>[] rows =new LinkedList[N];
        Deque<Integer> col1 = new LinkedList<>();
        Deque<Integer> col2 = new LinkedList<>();
        
        for (int i = 0; i < N; i++) {
            rows[i] = new LinkedList<>();
            for (int j = 0; j < M; j++) {
                rows[i].add(rc[i][j]);
            }
            col1.add(rc[i][0]);
            col2.add(rc[i][M - 1]);
        }
        
        int idx = 0;
        for (String op : operations) {
            if (op.equals("ShiftRow")) {
                idx--;
                if (idx < 0) {
                    idx += N;
                }
                col1.addFirst(col1.pollLast());
                col2.addFirst(col2.pollLast());
            } else {
                rows[idx].pollFirst();
                rows[idx].addFirst(col1.pollFirst());
                rows[idx].addFirst(col1.peekFirst());
                rows[idx].pollLast();
                col2.addFirst(rows[idx].peekLast());
                int lIdx = idx - 1 < 0 ? idx - 1 + N : idx - 1;
                rows[lIdx].pollLast();
                rows[lIdx].add(col2.pollLast());
                rows[lIdx].add(col2.peekLast());
                rows[lIdx].pollFirst();
                col1.add(rows[lIdx].peekFirst());
            }
        }
        
        for (int i = 0; i < N; i++) {
            answer[i][0] = col1.pollFirst();
            answer[i][M - 1] = col2.pollFirst();
            rows[idx].pollFirst();
            for (int j = 1; j < M - 1; j++) {
                answer[i][j] = rows[idx].pollFirst();
            }
            idx = (idx + 1) % N;
        }
        
        return answer;
    }
}