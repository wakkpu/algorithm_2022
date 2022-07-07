package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17825 {

    static List<Integer> dices;
    static List<Integer> red;
    static List<List<Integer>> blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dices = new ArrayList<>();
        while(st.hasMoreTokens()) {
            dices.add(Integer.parseInt(st.nextToken()));
        }

        red = new ArrayList<>();
        red.add(2);
        blue = new ArrayList<>();
    }
}
