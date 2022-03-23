package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_19238 {

    static int N, M, F;
    static Taxi taxi;
    static char[][] map;
    static Customer[] customers;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    static class Taxi {
        int r;
        int c;
        int fuel;
        int dist;

        public Taxi(int r, int c, int fuel, int dist) {
            this.r = r;
            this.c = c;
            this.fuel = fuel;
            this.dist = dist;
        }

        // 도착하면 소모한 연료량 2배 충전
        public void charge() {
            this.fuel += 2*this.dist;
            this.dist = 0;
        }

        // (nr, nc)로 이동
        public void move(int nr, int nc) {
            this.r = nr;
            this.c = nc;
        }
    }

    static class Customer {
        int sr;
        int sc;
        int dr;
        int dc;

        public Customer(int sr, int sc, int dr, int dc) {
            this.sr = sr;
            this.sc = sc;
            this.dr = dr;
            this.dc = dc;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken()); // 초기 연료의 양. 초기 연료의 양 넘어서 무한히 충전 가능함.

        map = new char[N+1][N+1];
        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++) {
                map[i][j] = st.nextToken().charAt(0); // 벽은 1
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new Taxi(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), F, 0);

        customers = new Customer[M+1];
        for (int i=1; i<M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());
            int dr = Integer.parseInt(st.nextToken());
            int dc = Integer.parseInt(st.nextToken());
            customers[i] = new Customer(sr, sc, dr, dc);

            map[sr][sc] = '2'; // 승객은 2로 표시
        }

        int fuelLeft = taxi.fuel;
        for(int i=1; i<M+1; i++) {
            fuelLeft = moveTaxi();
            if(fuelLeft == -1) break;
        }
        System.out.println(fuelLeft);
    }

    // 택시 이동 후 남은 연료 반환
    public static int moveTaxi() {
        int[] closestCustomerInfo = findClosestCustomer();

        int closestCustomerNumber = closestCustomerInfo[0];
        int closestDistance = closestCustomerInfo[1];

        // 태울 수 있는 고객 없으므로 종료
        if(closestCustomerNumber == M+1) return -1;

        // 가장 가까운 고객
        Customer closestCustomer = customers[closestCustomerNumber];

        // 택시 위치에서 고객 위치로 이동, 고객 태웠음
        map[closestCustomer.sr][closestCustomer.sc] = '0';
        taxi.move(closestCustomer.sr, closestCustomer.sc);

        taxi.fuel -= closestDistance; // 연료 사용

        if(taxi.fuel < 0) return -1; // 연료 다 떨어졌나 확인

        // 택시의 위치부터 고객 목적지까지의 최단 거리
        int toDestination = bfs(taxi.r, taxi.c, closestCustomer.dr, closestCustomer.dc);

        // (고객을 태운 뒤 목적지까지의 거리)*2만큼 충전되므로 고객을 태울 때까지의 거리는 저장 X
        taxi.dist += toDestination;
        taxi.fuel -= toDestination;

        if(taxi.fuel < 0) return -1; // 연료 다 떨어졌나 확인

        // 도착했으면 택시 위치 갱신하고 연료 충전
        taxi.move(closestCustomer.dr, closestCustomer.dc);
        taxi.charge();

        return taxi.fuel;
    }

    // 가장 가까운 고객 번호와 그 고객까지의 거리 반환
    public static int[] findClosestCustomer() {
        int closestCustomer = 1;
        int minDistance = Integer.MAX_VALUE;
        for(int i=1; i<M+1; i++) {
            int r = customers[i].sr;
            int c = customers[i].sc;

            if(map[r][c] == '2') { // 아직 택시 안 탄 고객만
                int distance = bfs(r, c, taxi.r, taxi.c); // 고객부터 택시까지의 거리 계산

                // 태울 수 있는 고객 X
                if(distance == Integer.MAX_VALUE) {
                    return new int[] {M+1, Integer.MAX_VALUE};
                }

                if(distance < minDistance) { // 최단 거리 갱신
                    minDistance = distance;
                    closestCustomer = i;
                } else if(distance == minDistance) { // 거리 같으면
                    if(r < customers[closestCustomer].sr) { // 행 번호가 가장 작은 승객
                        closestCustomer = i;
                    } else if(r == customers[closestCustomer].sr) { // 행 번호도 같으면
                        if(c < customers[closestCustomer].sc) { // 열 번호가 가장 작은 승객
                            closestCustomer = i;
                        }
                    }
                }
            }
        }
        return new int[]{closestCustomer, minDistance};
    }

    // 고객에서 택시까지의 최단 거리 구하기
    public static int bfs(int r, int c, int targetR, int targetC) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][N+1];

        q.offer(new int[]{r, c, 0}); // 고객의 좌표와 이동 거리
        visited[r][c] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int sr = curr[0];
            int sc = curr[1];
            int dist = curr[2];

            if(sr == targetR && sc == targetC) { // 도착
                return dist;
            }

            for(int d=0; d<4; d++) {
                int nr = sr+di[d];
                int nc = sc+dj[d];

                if(nr < 1 || nr > N || nc < 1 || nc > N || visited[nr][nc]) continue;

                visited[nr][nc] = true;

                if(map[nr][nc] != '1') {
                    q.offer(new int[]{nr, nc, dist+1});
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}
