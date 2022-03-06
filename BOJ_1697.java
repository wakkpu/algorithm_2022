import java.io.*;
import java.util.*;

public class BOJ_1697 {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int count = bfs();
        System.out.println(count);
    }
    public static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[100_001];
        Arrays.fill(visited, -1);

        visited[N] = 0;
        q.offer(N);

        while(!q.isEmpty()) {
            int x = q.poll();

            if(x == K) {
                return visited[K];
            }
            if(0 <= x-1 && visited[x-1] == -1) {
                //System.out.println(x+"->"+(x-1));
                q.offer(x-1);
                visited[x-1] = visited[x]+1;
            }
            if(x+1 <= 100000 && visited[x+1] == -1) {
                //System.out.println(x+"->"+(x+1));
                q.offer(x+1);
                visited[x+1] = visited[x]+1;
            }
            if(2*x <= 100000 && visited[2*x] == -1) {
                //System.out.println(x+"->"+(2*x));
                q.offer(2*x);
                visited[2*x] = visited[x]+1;
            }
        }
        return 0;
    }
}
