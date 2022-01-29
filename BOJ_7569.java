package jungol.language_coder;

import java.io.*;
import java.util.*;

class Map {
	public int x;
	public int y;
	public int z;
	
	public Map(int z, int x, int y) {
		this.z = z;
		this.x = x;
		this.y = y;
	}
}

public class BOJ_7569 {
	
	static Queue<Map> q = new LinkedList<>(); // BFS queue
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	static int dz[] = {-1, 1};
	
	static int m, n, h;
	
	static int map[][][];
	static int time = -1;
	static int numZero = 0;
	
	public static void BFS() {
		while(!q.isEmpty()) {
			int temp = q.size();
			time++;
			for(int i=0; i<temp; i++) {
				Map curr = q.poll();
				for(int j=0; j<4; j++) {
					int xx = curr.x + dx[j];
					int yy = curr.y + dy[j];
					
					if(xx < 0 || xx >= n || yy < 0 || yy >= m)
						continue;
					
					if(map[curr.z][xx][yy] == 0) {
						map[curr.z][xx][yy] = 1;
						numZero--;
						q.add(new Map(curr.z, xx, yy));
					}
				}
				for(int j=0; j<2; j++) {
					int zz = curr.z + dz[j];
					
					if(zz < 0 || zz >= h)
						continue;
					
					if(map[zz][curr.x][curr.y] == 0) {
						map[zz][curr.x][curr.y] = 1;
						numZero--;
						q.add(new Map(zz, curr.x, curr.y));
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		map = new int[h][n][m];
		
		for(int k=0; k<h; k++) {
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<m; j++) {
					map[k][i][j] = Integer.parseInt(st.nextToken());
					if(map[k][i][j] == 1)
						q.add(new Map(k, i, j));
					
					if(map[k][i][j] == 0)
						numZero++;
				}
			}
		}
		
		if(numZero == 0) {
			System.out.println(0);
			return;
		}
		
		BFS();
		
		if(numZero != 0)
			System.out.println(-1);
		else
			System.out.println(time);
		
	}
}
