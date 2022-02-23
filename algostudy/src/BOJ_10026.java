import java.io.*;
import java.util.*;

public class BOJ_10026 {
	
	static int N;
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][N];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		char[][] rgmap = new char[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] == 'G' || map[i][j] == 'R') {
					rgmap[i][j] = 'R';
				} else {
					rgmap[i][j] = 'B';
				}
			}
		}
		
		int count1 = 0;
		int count2 = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] != 'x') {
					dfs(i, j, map[i][j], map);
					count1++;
				}
				if(rgmap[i][j] != 'x') {
					dfs(i, j, rgmap[i][j], rgmap);
					count2++;
				}
			}
		}
		System.out.println(count1+" "+count2);
	}
	
	public static void dfs(int si, int sj, char color, char[][] m) {
		m[si][sj] = 'x';
		
		for(int i=0; i<4; i++) {
			int ni = si + di[i];
			int nj = sj + dj[i];
			
			if(ni < 0 || ni >= N || nj < 0 || nj >= N || m[ni][nj] == 'x' || color != m[ni][nj]) {
				continue;
			}
			
			dfs(ni, nj, m[ni][nj], m);
		}
	}
}
