package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            String word = br.readLine();
            int k = Integer.parseInt(br.readLine());

            Map<Character, List<Integer>> count = new HashMap<>();
            for(int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                if(!count.containsKey(c)) {
                    count.put(c, new ArrayList<>());
                }
                count.get(c).add(i);
            }

            int min = Integer.MAX_VALUE;
            int max = -1;

            for(Character key: count.keySet()) {
                List<Integer> values = count.get(key);
                if(values.size() < k) continue;

                for(int j=0; j<values.size()-k+1; j++) {
                    min = Math.min(min, values.get(j+k-1) - values.get(j) + 1);
                    max = Math.max(max, values.get(j+k-1) - values.get(j) + 1);
                }
            }

            if(min == Integer.MAX_VALUE || max == -1) {
                bw.write("-1\n");
            } else {
                bw.write(min+" "+max+"\n");
            }
        }
        bw.flush();
    }
}
