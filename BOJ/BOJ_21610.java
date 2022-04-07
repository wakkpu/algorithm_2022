package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_21610 {

    static int N, M;
    static int[][] water;
    static int[][] spells;
    static boolean[][] clouds;
    static boolean[][] disappear;

    // 방향은 1...8
    static int[][] vectors = {{}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};

    /*
    이동 명령은 방향 d와 거리 s오 이루어짐

    1. 모든 구름이 d 방향으로 s칸 이동함
    2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가
    3. 구름이 모두 사라짐
    4. 2에서 물이 증가한 칸 (r, c)에 물복사 마법. 물복사 마법을 사용하면 대각선 방향으로 거리가 1인 칸에
    물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물의 양이 증가.

    * 이 때는 경계를 넘어가는 칸은 대각선 안침.

    5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어듬. 이때 구름이 생기는 칸은
    3에서 구름이 사라진 칸이 아니어야 함.

    M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 구해보자.
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        disappear = new boolean[N][N];
        clouds = new boolean[N][N];
        water = new int[N][N];
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++) {
                water[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        spells = new int[M][2];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            spells[i][0] = Integer.parseInt(st.nextToken());
            spells[i][1] = Integer.parseInt(st.nextToken());
        }

        clouds[N-1][0] = true;
        clouds[N-1][1] = true;
        clouds[N-2][0] = true;
        clouds[N-2][1] = true;

        for(int[] spell: spells) {
            move(spell);
            rain();
            sunny();
            copyWater();
            makeClouds();
        }
        System.out.println(countWater());
    }

    // 1. 모든 구름이 d 방향으로 s칸 이동함
    public static void move(int[] spell) {
        int dir = spell[0];
        int dist = spell[1]%N;

        boolean[][] temp = new boolean[N][N];

        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(!clouds[r][c]) continue;
                int nr = r + dist*vectors[dir][0];
                int nc = c + dist*vectors[dir][1];

                if(nr >= N) {
                    nr -= N;
                } else if(nr < 0) {
                    nr += N;
                }

                if(nc >= N) {
                    nc -= N;
                } else if(nc < 0) {
                    nc += N;
                }

                temp[nr][nc] = true;
            }
        }
        clouds = temp;
    }

    // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가
    public static void rain() {
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(clouds[r][c]) {
                    water[r][c]++;
                }
            }
        }
    }

    // 3. 구름이 모두 사라짐
    public static void sunny() {
        disappear = clouds;
        clouds = new boolean[N][N];
    }

    // 4. 2에서 물이 증가한 칸 (r, c)에 물복사 마법. 물복사 마법을 사용하면 대각선 방향으로 거리가 1인 칸에
    //    물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물의 양이 증가.
    public static void copyWater() {
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(!disappear[r][c]) continue;

                int count = 0;
                for(int d=2; d<=8; d+=2) {
                    int nr = r + vectors[d][0];
                    int nc = c + vectors[d][1];

                    if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                    if(water[nr][nc]>=1) count++;
                }
                water[r][c] += count;
            }
        }
    }

    // 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어듬. 이때 구름이 생기는 칸은
    //    3에서 구름이 사라진 칸이 아니어야 함.
    public static void makeClouds() {
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(!disappear[r][c] && water[r][c]>=2) {
                    clouds[r][c] = true;
                    water[r][c] -= 2;
                }
            }
        }
    }

    public static int countWater() {
        int count = 0;
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                count += water[r][c];
            }
        }
        return count;
    }
}
