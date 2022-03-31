package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1941 {

    static char[][] map;
    static boolean[] visited;
    static List<Integer> choosed;

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[5][5];
        for(int i=0; i<5; i++) {
            String str = br.readLine();
            for(int j=0; j<5; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        visited = new boolean[25];
        choosed = new ArrayList<>();
        combi(0, 0, 0);

        System.out.println(count);
    }

    public static void combi(int startIdx, int yCount, int sCount) {
        if(yCount >= 4) return;

        //System.out.println(choosed.size());

        if(choosed.size() == 7) {
            if(checkAdj()) count++;
            return;
        }

        for(int i=startIdx; i<25; i++) {

            visited[i] = true;
            choosed.add(i);

            if(map[i/5][i%5] == 'S') combi(i+1, yCount, sCount+1);
            else combi(i+1, yCount+1, sCount);

            choosed.remove(Integer.valueOf(i));
            visited[i] = false;
        }
    }

    public static boolean checkAdj() {
        boolean[] checked = new boolean[25];

        Queue<Integer> q = new LinkedList<>();
        q.offer(choosed.get(0));
        int connect = 1;

        while(!q.isEmpty()) {
            int p = q.poll();
            int si = p/5;
            int sj = p%5;
            checked[si*5+sj] = true;

            for(int d=0; d<4; d++) {
                int ni = si+di[d];
                int nj = sj+dj[d];

                if(ni < 0 || ni >= 5 || nj < 0 || nj >= 5) continue;
                if(checked[ni*5+nj]) continue;
                if(!visited[ni*5+nj]) continue;

                connect++;
                checked[ni*5+nj] = true;
                q.offer(ni*5+nj);
            }
        }

        if(connect == 7) return true;
        else return false;
    }
}
