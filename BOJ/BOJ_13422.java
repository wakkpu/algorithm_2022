package BOJ;

import java.io.*;
import java.util.StringTokenizer;

/*
    마을에 N개의 집, 도둑은 연속된 M개의 집에서 돈을 훔침.
    K원 이상 훔치면 방범장치 가동. K원 미만이어야 함.

    붙잡히지 않고 무사히 마을을 빠져나가기 위해 돈을 훔칠 M개의 연속된 집을 고르는 방법의 수 출력
 */

public class BOJ_13422 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            int[] money = new int[N];
            for(int i=0; i<N; i++) {
                money[i] = Integer.parseInt(st.nextToken());
            }

            int count = 0;

            int sum = 0;
            for(int i=0; i<M; i++) {
                sum += money[i];
            }

            if(N == M) {
                if(sum < K) bw.write("1\n");
                else bw.write("0\n");
                continue;
            }

            int cursor = 0;
            while(cursor != N) {
                int head = money[(cursor + M) % N];
                int tail = money[cursor];

                sum = sum + head - tail;
                if (sum < K) count++;
                cursor++;
            }

            bw.write(count+"\n");
        }
        bw.flush();
        bw.close();
    }
}
