import java.util.LinkedList;
import java.util.Queue;

public class DevMatch_1 {

    static char[][] copy;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) {
        String[] grid = {"??b", "abc", "cc?"};
        System.out.println(solution(grid));
    }

    public static int solution(String[] grid) {
        copy = new char[grid.length][grid[0].length()];
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length(); j++) {
                copy[i][j] = grid[i].charAt(j);
            }
        }
        int qCount = countQuestion(copy);
        int answer = makeCombination(qCount, new char[qCount], 0);
        return answer;
    }

    public static int countQuestion(char[][] grid) {
        int countQ = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '?') {
                    countQ++;
                }
            }
        }
        System.out.println("?는 "+countQ+"개");
        return countQ;
    }

    public static int makeCombination(int toChoose, char[] choosed, int startIdx) {
        if(toChoose == 0) {
            int count = 0;
            int idx=0;

            for(int i=0; i< copy.length; i++) {
                for(int j=0; j<copy[0].length; j++) {
                    if(copy[i][j] == '?') {
                        copy[i][j] = choosed[idx++];
                    }
                }
            }

            System.out.println("? 채우고 난 후");
            for(int i=0; i<copy.length; i++) {
                for(int j=0; j<copy[0].length; j++) {
                    System.out.print(copy[i][j]+" ");
                }
                System.out.println();
            }

            if(checkAdj(copy)) count++;

            return count;
        }

        char[] src = new char[] {'a', 'b', 'c'};
        for(int i=startIdx; i<src.length; i++) {
            choosed[choosed.length - toChoose] = src[i];
            makeCombination(toChoose-1, choosed, i+1);
        }
        return 0;
    }

    public static boolean checkAdj(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        boolean[] checked = new boolean[3];

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        if(grid[0][0] == 'a') checked[0] = true;
        if(grid[0][0] == 'b') checked[1] = true;
        if(grid[0][0] == 'c') checked[2] = true;

        while(!q.isEmpty()) {
            int[] s = q.poll();
            int si = s[0];
            int sj = s[1];

            for(int d=0; d<4; d++) {
                int ni = si+di[d];
                int nj = sj+dj[d];

                if(ni < 0 || ni >= grid.length || nj < 0 || nj >= grid[0].length || visited[ni][nj]) continue;
                visited[ni][nj] = true;

                if(checked[0] && grid[si][sj] != 'a' && grid[ni][nj] == 'a') return false;
                if(checked[1] && grid[si][sj] != 'b' && grid[ni][nj] == 'b') return false;
                if(checked[2] && grid[si][sj] != 'c' && grid[ni][nj] == 'c') return false;

                if(!checked[0] && grid[ni][nj] == 'a') checked[0] = true;
                if(!checked[1] && grid[ni][nj] == 'b') checked[1] = true;
                if(!checked[2] && grid[ni][nj] == 'c') checked[2] = true;
                q.offer(new int[]{ni, nj});
            }
        }

        return true;
    }
}
