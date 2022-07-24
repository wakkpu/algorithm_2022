package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1132 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        Map<Character, Integer> map = new HashMap<>();
        while(N-->0) {
            String str = br.readLine();
            for(int i=0; i<str.length(); i++) {
                if(!map.containsKey(str.charAt(i))) {
                    map.put(str.charAt(i), 0);
                }
                map.put(str.charAt(i), map.get(str.charAt(i))+(int)Math.pow(10, (str.length()-1-i)));
            }
        }

        List<Integer> nums = new ArrayList<>();
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            nums.add(entry.getValue());
        }
        nums.sort(Collections.reverseOrder());

        long result = 0;
        int value = 9;

        // 0으로 시작하는 수는 없다. -> 이 조건 어떡해야 할까

        for(Integer num: nums) {
            result += (long) num * (value--);
        }
        System.out.println(result);
    }
}
