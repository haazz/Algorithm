import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Stack<Integer> stack;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        stack = new Stack<>();
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            int height = Integer.parseInt(st.nextToken());

            if (stack.isEmpty() || stack.peek() <= height) {
                if (height != 0) {
                    stack.push(height);
                }
            } else {
                int prevHeight = -1;
                while (!stack.isEmpty() && stack.peek() > height) {
                    if (prevHeight == stack.peek()) {
                        stack.pop();
                        continue;
                    }
                    prevHeight = stack.pop();
                    result++;
                }
                if (height != 0) {
                    stack.push(height);
                }
            }
        }
        int prevHeight = -1;
        while (!stack.isEmpty()) {
            if (prevHeight == stack.peek()) {
                stack.pop();
                continue;
            }
            prevHeight = stack.pop();
            result++;
        }
        System.out.println(result);
    }
}