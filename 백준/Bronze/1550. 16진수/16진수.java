import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int decimal = Integer.parseInt(br.readLine(), 16);
        System.out.println(decimal);
    }
}