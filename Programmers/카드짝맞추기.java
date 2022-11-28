package Programmers;

import java.io.*;
import java.util.*;

public class 카드짝맞추기 {
    public static void main(String[] args) {

    }

    static Set<String> allPermutations;
    static int[][] copy;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Info {
        int r;
        int c;
        int move;

        public Info(int r, int c, int move) {
            this.r = r;
            this.c = c;
            this.move = move;
        }
    }

    public int solution(int[][] board, int r, int c) {
        int answer = Integer.MAX_VALUE;

        int cardNum = 0;
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                cardNum = Math.max(cardNum, board[i][j]);
            }
        }

        List<Integer> cards = new ArrayList<>();
        for(int i=1; i<=cardNum; i++) {
            cards.add(i);
        }

        allPermutations = new HashSet<>();
        permutation(cardNum, "", cards);

        for(String perm: allPermutations) {
            String[] str = perm.split("");

            int moveCount = 0;
            int[] pos = new int[]{r, c};

            copy = new int[4][4];
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    copy[i][j] = board[i][j];
                }
            }

            for(String toFind: str) {
                int cardToFind = Integer.parseInt(toFind);

                moveCount += bfs(pos, cardToFind);
                copy[pos[0]][pos[1]] = 0;
                moveCount++;

                moveCount += bfs(pos, cardToFind);
                copy[pos[0]][pos[1]] = 0;
                moveCount++;

            }
            answer = Math.min(answer, moveCount);
        }

        return answer;
    }

    public void permutation(int toChoose, String choosed, List<Integer> cards) {
        if(toChoose == 0) {
            allPermutations.add(choosed);
            return;
        }

        for(int i=0; i<cards.size(); i++) {
            int card = cards.get(i);
            if(!choosed.contains(String.valueOf(card))) {
                permutation(toChoose-1, choosed+card, cards);
            }
        }
    }

    public int bfs(int[] pos, int toFind) {
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(pos[0], pos[1], 0));

        boolean[][] visited = new boolean[4][4];
        visited[pos[0]][pos[1]] = true;

        while(!q.isEmpty()) {
            Info curr = q.poll();

            int sr = curr.r;
            int sc = curr.c;
            int move = curr.move;

            if(copy[sr][sc] == toFind) {
                pos[0] = sr;
                pos[1] = sc;
                return move;
            }

            // 한 칸 짜리
            for(int i=0; i<4; i++) {
                int nr = sr + dr[i];
                int nc = sc + dc[i];

                if(nr < 0 || nr > 3 || nc < 0 || nc > 3) continue;
                if(visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new Info(nr, nc, move+1));
            }

            // n칸 짜리
            for(int i=0; i<4; i++) {
                Info next = checkMove(sr, sc, i);

                int nr = next.r;
                int nc = next.c;

                if(next.r == sr && next.c == sc) continue;
                if(visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new Info(nr, nc, move+1));
            }
        }
        return 0;
    }

    public Info checkMove(int r, int c, int dir) {
        r += dr[dir];
        c += dc[dir];

        while(0 <= r && r < 4 && 0 < c && c < 4) {
            if(copy[r][c] != 0) {
                return new Info(r, c, 0);
            }

            r += dr[dir];
            c += dc[dir];
        }
        return new Info(r-dr[dir], c-dc[dir], 0);
    }
}
