import java.io.*;
import java.util.*;

public class BOJ_3109 {
	
	static int R, C, count;
	static boolean[][] using;
	static int[] dir = {-1, 0, 1};

	public static boolean Pipe(boolean[][] using, int currR, int currC) {
		if(currC == C-1) {
			count++;
			return true;
		}

		int nextR;
		int nextC = currC+1;
		for(int i=0; i<3; i++) {
			nextR = currR+dir[i];
			if(nextR < 0 || nextR >= R || using[nextR][nextC]) continue;
			using[nextR][nextC] = true;
			if(Pipe(using, nextR, nextC)) return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		boolean[][] input = new boolean[R][C];
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				if(str.charAt(j) == 'x') {
					input[i][j] = true;
				}
			}
		}
		
		count = 0;
		for(int i=0; i<R; i++) {
			using = input.clone(); // 지도 초기화
			using[i][0] = true; // [i][0]에 파이프 놓고 시작
			Pipe(using, i, 0);
		}
		System.out.println(count);

	}
}
