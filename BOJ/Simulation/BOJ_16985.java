package BOJ.Simulation;

import java.io.*;
import java.util.*;

public class BOJ_16985 {

    static int N = 5;
    static int minMove = Integer.MAX_VALUE;
    static int[][][] boards;
    static int[][][] copiedBoards;
    static int[] dk = {1, -1, 0, 0, 0, 0};
    static int[] di = {0, 0, 1, -1, 0, 0};
    static int[] dj = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1: 갈 수 있는 곳, 0: 갈 수 없는 곳
        boards = new int[N][N][N];
        for(int k=0; k<N; k++) {
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    boards[k][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        makeOrder(new ArrayList<>(), new boolean[N]);

        if(minMove == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minMove);
        }
    }

    // 1. 보드의 순서를 결정한다.
    public static void makeOrder(List<Integer> order, boolean[] visited) {
        if(order.size() == N) {
            // 2. 보드의 순서를 다 결정했다면
            copiedBoards = copyBoards(order);
            // 3. 각 보드를 몇 번 돌릴지 계산해서
            rotateCounts(new ArrayList<>());
            return;
        }

        for(int i=0; i<N; i++) { // 순열
            if(!visited[i]) {
                visited[i] = true;
                order.add(i);
                makeOrder(order, visited);
                order.remove(order.size()-1);
                visited[i] = false;
            }
        }
    }

    // 2. 보드의 순서를 다 결정했다면 정해진 순서대로 보드를 옮긴다
    public static int[][][] copyBoards(List<Integer> order) {
        int[][][] result = new int[N][N][N];
        int k = 0;
        for(Integer num: order) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    result[k][i][j] = boards[num][i][j];
                }
            }
            k++;
        }
        return result;
    }

    // 3. 각 보드를 몇 번 돌릴지 계산해서
    public static void rotateCounts(List<Integer> order) {
        if(order.size() == N) {
            rotateBoards(order); // 4. 보드를 회전시킨다
            // 5. 미로를 탐색한다.
            bfs(new int[]{0, 0, 0}, new int[]{4, 4, 4});
            bfs(new int[]{0, 0, 4}, new int[]{4, 4, 0});
            bfs(new int[]{0, 4, 0}, new int[]{4, 0, 4});
            bfs(new int[]{0, 4, 4}, new int[]{4, 0, 0});
            return;
        }

        for(int i=0; i<N-1; i++) { // 중복순열
            order.add(i);
            rotateCounts(order);
            order.remove(order.size()-1);
        }
    }

    // 4. 계산한 보드의 회전수대로 보드를 회전한다
    public static void rotateBoards(List<Integer> counts) {
        int k=0;
        for(Integer num: counts) {
            for(int rotate=0; rotate<num; rotate++) {
                copiedBoards[k] = rotateBoard(copiedBoards[k]);
            }
            k++;
        }
    }

    // 5. 미로 탐색
    public static void bfs(int[] start, int[] end) {
        if(copiedBoards[start[0]][start[1]][start[2]] == 0 || copiedBoards[end[0]][end[1]][end[2]] == 0) return;

        boolean[][][] visited = new boolean[N][N][N];
        visited[start[0]][start[1]][start[2]] = true;

        Queue<int[]> q = new LinkedList<>();
        q.offer(start);

        int move = 0;
        while(!q.isEmpty()) {
            int qSize = q.size();

            while(qSize-->0) {
                int[] curr = q.poll();

                if(curr[0] == end[0] && curr[1] == end[1] && curr[2] == end[2]) {
                    minMove = Math.min(minMove, move);
                    return;
                }

                for(int d=0; d<6; d++) {
                    int nk = curr[0]+dk[d];
                    int ni = curr[1]+di[d];
                    int nj = curr[2]+dj[d];

                    if(isIn(nk, ni, nj) && !visited[nk][ni][nj] && copiedBoards[nk][ni][nj] == 1) {
                        visited[nk][ni][nj] = true;
                        q.offer(new int[]{nk, ni, nj});
                    }
                }
            }
            move++;
        }
    }

    public static int[][] rotateBoard(int[][] board) {
        int[][] result = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                result[i][j] = board[N-1-j][i];
            }
        }
        return result;
    }

    public static boolean isIn(int k, int i, int j) {
        return (0 <= k && k < N) && (0 <= i && i < N) && (0 <= j && j < N);
    }

    public static void printBoard(int k) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(copiedBoards[k][i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
