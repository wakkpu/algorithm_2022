package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18808 {

    static int N, M, K;
    static int[][][] stickers;
    static int[][] laptop;
    static int area = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        laptop = new int[N][M];

        K = Integer.parseInt(st.nextToken());

        stickers = new int[K][][];
        for(int k=0; k<K; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            stickers[k] = new int[r][c];
            for(int i=0; i<r; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<c; j++) {
                    stickers[k][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        for(int k=0; k<K; k++) { // k번째 스티커에 대해
            for(int c=0; c<4; c++) { // 3번 시도해서 안되면 버림
                if(!attach(stickers[k])) { // 스티커 못 붙이면 90도 돌림
                    stickers[k] = rotate(stickers[k]);
                } else { // 붙였으면 종료
                    break;
                }
            }
        }
        System.out.println(area);
    }

    public static int[][] rotate(int[][] sticker) {
        int N = sticker.length;
        int M = sticker[0].length;

        int[][] temp = new int[M][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                temp[j][N-1-i] = sticker[i][j];
            }
        }

        return temp;
    }

    public static boolean attach(int[][] sticker) {

        int R = sticker.length;
        int C = sticker[0].length;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(i+R > N || j+C > M) continue; // 노트북 사이즈 넘어가면 안됨

                boolean flag = true; // 붙일 수 있나 확인
                OUT:
                for(int r=0; r<R; r++) {
                    for(int c=0; c<C; c++) {
                        if(laptop[i+r][j+c]==1 && sticker[r][c]==1) { // 다른 스티커랑 겹치면 안됨
                            flag = false;
                            break OUT;
                        }
                    }
                }

                // 안 겹치니까 붙이면 됨
                if(flag) {
                    for(int r=0; r<R; r++) {
                        for(int c=0; c<C; c++) {
                            if(sticker[r][c]==1) {
                                laptop[i+r][j+c] = sticker[r][c];
                                area++;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
