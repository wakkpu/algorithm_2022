package BOJ;

/*
 장애물 3개를 설치하여 모든 학생들이 선생님들의 감시를 피할 수 있는지 확인.
 => 조합으로 장애물 3개의 위치를 정한다
 => 선생님으로부터 학생들을 쳐다본다
 => 학생이 걸리면 그 경우의 탐색을 멈춘다

 // S: 학생, T: 선생, X: 빈칸, O: 기둥 */

import java.io.*;
import java.util.*;

public class BOJ_18428 {

    static int N;
    static String[][] map;
    static boolean[] candidates;
    static boolean finished;

    static List<int[]> teachers = new ArrayList<>();

    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new String[N][N];
        candidates = new boolean[N*N];
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++) {
                map[r][c] = st.nextToken();
                if(map[r][c].equals("T")) {
                    teachers.add(new int[]{r, c});

                    for(int d=0; d<4; d++) { // 선생이 볼 수 있는 곳만 탐색을 하면 됨
                        int nr = r+dr[d];
                        int nc = c+dc[d];

                        while(isIn(nr, nc)) {
                            candidates[nr*N+nc] = true;
                            nr += dr[d];
                            nc += dc[d];

                        }
                    }
                }
            }
        }

        bruteforce(0, 0);

        if(finished) System.out.println("YES");
        else System.out.println("NO");
    }

    public static void printMap() {
        System.out.println("-------------------");
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }

    public static void bruteforce(int idx, int depth) {
        if(finished) return;

        if(depth == 3) { // 기둥 위치 3개 정했음
            if(find()) { // 감시
                finished = true;
                return;
            }
        }

        for(int i=idx; i<N*N; i++) {
            if(candidates[i]) {
                int r = i/N;
                int c = i%N;
                if (map[r][c].equals("X")) { // 빈 자리라면
                    map[r][c] = "O";
                    bruteforce(i + 1, depth + 1);
                    map[r][c] = "X";
                }
            }
        }
    }

    // true이면 진행.
    public static boolean find() {
        // 모든 선생들이
        for(int[] teacher: teachers) {
            int r = teacher[0];
            int c = teacher[1];
            // 4방향에 학생이 있는지 확인.
            for(int d=0; d<4; d++) {
                // 선생 위치에서 시작
                int nr = r+dr[d];
                int nc = c+dc[d];
                while(isIn(nr, nc)) { // 맵 벗어날 때까지 그 방향으로 탐색
                    if(map[nr][nc].equals("S")) return false; // 학생 발견 시 false
                    if(map[nr][nc].equals("O")) break; // 기둥 너머는 볼 수 없음
                    nr += dr[d];
                    nc += dc[d];
                }
            }
        }

        // 4방향에 다 학생 없음.
        return true;
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
