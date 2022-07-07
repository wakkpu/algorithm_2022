package BOJ.Greedy;

import java.io.*;
import java.util.*;

public class BOJ_1092 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Integer[] cranes = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cranes, Comparator.reverseOrder());

        int M = Integer.parseInt(br.readLine());
        List<Integer> items = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            items.add(Integer.parseInt(st.nextToken()));
        }
        items.sort(Comparator.reverseOrder());

        if(cranes[0] < items.get(0)) {
            System.out.println(-1);
            System.exit(0);
        }

        int count = 0;
        while(!items.isEmpty()) {
            for(Integer crane: cranes) {
                for(int i=0; i<items.size(); i++) {
                    if(crane >= items.get(i)) {
                        items.remove(i);
                        break;
                    }
                }
            }
            count++;
        }
        System.out.println(count);
    }
}
