package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16120 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] str = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();

        for(Character c: str) {
            stack.add(c);

            if(stack.size() >= 4) {
                char[] pop = new char[4];
                for(int i=3; i>=0; i--) {
                    pop[i] = stack.pop();
                }
                if(pop[0] == 'P' && pop[1] == 'P' && pop[2] == 'A' && pop[3] == 'P') {
                    stack.add('P');
                } else {
                    for(int i=0; i<4; i++) {
                        stack.add(pop[i]);
                    }
                }
            }
        }
        if(stack.pop() == 'P' && stack.isEmpty()) {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }
}
