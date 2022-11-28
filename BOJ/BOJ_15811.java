package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_15811 {
    static String[] words = new String[3]; // 입력 string
    static int[] alphabetNumber = new int[26]; // 알파벳에 숫자 매칭
    static boolean[] isUsedNumber = new boolean[10]; // 숫자가 쓰였는지
    static List<Character> usedNumber = new ArrayList<>(); // 쓰인 알파벳들
    static boolean isPossible = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<3; i++) {
            words[i] = st.nextToken();
            for(int j=0; j<words[i].length(); j++) {
                if(!usedNumber.contains(words[i].charAt(j))) {
                    usedNumber.add(words[i].charAt(j));
                }
            }
        }

        if(usedNumber.size() > 10) {
            System.out.println("NO");
            return;
        }

        dfs(0);

        if(isPossible) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void dfs(int depth) {
        if(isPossible) return;

        if(depth == usedNumber.size()) {
            long[] num = new long[3];
            for(int i=0; i<3; i++) {
                for(int j=0; j<words[i].length(); j++) {
                    num[i] = num[i]*10 + alphabetNumber[words[i].charAt(j) - 'A'];
                }
            }
            if(num[0] + num[1] == num[2]) {
                isPossible = true;
            }
            return;
        }

        // 숫자와 알파벳을 매칭
        for(int i=0; i<10; i++) {
            if(!isUsedNumber[i]) {
                isUsedNumber[i] = true;
                alphabetNumber[usedNumber.get(depth) - 'A'] = i;
                dfs(depth+1);
                isUsedNumber[i] = false;
            }
        }
    }
}
