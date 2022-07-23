package Programmers;

import java.util.*;

public class FriendsFourBlock {

    static int result, M, N;
    static char[][] map;
    static boolean[][] deleted;

    public static void main(String[] args) {
        FriendsFourBlock Main = new FriendsFourBlock();

//        Main.solution(4, 5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"});
        Main.solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"});
    }

    public int solution(int m, int n, String[] board) {
        result = 0;
        M = m;
        N = n;
        map = new char[M][N];
        for(int r=0; r<M; r++) {
            for(int c=0; c<N; c++) {
                map[r][c] = board[r].charAt(c);
            }
        }


        while(true) {
            deleted = new boolean[M][N];
            findFourBlock();
//            printDeleted();

            if(isEnd()) break;
//            System.out.println("Before Drop");
//            printMap();
            for(int c=0; c<N; c++) {
                drop(c);
            }
//            System.out.println("After Drop");
//            printMap();
        }

//        System.out.println("result: "+result);
        return result;
    }

    public static void drop(int col) {
        Stack<Character> q = new Stack<>();
        for(int r=0; r<M; r++) {
            if(deleted[r][col]) { // 삭제될 것
                result++;
            } else { // 남길 것
                q.add(map[r][col]);
            }
            map[r][col] = 'X';
        }

        int idx = M-1;
        while(!q.isEmpty()) {
            map[idx--][col] = q.pop();
        }
    }

    public static void printDeleted() {
        for(int r=0; r<M; r++) {
            for(int c=0; c<N; c++) {
                System.out.print(deleted[r][c]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printMap() {
        for(int r=0; r<M; r++) {
            for(int c=0; c<N; c++) {
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void findFourBlock() {
        for(int r=0; r<M-1; r++) {
            for(int c=0; c<N-1; c++) {
                char word = map[r][c];
                check(r, c, word);
            }
        }
    }

    public static void check(int r, int c, char word) {
        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                if(map[r+i][c+j] != word || map[r+i][c+j] == 'X') {
                    return;
                }
            }
        }

        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                deleted[r+i][c+j] = true;
            }
        }
    }

    public static boolean isEnd() {
        for(int r=0; r<M; r++) {
            for(int c=0; c<N; c++) {
                if(deleted[r][c]) return false;
            }
        }
        return true;
    }
}
