package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_5427 {

    static int w, h;
    static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            int[] start = new int[2];

            boolean[][][] visited = new boolean[h][w][2]; // 0: 상근, 1: 불
            Queue<int[]> fireQueue = new LinkedList<>();

            char[][] map = new char[h][w];
            for(int r=0; r<h; r++) {
                String str = br.readLine();
                for(int c=0; c<w; c++) {
                    map[r][c] = str.charAt(c);
                    if(map[r][c] == '@') {
                        start = new int[]{r, c};
                        visited[r][c][0] = true;
                        map[r][c] = '.';
                    } else if(map[r][c] == '*') {
                        fireQueue.offer(new int[]{r, c});
                        visited[r][c][1] = true;
                    }
                }
            }

            Queue<int[]> manQueue = new LinkedList<>(); // 상근이 큐
            manQueue.offer(start);

            int minTime = Integer.MAX_VALUE;

            int time = 1;
            outer: while(!manQueue.isEmpty()) {
                int fQSize = fireQueue.size();

                while(fQSize-->0) {
                    int[] curr = fireQueue.poll();

                    for(int[] dir: dirs) {
                        int nr = curr[0] + dir[0];
                        int nc = curr[1] + dir[1];

                        if(isIn(nr, nc) && !visited[nr][nc][1] && map[nr][nc] == '.') {
                            map[nr][nc] = '*';
                            visited[nr][nc][1] = true;
                            fireQueue.offer(new int[]{nr, nc});
                        }
                    }
                }

                int mQSize = manQueue.size();
                while(mQSize-->0) {
                    int[] curr = manQueue.poll();

                    if(escape(curr)) {
                        minTime = time;
                        break outer;
                    }

                    for(int[] dir: dirs) {
                        int nr = curr[0] + dir[0];
                        int nc = curr[1] + dir[1];

                        if(isIn(nr, nc) && !visited[nr][nc][0] && map[nr][nc] == '.') {
                            manQueue.offer(new int[]{nr, nc});
                            visited[nr][nc][0] = true;
                        }
                    }
                }
                time++;
            }

            if(minTime == Integer.MAX_VALUE) {
                bw.write("IMPOSSIBLE\n");
            } else {
                bw.write(minTime+"\n");
            }
        }
        bw.flush();
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < h && 0 <= c && c < w;
    }

    public static boolean escape(int[] curr) {
        return curr[0] == 0 || curr[0] == h-1 || curr[1] == 0 || curr[1] == w-1;
    }
}
