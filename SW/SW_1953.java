package SW;

import java.io.*;
import java.util.*;

public class SW_1953 {

    static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M, R, C, L, count;
    static int[][] map;
    static boolean[][] visited;

    static int[][] dir1 = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] dir2 = {{-1, 0}, {1, 0}};
    static int[][] dir3 = {{0, -1}, {0, 1}};
    static int[][] dir4 = {{-1, 0},{0, 1}};
    static int[][] dir5 = {{1, 0}, {0, 1}};
    static int[][] dir6 = {{1, 0}, {0, -1}};
    static int[][] dir7 = {{-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            L = Integer.parseInt(st.nextToken());

            Pos start = new Pos(R, C);

            count = 0;
            map = new int[N][M];
            visited = new boolean[N][M];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs(start);
            bw.write("#"+t+" "+count+"\n");
        }
        bw.flush();
        bw.close();
    }

    public static void bfs(Pos start) {
        Queue<Pos> q = new LinkedList<>();
        q.offer(start);
        visited[start.r][start.c] = true;

        while(L-->0) {
            int size = q.size();
            while(size-->0) {
                Pos curr = q.poll();
                int r = curr.r;
                int c = curr.c;

                count++;

                if(map[r][c] == 1) {
                    for(int d=0; d<4; d++) {
                        int nr = r+dir1[d][0];
                        int nc = c+dir1[d][1];

                        if(isIn(nr, nc) && !visited[nr][nc]) {
                            if(d == 0 && (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 5 || map[nr][nc] == 6)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            } else if(d == 1 && (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 7)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            } else if(d == 2 && (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 5)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            } else if(d == 3 && (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 6 || map[nr][nc] == 7)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            }
                        }
                    }
                } else if(map[r][c] == 2) {
                    for(int d=0; d<2; d++) {
                        int nr = r+dir2[d][0];
                        int nc = c+dir2[d][1];

                        if(isIn(nr, nc) && !visited[nr][nc]) {
                            if(d == 0 && (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 5 || map[nr][nc] == 6)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            } else if(d == 1 && (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 7)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            }
                        }
                    }
                } else if(map[r][c] == 3) {
                    for(int d=0; d<2; d++) {
                        int nr = r+dir3[d][0];
                        int nc = c+dir3[d][1];

                        if(isIn(nr, nc) && !visited[nr][nc]) {
                            if(d == 0 && (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 5)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            } else if(d == 1 && (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 6 || map[nr][nc] == 7)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            }
                        }
                    }
                } else if(map[r][c] == 4) {
                    for(int d=0; d<2; d++) {
                        int nr = r+dir4[d][0];
                        int nc = c+dir4[d][1];

                        if(isIn(nr, nc) && !visited[nr][nc]) {
                            if(d == 0 && (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 5 || map[nr][nc] == 6)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            } else if(d == 1 && (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 6 || map[nr][nc] == 7)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            }
                        }
                    }
                } else if(map[r][c] == 5) {
                    for(int d=0; d<2; d++) {
                        int nr = r+dir5[d][0];
                        int nc = c+dir5[d][1];

                        if(isIn(nr, nc) && !visited[nr][nc]) {
                            if(d == 0 && (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 7)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            } else if(d == 1 && (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 6 || map[nr][nc] == 7)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            }
                        }
                    }
                } else if(map[r][c] == 6) {
                    for(int d=0; d<2; d++) {
                        int nr = r+dir6[d][0];
                        int nc = c+dir6[d][1];

                        if(isIn(nr, nc) && !visited[nr][nc]) {
                            if(d == 0 && (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 4 || map[nr][nc] == 7)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            } else if(d == 1 && (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 5)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            }
                        }
                    }
                } else if(map[r][c] == 7) {
                    for(int d=0; d<2; d++) {
                        int nr = r+dir7[d][0];
                        int nc = c+dir7[d][1];

                        if(isIn(nr, nc) && !visited[nr][nc]) {
                            if(d == 0 && (map[nr][nc] == 1 || map[nr][nc] == 2 || map[nr][nc] == 5 || map[nr][nc] == 6)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            } else if(d == 1 && (map[nr][nc] == 1 || map[nr][nc] == 3 || map[nr][nc] == 4 || map[nr][nc] == 5)) {
                                visited[nr][nc] = true;
                                q.offer(new Pos(nr, nc));
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < M);
    }
}
