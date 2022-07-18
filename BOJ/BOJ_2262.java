package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2262 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> ranks = new LinkedList<>();
        for(int i=0; i<N; i++) {
            ranks.add(Integer.parseInt(st.nextToken()));
        }

        int result = 0;
        while(ranks.size() > 1) {
            int max = 0;
            int maxIdx = 0;
            for(Integer rank: ranks) {
                if(rank > max) {
                    max = rank;
                    maxIdx = ranks.indexOf(rank);
                }
            }

            if(maxIdx == 0) {
                result += Math.abs(ranks.get(maxIdx) - ranks.get(maxIdx+1));
            } else if(maxIdx == ranks.size()-1) {
                result += Math.abs(ranks.get(maxIdx) - ranks.get(maxIdx-1));
            } else {
                result += Math.min(
                        Math.abs(ranks.get(maxIdx) - ranks.get(maxIdx+1)),
                        Math.abs(ranks.get(maxIdx) - ranks.get(maxIdx-1))
                );
            }
            ranks.remove(maxIdx);
        }
        System.out.println(result);
    }
}
