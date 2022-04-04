package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_16235 {

    static class Tree implements Comparable<Tree> {
        int r;
        int c;
        int age;

        public Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "r=" + r +
                    ", c=" + c +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    static int N, M, K;
    static int[][] map; // 양분 정보
    static int[][] A;
    static PriorityQueue<Tree> trees;
    static ArrayList<Tree> alive;
    static ArrayList<Tree> dead;
    static ArrayList<Tree> babies;
    static ArrayList<Tree> parents;

    // 8방 탐색 (좌측 상단부터 시계 방향)
    static int[] dr = new int[] {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = new int[] {-1, 0, 1, 1, 1, 0, -1, -1};

    /*
        가장 처음에 모든 칸에 양분 5씩 들어있음. M개의 나무를 심었음. 같은 칸에 여러 나무가 심어져 있을 수 있음.
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 땅의 크기 N x N
        M = Integer.parseInt(st.nextToken()); // 처음에 심는 나무 갯수
        K = Integer.parseInt(st.nextToken()); // K년 후 살아 남은 나무의 수

        // 겨울에 줄 양분 정보
        A = new int[N+1][N+1];
        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5; // 모든 칸의 초기 양분은 5
            }
        }

        // 초기 나무 M개
        trees = new PriorityQueue<>();
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees.offer(new Tree(r, c, age));
        }

        for(int i=0; i<K; i++) {
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(trees.size());
    }

    /*
    봄
        나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가함. 나무는 자신의 칸에 있는 양분만 먹을 수 있음.
        하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹음. 양분이 부족해 자신의 나이만큼
        양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽음.
     */
    private static void spring() {
        dead = new ArrayList<>();
        alive = new ArrayList<>();

        while(!trees.isEmpty()) {
            Tree tree = trees.poll();

            if(map[tree.r][tree.c] >= tree.age) {
                map[tree.r][tree.c] -= tree.age;
                alive.add(new Tree(tree.r, tree.c, tree.age+1));
            } else {
                dead.add(tree);
            }
        }

        for(Tree tree: alive) {
            trees.offer(tree);
        }
    }

    /*
    여름
        봄에 죽은 나무가 양분으로 변함. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가됨.
        소수점 아래는 버림.
     */
    private static void summer() {
        for(Tree tree: dead) {
            map[tree.r][tree.c] += (tree.age / 2);
        }
    }

    /*
    가을
        나무가 번식함. 번식하는 나무는 나이가 5의 배수여야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
     */
    private static void fall() {
        babies = new ArrayList<>();
        parents = new ArrayList<>();

        while(!trees.isEmpty()) {
            Tree tree = trees.poll();
            if(tree.age % 5 == 0) {
                for(int d=0; d<8; d++) {
                    int nr = tree.r + dr[d];
                    int nc = tree.c + dc[d];

                    if(nr < 1 || nr > N || nc < 1 || nc > N) continue;

                    babies.add(new Tree(nr, nc, 1));
                }
            }
            parents.add(tree);
        }

        for(Tree tree: babies) {
            trees.offer(tree);
        }

        for(Tree tree: parents) {
            trees.offer(tree);
        }
    }

    /*
    겨울
        땅에 양분을 추가한다. 각 칸에 추가되는 양분은 입력에 주어진다.
     */
    private static void winter() {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                map[i][j] += A[i][j];
            }
        }
    }
}
