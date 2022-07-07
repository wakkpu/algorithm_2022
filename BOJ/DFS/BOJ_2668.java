package BOJ.DFS;

import java.io.*;
import java.util.*;

public class BOJ_2668 {

    static int N;
    static int[] num;
    static boolean[] visited;
    static List<Integer> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        num = new int[N+1];
        visited = new boolean[N+1];

        for(int i=1; i<=N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1; i<=N; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }

        Collections.sort(result);
        bw.write(result.size()+"\n");
        for(Integer n: result) {
            bw.write(n+"\n");
        }

        bw.flush();
        bw.close();
    }

    public static void dfs(int start, int end) {
        if(num[start] == end) {
            result.add(end);
            return;
        }

        if(!visited[num[start]]) {
            visited[num[start]] = true;
            dfs(num[start], end);
            visited[num[start]] = false;
        }
    }
}
