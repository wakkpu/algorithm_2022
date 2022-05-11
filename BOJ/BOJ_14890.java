package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_14890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[N][N];
        int[][] map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for(int i=0; i<N; i++) { // 행
            boolean flag = true;
            for(int j=0; j<N-1; j++) {
                if(map[i][j] - map[i][j+1] == 1) { // 내리막
                    if(j + L > N-1) { // L칸 미만 남음
                        flag = false;
                        continue;
                    }
                    // 이미 경사로 있거나, 높이 차이 있으면 못 놓음
                    for(int k=1; k<=L; k++) {
                        if(visited[i][j+k] || map[i][j+k]+1 != map[i][j]) {
                            flag = false;
                            break;
                        }
                    }
                    // 경사로 가능
                    if(flag) {
                        for(int k=1; k<=L; k++) {
                            visited[i][j+k] = true;
                        }
                    }
                } else if(map[i][j] - map[i][j+1] == -1) { // 오르막
                    if(j - L + 1 < 0) { // L칸 미만 남음
                        flag = false;
                        continue;
                    }
                    // 이미 경사로 있거나, 높이 차이 있으면 못 놓음
                    for(int k=0; k<L; k++) {
                        if(visited[i][j-k] || map[i][j-k]+1 != map[i][j+1]) {
                            flag = false;
                            break;
                        }
                    }
                    // 경사로 가능
                    if(flag) {
                        for(int k=0; k<L; k++) {
                            visited[i][j-k] = true;
                        }
                    }
                } else if(map[i][j] - map[i][j+1] == 0) { // 평지

                } else { // 불가
                    flag = false;
                }
            }
            if(flag) count++;
        }
        visited = new boolean[N][N];
        for(int j=0; j<N; j++) { // 열
            boolean flag = true;
            for(int i=0; i<N-1; i++) {
                if(map[i][j] - map[i+1][j] == 1) { // 내리막
                    if(i + L > N-1) { // L칸 미만 남음
                        flag = false;
                        continue;
                    }
                    // 이미 경사로 있거나, 높이 차이 있으면 못 놓음
                    for(int k=1; k<=L; k++) {
                        if(visited[i+k][j] || map[i+k][j]+1 != map[i][j]) {
                            flag = false;
                            break;
                        }
                    }
                    // 경사로 가능
                    if(flag) {
                        for(int k=1; k<=L; k++) {
                            visited[i+k][j] = true;
                        }
                    }
                } else if(map[i][j] - map[i+1][j] == -1) { // 오르막
                    if(i - L + 1 < 0) { // L칸 미만 남음
                        flag = false;
                        continue;
                    }
                    // 이미 경사로 있거나, 높이 차이 있으면 못 놓음
                    for(int k=0; k<L; k++) {
                        if(visited[i-k][j] || map[i-k][j]+1 != map[i+1][j]) {
                            flag = false;
                            break;
                        }
                    }
                    // 경사로 가능
                    if(flag) {
                        for(int k=0; k<L; k++) {
                            visited[i-k][j] = true;
                        }
                    }
                } else if(map[i][j] - map[i+1][j] == 0) { // 평지

                } else { // 불가
                    flag = false;
                }
            }
            if(flag) count++;
        }

        System.out.println(count);
    }
}
