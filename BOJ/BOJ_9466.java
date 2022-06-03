package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_9466 {

    static int[] num;
    static int count;
    static boolean[] visited;
    static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            int N = Integer.parseInt(br.readLine());
            num = new int[N+1];
            count = N;
            visited = new boolean[N+1];
            checked = new boolean[N+1];

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<=N; i++) {
                dfs(i);
            }

            bw.write(count+"\n");
        }
        bw.flush();
        bw.close();
    }

    public static void dfs(int curr) {
        if(visited[curr]) return;

        visited[curr] = true;
        int next = num[curr];
        //System.out.println("curr:"+curr+" -> next:"+next);

        if(!visited[next]) {
            //System.out.println("아직 next("+next+") 확인 안했으므로 next("+next+") 탐색");
            dfs(next);
        } else { // next 확인 했음
            if(!checked[next]) {
                //System.out.println("cycle: "+curr+"->"+next);
                count--;
                while(curr != next) {
                    //System.out.println("cycle: "+curr+"->"+next);
                    count--;
                    next = num[next];
                }
            }
        }
        checked[curr] = true;
    }
}
