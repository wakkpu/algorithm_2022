import java.io.*;
import java.util.*;

public class BOJ_17070 {

    static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static Pos[] pipe;
    static int N;
    static int[][] map;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        pipe = new Pos[] {new Pos(1, 1), new Pos(1, 2)};

        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move(pipe);
        System.out.println(count);
    }

    public static void move(Pos[] pipe) {

        Pos tail = pipe[0];
        Pos head = pipe[1];

        // 도착
        if((head.r == N && head.c == N) || (tail.r == N && tail.c == N)) {
            count++;
            return;
        }

        // 가로일 때
        if(head.r == tail.r && head.c == tail.c+1) {
            // 오른쪽 이동이 가능하다면
            if (head.c + 1 <= N && map[head.r][head.c + 1] == 0) {
                move(new Pos[]{new Pos(head.r, head.c), new Pos(head.r, head.c+1)});
            }

            // 대각선 이동이 가능하다면
            if(head.c+1 <= N && map[head.r][head.c+1] == 0 &&
               head.r+1 <= N && head.c+1 <= N && map[head.r+1][head.c+1] == 0 &&
               head.r+1 <= N && map[head.r+1][head.c] == 0) {
                move(new Pos[]{new Pos(head.r, head.c), new Pos(head.r+1, head.c+1)});
            }
        }

        // 세로일 때
        if(tail.r+1 == head.r && head.c == tail.c) {
            // 대각선 이동이 가능하다면
            if(head.c+1 <= N && map[head.r][head.c+1] == 0 &&
               head.r+1 <= N && head.c+1 <= N && map[head.r+1][head.c+1] == 0 &&
               head.r+1 <= N && map[head.r+1][head.c] == 0) {
                move(new Pos[]{new Pos(head.r, head.c), new Pos(head.r+1, head.c+1)});
            }

            // 아래 이동이 가능하다면
            if(head.r+1 <= N && map[head.r+1][head.c] == 0) {
                move(new Pos[]{new Pos(head.r, head.c), new Pos(head.r+1, head.c)});
            }
        }

        // 대각선일 때
        if(tail.r+1 == head.r && tail.c+1 == head.c) {
            // 오른쪽 이동이 가능하다면
            if (head.c + 1 <= N && map[head.r][head.c + 1] == 0) {
                move(new Pos[]{new Pos(head.r, head.c), new Pos(head.r, head.c+1)});
            }

            // 대각선 이동이 가능하다면
            if(head.c+1 <= N && map[head.r][head.c+1] == 0 &&
               head.r+1 <= N && head.c+1 <= N && map[head.r+1][head.c+1] == 0 &&
               head.r+1 <= N && map[head.r+1][head.c] == 0) {
                move(new Pos[]{new Pos(head.r, head.c), new Pos(head.r+1, head.c+1)});
            }

            // 아래 이동이 가능하다면
            if(head.r+1 <= N && map[head.r+1][head.c] == 0) {
                move(new Pos[]{new Pos(head.r, head.c), new Pos(head.r+1, head.c)});
            }
        }
    }
}
