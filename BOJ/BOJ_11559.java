package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_11559 {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int R = 12, C = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] board = new char[R][C];
        for(int i=0; i<R; i++) {
            String str = br.readLine();
            for(int j=0; j<C; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        int chain = 0;
        while(true) {
            boolean[][] toExplode = findChain(board);
            if(!checkChain(toExplode)) break;
            board = boomChain(toExplode, board);
//            System.out.println("after boom");
//            printMap(board);
            board = fallDown(board);
//            System.out.println("after fall");
//            printMap(board);
            chain++;
        }
        System.out.println(chain);

    }

    public static boolean[][] findChain(char[][] board) {
        boolean[][] result = new boolean[R][C];

        boolean[][] visited = new boolean[R][C];
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(board[i][j] == '.' || visited[i][j]) continue;

                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[]{i, j});

                visited[i][j] = true;

                List<int[]> list = new ArrayList<>();
                list.add(new int[]{i, j});

                while(!q.isEmpty()) {
                    int[] curr = q.poll();
                    int sr = curr[0];
                    int sc = curr[1];

                    for(int d=0; d<4; d++) {
                        int nr = sr + dr[d];
                        int nc = sc + dc[d];

                        if(!isIn(nr, nc) || visited[nr][nc]) continue;
                        if(board[sr][sc] != board[nr][nc]) continue;

                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                        list.add(new int[]{nr, nc});
                    }
                }

                if(list.size() >= 4) {
                    for(int[] pos: list) {
                        result[pos[0]][pos[1]] = true;
                    }
                }
            }
        }

        return result;
    }

    public static boolean checkChain(boolean[][] toExplode) {
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(toExplode[i][j]) return true;
            }
        }
        return false;
    }

    public static char[][] boomChain(boolean[][] toExplode, char[][] board) {
        char[][] result = new char[R][C];
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(toExplode[i][j]) {
                    result[i][j] = '.';
                } else {
                    result[i][j] = board[i][j];
                }
            }
        }
        return result;
    }

    public static char[][] fallDown(char[][] board) {
        for(int j=0; j<C; j++) {
            for(int i=R-1; i>=0; i--) {
                if(board[i][j] != '.') {
                    while(i<11 && board[i+1][j] == '.') {
                        board[i+1][j] = board[i][j];
                        board[i][j] = '.';
                        i++;
                    }
                }
            }
        }
        return board;
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    public static void printMap(char[][] board) {
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
