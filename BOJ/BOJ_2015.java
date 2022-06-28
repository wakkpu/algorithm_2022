package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2015 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long result = 0L;

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] sum = new int[N+1];
        for(int i=1; i<=N; i++) {
            sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());

            if(sum[i] == K) {
                result++;
                //System.out.println("result: "+result);
            }
        }

        //System.out.println(Arrays.toString(sum));

        Map<Integer, Long> map = new HashMap<>();

        for(int i=1; i<=N; i++) {
            //System.out.println(i+" : "+map);
            if(map.containsKey(sum[i] - K)) {
                //System.out.print("result: "+result);
                result += map.get(sum[i] - K);
                //System.out.println(" -> "+result);
            }

            if(map.containsKey(sum[i])) {
                map.put(sum[i], map.get(sum[i])+1);
            } else {
                map.put(sum[i], 1L);
            }
        }

        System.out.println(result);
    }
}
