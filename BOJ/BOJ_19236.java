package BOJ;

import java.io.*;
import java.util.*;

/*
 * 4 x 4 크기의 공간이 있고, 크기가 1 x 1인 정사각형 칸으로 나누어져 있다.
 * 공간의 각 칸은 (x, y)와 같이 표현하며, x는 행의 번호, y는 열의 번호이다.
 *
 * 한 칸에는 물고기가 한 마리 존재한다. 각 물고기는 번호와 방향을 가지고 있다.
 * 번호는 1 ~ 16의 자연수이고, 모든 물고기는 다른 번호를 갖는다.
 * 방향은 8가지(상하좌우, 대각선) 중 하나이다.
 *
 * 청소년 상어는 (0, 0)에 있는 물고기를 먹고 (0, 0)에 들어가게 된다.
 * 상어의 방향은 (0, 0)에 있던 물고기의 방향과 같다. 이후 물고기가 이동한다.
 *
 * 물고기는 번호가 작은 물고기부터 순서대로 이동한다. 물고기는 한 칸을 이동할 수 있고,
 * 빈 칸과 다른 물고기가 있는 칸으로는 이동할 수 있고, 상어가 있거나 4 x 4의 경계를
 * 넘어가는 칸으로는 이동할 수 없다. 이동할 수 있는 칸을 향할 때까지 45도 반시계 회전한다.
 * 만약 이동할 수 있는 칸이 없으면 이동하지 않는다. 물고기가 다른 물고기가 있는 칸으로 이동할 때는
 * 서로의 위치를 바꾸는 방식으로 이동한다.
 *
 * 물고기의 이동이 모두 끝나면 상어가 이동한다. 상어는 방향에 있는 칸으로 이동할 수 있는데,
 * 한 번에 여러 개의 칸을 이동할 수 있다. 상어가 물고기가 있는 칸으로 이동했다면, 그 칸에 있는
 * 물고기를 먹고, 그 물고기의 방향을 가지게 된다. 이동하는 중에 지나가는 칸에 있는 물고기는
 * 먹지 않는다. 물고기가 없는 칸으로는 이동할 수 없다. 상어가 이동할 수 있는 칸이 없으면
 * 집에 간다. 상어가 이동한 후에는 다시 물고기가 이동하며, 이후 이 과정이 반복된다.
 *
 * 상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 출력한다.
 */
public class BOJ_19236 {
    static class Fish {
        int r;
        int c;
        int dir;

        public Fish(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dir=" + dir +
                    '}';
        }
    }

    static int N = 4;
    static int result = 0;
    static int[][] dirs = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 물고기의 번호가 key, 물고기의 위치와 방향 정보가 value
        TreeMap<Integer, Fish> fishes = new TreeMap<>();
        int[][] nums = new int[N][N];

        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                Fish fish = new Fish(r, c, dir-1);
                nums[r][c] = num;
                fishes.put(num, fish);
            }
        }

        int eaten = nums[0][0]; // (0, 0)에 있는 애 잡아먹고 시작함.
        nums[0][0] = -1; // 상어는 -1번 물고기

        Fish shark = new Fish(0, 0, fishes.get(eaten).dir); // 상어
        fishes.put(eaten, null); // (0, 0)에 있는 애 먹혔으니 제거

        simulate(nums, fishes, shark, eaten);

        System.out.println(result);
    }

    public static void simulate(int[][] nums, TreeMap<Integer, Fish> fishes, Fish shark, int eaten) {
        int[][] tempNums = new int[N][N];
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                tempNums[r][c] = nums[r][c];
            }
        }

        TreeMap<Integer, Fish> tempFishes = new TreeMap<>(fishes);
        for(Map.Entry<Integer, Fish> entry: fishes.entrySet()) {
            Integer i = entry.getKey();
            Fish f = entry.getValue();
            if(f == null) continue;
            tempFishes.put(i, new Fish(f.r, f.c, f.dir));
        }

        /*System.out.println("Before Fish Moves");
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                System.out.print(tempNums[r][c]+" ");
            }
            System.out.println();
        }*/

        // 물고기들 이동
        for(Map.Entry<Integer, Fish> entry: tempFishes.entrySet()) {
            Integer num = entry.getKey();
            Fish fish = entry.getValue();

            if(fish == null) continue; // 먹힌 애면 패스

            int sr = fish.r;
            int sc = fish.c;
            int sdir = fish.dir;

            // 한 바퀴 돌 때까지
            do {
                int nr = fish.r + dirs[fish.dir][0];
                int nc = fish.c + dirs[fish.dir][1];

                // 못 가는 곳이면 회전
                if(nr < 0 || nr >= N || nc < 0 || nc >= N || tempNums[nr][nc] == -1) {
                    fish.dir = (fish.dir+1) % 8;
                    continue;
                }

                // 가려고 하는 목적지에 있는 물고기
                int target = tempNums[nr][nc];
                Fish targetFish = tempFishes.get(target);

                if(target == 0) { // 가려는 곳에 물고기 없으면 그냥 감
                    tempFishes.put(num, new Fish(nr, nc, fish.dir)); // 자신 정보만 갱신
                } else { // 있으면 자리 바꿔야 됨
                    tempFishes.put(target, new Fish(sr, sc, targetFish.dir)); // 타겟 물고기를 자기 원래 자리로
                    tempFishes.put(num, new Fish(nr, nc, fish.dir)); // 자기는 타겟 물고기의 자리로
                }

                // 번호는 그냥 바꾸면 됨
                tempNums[nr][nc] = num;
                tempNums[sr][sc] = target;
                break;

            } while(sdir != fish.dir);
        }

        /*System.out.println("After Fish Moves");
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                System.out.print(tempNums[r][c]+" ");
            }
            System.out.println();
        }*/

        for(int d=1; d<N; d++) { // 상어는 멀리 가봐야 3칸
            int nr = shark.r + dirs[shark.dir][0] * d;
            int nc = shark.c + dirs[shark.dir][1] * d;

            if(nr < 0 || nr >= N || nc < 0 || nc >= N || tempNums[nr][nc] == 0) continue;

            // 잡아먹을 물고기
            int target = tempNums[nr][nc];
            Fish fish = tempFishes.get(target);

            // 잡아먹음
            tempFishes.put(target, null); // 먹혔으니 제거
            Fish tempShark = new Fish(nr, nc, fish.dir); // 상어 정보 갱신. 잡아먹으면 방향도 바뀜

            tempNums[nr][nc] = -1; // 상어 이동
            tempNums[shark.r][shark.c] = 0; // 이전 자리 비우기

            /*System.out.println("After Shark Moves");
            for(int r=0; r<N; r++) {
                for(int c=0; c<N; c++) {
                    System.out.print(tempNums[r][c]+" ");
                }
                System.out.println();
            }*/

            //System.out.println("Shark eaten: "+eaten);
            //System.out.println("Shark before eat: "+tempShark.toString());

            simulate(tempNums, tempFishes, tempShark, eaten + target);

            //System.out.println("Shark after eat: "+tempShark.toString());
            //System.out.println("Shark eaten backtrack: "+eaten);
            // 백트래킹
            tempNums[shark.r][shark.c] = -1; // 원래 자리로 돌아감
            tempNums[nr][nc] = target; // 먹은거 뱉음

            tempFishes.put(target, fish); // target 물고기 되돌리기
        }

        // 더이상 갈 곳 없으면 집 감.
        result = Math.max(result, eaten);
    }
}
