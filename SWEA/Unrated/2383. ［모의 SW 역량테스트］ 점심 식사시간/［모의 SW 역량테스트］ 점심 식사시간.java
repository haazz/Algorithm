import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
    static int N;
    static int result;
    final static int[] dy = { 0, 1, 0, -1 };
    final static int[] dx = { 1, 0, -1, 0 };

    // 계단에 대한 클래스
    static class floor {
        int i;
        int j;
        int depth;
        // 계단에 도착한 사람들의 거리 리스트
        List<Integer> people = new ArrayList<>();

        floor(int i, int j, int depth) {
            this.i = i;
            this.j = j;
            this.depth = depth;
        }
    }

    public static boolean isAllVisited(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    public static int getTotalTime(List<floor> floors) {
        int totalFloorTime = 0;
        int time = 0;

        for (int i = 0; i < floors.size(); i++) {
            time = 0;
            // 빨리 도착한 사람 순으로 정렬
            Collections.sort(floors.get(i).people);
            for (int j = 0; j < floors.get(i).people.size(); j++) {
                time = floors.get(i).people.get(j);
                // 도착한 사람이 3명이 넘어가면 맨 앞에 있는 사람이 나가는 것과 도착한 시간을 비교
                if (j >= 3) {
                    time = Math.max(floors.get(i).people.get(j - 3) + floors.get(i).depth, time);
                }
            }
            // 마지막으로 나가는 계단을 구하기 위해 max로 구함
            totalFloorTime = Math.max(time + floors.get(i).depth + 1, totalFloorTime);
        }
        return totalFloorTime;
    }

    public static void dfs(boolean[] visited, List<int[]> people, List<floor> floors, int start) {
        // 모든 사람이 계단을 정했다면 총 걸리는 시간을 확인
        if (isAllVisited(visited)) {
            result = Math.min(getTotalTime(floors), result);
        }
        for (int i = start; i < people.size(); i++) {
            visited[i] = true;
            for (int j = 0; j < floors.size(); j++) {
                // 계단과의 거리를 구하여 floor people에 넣어줌
                int dist = Math.abs(people.get(i)[0] - floors.get(j).i)
                        + Math.abs(people.get(i)[1] - floors.get(j).j);
                floors.get(j).people.add(dist);
                dfs(visited, people, floors, i + 1);
                floors.get(j).people.remove(new Integer(dist));
            }
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            List<int[]> people = new ArrayList<>();
            List<floor> floors = new ArrayList<>();
            result = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int tile = Integer.parseInt(st.nextToken());
                    if (tile == 1) {
                        people.add(new int[] { i, j });
                    } else if (tile != 0) {
                        floors.add(new floor(i, j, tile));
                    }
                }
            }

            dfs(new boolean[people.size()], people, floors, 0);
            System.out.println("#" + tc + " " + result);
        }
    }
}