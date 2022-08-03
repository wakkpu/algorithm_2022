package BOJ;

import java.io.*;

public class BOJ_9207 {

    static int C = 9;
    static int R = 5;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N, minJump, minCount, check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        check = 0;
        while(N-->0) {
            minCount = 0;
            minJump = 0; // 아예 이동을 못하는 경우도 있으니 minJump는 0으로 초기화해야함. 최소래서 INF로 했다가 계속 틀림ㅠ
            int count = 0;
            char[][] board = new char[R][C];
            boolean[][] visited = new boolean[R][C];
            for (int r = 0; r < R; r++) {
                String str = br.readLine();
                for (int c = 0; c < C; c++) {
                    board[r][c] = str.charAt(c);
                    if (board[r][c] == 'o') {
                        minCount++;
                        count++;
                        visited[r][c] = true;
                    }
                }
            }
            if(N >= 1) br.readLine();

            solitaire(board, 0, count);
            bw.write(minCount+" "+minJump+"\n");
        }
        bw.flush();
    }

    public static void solitaire(char[][] currentBoard, int currentJump, int currentCount) {
        if(currentCount == 1) {
            minCount = currentCount;
            minJump = currentJump;
            return;
        }

        if(currentCount < minCount) { // 현재 핀 갯수가 최소라면
            minJump = currentJump; // 최소 이동 횟수 기록
            minCount = currentCount; // 핀 갯수 갱신
        }


        // 모든 지점 탐색
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(currentBoard[r][c] == 'o') { // [r][c]에 핀이 있으면
                    for(int d=0; d<4; d++) { // 사방 탐색
                        // 인접 지점
                        int nr1 = r+dr[d];
                        int nc1 = c+dc[d];
                        // 건너편 지점
                        int nr2 = r+2*dr[d];
                        int nc2 = c+2*dc[d];

                        // 보드 밖이면 무시
                        if(!isIn(nr1, nc1) || !isIn(nr2, nc2)) continue;

                        // 인접한 곳에 핀이 있고, 건너편은 비어있어야 함
                        if(currentBoard[nr1][nc1] == 'o' && currentBoard[nr2][nc2] == '.') {
                            currentBoard[r][c] = '.'; // 현재 핀 출발
                            currentBoard[nr1][nc1] = '.'; // 인접 핀 제거
                            currentBoard[nr2][nc2] = 'o'; // 현재 핀 도착
                            solitaire(currentBoard, currentJump+1, currentCount-1);
                            currentBoard[r][c] = 'o'; // 현재 핀 원상복구
                            currentBoard[nr1][nc1] = 'o'; // 인접 핀 원상복구
                            currentBoard[nr2][nc2] = '.'; // 현재 핀 원상복구
                        }
                    }
                }
            }
        }
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    public static void printMap(char[][] map) {
        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                System.out.print(map[r][c]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
