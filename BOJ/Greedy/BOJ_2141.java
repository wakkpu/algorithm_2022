package BOJ.Greedy;

import java.io.*;
import java.util.*;

public class BOJ_2141 {
    static class Town implements Comparable<Town> {
        long num;
        long people;

        public Town(long num, long people) {
            this.num = num;
            this.people = people;
        }

        @Override
        public int compareTo(Town o) {
            int result = Long.compare(this.num, o.num);
            if(result == 0) {
                result = Long.compare(this.people, o.people);
            }
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long total = 0;

        Town[] towns = new Town[N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            long num = Long.parseLong(st.nextToken());
            long people = Long.parseLong(st.nextToken());

            towns[i] = new Town(num, people);
            total += people;
        }
        Arrays.sort(towns);

        long sum = 0;
        for(Town town: towns) {
            sum += town.people;

            if(sum >= (total+1)/2) {
                System.out.println(town.num);
                return;
            }
        }
    }
}
