package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_11437 {

    static List<List<Integer>> tree;
    static int[] parents, depth;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        parents = new int[N+1];
        depth = new int[N+1];

        tree = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            tree.add(new ArrayList<>());
        }

        // 트리 구성
        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            tree.get(from).add(to);
            tree.get(to).add(from);
        }

        M = Integer.parseInt(br.readLine());

        // dfs로 각 정점들의 depth 구하기
        dfs(1, 1);
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int lca = findLCA(a, depth[a], b, depth[b]);
            bw.write(lca+"\n");
        }
        bw.flush();
    }

    // 두 노드의 depth를 같게 만들고 같은 노드를 가리킬 때까지 부모를 타고 올라옴
    static int findLCA(int a, int a_depth, int b, int b_depth) {
        // 두 노드의 depth 같도록 하는 과정
        if(a_depth > b_depth) {
            while(a_depth != b_depth) {
                a_depth--;
                a = parents[a];
            }
        } else if(a_depth < b_depth) {
            while(a_depth != b_depth) {
                b_depth--;
                b = parents[b];
            }
        }

        // 같은 노드를 가리킬 때까지 부모 타고 올라가기
        while(a != b) {
            a = parents[a];
            b = parents[b];
        }

        return a;
    }

    static void dfs(int from, int count) {
        depth[from] = count++;
        for(Integer next: tree.get(from)) {
            if(depth[next] == 0) {
                dfs(next, count);
                parents[next] = from;
            }
        }
    }
}
