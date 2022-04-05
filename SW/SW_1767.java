package SW;

import java.io.*;
import java.util.*;

public class SW_1767 {

    static class Pos {
        int i;
        int j;

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }

    static class Cable implements Comparable<Cable> {
        int cores;
        int cableLen;

        public Cable(int cores, int cableLen) {
            this.cores = cores;
            this.cableLen = cableLen;
        }

        @Override
        public String toString() {
            return "Cable{" +
                    "cores=" + cores +
                    ", cableLen=" + cableLen +
                    '}';
        }

        @Override
        public int compareTo(Cable o) {
            if(this.cores == o.cores) {
                return this.cableLen - o.cableLen;
            } else {
                return o.cores - this.cores;
            }
        }
    }

    static List<Pos> cores;
    static int len, N;
    static int[][] map;

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static PriorityQueue<Cable> cables;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N+1][N+1];
            cores = new ArrayList<>();
            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=1; j<=N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(1 < i && i < N && 1 < j && j < N && map[i][j] == 1) {
                        cores.add(new Pos(i, j));
                    }
                }
            }

            len = cores.size();
            cables = new PriorityQueue<>();
            for(int i = len; i>=0; i--) {
                makeCombination(i, new ArrayList<>(), 0);
            }

            //System.out.println(cables.toString());

            bw.write("#"+t+" "+cables.poll().cableLen+"\n");

        }
        bw.flush();
        bw.close();
    }

    public static void makeCombination(int toChoose, List<Pos> choosed, int startIdx) {
        if(toChoose == 0) {
            dfs(choosed, 0, 0);
            return;
        }

        for(int i = startIdx; i<len; i++) {
            choosed.add(cores.get(i));
            makeCombination(toChoose-1, choosed, i+1);
            choosed.remove(cores.get(i));
        }
    }

    public static void dfs(List<Pos> choosed, int count, int idx) {
        if(idx == choosed.size()) {
            //System.out.println(count+" : "+choosed.toString());
            cables.offer(new Cable(choosed.size(), count));
            return;
        }

        for(int d=0; d<4; d++) {
            int si = choosed.get(idx).i;
            int sj = choosed.get(idx).j;

            int ni = si;
            int nj = sj;
            int temp = 0;

            boolean flag = false;
            while(true) {
                ni += di[d];
                nj += dj[d];

                if(ni < 1 || ni > N || nj < 1 || nj > N) {
                    flag = true;
                    break;
                }

                if(map[ni][nj] != 0) break;
                map[ni][nj] = 2;
                temp++;
            }

            if(flag) dfs(choosed, count+temp, idx+1);

            while(true) {
                ni -= di[d];
                nj -= dj[d];

                if(ni == si && nj == sj) break;
                map[ni][nj] = 0;
            }
        }
    }
}
