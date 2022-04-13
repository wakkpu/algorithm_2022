package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_9205 {

    static int N, sx, sy, dx, dy;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            N = Integer.parseInt(br.readLine());

            ArrayList<int[]> list = new ArrayList<>();
            for(int i=0; i<N+2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                if(i == 0) {
                    sx = x;
                    sy = y;
                }else if(i == N+1) {
                    dx = x;
                    dy = y;
                }else {
                    list.add(new int[]{x,y});
                }
            }

            if(bfs(list)) bw.write("happy\n");
            else bw.write("sad\n");
        }

        bw.flush();
        bw.close();
    }

    static boolean bfs(ArrayList<int[]> list) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[N];
        q.add(new int[] {sx, sy});

        while(!q.isEmpty()) {
            int[] pos = q.poll();
            int px = pos[0], py = pos[1];

            if(Math.abs(px-dx) + Math.abs(py-dy) <= 1000) {
                return true;
            }

            for(int i=0; i<N; i++) {
                if(!visited[i]) {
                    int nx = list.get(i)[0];
                    int ny = list.get(i)[1];
                    int dis = Math.abs(px - nx) + Math.abs(py - ny);

                    if(dis <= 1000) {
                        visited[i] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
        return false;
    }

}