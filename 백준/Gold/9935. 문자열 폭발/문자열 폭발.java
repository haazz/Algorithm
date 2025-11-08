import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> s = new Stack<>();

        String s1 = br.readLine();
        String s2 = br.readLine();

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(s2.length() - 1)) {
                s.push(s1.charAt(i));
                continue;
            }
            int j = s2.length() - 2;
            Stack<Character> tmp = new Stack<>();
            while (j >= 0 && !s.isEmpty() && s.peek() == s2.charAt(j)) {
                tmp.add(s.pop());
                j--;
            }

            if (j >= 0) {
                while (!tmp.isEmpty()) {
                    s.add(tmp.pop());
                }
                s.add(s1.charAt(i));
            }
        }

        if (s.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            while (!s.isEmpty()) {
                sb.append(s.pop());
            }
            System.out.println(sb.reverse().toString());
        }
    }
}