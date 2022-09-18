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

    static int N, M, K;
    static Odor[][] odorMap;
    static PriorityQueue[][] sharkMap; // 위치 관리, 위치 겹치면 쉽게 처리하기 위해 pq
    static Map<Integer, Shark> sharkTable; // 정보 관리
    static int currentTime;

    static int[][] dirs = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static class Shark {
        int r;
        int c;
        int dir;
        int[][] prior;

        public Shark(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dir=" + dir +
                    '}';
        }

        public void setDir(int dir) {
            this.dir = dir;
        }

        public void setPrior(int[][] prior) {
            this.prior = prior;
        }
    }

    static class Odor {
        int num; // 어느 상어의 냄새인지
        int validTime; // validTime까지 유효하다는 의미

        public Odor(int num, int validTime) {
            this.num = num;
            this.validTime = validTime;
        }

        public boolean isValid(int currentTime) {
            if(this.validTime <= currentTime) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    public static void printSharkTable() {
        for(Map.Entry<Integer, Shark> entry: sharkTable.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

    public static void printOdorMap() {
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(odorMap[r][c] == null) {
                    System.out.print("0 ");
                } else {
                    System.out.print(odorMap[r][c].num+" ");
                }
            }
            System.out.println();
        }
    }

    public static void printSharkMap() {
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(sharkMap[r][c].isEmpty()) {
                    System.out.print("0 ");
                } else {
                    System.out.print(sharkMap[r][c].peek()+" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(N == 1) {
            System.out.println(-1);
            return;
        }

        currentTime = 0;

        sharkTable = new HashMap<>();

        odorMap = new Odor[N][N];

        sharkMap = new PriorityQueue[N][N];
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                sharkMap[r][c] = new PriorityQueue<>();
            }
        }

        // 상어 초기 위치
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++) {
                int num = Integer.parseInt(st.nextToken());
                if(num != 0) {
                    sharkMap[r][c].add(num);
                    odorMap[r][c] = new Odor(num, currentTime+K);
                    sharkTable.put(num, new Shark(r, c));
                }
            }
        }

        // 상어 초기 방향
        st = new StringTokenizer(br.readLine());
        for(int k=1; k<=M; k++) {
            sharkTable.get(k).setDir(Integer.parseInt(st.nextToken()));
        }

        // 상어 방향 우선순위
        for(int k=1; k<=M; k++) {
            int[][] prior = new int[5][5];
            for(int i=1; i<=4; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=1; j<=4; j++) {
                    prior[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sharkTable.get(k).setPrior(prior);
        }

        while(currentTime <= 1000) {
//            System.out.println("current time: "+currentTime);
//            System.out.println("-------- shark info --------");
//            printSharkTable();
//            System.out.println("-------- shark map --------");
//            printSharkMap();
//            System.out.println("-------- odor map --------");
//            printOdorMap();

            currentTime++;

            // 끝났는지(1번 상어 말고 모두 없어졌는지) 확인
            boolean isEnd = true;
            for(Map.Entry<Integer, Shark> entry: sharkTable.entrySet()) {
                if (entry.getKey() > 1 && (entry.getValue().r != -1 && entry.getValue().c != -1)) {
                    isEnd = false;
                    break;
                }
            }
            if(isEnd) {
                System.out.println(currentTime-1);
                return;
            }

            PriorityQueue[][] copyShark = new PriorityQueue[N][N];
            for(int r=0; r<N; r++) {
                for(int c=0; c<N; c++) {
                    copyShark[r][c] = new PriorityQueue<>();
                }
            }

            Odor[][] copyOdor = new Odor[N][N];
            for(int r=0; r<N; r++) {
                for(int c=0; c<N; c++) {
                    copyOdor[r][c] = odorMap[r][c];
                }
            }

            // sharkMap 돌면서 상어 정보 갱신
            for(int r=0; r<N; r++) {
                for(int c=0; c<N; c++) {
                    if(sharkMap[r][c].size() >= 1) { // 상어가 있는 칸만
                        int alive = (int) sharkMap[r][c].poll(); // 가장 번호가 작은 한 마리만 살아남음

                        Shark shark = sharkTable.get(alive);
                        int nr = r;
                        int nc = c;
                        int dir = 0;
                        boolean isEmpty = false;
                        for(int d=1; d<=4; d++) { // 현재 위치에서 상어가 바라보는 방향을 기준으로 우선 순위 확인
                            if(!isIn(nr+dirs[shark.prior[shark.dir][d]][0], nc+dirs[shark.prior[shark.dir][d]][1])) continue;
                            if(odorMap[nr+dirs[shark.prior[shark.dir][d]][0]][nc+dirs[shark.prior[shark.dir][d]][1]] == null) { // 빈 곳 있으면
                                nr += dirs[shark.prior[shark.dir][d]][0]; // 위치 변경
                                nc += dirs[shark.prior[shark.dir][d]][1]; // 위치 변경
                                dir = shark.prior[shark.dir][d]; // 방향 변경
                                isEmpty = true;
                                break;
                            }
                        }

                        if(isEmpty) { // 빈 곳이 있다면 거기에 냄새 뿌리고 넘어감
                            sharkTable.put(alive, new Shark(nr, nc));
                            sharkTable.get(alive).setPrior(shark.prior);
                            sharkTable.get(alive).setDir(dir);
                            copyShark[nr][nc].add(alive);
                        } else { // 빈 곳이 없다면 자신의 냄새가 있는 곳 찾아야 함
                            for(int d=1; d<=4; d++) {
                                if(!isIn(nr+dirs[shark.prior[shark.dir][d]][0], nc+dirs[shark.prior[shark.dir][d]][1])) continue;
                                if(odorMap[nr+dirs[shark.prior[shark.dir][d]][0]][nc+dirs[shark.prior[shark.dir][d]][1]].num == alive) { // 자신의 냄새가 있으면
                                    nr += dirs[shark.prior[shark.dir][d]][0]; // 위치 변경
                                    nc += dirs[shark.prior[shark.dir][d]][1]; // 위치 변경
                                    dir = shark.prior[shark.dir][d]; // 방향 변경
                                    break;
                                }
                            }
                            sharkTable.put(alive, new Shark(nr, nc));
                            sharkTable.get(alive).setPrior(shark.prior);
                            sharkTable.get(alive).setDir(dir);
                            copyShark[nr][nc].add(alive);
                        }
                    }
                }
            }

            // 상어 정보 갱신
            for(int r=0; r<N; r++) {
                for(int c=0; c<N; c++) {
                    if(copyShark[r][c].size() >= 1) {
                        int alive = (int) copyShark[r][c].poll();
                        sharkMap[r][c].add(alive);
                        copyOdor[r][c] = new Odor(alive, currentTime+K);
                    }
                    while(!copyShark[r][c].isEmpty()) {
                        sharkTable.put((int) copyShark[r][c].poll(), new Shark(-1, -1));
                    }
                }
            }

            // 냄새 유효 시간 확인해서 유효 시간 끝났으면 삭제
            for(int r=0; r<N; r++) {
                for(int c=0; c<N; c++) {
                    if(copyOdor[r][c] != null && !copyOdor[r][c].isValid(currentTime)) copyOdor[r][c] = null;
                }
            }

            // 냄새 정보 갱신
            for(int r=0; r<N; r++) {
                for(int c=0; c<N; c++) {
                    odorMap[r][c] = copyOdor[r][c];
                }
            }
        }

        System.out.println(-1);
    }
}
