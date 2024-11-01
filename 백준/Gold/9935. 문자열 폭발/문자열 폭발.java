import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Main {

    static class Node {
        private char ch;
        private int idx;

        public Node(char ch, int idx) {
            this.ch = ch;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();
        String bomb = br.readLine();
        Deque<Node> deque = new LinkedList<>();
        int bombLength = bomb.length();

        for (int i = 0; i < s.length(); i++) {

            if (!deque.isEmpty() && deque.peek().idx == bombLength - 1) {
                while (!deque.isEmpty() && deque.pop().idx != 0)
                    ;
            }

            if (!deque.isEmpty() && bomb.charAt(deque.peek().idx + 1) == s.charAt(i)) {
                deque.push(new Node(s.charAt(i), deque.peek().idx + 1));
            } else if (bomb.charAt(0) == s.charAt(i)) {
                deque.push(new Node(s.charAt(i), 0));
            } else {
                deque.push(new Node(s.charAt(i), -1));
            }
        }

        if (!deque.isEmpty() && deque.peek().idx == bombLength - 1) {
            while (!deque.isEmpty() && deque.pop().idx != 0)
                ;
        }

        if (deque.isEmpty()) {
            System.out.println("FRULA");
        } else {
            while (!deque.isEmpty()) {
                sb.append(deque.pollLast().ch);
            }
        }
        System.out.print(sb);
    }
}
