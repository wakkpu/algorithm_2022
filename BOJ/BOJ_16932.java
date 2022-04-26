package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16932 {

    static Map<Integer, Integer> size;
    static int[][] map,  group;
    static int N, M, maxSize;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        group = new int[N][M];
        size = new HashMap<>();

        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 1;
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(map[r][c] == 1 && group[r][c] == 0) {
                    size.put(num, 0);
                    makeGroup(r, c, num++);
                }
            }
        }

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(map[r][c] == 1) continue;
                checkSize(r, c);
            }
        }
        System.out.println(maxSize);
    }

    public static void makeGroup(int sr, int sc, int num) {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{sr, sc});
        group[sr][sc] = num;
        int shape = 1;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            for(int d=0; d<4; d++) {
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                if(map[nr][nc] == 1 && group[nr][nc] == 0) {
                    q.offer(new int[]{nr, nc});
                    group[nr][nc] = num;
                    shape++;
                }
            }
        }
        size.put(num, shape);
    }

    public static void checkSize(int r, int c) {

        int shape = 1;

        Set<Integer> visited = new HashSet<>();

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
            if(visited.contains(group[nr][nc]) || group[nr][nc] == 0) continue;

            visited.add(group[nr][nc]);
            int groupSize = size.get(group[nr][nc]);

            shape += groupSize;
        }
        maxSize = Math.max(shape, maxSize);
    }
}
