package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_9328 {

    static char[][] map;
    static boolean[][][] visited;
    static boolean[] keys;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int h, w, max, maxKey;

    private static class Pos implements Comparable<Pos> {
        int r;
        int c;
        int keyNum;

        public Pos(int r, int c, int keyNum) {
            this.r = r;
            this.c = c;
            this.keyNum = keyNum;
        }

        @Override
        public int compareTo(Pos o) {
            return Integer.compare(o.keyNum, this.keyNum);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h+2][w+2];
            visited = new boolean['z'-'a'+2][h+2][w+2];
            keys = new boolean['z'-'a'+1];

            for(int i=1; i<=h; i++) {
                char[] chars = br.readLine().toCharArray();
                for(int j=0; j<chars.length; j++) {
                    map[i][j+1] = chars[j];
                }
            }

            for(int j=0; j<=w+1; j++) {
                map[0][j] = '.';
                map[h+1][j] = '.';
            }

            for(int i=0; i<=h+1; i++) {
                map[i][0] = '.';
                map[i][w+1] = '.';
            }

            String key = br.readLine();
            int keyNum = 0;
            for(int i=0; i<key.length(); i++) {
                if(key.charAt(i) == '0') break;
                keys[key.charAt(i)-'a'] = true;
                keyNum++;
            }
            maxKey = keyNum;

            max = 0;
            bfs(new Pos(0, 0, keyNum));
            bw.write(max+"\n");
        }
        bw.flush();
    }

    private static void bfs(Pos start) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(start);

        while(!pq.isEmpty()){
            Pos curr = pq.poll();

            int sr = curr.r;
            int sc = curr.c;
            int sk = curr.keyNum;

            if(sk < maxKey) return;

            maxKey = sk;

            visited[sk][sr][sc] = true;

            for(int d=0;d<4;d++){
                int nr = sr+dr[d];
                int nc = sc+dc[d];
                int nk = sk+1;

                if(nr < 0 || nr > h+1 || nc < 0 || nc > w+1) continue;
                if(visited[sk][nr][nc] || map[nr][nc] == '*') continue;

                if(map[nr][nc] == '.') { // 길
                    pq.add(new Pos(nr, nc, sk));
                } else if(Character.isLowerCase(map[nr][nc])) { // 열쇠
                    if(!keys[map[nr][nc]-'a']) { // 없는 열쇠
                        keys[map[nr][nc]-'a'] = true;
                        map[nr][nc] = '.';
                        pq.add(new Pos(nr, nc, nk)); // 열쇠 정보 추가
                    } else { // 있는 열쇠
                        map[nr][nc] = '.';
                        pq.add(new Pos(nr, nc, sk)); // 기존 열쇠 정보
                    }
                } else if(Character.isUpperCase(map[nr][nc])) { // 문
                    if(keys[map[nr][nc]-'A']){ // 열쇠 있으면 감
                        map[nr][nc] = '.';
                        pq.add(new Pos(nr, nc, sk));
                    }
                } else if(map[nr][nc] == '$') { // 문서 발견
                    max++;
                    map[nr][nc] = '.';
                    pq.add(new Pos(nr, nc, sk));
                }
            }
        }
    }
}
