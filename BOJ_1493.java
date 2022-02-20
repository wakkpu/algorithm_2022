

import java.io.*;
import java.util.*;


class Cube {
	public int side; // 변의 길이
	public int num; // 갯수
	
	public Cube(int side, int num) {
		this.side = side;
		this.num = num;
	}
}

public class BOJ_1493 {

	static boolean flag;
	static int N;
	static Cube[] cubes;
	static int usedCube;
	
	public static void stackCube(int l, int w, int h) {
		if(!flag) {
			return;
		}
		if(l == 0 || w == 0 || h == 0) {
			return;
		}
		
		// 큰 큐브부터 사용함 (greedy)
		for(int i=N-1; i>=0; i--) {
			int side = 1 << cubes[i].side;
			if(cubes[i].num > 0 && l >= side && w >= side && h >= side) {
				cubes[i].num--;
				usedCube++;
				// 큐브를 하나 넣고 남은 공간을 세 직육면체로 나타낼 수 있음. -> 재귀
				stackCube(l, w, h-side);
				stackCube(side, w-side, side);
				stackCube(l-side, w, side);
				return;
			}
		}
		// 다 돌았는데도 큐브를 하나도 못 넣었다 -> 문제 못 품
		flag = false;
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int length = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		
		N = Integer.parseInt(br.readLine());
		
		// cubes엔 작은 순서대로 입력되었음. 사용할 땐 거꾸로 (greedy)
		cubes = new Cube[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			cubes[i] = new Cube(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		flag = true;
		usedCube = 0;
		stackCube(length, width, height);
		
		if(flag) {
			System.out.println(usedCube);
		} else {
			System.out.println(-1);
		}
	}

}
