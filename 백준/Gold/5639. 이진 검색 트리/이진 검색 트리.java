import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N;
    static Node root;
    static StringBuilder sb = new StringBuilder();

    static class Node {
        int num;
        Node left, right, parent;

        public Node(int num) {
            this.num = num;
            this.left = this.right = this.parent = null;
        }

        public Node(Node parent, int num) {
            this.num = num;
            this.parent = parent;
            this.left = this.right = null;
        }

    }

    public static void insert(int num) {
        Node node = root;

        while (Objects.nonNull(node)) {
            if (node.num >= num) {
                if (Objects.isNull(node.left)) {
                    node.left = new Node(node, num);
                    break;
                }
                node = node.left;
            } else {
                if (Objects.isNull(node.right)) {
                    node.right = new Node(node, num);
                    break;
                }
                node = node.right;
            }
        }
    }

    public static void backSearch(Node node) {
        if (Objects.isNull(node)) {
            return;
        }
        backSearch(node.left);
        backSearch(node.right);
        sb.append(node.num);
        sb.append("\n");

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String numString = br.readLine();
        if (numString == null || numString.isBlank()) {
            return;
        }
        root = new Node(Integer.parseInt(numString));

        numString = br.readLine();
        while (numString != null && !numString.isBlank()) {
            int num = Integer.parseInt(numString);
            insert(num);
            numString = br.readLine();
        }

        backSearch(root);
        System.out.println(sb);
    }
}