import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        Stack<Character> op = new Stack<>();
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {
                answer.append(ch);
                // System.out.println(op);
                if (op.isEmpty()) {
                    continue;
                }
                if (op.peek() == '*' || op.peek() == '/') {
                    answer.append(op.pop());
                    // while (!op.isEmpty()) {
                    // if (op.peek() == '(') {
                    // break;
                    // }
                    // answer.append(op.pop());
                    // }
                }
            } else {
                if (ch == '+' || ch == '-') {
                    while (!op.isEmpty()) {
                        if (op.peek() == '(') {
                            break;
                        }
                        answer.append(op.pop());
                    }
                    op.push(ch);
                } else if (ch == ')') {
                    while (!op.isEmpty()) {
                        if (op.peek() == '(') {
                            op.pop();
                            break;
                        }
                        answer.append(op.pop());
                    }
                    if (!op.isEmpty() && (op.peek() == '*' || op.peek() == '/')) {
                        answer.append(op.pop());
                    }
                } else {
                    op.push(ch);
                }
            }
        }
        while (!op.isEmpty()) {
            if (op.peek() == '(' || op.peek() == ')') {
                op.pop();
                continue;
            }
            answer.append(op.pop());
        }

        System.out.println(answer);
    }
}