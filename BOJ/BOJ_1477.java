package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] num = new int[N+2];
        num[0] = 0;
        num[N+1] = L;
        for(int i=0; i<N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);

        int left = 1;
        int right = L-1;

        // 폭에 대한 이분 탐색
        while(left <= right) {
            System.out.println("left:"+left+" right:"+right);
            int mid = (left + right) / 2;
            System.out.println("현재 mid: "+mid);

            int count = 0;

            // 이 폭으로 휴게소 사이에 몇 개씩 놓을 수 있는지 확인
            for(int i=1; i<N+2; i++) {
                System.out.println(num[i-1]+"와 "+num[i]+" 사이에 "+((num[i] - num[i-1] - 1) / mid)+"개 놓을 수 있음");
                count += (num[i] - num[i-1] - 1) / mid;
            }

            System.out.println("총 count: "+count);

            if(count > M) {
                System.out.print("갯수가 M 초과이므로 더 큰 폭으로 놔보자. left: "+left);
                left = mid + 1;
                System.out.println("->"+left);
            } else {
                System.out.print("갯수가 M 미만이므로 더 작은 폭으로 놔보자. right: "+right);
                right = mid - 1;
                System.out.println("->"+right);
            }
        }

        System.out.println(left);
    }
}
