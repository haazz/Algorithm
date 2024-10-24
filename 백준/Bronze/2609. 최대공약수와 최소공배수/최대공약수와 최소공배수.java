import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());

        int n1 = num1;
        int n2 = num2;
        while (n1 % n2 != 0) {
            int remain = n1 % n2;
            n1 = n2;
            n2 = remain;
        }
        System.out.println(n2);
        System.out.println(num1 * num2 / n2);
    }
}