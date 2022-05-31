package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_1726 {

    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    static int M, N;
    static int[] end;

    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        for(int r=0; r<M; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 행, 열, 방향
        int[] start = new int[3];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<3; i++) {
            start[i] = Integer.parseInt(st.nextToken())-1;
        }

        end = new int[3];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<3; i++) {
            end[i] = Integer.parseInt(st.nextToken())-1;
        }

        bfs(start);
    }

    public static void bfs(int[] start) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);

        boolean[][][] visited = new boolean[M][N][4];
        visited[start[0]][start[1]][start[2]] = true;

        int time = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            while(size-->0) {
                int[] curr = q.poll();

//                System.out.println(Arrays.toString(curr)+" at "+time);

                visited[curr[0]][curr[1]][curr[2]] = true;

                if(isSame(curr, end)) {
                    System.out.println(time);
                    return;
                }

                int[] left = turnLeft(curr);
                if(!visited[left[0]][left[1]][left[2]]) {
                    q.offer(left);
                }

                int[] right = turnRight(curr);
                if(!visited[right[0]][right[1]][right[2]]) {
                    q.offer(right);
                }

                for(int k=1; k<=3; k++) {
                    int[] next = go(curr, k);

                    if(!isIn(next)) break;
                    if(visited[next[0]][next[1]][next[2]]) continue;

                    visited[next[0]][next[1]][next[2]] = true;

                    q.offer(next);
                }
            }
            time++;
        }
    }

    public static int[] go(int[] arr, int k) {
        int[] result = new int[3];

        result[0] = arr[0] + dr[arr[2]] * k;
        result[1] = arr[1] + dc[arr[2]] * k;
        result[2] = arr[2];

        return result;
    }

    public static int[] turnLeft(int[] arr) {
        int[] result = new int[3];
        for(int i=0; i<3; i++) {
            result[i] = arr[i];
        }

        if(result[2] == 0) { // 동 -> 북
            result[2] = 3;
        } else if(result[2] == 1) { // 서 -> 남
            result[2] = 2;
        } else if(result[2] == 2) { // 남 -> 동
            result[2] = 0;
        } else { // 북 -> 서
            result[2] = 1;
        }

        return result;
    }

    public static int[] turnRight(int[] arr) {
        int[] result = new int[3];
        for(int i=0; i<3; i++) {
            result[i] = arr[i];
        }

        if(result[2] == 0) { // 동 -> 남
            result[2] = 2;
        } else if(result[2] == 1) { // 서 -> 북
            result[2] = 3;
        } else if(result[2] == 2) { // 남 -> 서
            result[2] = 1;
        } else { // 북 -> 동
            result[2] = 0;
        }

        return result;
    }

    public static boolean isSame(int[] arr1, int[] arr2) {
        for(int i=0; i<3; i++) {
            if(arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIn(int[] arr) {
        return 0 <= arr[0] && arr[0] < M &&
               0 <= arr[1] && arr[1] < N &&
               map[arr[0]][arr[1]] == 0;
    }
}
