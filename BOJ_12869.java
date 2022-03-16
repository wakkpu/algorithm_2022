import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_12869 {

    static int[][][] dp = new int[61][61][61];
    static int sol = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int[] hp = new int[3];
        for(int i=0; i<N; i++) {
            hp[i] = Integer.parseInt(st.nextToken());
        }

        solve(hp[0], hp[1], hp[2], 0);

        System.out.println(sol);
    }

    public static void solve(int a, int b, int c, int count) {
        if(a <= 0 && b <= 0 && c <= 0) {
            sol = Math.min(sol, count);
            return;
        }

        if(a < 0) a = 0;
        if(b < 0) b = 0;
        if(c < 0) c = 0;

        if((a != 0 || b != 0 || c != 0) && dp[a][b][c] != 0 && dp[a][b][c] <= count) {
            return;
        }

        dp[a][b][c] = count;

        solve(a-1, b-3, c-9, count+1);
        solve(a-1, b-9, c-3, count+1);
        solve(a-3, b-1, c-9, count+1);
        solve(a-3, b-9, c-1, count+1);
        solve(a-9, b-1, c-3, count+1);
        solve(a-9, b-3, c-1, count+1);
    }
}
