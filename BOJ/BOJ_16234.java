package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16234 {

    static int N, L, R;
    static int[][] map, group;
    static Map<Integer, List<int[]>> groupNum;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        int val = 1;
        while(true) {
            boolean flag = false;

            groupNum = new HashMap<>();
            group = new int[N][N];
            for(int r=0; r<N; r++) {
                for(int c=0; c<N; c++) {
                    if(group[r][c] == 0) {
                        if(makeGroup(r, c, val++)) {
                            flag = true;
                        }
                        //printMap();
                    }
                }
            }

            for (Integer num : groupNum.keySet()) {
                makeSame(num);
            }

            if(flag) {
                day++;
            } else {
                break;
            }
        }
        System.out.println(day);

    }

    public static void printMap() {
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }

    public static boolean makeGroup(int sr, int sc, int val) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc});

        boolean flag = false;
        while(!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];

            for(int d=0; d<4; d++) {
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(nr < 0 || nr >= N || nc < 0 || nc >= N || group[nr][nc] != 0) continue;

                int diff = Math.abs(map[nr][nc] - map[r][c]);
                if(L <= diff && diff <= R) {
                    q.offer(new int[]{nr, nc});
                    group[nr][nc] = val;

                    if(!groupNum.containsKey(val)) {
                        groupNum.put(val, new ArrayList<>());
                    }
                    groupNum.get(val).add(new int[]{nr, nc});
                    flag = true;
                }
            }
        }
        return flag;
    }

    public static void makeSame(int val) {
        List<int[]> values = groupNum.get(val);

        int sum = 0;
        for(int[] pos: values) {
            sum += map[pos[0]][pos[1]];
        }
        sum /= values.size();

        for(int[] pos: values) {
            map[pos[0]][pos[1]] = sum;
        }
    }
}
