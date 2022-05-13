package Programmers;

import java.io.*;
import java.util.*;

public class 경주로건설 {

    static int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int N;

    static class Info {
        int r;
        int c;
        int dir;
        int cost;

        public Info(int r, int c, int dir, int cost) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cost = cost;
        }
    }

    public int solution(int[][] board) {
        N = board.length;
        return bfs(board, new Info(0, 0, -1, 0));
    }

    public static int bfs(int[][] board, Info start) {
        int result = Integer.MAX_VALUE;

        boolean[][][] visited = new boolean[4][N][N];
        for(int d=0; d<4; d++) {
            visited[d][0][0] = true;
        }

        Queue<Info> q = new LinkedList<>();
        q.offer(start);

        while(!q.isEmpty()) {
            Info curr = q.poll();

            if(curr.r == N-1 && curr.c == N-1) {
                result = Math.min(result, curr.cost);
            }

            for(int d=0; d<4; d++) {
                int nr = curr.r + dirs[d][0];
                int nc = curr.c + dirs[d][1];
                int ncost = curr.cost;

                if(!isIn(nr, nc) || board[nr][nc] == 1) continue;

                if(curr.dir == d || curr.dir == -1) {
                    ncost += 100;
                } else {
                    ncost += 600;
                }

                if(!visited[d][nr][nc] || ncost <= board[nr][nc]) {
                    board[nr][nc] = ncost;
                    visited[d][nr][nc] = true;
                    q.offer(new Info(nr, nc, d, ncost));
                }
            }
        }
        return result;
    }

    public static boolean isIn(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N;
    }
}
