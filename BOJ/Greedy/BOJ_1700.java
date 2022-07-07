package BOJ.Greedy;

import java.io.*;
import java.util.*;

public class BOJ_1700 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> items = new ArrayList<>();
        for(int i=0; i<K; i++) {
            items.add(Integer.parseInt(st.nextToken()));
        }

        Set<Integer> plugs = new HashSet<>();

        int count = 0;
        for(int i=0; i<K; i++) {
            if(!plugs.contains(items.get(i))) { // 이미 꽂혀있는건 패스
                if(plugs.size() < N) { // N개 미만이 꽂혀있으면
                    plugs.add(items.get(i)); // 그냥 추가
                } else if(plugs.size() == N) { // N개 다 꽂혀있으면

                }
            }
        }
        System.out.println(count);
    }
}
