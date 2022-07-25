package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1132 {
    static class Info implements Comparable<Info> {
        long num;
        boolean cameFirst;

        public Info(int num, boolean cameFirst) {
            this.num = num;
            this.cameFirst = cameFirst;
        }

        @Override
        public int compareTo(Info o) {
            return Long.compare(this.num, o.num);
        }

        @Override
        public String toString() {
            return "Info{" +
                    "num=" + num +
                    ", cameFirst=" + cameFirst +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Info[] infos = new Info[10];
        for(int i=0; i<10; i++) {
            infos[i] = new Info(0, false);
        }

        while(N-->0) {
            String str = br.readLine();
            infos[str.charAt(0)-'A'].cameFirst = true;
            for(int i=0; i<str.length(); i++) {
                infos[str.charAt(i)-'A'].num += Math.pow(10, str.length()-1-i);
            }
        }
        Arrays.sort(infos);
//        for(Info info: infos) {
//            System.out.println(info.toString());
//        }

        long result = 0;
        boolean[] used = new boolean[10]; // 숫자 i가 쓰였는지
        for(Info info: infos) {
            if(info.cameFirst) { // 처음에 나오는 숫자는 0이 되면 안됨
                for(int j=1; j<10; j++) {
                    if(!used[j]) {
                        result += info.num * j;
                        used[j] = true;
                        break;
                    }
                }
            } else { // 처음에 안나오는 숫자는 0이어도 됨
                for(int j=0; j<10; j++) {
                    if(!used[j]) {
                        result += info.num * j;
                        used[j] = true;
                        break;
                    }
                }
            }
        }
        System.out.println(result);
    }
}
