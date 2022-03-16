import java.io.*;
import java.util.*;

public class BOJ_16202 {

    static class Node {
        int from;
        int to;
        int weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static int N, M, K;
    static Node[] graph;
    static int[] rep;
    static boolean[] available;

    public static void main(String[] args) throws IOException {
        /*
        그래프에서 간선을 하나씩 제거하면서 MST의 비용을 구하는 게임.
        MST의 비용이란 MST를 이루고 있는 가중치의 합을 의미한다.
        각 턴의 점수는 해당 턴에서 찾은 MST의 비용이 된다.
        K턴에 걸쳐서 진행. 첫 턴에는 입력으로 주어진 그래프의 MST 비용을 구해야 한다.
        각 턴이 종료된 후에는 그 턴에서 구한 MST에서 가장 가중치가 작은 간선 하나를 제거한다.
        한 번 제거된 간선은 이후의 턴에서 사용할 수 없음.
        어떤 턴에서 MST를 만들 수 없다면 그 턴의 점수는 0. 당연히 이후 모든 턴의 점수도 0.
        첫 턴에 MST를 만들 수 없는 경우도 있음.

        양방향 간선 그래프
        */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점의 개수
        M = Integer.parseInt(st.nextToken()); // 간선의 개수
        K = Integer.parseInt(st.nextToken()); // 턴의 수

        graph = new Node[M+1];
        available = new boolean[M+1];
        Arrays.fill(available, true);

        for(int weight=1; weight<=M; weight++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[weight] = new Node(from, to, weight);
        }

        boolean flag = false;
        for(int k=0; k<K; k++) {
            if(flag) {
                System.out.print("0 ");
                continue;
            }

            int sol = kruskal();
            if(sol == 0) flag = true;
            System.out.print(sol+" ");
        }
    }

    public static int kruskal() {

        make();

        int score = 0;
        int count = 0;

        boolean foundMin = false;
        int min = M+1;

        for(int i=1; i<=M; i++) {
            Node node = graph[i];
            if(available[i]) {
                if(union(node.from, node.to)) {
                    score += node.weight;
                    count++;
                    if(!foundMin) {
                        min = i;
                        foundMin = true;
                    }
                }
            }
        }

        available[min] = false;

        if(count == N-1) {
            return score;
        } else {
            return 0;
        }
    }

    public static void make() {
        rep = new int[N+1];
        for(int i=1; i<=N; i++) {
            rep[i] = i;
        }
    }

    public static int find(int a) {
        if(rep[a] == a) return a;
        return rep[a] = find(rep[a]);
    }

    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) {
            return false;
        } else if(a > b) {
            rep[a] = b;
            return true;
        } else {
            rep[b] = a;
            return true;
        }
    }
}
