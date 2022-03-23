package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1062 {

    static int N, K, max = -1;
    static boolean[] choosed;
    static String[] words;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(K < 5) {
            System.out.println(0);
        } else if(K == 26) {
            System.out.println(N);
        } else {
            choosed = new boolean[26];
            choosed['a'-'a'] = true;
            choosed['n'-'a'] = true;
            choosed['t'-'a'] = true;
            choosed['i'-'a'] = true;
            choosed['c'-'a'] = true;

            words = new String[N];
            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                words[i] = str.substring(4, str.length()-4);
            }

            makeCombination(K-5, choosed, 0);
            System.out.println(max);
        }
    }
    public static void makeCombination(int toChoose, boolean[] choosed, int startIdx) {
        if(toChoose == 0) { // 알파벳 다 뽑음. 이 알파벳 조합으로 몇 단어나 읽을 수 있는지 확인
            int count = 0;
            for(String word: words) { // 각 단어에 대해
                boolean flag = true; // 읽을 수 있느냐
                for(int j=0; j<word.length(); j++) { // 단어의 각 알파벳에 대해
                    if(!choosed[word.charAt(j)-'a']) {
                        flag = false; // 못 읽음. 다음 단어 search
                        break;
                    }
                }
                if(flag) count++;
            }
            max = Math.max(max, count); // max 갱신
        }

        for(int i=startIdx; i<26; i++) {
            if(!choosed[i]) {
                choosed[i] = true;
                makeCombination(toChoose-1, choosed, i+1);
                choosed[i] = false;
            }
        }
    }

}
