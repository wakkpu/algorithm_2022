package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17472 {

    static int N, M;
    static int[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static PriorityQueue<Bridge> pq;
    static int numIsland;
    static int[] rep;

    static class Bridge implements Comparable<Bridge>{
        int from;
        int to;
        int len;

        public Bridge(int from, int to, int len) {
            this.from = from;
            this.to = to;
            this.len = len;
        }

        @Override
        public int compareTo(Bridge o) {
            return this.len - o.len;
        }
    }

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

        // 섬이 1로 입력되므로 -1, -2, -3, ...로 numbering.
        int islandNumber = -1;

        // 1. 섬을 구분
        // 완전 탐색하다가 아직 탐색, 넘버링하지 않은 섬이 있다면 BFS로 넘버링.
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 1) {
                    islandNumbering(i, j, islandNumber);
                    islandNumber--;
                }
            }
        }

        pq = new PriorityQueue<>();

        // 2. 다리 짓기
        //
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] != 0) {
                    buildingBridge(i, j, map[i][j]);
                }
            }
        }

        numIsland = -1 * islandNumber;

        // 3. 섬이 모두 연결가능한지, 섬을 모두 연결하는 최단 경로의 길이 확인
        // 크루스칼 사용하기 위해 union, find 구현
        // union, find 사용하기 위해 rep 구현
        rep = new int[numIsland+1];
        for(int i=1; i<=numIsland; i++) {
            rep[i] = i;
        }

        int shortestPath = Kruskal();
        System.out.println(shortestPath);

    }

    // 섬 구분
    public static void islandNumbering(int si, int sj, int islandNumber) {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[] {si, sj});
        map[si][sj] = islandNumber; // map의 값으로 탐색할지 말지 결정되므로 visited 없어도 됨

        // 한 번에 주변 4방향을 BFS.
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for(int d=0; d<4; d++) {
                int ni = curr[0] + di[d];
                int nj = curr[1] + dj[d];

                if(ni < 0 || ni > N-1 || nj < 0 || nj > M-1) {
                    continue;
                }

                if(map[ni][nj] == 1) {
                    map[ni][nj] = islandNumber;
                    q.offer(new int[] {ni, nj});
                }
            }
        }
    }

    // 다리 짓기
    public static void buildingBridge(int si, int sj, int num) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        // 한 번에 주변 한 방향만 쭉 탐색.
        for(int d=0; d<4; d++) {
            q.offer(new int[] {si, sj, 0});
            visited[si][sj] = true;

            while(!q.isEmpty()) {
                int[] p = q.poll();
                int ni = p[0] + di[d];
                int nj = p[1] + dj[d];
                int len = p[2];

                if(ni < 0 || ni > N-1 || nj < 0 || nj > M-1 || visited[ni][nj]) {
                    continue;
                }

                if(map[ni][nj] != num) { // 같은 섬 내에서는 탐색 X
                    if(map[ni][nj] != 0) { // 다른 섬에 도착
                        int from = -1 * num;
                        int to = -1 * map[ni][nj];

                        if(len >= 2) { // 이 때 다리의 길이가 2 이상이면 다리 지을 수 있음
                            pq.offer(new Bridge(from, to, len)); // 완성된 다리는 pq로.
                            break; // 다리 지었으니까 이쪽 방향으로는 더 이상 탐색 X
                        }
                    } else { // 아직 바다
                        visited[ni][nj] = true;
                        q.offer(new int[] {ni, nj, len+1});
                    }
                }
            }
            q.clear();
        }
    }

    public static int Kruskal() {
        int sum = 0; // 다리 길이 합
        int numBridge = 0; // 다리 갯수
        int size = pq.size(); // 초기 pq 크기

        for(int i=0; i<size; i++) { // 초기 pq 크기 만큼 반복
            Bridge curr = pq.poll();
            int a = curr.from;
            int b = curr.to;

            // 섬 연결
            if(find(a) != find(b)) {
                sum += curr.len;
                union(a, b);
                numBridge++;
            }
        }

        // N-1개의 간선이 선택됐다면 모두 연결 가능한 것
        if(numBridge == numIsland-2) {
            return sum;
        } else {
            return -1;
        }
    }

    public static int find(int x) {
        if(x == rep[x]) return x;
        else return rep[x] = find(rep[x]);
    }

    public static void union(int x, int y) {
        if(find(x) == find(y)) return;
        else rep[find(x)] = find(y);
    }
}
