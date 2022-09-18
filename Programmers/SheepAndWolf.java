package Programmers;

import java.util.*;

public class SheepAndWolf {

    static List<List<Integer>> graph;
    static int[] copiedInfo;
    static boolean[][][] visited;
    static int max = 0;

    public static void main(String[] args) {
        SheepAndWolf Main = new SheepAndWolf();
//        System.out.println(Main.solution(new int[]{0,0,1,1,1,0,1,0,1,0,1,1}, new int[][]{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}}));
        System.out.println(Main.solution(new int[]{0,1,0,1,1,0,1,0,0,1,0}, new int[][]{{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}}));
    }

    public int solution(int[] info, int[][] edges) {
        copiedInfo = new int[info.length];
        for(int i=0; i<info.length; i++) {
            copiedInfo[i] = info[i];
        }

        // [node갯수][양 최대 마릿수][늑대 최대 마릿수]
        visited = new boolean[info.length][info.length+1][info.length+1];

        graph = new ArrayList<>();
        for(int i=0; i<info.length; i++) {
            graph.add(new ArrayList<>());
        }

        // 올라가기도 해야하니까 양방향 그래프로
        for(int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        dfs(0, 0, 0);

        return max;
    }

    public void dfs(int curr, int sheep, int wolf) {
        if(copiedInfo[curr] == 0) {
            sheep++;
        } else if(copiedInfo[curr] == 1) {
            wolf++;
        }

        if(wolf >= sheep) return;

        max = Math.max(max, sheep);

        for(Integer adj: graph.get(curr)) {
            int temp = copiedInfo[curr]; // 왔던 곳 돌아갈 수 있어야 함
            if(!visited[adj][sheep][wolf]) {
                copiedInfo[curr] = 2; // 처음에 조건문에서 지나가되 양이나 늑대 숫자는 변하면 안됨
                visited[curr][sheep][wolf] = true;
                dfs(adj, sheep, wolf);
                visited[curr][sheep][wolf] = false;
                copiedInfo[curr] = temp; // 값 되돌림
            }
        }
    }
}
