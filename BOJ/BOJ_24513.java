package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_24513 {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] oneStart = new int[3];
        int[] twoStart = new int[3];

        int[][] map = new int[N][M];
        int[][] visited = new int[N][M]; // 0: 아직 안 간 곳, 1: 방금 감염, 2: 완전히 감염
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 1) {
                    oneStart = new int[]{r, c, 1};
                    visited[r][c] = 2;
                } else if(map[r][c] == 2) {
                    twoStart = new int[]{r, c, 2};
                    visited[r][c] = 2;
                }
            }
        }

        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};

        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> tempQ = new LinkedList<>();
        q.offer(oneStart);
        q.offer(twoStart);

        while(!q.isEmpty()) {
            int qSize = q.size();
            while(qSize-->0) {
                int[] curr = q.poll();
                int sr = curr[0];
                int sc = curr[1];
                int sv = curr[2];

                for(int d=0; d<4; d++) {
                    int nr = sr+dr[d];
                    int nc = sc+dc[d];

                    if(isIn(nr, nc) && map[nr][nc] != -1 && visited[nr][nc] != 2) {
                        if(map[nr][nc] == 0) {
                            map[nr][nc] = sv;
                            tempQ.offer(new int[]{nr, nc, sv});
                        } else if(map[nr][nc] == 1) {
                            if(sv == 2) {
                                map[nr][nc] = 3;
                                tempQ.offer(new int[]{nr, nc, 3});
                            }
                        } else if(map[nr][nc] == 2) {
                            if(sv == 1) {
                                map[nr][nc] = 3;
                                tempQ.offer(new int[]{nr, nc, 3});
                            }
                        }
                        visited[nr][nc] = 1;
                    }
                }
            }

            while(!tempQ.isEmpty()) {
                int[] temp = tempQ.poll();
                if(visited[temp[0]][temp[1]] == 1 && (map[temp[0]][temp[1]] == 1 || map[temp[0]][temp[1]] == 2)) {
                    q.offer(temp);
                    visited[temp[0]][temp[1]] = 2;
                }
            }
        }

        int[] nums = new int[3];
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(map[r][c] == 1) {
                    nums[0]++;
                } else if(map[r][c] == 2) {
                    nums[1]++;
                } else if(map[r][c] == 3) {
                    nums[2]++;
                }
            }
        }
        System.out.println(nums[0]+" "+nums[1]+" "+nums[2]);
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}
