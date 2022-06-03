package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1943 {

    static class Coin {
        int value;
        int num;

        public Coin(int value, int num) {
            this.value = value;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i=0; i<3; i++) {
            int N = Integer.parseInt(br.readLine());

            Coin[] coins = new Coin[N];
            for(int j=0; j<N; j++) {
                st = new StringTokenizer(br.readLine());
                coins[j] = new Coin(Integer.parseInt(
                        st.nextToken()), Integer.parseInt(st.nextToken())
                );
            }
        }
    }
}
