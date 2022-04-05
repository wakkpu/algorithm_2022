package SW;

import java.io.*;
import java.util.*;

public class SW_1210 {

	static int [][] map;
	
	public static boolean ladder(int x) {

		boolean[][] visited = new boolean[100][100];
		
		int ch = 0;
		int cx = x;
		while(true) {
			//System.out.println("start from "+x+" h: "+ch+" x: "+cx);
			if(ch == 99 && map[ch][cx] == 1) {
				//System.out.println("꽝");
				return false;
			}
			if(ch == 99 && map[ch][cx] == 2) {
				//System.out.println("당첨");
				return true;
			}
			
			if(cx+1 < 100 && map[ch][cx+1] != 0 && visited[ch][cx+1] == false) {
				//System.out.println("right");
				visited[ch][cx+1] = true;
				cx++;
			}
			if(cx-1 >= 0 && map[ch][cx-1] != 0 && visited[ch][cx-1] == false) {
				//System.out.println("left");
				visited[ch][cx-1] = true;
				cx--;
			}
			if(ch+1 < 100 && map[ch+1][cx] != 0 && visited[ch+1][cx] == false) {
				//System.out.println("down");
				visited[ch+1][cx] = true;
				ch++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[100][100];
		
		for(int t=0; t<10; t++) {
			st = new StringTokenizer(br.readLine());
			String T = st.nextToken();
			
			for(int i=0; i<100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int x=0; x<100; x++) {
				if(map[0][x] == 1) {
					boolean flag = ladder(x);
					if(flag == true) {
						System.out.println("#"+T+" "+x);
					}
				}
			}
		}
	}
}
