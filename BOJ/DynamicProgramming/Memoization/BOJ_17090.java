package BOJ.DynamicProgramming.Memoization;

import java.io.*;
import java.util.*;

public class BOJ_17090 {

    static int N, M;
    static char[][] map;
    static int[][] isSuccess;
    static Queue<int[]> posQ;

    /*
    1. 모든 [r][c]에서 출발 -> 시간 초과
    2. Memoization 적용 -> 시간 초과
    3. 매번 boolean[][] visited 생성이 문제인 듯 -> isSuccess의 int값으로 변경 -> 정답
    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        isSuccess = new int[N][M]; // 0: 아직 안 간 곳, 1: 갔던 곳, 2: 탈출 경로
        posQ = new LinkedList<>();

        for(int r=0; r<N; r++) {
            String str = br.readLine();
            for(int c=0; c<M; c++) {
                map[r][c] = str.charAt(c);
            }
        }

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                dfs(r, c);
            }
        }

        int result = 0;
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(isSuccess[r][c] == 2) result++;
            }
        }
        System.out.println(result);
    }

    public static void printMap(boolean[][] map) {
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void dfs(int r, int c) {
        if(!isIn(r, c) || isSuccess[r][c] == 2) {
            while(!posQ.isEmpty()) {
                int[] pos = posQ.poll();
                if(isIn(pos[0], pos[1])) {
                    isSuccess[pos[0]][pos[1]] = 2;
                }
            }
            return;
        }

        if(isSuccess[r][c] == 1) { // 갔던데 또 가면 탈출 불가능임.
            posQ.clear();
            return;
        }

        isSuccess[r][c] = 1;
        posQ.offer(new int[]{r, c});

        if(map[r][c] == 'U') {
            dfs(r-1, c);
        } else if(map[r][c] == 'R') {
            dfs(r, c+1);
        } else if(map[r][c] == 'D') {
            dfs(r+1, c);
        } else if(map[r][c] == 'L') {
            dfs(r, c-1);
        }
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}
