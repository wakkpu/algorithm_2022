import java.io.*;
import java.util.*;

public class BOJ_14502 {

    static int N, M;
    static int[][] map;
    static int maxSafe = 0;
    static int wallCount = 0;
    static int[] di = {-1, 1, 0, 0}; // 상하좌우
    static int[] dj = {0, 0, -1, 1}; // 상하좌우
    static ArrayList<Point> virus;

    static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Point [" + "i=" + i + ", j=" + j + "]";
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        virus = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    wallCount++;
                } else if(map[i][j] == 2) {
                    virus.add(new Point(i, j));
                }
            }
        }
        // 1. 빈 곳(0)에서 임의로 세 지점 뽑아서 벽 세우기
        // 2. 바이러스에서 bfs해서 안전 영역 크기(0) 계산
        makeWalls(3);
        System.out.println(maxSafe);
    }

    // 벽 3개 지을거임
    public static void makeWalls(int toChoose) {
        if(toChoose == 0) { // 벽 다 세웠으면 바이러스 퍼뜨려봄
            spread();
            return;
        }

        // 모든 빈 칸에 대해서 조합
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    makeWalls(toChoose-1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void spread() {
        boolean[][] visited = new boolean[N][M];
        Queue<Point> q = new LinkedList<>();
        int infected = 0;

        // 모든 바이러스에 대해 BFS
        for(Point v: virus) {
            q.offer(v);

            while(!q.isEmpty()) {
                Point p = q.poll();

                for(int i=0; i<4; i++) {
                    int ni = p.i + di[i];
                    int nj = p.j + dj[i];

                    if(ni < 0 || ni > N-1 || nj < 0 || nj > M-1) continue;
                    if(map[ni][nj] == 0 && !visited[ni][nj]) {
                        visited[ni][nj] = true;
                        infected++;
                        q.offer(new Point(ni, nj));
                    }
                }
            }
        }

        // 안전 영역 = 전체 넓이 - (처음 기둥 갯수 + 3) - (초기 바이러스 갯수 + 추가된 바이러스 갯수)
        int safe = N*M - (wallCount+3) - (virus.size()+infected);

        // 최대 안전 영역 갱신
        maxSafe = Math.max(maxSafe, safe);
    }
}
