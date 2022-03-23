package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] stickers = new int[][][] {
                {{1, 0, 0, 0},
                 {1, 0, 0, 0},
                 {1, 0, 0, 0},
                 {1, 0, 0, 0}},
                {{1, 1},
                 {1, 1}},
                {{0, 1, 0},
                 {0, 1, 0},
                 {1, 1, 0}},
                {{0, 0, 1},
                 {0, 0, 1},
                 {0, 1, 1}},
                {{0, 1, 0},
                 {0, 1, 0},
                 {0, 1, 1}},
                {{1, 0, 0},
                 {1, 0, 0},
                 {1, 1, 0}},
                {{1, 0, 0},
                 {1, 1, 0},
                 {0, 1, 0}},
                {{0, 0, 1},
                 {0, 1, 1},
                 {0, 1, 0}},
                {{1, 0, 0},
                 {1, 1, 0},
                 {1, 0, 0}},
                {{0, 1, 0},
                 {1, 1, 0},
                 {0, 1, 0}}
        };

        int area = 0;

        for(int[][] sticker: stickers) {
            for(int r=0; r<4; r++) {
                sticker = rotate(sticker);
                area = Math.max(area, attach(sticker));

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

    public static int attach(int[][] sticker) {

        int R = sticker.length;
        int C = sticker[0].length;

        int tempMax = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(i+R > N || j+C > M) continue;

                int temp = 0;

                for(int r=0; r<R; r++) {
                    for(int c=0; c<C; c++) {
                        if(sticker[r][c]==1) {
                            temp += map[i+r][j+c];
                        }
                    }
                }

                tempMax = Math.max(tempMax, temp);
            }
        }
        return tempMax;
    }
}
