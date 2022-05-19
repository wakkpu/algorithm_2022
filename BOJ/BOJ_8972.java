package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_8972 {

    static int[] dr = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1}; // 1 ~ 9
    static int[] dc = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    static int R, C;
    static char[][] map;
    static int[][] arduinoMap;
    static int[] moves;
    static boolean boom;
    static Pos I;

    static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int getDistance(Pos o) {
            return Math.abs(this.r - o.r) + Math.abs(this.c - o.c);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        Queue<Pos> rQueue = new LinkedList<>();

        map = new char[R][C];
        arduinoMap = new int[R][C];
        for(int r=0; r<R; r++) {
            String str = br.readLine();
            for(int c=0; c<C; c++) {
                map[r][c] = str.charAt(c);
                if(map[r][c] == 'I') {
                    I = new Pos(r, c);
                } else if(map[r][c] == 'R') {
                    rQueue.offer(new Pos(r, c));
                    arduinoMap[r][c]++;
                }
            }
        }

        String str = br.readLine();
        moves = new int[str.length()];
        for(int i=0; i<str.length(); i++) {
            moves[i] = str.charAt(i) - '0';
        }

        for(int i=0; i<moves.length; i++) {
            //System.out.println(i+": "+moves[i]);
            moveI(I, moves[i]);
            //printMap();
            //System.out.println();
            if(boom) {
                System.out.println("kraj "+(i+1));
                return;
            }
            moveR(rQueue);
            if(boom) {
                System.out.println("kraj "+(i+1));
                return;
            }
        }
        printMap();
    }

    public static void printMap() {
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                System.out.print(map[r][c]);
            }
            System.out.println();
        }
    }

    public static void moveI(Pos i, int d) {
        int sr = i.r;
        int sc = i.c;

        map[sr][sc] = '.';

        int nr = i.r+dr[d];
        int nc = i.c+dc[d];

        if(map[nr][nc] == 'R') { // 미친 아두이노에 박으면 게임 끝
            boom = true;
            return;
        }

        map[nr][nc] = 'I';
        I = new Pos(nr, nc);
    }

    public static void moveR(Queue<Pos> rQueue) {
        int[][] temp = new int[R][C];
        int rSize = rQueue.size();
        //System.out.println("rSize:"+rSize);
        while(rSize-->0) {
            Pos curr = rQueue.poll();

            int dir = 0;
            int minDistance = Integer.MAX_VALUE;
            for(int d=1; d<=9; d++) {
                int tempR = curr.r+dr[d];
                int tempC = curr.c+dc[d];
                int distance = I.getDistance(new Pos(tempR, tempC));

                if(distance < minDistance) {
                    minDistance = distance;
                    dir = d;
                }
            }

            int nr = curr.r+dr[dir];
            int nc = curr.c+dc[dir];

            if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

            temp[nr][nc]++;

        }

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(map[r][c] == 'I') {
                    if(temp[r][c] >= 1) {
                        boom = true;
                        return;
                    }
                } else {
                    if(temp[r][c] == 1) {
                        map[r][c] = 'R';
                        rQueue.offer(new Pos(r, c));
                    } else {
                        map[r][c] = '.';
                    }
                }
            }
        }

        //printMap();
        //System.out.println();
    }
}
