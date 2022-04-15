package BOJ;

/*
    recommend x:
    x가 1인 경우 추천 문제 리스트에서 가장 어려운 문제의 번호를 출력
    만약 가장 어려운 문제가 여러 개라면 문제 번호가 큰 것을 출력

    x가 -1인 경우 추천 문제 리스트에서 가장 쉬운 문제의 번호를 출력
    만약 가장 쉬운 문제가 여러 개라면 문제 번호가 작은 것을 출력

    add P L:
    추천 문제 리스트에 난이도가 L인 문제 번호 P를 추가한다.

    solved P:
    추천 문제 리스트에서 문제 번호 P를 제거한다.
 */

import java.io.*;
import java.util.*;

public class BOJ_21939 {
    static class Problem implements Comparable<Problem> {
        int P; // 문제 번호
        int L; // 난이도

        public Problem(int p, int l) {
            P = p;
            L = l;
        }

        // 난이도가 같으면 문제 번호로 비교
        @Override
        public int compareTo(Problem o) {
            if(this.L == o.L) {
                return this.P - o.P;
            } else {
                return this.L - o.L;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Problem problem = (Problem) o;
            return P == problem.P && L == problem.L;
        }

        @Override
        public int hashCode() {
            return Objects.hash(P, L);
        }

        @Override
        public String toString() {
            return "Problem{" +
                    "P=" + P +
                    ", L=" + L +
                    '}';
        }
    }

    static int N, M;
    static TreeMap<Integer, Integer> dict = new TreeMap<>();
    static TreeSet<Problem> set = new TreeSet<>();

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        while(N-->0) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            add(P, L);
        }

        M = Integer.parseInt(br.readLine());
        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int arg0 = Integer.parseInt(st.nextToken());
            int arg1 = 0;
            if(st.hasMoreTokens()) {
                arg1 = Integer.parseInt(st.nextToken());
            }

            switch(cmd) {
                case "recommend":
                    recommend(arg0);
                    break;

                case "solved":
                    solved(arg0);
                    break;

                case "add":
                    add(arg0, arg1);
                    break;
            }
        }
        bw.flush();
        bw.close();
    }

    /**
     * param이 +1이면 가장 난이도가 높은 문제의 번호.
     * param이 -1이면 가장 난이도가 낮은 문제의 번호.
     * @param param: -1 or +1
     * @throws IOException
     */

    public static void recommend(int param) throws IOException {
        if(param == -1) {
            bw.write(set.first().P+"\n");
        } else if(param == 1) {
            bw.write(set.last().P+"\n");
        }

    }

    public static void solved(int P) {
        set.remove(new Problem(P, dict.get(P)));
        dict.remove(P);
    }

    public static void add(int P, int L) {
        set.add(new Problem(P, L));
        dict.put(P, L);
    }
}
