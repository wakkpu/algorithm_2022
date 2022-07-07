package BOJ.DynamicProgramming.Tabulation;

import java.io.*;

public class BOJ_2011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        if(str.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        int N = str.length();
        int[] dp = new int[N+1]; // dp[i]: i번째 자리까지 봤을 때 가능한 경우의 수
        dp[0] = 1;
        dp[1] = 1;

        for(int i=2; i<=N; i++) {
            int curr = str.charAt(i-1) - '0';
            int prev = str.charAt(i-2) - '0';
            if(curr == 0) { // 현재 단어가 0이면
                if(1 <= prev && prev <= 2) { // 이전 단어가 1, 2일때만 가능
                    dp[i] = dp[i-2] % 1_000_000;
                } else { // 잘못된 암호
                    System.out.println(0);
                    return;
                }
            } else { // 현재 단어가 0이 아닐 때
                if(prev == 0) { // 이전 단어가 0이면 그냥 붙임
                    dp[i] = dp[i-1] % 1_000_000;
                } else { // 0이 아니라면 1 이상 26 이하인지 확인
                    if(1 <= 10 * prev + curr && 10 * prev + curr <= 26) { // 새로운 경우 조합 가능
                        dp[i] = (dp[i-1] + dp[i-2]) % 1_000_000;
                    } else { // 이전 단어랑 합쳤을 때 1 이상 26 이하가 아니면 그냥 붙임
                        dp[i] = dp[i-1] % 1_000_000;
                    }
                }
            }
        }

        System.out.println(dp[N]);
    }
}
