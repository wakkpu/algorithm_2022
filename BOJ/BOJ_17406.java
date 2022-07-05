package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17406 {

    static int N, M, K, minValueA;
    static int[][] arr;
    static int[][] info;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1][M+1];
        for(int r=1; r<=N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=1; c<=M; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        info = new int[K][3];
        for(int r=0; r<K; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<3; c++) {
                info[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        minValueA = Integer.MAX_VALUE;

        perm(new ArrayList<>(), new boolean[K]);

        System.out.println(minValueA);
    }

    public static int[][] copyArray(int[][] arr) {
        int[][] result = new int[N+1][M+1];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                result[i][j] = arr[i][j];
            }
        }
        return result;
    }

    public static int[][] spin(int[] info, int[][] arr) {
        int r = info[0];
        int c = info[1];
        int s = info[2];

        for(int i=1; i<=s; i++) {
            int temp = arr[r-i][c-i];

            for(int j=r-i; j<r+i; j++) {
                arr[j][c-i] = arr[j+1][c-i];
            }

            for(int j=c-i; j<c+i; j++) {
                arr[r+i][j] = arr[r+i][j+1];
            }

            for(int j=r+i; j>r-i; j--) {
                arr[j][c+i] = arr[j-1][c+i];
            }

            for(int j=c+i; j>c-i+1; j--) {
                arr[r-i][j] = arr[r-i][j-1];
            }
            arr[r-i][c-i+1] = temp;
        }

        return arr;
    }

    public static void printArray(int[][] arr) {
        for(int r=1; r<=N; r++) {
            for(int c=1; c<=M; c++) {
                System.out.print(arr[r][c]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int valueA(int[][] arr) {
        int min = Integer.MAX_VALUE;
        for(int r=1; r<=N; r++) {
            int temp = 0;
            for(int c=1; c<=M; c++) {
                temp += arr[r][c];
            }
            min = Math.min(min, temp);
        }
        return min;
    }

    public static void perm(List<Integer> choosed, boolean[] visited) {
        if(choosed.size() == K) {
            int[][] result = copyArray(arr);
            for(Integer num: choosed) {
                result = spin(info[num], result);
//                printArray(result);
            }

            minValueA = Math.min(minValueA, valueA(result));
            return;
        }

        for(int i=0; i<K; i++) {
            if(!visited[i]) {
                visited[i] = true;
                choosed.add(i);
                perm(choosed, visited);
                choosed.remove(Integer.valueOf(i));
                visited[i] = false;
            }
        }
    }
}
