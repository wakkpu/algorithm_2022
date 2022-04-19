package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2310 {

    static class Room {
        String name;
        int coin;

        public Room(String name, int coin) {
            this.name = name;
            this.coin = coin;
        }

        @Override
        public String toString() {
            return "Room{" +
                    "name='" + name + '\'' +
                    ", coin=" + coin +
                    '}';
        }
    }

    static class Player {
        int curr;
        int coin;

        public Player(int curr, int coin) {
            this.curr = curr;
            this.coin = coin;
        }
    }

    static int N;
    static boolean flag;
    static Room[] rooms;
    static boolean[] visited;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            rooms = new Room[N+1];
            list = new ArrayList[N+1];
            for(int i=0; i<=N; i++) {
                list[i] = new ArrayList<>();
            }

            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());

                String name = st.nextToken();
                int coin = Integer.parseInt(st.nextToken());
                rooms[i] = new Room(name, coin);

                while(true) {
                    int to = Integer.parseInt(st.nextToken());
                    if(to == 0) break;
                    list[i].add(to);
                }
            }

            /*for(int i=1; i<=N; i++) {
                System.out.println(list[i].toString());
                System.out.println(rooms[i]);
            }*/

            flag = false;
            visited = new boolean[N+1];

            dfs(1, 0);

            if(flag) bw.write("Yes\n");
            else bw.write("No\n");
        }
        bw.flush();
        bw.close();
    }

    public static void dfs(int room, int coin) {
        if(flag) return;

        if(room == N) {
            flag = true;
            return;
        }

        for(int adj: list[room]) {
            if(visited[adj]) continue;

            Room next = rooms[adj];

            if(next.name.equals("T")) {
                if(next.coin <= coin) {
                    visited[adj] = true;
                    dfs(adj, coin-next.coin);
                }
            } else if(next.name.equals("L")) {
                visited[adj] = true;
                dfs(adj, Math.max(next.coin, coin));
            } else {
                visited[adj] = true;
                dfs(adj, coin);
            }
        }
    }
}
