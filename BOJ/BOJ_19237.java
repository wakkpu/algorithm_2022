package BOJ;

/*
상어에는 서로 다른 1 이상 M 이하의 자연수 번호가 붙어 있다.
상어들은 영역을 사수하기 위해 다른 상어들을 쫓아냄. 번호가 작은게 이김.

N x N 크기의 격자 중 M개의 칸에 상어가 한 마리씩 들어있음.
맨 처음에는 모든 상어가 자신의 위치에 자신의 냄새를 뿌린다.
그 후 모든 상어가 동시로 상하좌우로 인접한 칸 중 하나로 이동하고,
자신의 냄새를 그 칸에 뿌린다. 냄새는 상어가 K번 이동하고 나면 사라진다.

각 상어가 이동 방향을 결정할 때는, 먼저 인접한 칸 중 아무 냄새가 없는 칸의 방향으로 잡는다.
그런 칸이 없으면 자신의 냄새가 있는 칸의 방향으로 잡는다. 이때 가능한 칸이 여러 개일 수 있는데,
그 경우에는 특정한 우선순위를 따른다. 우선순위는 상어마다 다를 수 있고, 같은 상어라도 현재
상어가 보고 있는 방향에 따라 또 다를 수 있다. 상어가 맨 처음 보고 있는 방향은 입력으로 주어지고,
그 후에는 방금 이동한 방향이 보고 있는 방향이 된다.

모든 상어가 이동한 후 한 칸에 여러 마리의 상어가 남아 있으면, 가장 작은 번호를 가진 상어를 제외하고
모두 격자 밖으로 쫓겨난다.
 */

/*
1, 2, 3, 4는 위, 아래, 왼쪽, 오른쪽
 */

import java.io.*;
import java.util.*;

public class BOJ_19237 {

    static class Shark {
        int r;
        int c;
        int num;

        int dir;

        int[][] prior;

        public Shark(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }

    static class Odor {
        int r;
        int c;

        int num;
        int validTime;

        public Odor(int r, int c, int num, int validTime) {
            this.r = r;
            this.c = c;
            this.num = num;
            this.validTime = validTime;
        }

        public boolean checkTime() {
            if(this.validTime > time) return false;
            return true;
        }
    }

    static int N, M, K;
    static int[][] map;

    static int time;

    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, -1, 1};

    static Shark[] sharks;
    static Queue<Odor> odors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 상어 정보 배열
        sharks = new Shark[M+1];

        // 상어 냄새 큐
        odors = new LinkedList<>();

        time = 0;

        map = new int[N][N];
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] != 0) {
                    sharks[map[r][c]] = new Shark(r, c, map[r][c]); // 상어 번호, 초기 위치
                    odors.offer(new Odor(r, c, map[r][c], K)); // 상어 초기 냄새
                }
            }
        }

        // 상어 초기 이동방향
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<M+1; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken());
            sharks[i].prior = new int[5][5];
        }

        for(int k=1; k<M+1; k++) { // 각 상어별 우선순위
            for(int i=1; i<4+1; i++) { // 상, 하, 좌, 우
                st = new StringTokenizer(br.readLine());
                for(int j=1; j<4+1; j++) { // 상, 하, 좌, 우 별 우선 순위
                    sharks[k].prior[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        while(!isEnd()) {

        }

    }

    // 끝나는 조건 확인
    public static boolean isEnd() {
        for(int i=2; i<M+1; i++) {
            if(sharks[i].r != -1 && sharks[i].c != -1) {
                return false;
            }
        }
        return true;
    }

    public static void moveShark() {

    }

}
