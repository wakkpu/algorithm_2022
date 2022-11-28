package Programmers;

import java.util.*;

public class 자물쇠와열쇠 {
    public static void main(String[] args) {
        자물쇠와열쇠 Main = new 자물쇠와열쇠();

        int[][] key = new int[][] {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        Main.solution(key, lock);
    }

    public boolean solution(int[][] key, int[][] lock) {
        return bfs(key, lock);
    }

    public boolean bfs(int[][] key, int[][] lock) {
        Set<String> visited = new HashSet<>();
        visited.add(makeString(key));

        Queue<int[][]> q = new LinkedList<>();
        q.offer(key);

        while(!q.isEmpty()) {
            int[][] curr = q.poll();

            System.out.println("curr");
            printArr(curr);

            if(isMatch(curr, lock)) {
                System.out.println("found!");
                return true;
            }

            int[][] rotateKey = new int[key.length][key.length];
            for(int i=0; i<key.length; i++) {
                for(int j=0; j<key.length; j++) {
                    rotateKey[i][j] = curr[i][j];
                }
            }

            for(int i=0; i<3; i++) {
                System.out.println("rotate");
                rotateKey = rotate(rotateKey);
                printArr(rotateKey);
                String rotateStr = makeString(rotateKey);

                if(!visited.contains(rotateStr)) {
                    visited.add(rotateStr);
                    q.offer(rotateKey);
                }
            }

            int[][] left = moveLeft(curr);
            System.out.println("left");
            printArr(left);
            String leftStr = makeString(left);
            if(!visited.contains(leftStr)) {
                visited.add(leftStr);
                q.offer(left);
            }

            int[][] right = moveRight(curr);
            System.out.println("right");
            printArr(right);
            String rightStr = makeString(right);
            if(!visited.contains(rightStr)) {
                visited.add(rightStr);
                q.offer(right);
            }

            int[][] up = moveUp(curr);
            System.out.println("up");
            printArr(up);
            String upStr = makeString(up);
            if(!visited.contains(upStr)) {
                visited.add(upStr);
                q.offer(up);
            }

            int[][] down = moveDown(curr);
            System.out.println("down");
            printArr(down);
            String downStr = makeString(down);
            if(!visited.contains(downStr)) {
                visited.add(downStr);
                q.offer(down);
            }
        }
        return false;
    }

    public void printArr(int[][] arr) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public String makeString(int[][] key) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<key.length; i++) {
            for(int j=0; j<key.length; j++) {
                sb.append(key[i][j]).append(" ");
            }
        }
        return sb.toString();
    }

    public int[][] rotate(int[][] key) {
        int M = key.length;

        int[][] copy = new int[M][M];
        for(int i=0; i<M; i++) {
            for(int j=0; j<M; j++) {
                copy[j][M-1-i] = key[i][j];
            }
        }
        return copy;
    }

    public int[][] moveUp(int[][] key) {
        int[][] result = new int[key.length][key.length];
        for(int r=1; r<key.length; r++) {
            for(int c=0; c<key.length; c++) {
                result[r-1][c] = key[r][c];
            }
        }
        return result;
    }

    public int[][] moveDown(int[][] key) {
        int[][] result = new int[key.length][key.length];
        for(int r=0; r<key.length-1; r++) {
            for(int c=0; c<key.length; c++) {
                result[r+1][c] = key[r][c];
            }
        }
        return result;
    }

    public int[][] moveLeft(int[][] key) {
        int[][] result = new int[key.length][key.length];
        for(int r=0; r<key.length; r++) {
            for(int c=1; c<key.length; c++) {
                result[r][c-1] = key[r][c];
            }
        }
        return result;
    }

    public int[][] moveRight(int[][] key) {
        int[][] result = new int[key.length][key.length];
        for(int r=0; r<key.length; r++) {
            for(int c=0; c<key.length-1; c++) {
                result[r][c+1] = key[r][c];
            }
        }
        return result;
    }

    public boolean isMatch(int[][] key, int[][] lock) {
        int M = key.length;
        int N = lock.length;

        for(int i=0; i<N; i++) { // lock
            for(int j=0; j<N; j++) {

                int[][] copyLock = new int[N][N];
                for(int r=0; r<N; r++) {
                    for(int c=0; c<N; c++) {
                        copyLock[r][c] = lock[r][c];
                    }
                }

                for(int r=0; r<M; r++) {
                    for(int c=0; c<M; c++) {
                        if(i+r >= N || j+c >= N) continue;
                        copyLock[i+r][j+c] += key[r][c];
                        if(copyLock[i+r][j+c] != 1) break;
                    }
                }

                boolean flag = true;
                for(int r=0; r<M; r++) { // key
                    for(int c=0; c<M; c++) {
                        if(copyLock[r][c] != 1) {
                            flag = false;
                            break;
                        }
                    }
                }
                if(flag) return true;
            }
        }

        return false;
    }
}
