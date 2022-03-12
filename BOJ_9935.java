import java.io.*;
import java.util.*;

public class BOJ_9935 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String exp = br.readLine();

        Stack<Character> stack = new Stack<>();

        for(int i=0; i<str.length(); i++) {
            stack.push(str.charAt(i));

            if (stack.size() >= exp.length()) {
                boolean boom = true;
                for (int j = 0; j < exp.length(); j++) {
                    if (stack.get(stack.size() - exp.length() + j) != exp.charAt(j)) {
                        boom = false;
                        break;
                    }
                }
                if (boom) {
                    for (int j = 0; j < exp.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        str = sb.reverse().toString();

        if(str.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(str);
        }
    }
}
