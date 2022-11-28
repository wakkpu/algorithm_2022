package BOJ.Greedy;

import java.io.*;
import java.util.*;

public class BOJ_1082 {

    /*
    가장 싼 숫자로 최대한 많이 삼.
    앞 자리부터 그 다음 싼 숫자로 대체해가면서 확인.
    */

    static int N, M;
    static Number[] numbers;

    static class Number implements Comparable<Number> {
        int value;
        int cost;

        public Number(int value, int cost) {
            this.value = value;
            this.cost = cost;
        }

        // 큰 value -> 작은 cost
        @Override
        public int compareTo(Number o) {
            int result = Integer.compare(o.value, this.value);
            if(result == 0) {
                result = Integer.compare(this.cost, o.cost);
            }
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        numbers = new Number[N];
        for(int i=0; i<N; i++) {
            int cost = Integer.parseInt(st.nextToken());
            numbers[i] = new Number(i, cost);
        }
        Arrays.sort(numbers);

        M = Integer.parseInt(br.readLine());

    }
}
