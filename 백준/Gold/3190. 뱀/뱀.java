import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[][] board;
    static int cy, cx;
    static int currentTime;
    static int currentDirection;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static Queue<int[]> snake;
    static Queue<int[]> moveArray;

    public static boolean isSnake() {
        for (int[] pos : snake) {
            if (cy == pos[0] && cx == pos[1]) {
                return true;
            }
        }
        return false;
    }

    public static int move() {
        
        while (true) {
            currentTime++;

            cy += dy[currentDirection];
            cx += dx[currentDirection];
            
            if (cy < 0 || cy >= N || cx < 0 || cx >= N) {
                return currentTime;
            }
            

            if (isSnake()) {
                return currentTime;
            }
            
            if (board[cy][cx] != 1) {
                snake.poll();
            } else {
                board[cy][cx] = 0;
            }
            snake.add(new int[] {cy, cx});

            if (!moveArray.isEmpty() && moveArray.peek()[0] == currentTime) {
                currentDirection += moveArray.poll()[1];
                currentDirection = currentDirection % 4;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int result = 0;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        snake = new LinkedList<>();
        snake.add(new int[] {0, 0});
        cy = cx = 0;
        currentTime = 0;
        currentDirection = 0;

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            board[y][x] = 1;
        }

        L = Integer.parseInt(br.readLine());
        moveArray = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            moveArray.add(new int[] {x, direction == 'D' ? 1 : 3});
        }
        
        System.out.println(move());
    }
}