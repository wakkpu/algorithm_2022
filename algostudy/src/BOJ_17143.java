import java.io.*;
import java.util.*;

public class BOJ_17143 {

	static int R, C, M;
	static int dir[][] = {{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}}; // 1:상, 2:하, 3:오, 4:왼
	static HashMap<Integer, Shark> sharks;
	static int[][] map;
	
	static class Shark {
		int num;
		int i;
		int j;
		int speed;
		int direction;
		int size;
		
		public Shark(int i, int j, int speed, int direction, int size) {
			this.i = i;
			this.j = j;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Shark [i=" + i + ", j=" + j + ", speed=" + speed + ", direction=" + direction + ", size=" + size + "]";
		}
	}
	
	public static int fishing(int k) {
		for(int i=1; i<=R; i++) {
			if(map[i][k] > 0) {
				int size = sharks.get(map[i][k]).size;
				sharks.put(map[i][k], null);
				map[i][k] = 0;
				return size;
			}
		}
		return 0;
	}
	
	public static void moveAndEat() {
		ArrayList<Shark> keys = new ArrayList<>(sharks.values());
		map = new int[R+1][C+1];
		
		for(Shark shark: keys) {
			if(shark == null) continue;
			
			int si = shark.i;
			int sj = shark.j;
			int direction = shark.direction;
			for(int s=0; s<shark.speed; s++) {
				si += dir[direction][0];
				sj += dir[direction][1];
				
				if(si < 1 || si > R || sj < 1 || sj > C) {
					if(direction == 1) direction = 2;
					else if(direction == 2) direction = 1;
					else if(direction == 3) direction = 4;
					else if(direction == 4) direction = 3;
					
					si += dir[direction][0]*2;
					sj += dir[direction][1]*2;
				}
			}
			
			if(map[si][sj] > 0) {
				if(map[si][sj] > shark.size) {
					sharks.put(shark.size, null);
				} else {
					shark.i = si;
					shark.j = sj;
					shark.direction = direction;
					sharks.put(map[si][sj], null);
					map[si][sj] = shark.size;
				}
			} else {
				shark.i = si;
				shark.j = sj;
				shark.direction = direction;
				map[si][sj] = shark.size;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R+1][C+1];
		sharks = new HashMap<>();
		
		for(int m=1; m<=M; m++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			Shark shark = new Shark(r, c, s, d, z);
			map[r][c] = z;
			sharks.put(z, shark);
		}
		
		int totalSize = 0;
		
		for(int k=1; k<=C; k++) {
	
			totalSize += fishing(k);
			
			moveAndEat();
			
		}
		System.out.println(totalSize);
	}
}
