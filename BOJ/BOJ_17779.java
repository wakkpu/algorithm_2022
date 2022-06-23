package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17779 {

    static int N;
    static int[][] people;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        people = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                people[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int x=0; x<N; x++) {
            for(int y=0; y<N; y++) {
                for(int d1=1; d1<N; d1++) {
                    for(int d2=1; d2<N; d2++) {
                        if(x+d1+d2 >= N) continue;
                        if(y-d1 < 0 || y+d2 >= N) continue;

                        gerrymandering(x, y, d1, d2);
                    }
                }
            }
        }
        System.out.println(min);
    }

    public static void gerrymandering(int x, int y, int d1, int d2) {
        int[][] map = new int[N][N];

        // 경계선
        for(int i=0; i<=d1; i++) {
            map[x+i][y-i] = 5;
            map[x+d2+i][y+d2-i] = 5;
        }
        // 경계선
        for(int i=0; i<=d2; i++) {
            map[x+i][y+i] = 5;
            map[x+d1+i][y-d1+i] = 5;
        }
        // 1구역
        for(int i=0; i<x+d1; i++) {
            for(int j=0; j<=y; j++) {
                if(map[i][j] == 5) break;
                map[i][j] = 1;
            }
        }
        // 2구역
        for(int i=0; i<=x+d2; i++) {
            for(int j=N-1; j>y; j--) {
                if(map[i][j] == 5) break;
                map[i][j] = 2;
            }
        }
        // 3구역
        for(int i=x+d1; i<N; i++) {
            for(int j=0; j<y-d1+d2; j++) {
                if(map[i][j] == 5) break;
                map[i][j] = 3;
            }
        }
        // 4구역
        for(int i=x+d2+1; i<N; i++) {
            for(int j=N-1; j>=y-d1+d2; j--) {
                if(map[i][j] == 5) break;
                map[i][j] = 4;
            }
        }

        // 각 지역 인구수
        int[] nums = new int[6];
        for(int k=1; k<=5; k++) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] == k) {
                        nums[k] += people[i][j];
                    }
                    if(k == 5 && map[i][j] == 0) {
                        nums[k] += people[i][j];
                    }
                }
            }
        }

        // 최대, 최소값
        int tempMax = Integer.MIN_VALUE;
        int tempMin = Integer.MAX_VALUE;
        for(int i=1; i<=5; i++) {
            tempMax = Math.max(nums[i], tempMax);
            tempMin = Math.min(nums[i], tempMin);
        }

        min = Math.min(min, tempMax-tempMin);
    }
}
