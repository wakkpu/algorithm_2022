package BOJ;

import java.io.*;
import java.util.*;

/**
 * N x N 체스판에서 말 K개 말은 원판 모양. 말 위에 다른 말 올릴 수 있음.
 * 각 칸은 흰색, 빨간색, 파란색으로 색칠.
 *
 * 말은 1번부터 K번까지 번호가 매겨져 있고, 이동방향도 미리 정해져 있다.
 *
 * 턴 한 번은 1번 말부터 K번 말까지 순서대로 이동시키는 것.
 * 한 말이 이동할 때 위에 올려져 있는 말도 함께 이동한다.
 * 말의 이동 방향에 있는 칸에 따라서 말의 이동이 달라짐.
 *
 * 말이 4개 이상 쌓이면 게임 종료.
 *
 * A번 말이 이동하려는 칸이 흰색인 경우에는 그 칸으로 이동.
 * 이동하려는 칸에 말이 이미 있는 경우는 가장 위에 A번 말을 올려 놓음.
 * A번 말 위에 다른 말이 있는 경우에는 A번 말과 위에 있는 모든 말이 이동함.
 *
 * A번 말이 이동하려는 칸이 빨간색인 경우에는 이동한 후에 A번 말과 그 위에 있는 모든 말의 쌓여 있는 순서를 반대로 바꾼다.
 *
 * A번 말이 이동하려는 칸이 파란색인 경우에는 A번 말의 이동 방향을 반대로 하고 한 칸 이동한다.
 * 방향을 반대로 바꾼 후에 이동하려는 칸이 파란색인 경우에는 이동하지 않고 가만히 있는다.
 *
 * 체스판을 벗어나는 경우에는 파란색과 같은 경우이다.
 */
public class BOJ_17837 {

    static int white = 0, red = 1, blue = 2;
    static int N, K;
    static int[] dr = new int[] {0, 0, 0, -1, 1};
    static int[] dc = new int[] {0, 1, -1, 0, 0};

    static int[][] color;
    static int count = 0;
    static Stack<Integer>[][] map;
    static Pawn[] pawns;

    static class Pawn {
        int r;
        int c;
        int dir;

        public Pawn(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Stack[N+1][N+1];
        for(int r=0; r<=N; r++) {
            for(int c=0; c<=N; c++) {
                map[r][c] = new Stack<>();
            }
        }

        color = new int[N+1][N+1];
        for(int r=1; r<=N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=1; c<=N; c++) {
                color[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        pawns = new Pawn[K+1];
        for(int i=1; i<=K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            pawns[i] = new Pawn(r, c, dir);
            map[r][c].push(i);
        }

        while(count <= 1000) {
            for(int i=1; i<=K; i++) {
                if(move(i)) count++;
                else {
                    System.out.println(count);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    public static boolean move(int i) {
        Pawn pawn = pawns[i];
        int r = pawn.r;
        int c = pawn.c;
        int dir = pawn.dir;

        int nr = r+dr[dir];
        int nc = c+dc[dir];

        // 맵 바깥 or 목적지 파란색
        if(nr <= 0 || nr > N || nc <= 0 || nc > N || color[nr][nc] == blue) {
            blue(i, r, c, r-dr[dir], c-dc[dir]);
            return true;
        } else { // 맵 안쪽
            if(color[nr][nc] == red) { // 목적지 빨간색
                return red(i, r, c, nr, nc);
            } else { // 목적지 흰색
                return white(i, r, c, nr, nc);
            }
        }
    }

    public static boolean white(int i, int r, int c, int nr, int nc) {
        Stack<Integer> temp = new Stack<>();

        while(!map[r][c].peek().equals(i)) {
            temp.push(map[r][c].pop());
        }
        temp.push(map[r][c].pop());

        while(!temp.isEmpty()) {
            Integer p = temp.pop();
            map[nr][nc].push(p);
            pawns[p].r = nr;
            pawns[p].c = nc;
        }

        return map[nr][nc].size() < 4;
    }

    public static boolean red(int i, int r, int c, int nr, int nc) {
        while(!map[r][c].peek().equals(i)) {
            Integer p = map[r][c].pop();
            map[nr][nc].push(p);
            pawns[p].r = nr;
            pawns[p].c = nc;
        }

        Integer p = map[r][c].pop();
        map[nr][nc].push(p);
        pawns[p].r = nr;
        pawns[p].c = nc;

        return map[nr][nc].size() < 4;
    }

    public static void blue(int i, int r, int c, int nr, int nc) {
        Stack<Integer> temp = new Stack<>();

        while(!map[r][c].peek().equals(i)) {
            temp.push(map[r][c].pop());
        }

        Integer num = map[r][c].pop();
        Pawn pawn = pawns[num];
        if(nr <= 0 || nr > N || nc <= 0 || nc > N || color[nr][nc] == blue) {
            if(pawn.dir % 2 == 0) pawn.dir--;
            else pawn.dir++;
            map[r][c].push(num);
            while(!temp.isEmpty()) {
                Integer p = temp.pop();
                map[r][c].push(p);
                pawns[p].r = nr;
                pawns[p].c = nc;
            }
        } else {
            if(pawn.dir % 2 == 0) pawn.dir--;
            else pawn.dir++;
            pawn.r = nr;
            pawn.c = nc;
            map[nr][nc].push(num);
            while(!temp.isEmpty()) {
                Integer p = temp.pop();
                map[nr][nc].push(p);
                pawns[p].r = nr;
                pawns[p].c = nc;
            }
        }
    }
}
