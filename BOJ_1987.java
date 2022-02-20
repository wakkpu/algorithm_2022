import java.io.*;
import java.util.*;

public class BOJ_1987 {

	static int R, C;
	static int maxLen;
	
	static char[][] map;
	static Set<Character> include;
	
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	public static void traversal(int si, int sj, int len) {
		char curr = map[si][sj];
		include.add(Character.valueOf(curr));
		for(int i=0; i<4; i++) {
			int ni = si+di[i];
			int nj = sj+dj[i];
			
			if(0 <= ni && ni < R && 0 <= nj && nj < C) {
				char next = map[ni][nj];
				if(!include.contains(Character.valueOf(next)) && curr != next) {
					traversal(ni, nj, len+1);
				}
			}
		}
		if(len > maxLen) {
			maxLen = len;
		}
		include.remove(Character.valueOf(curr));
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		include = new HashSet<Character>();
		map = new char[R][C];
		
		maxLen = 0;
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		traversal(0, 0, 1);
		
		System.out.println(maxLen);
		
	}

}
