package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_11509 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] balloons = new int[N]; // balloons[i]: i번째 인덱스에서 풍선 높이
        int max = 0;
        for(int i=0; i<N; i++) {
            balloons[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, balloons[i]);
        }

        int[] arrows = new int[2 + max]; // arrows[i]: 높이 i를 지나고 있는 화살의 갯수

        int count = 0;
        for(int height: balloons) {
            // System.out.print("height: "+height+", ");
            if(arrows[height+1] == 0) { // 현재 높이 +1에서 오는 화살 없으면
                arrows[height]++; // 현재 높이에서 화살 던져야 하므로 +1
                count++; // 화살 총 갯수 +1
                // System.out.println("no arrows coming from "+(height+1)+". count changed to "+count);
            } else { // 현재 높이 +1에서 오는 화살 있으면
                arrows[height+1]--; // 현재 높이 +1의 화살은 지나쳤으므로 -1
                arrows[height]++; // 현재 높이로 화살이 왔으므로 +1
                // System.out.println("arrows coming from "+(height+1)+".");
            }
            // System.out.println(Arrays.toString(arrows));
        }
        System.out.println(count);
    }
}
