package Programmers;

import java.util.*;

public class LittleFriends {

    List<Character> words = new ArrayList<>();
    Map<Character, List<int[]>> info = new TreeMap<>();
    String result = "IMPOSSIBLE";
    boolean isFinished = false;
    static int M, N;
    char[][] map;

    public static void main(String[] args) {
        LittleFriends Main = new LittleFriends();
//        Main.solution(3, 3, new String[]{"DBA", "C*A", "CDB"});
//        Main.solution(2, 2, new String[]{"AB", "BA"});
//        Main.solution(5, 5, new String[]{"FGHEI", "BAB..", "D.C*.", "CA..I", "DFHGE"});
        Main.solution(10, 10, new String[]{"M...M...DU", "....AR...R", "...E..OH.H", ".....O....", ".E..A..Q..", "Q....LL.*.", ".D.N.....U", "GT.T...F..", "....FKS...", "GN....K..S"});
    }

    public String solution(int m, int n, String[] board) {
        M = m;
        N = n;
        map = new char[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                map[i][j] = board[i].charAt(j);
                if('A' <= map[i][j] && map[i][j] <= 'Z') {
                    if(!info.containsKey(map[i][j])) {
                        info.put(map[i][j], new ArrayList<>());
                        words.add(map[i][j]);
                    }
                    info.get(map[i][j]).add(new int[]{i, j});
                }
            }
        }
        Collections.sort(words);
        System.out.println(words);

        makePermutation(info.size(), new ArrayList<>(), new boolean[info.size()]);

        return result;
    }

    public void makePermutation(int toChoose, List<Character> order, boolean[] checked) {
        if(isFinished) return;

        if(toChoose == 0) {
            playGame(order, map);
            return;
        }

        for(int i=0; i<words.size(); i++) {
            if(!checked[i]) {
                checked[i] = true;
                order.add(words.get(i));
                makePermutation(toChoose-1, order, checked);
                order.remove(words.get(i));
                checked[i] = false;
            }
        }
    }

    public void playGame(List<Character> order, char[][] map) {
        for(Character ch: order) {
            List<int[]> pos = info.get(ch);

            int r1 = pos.get(0)[0];
            int c1 = pos.get(0)[1];

            int r2 = pos.get(1)[0];
            int c2 = pos.get(1)[1];

            boolean flag;
            if(c1 == c2) {
                flag = checkRow(r1, r2, c1, ch, map);
            } else if(r1 == r2) {
                flag = checkColumn(c1, c2, r1, ch, map);
            } else {
                flag = (checkRow(r1, r2, c2, ch, map) && checkColumn(c1, c2, r1, ch, map)) ||
                       (checkColumn(c1, c2, r2, ch, map) && checkRow(r1, r2, c1, ch, map));
            }

            if(flag) {
                map[r1][c1] = '.';
                map[r2][c2] = '.';
            } else {
                return;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(Character ch: order) {
            sb.append(ch);
        }
        result = sb.toString();
        isFinished = true;
    }

    public boolean checkRow(int r1, int r2, int c, char ch, char[][] map) {
        for(int i=Math.min(r1, r2); i<=Math.max(r1, r2); i++) {
            if(map[i][c] != '.' && map[i][c] != ch) return false;
        }
        return true;
    }

    public boolean checkColumn(int c1, int c2, int r, char ch, char[][] map) {
        for(int i=Math.min(c1, c2); i<=Math.max(c1, c2); i++) {
            if(map[r][i] != '.' && map[r][i] != ch) return false;
        }
        return true;
    }
}
