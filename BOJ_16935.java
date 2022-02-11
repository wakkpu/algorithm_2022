package jungol.language_coder;

import java.io.*;
import java.util.*;

public class BOJ_16935 {

	static int N, M, R;
	static int[][] map;
	
	// 상하 반전
	public static void first() {
		int N = map.length;
		int M = map[0].length;
		for(int j=0; j<M; j++) {
			int top = 0;
			int down = N-1;
			for(int i=0; i<N/2; i++) {
				int temp = map[top][j];
				map[top][j] = map[down][j];
				map[down][j] = temp;
				top++; down--;
			}
		}
	}
	
	// 좌우 반전
	public static void second() {
		int N = map.length;
		int M = map[0].length;
		for(int i=0; i<N; i++) {
			int left = 0;
			int right = M-1;
			for(int j=0; j<M/2; j++) {
				int temp = map[i][left];
				map[i][left] = map[i][right];
				map[i][right] = temp;
				left++; right--;
			}
		}
	}
	
	// 우측으로 90도 회전
	public static void third() {
		int N = map.length;
		int M = map[0].length;
		int[][] temp = new int[M][N];
		
		int tempi = 0;
		for(int j=0; j<M; j++) {
			int tempj = 0;
			for(int i=N-1; i>=0; i--) {
				temp[tempi][tempj] = map[i][j];
				tempj++;
			}
			tempi++;
		}
		
		map = new int[M][N];
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = temp[i][j];
			}
		}
	}
	
	// 좌측으로 90도 회전
	public static void fourth() {
		int N = map.length;
		int M = map[0].length;
		int[][] temp = new int[M][N];
		
		int tempi = 0;
		for(int j=M-1; j>=0; j--) {
			int tempj = 0;
			for(int i=0; i<N; i++) {
				temp[tempi][tempj] = map[i][j];
				tempj++;
			}
			tempi++;
		}
		
		map = new int[M][N];
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = temp[i][j];
			}
		}
	}
	
	// 부분 배열 시계 방향으로
	public static void fifth() {
		int N = map.length;
		int M = map[0].length;
		
		int[][] temp1 = new int[N/2][M/2];
		int[][] temp2 = new int[N/2][M/2];
		int[][] temp3 = new int[N/2][M/2];
		int[][] temp4 = new int[N/2][M/2];
		int tempi = 0;
		int tempj = 0;
		for(int i=0; i<N/2; i++) {
			tempj = 0;
			for(int j=0; j<M/2; j++) {
				temp1[tempi][tempj++] = map[i][j];
			}
			tempi++;
		}
		tempi = 0;
		for(int i=0; i<N/2; i++) {
			tempj = 0;
			for(int j=M/2; j<M; j++) {
				temp2[tempi][tempj++] = map[i][j];
			}
			tempi++;
		}
		tempi = 0;
		for(int i=N/2; i<N; i++) {
			tempj = 0;
			for(int j=M/2; j<M; j++) {
				temp3[tempi][tempj++] = map[i][j];
			}
			tempi++;
		}
		tempi = 0;
		for(int i=N/2; i<N; i++) {
			tempj = 0;
			for(int j=0; j<M/2; j++) {
				temp4[tempi][tempj++] = map[i][j];
			}
			tempi++;
		}
		tempi = 0;
		for(int i=0; i<N/2; i++) {
			tempj = 0;
			for(int j=M/2; j<M; j++) {
				map[i][j] = temp1[tempi][tempj++];
			}
			tempi++;
		}
		tempi = 0;
		for(int i=N/2; i<N; i++) {
			tempj = 0;
			for(int j=M/2; j<M; j++) {
				map[i][j] = temp2[tempi][tempj++];
			}
			tempi++;
		}
		tempi = 0;
		for(int i=N/2; i<N; i++) {
			tempj = 0;
			for(int j=0; j<M/2; j++) {
				map[i][j] = temp3[tempi][tempj++];
			}
			tempi++;
		}
		tempi = 0;
		for(int i=0; i<N/2; i++) {
			tempj = 0;
			for(int j=0; j<M/2; j++) {
				map[i][j] = temp4[tempi][tempj++];
			}
			tempi++;
		}
	}
	
	// 부분 배열 반시계 방향으로
	public static void sixth() {
		int N = map.length;
		int M = map[0].length;
		
		int[][] temp1 = new int[N/2][M/2];
		int[][] temp2 = new int[N/2][M/2];
		int[][] temp3 = new int[N/2][M/2];
		int[][] temp4 = new int[N/2][M/2];
		int tempi = 0;
		int tempj = 0;
		for(int i=0; i<N/2; i++) {
			tempj = 0;
			for(int j=0; j<M/2; j++) {
				temp1[tempi][tempj++] = map[i][j];
			}
			tempi++;
		}
		tempi = 0;
		for(int i=0; i<N/2; i++) {
			tempj = 0;
			for(int j=M/2; j<M; j++) {
				temp2[tempi][tempj++] = map[i][j];
			}
			tempi++;
		}
		tempi = 0;
		for(int i=N/2; i<N; i++) {
			tempj = 0;
			for(int j=M/2; j<M; j++) {
				temp3[tempi][tempj++] = map[i][j];
			}
			tempi++;
		}
		tempi = 0;
		for(int i=N/2; i<N; i++) {
			tempj = 0;
			for(int j=0; j<M/2; j++) {
				temp4[tempi][tempj++] = map[i][j];
			}
			tempi++;
		}
		tempi = 0;
		for(int i=N/2; i<N; i++) {
			tempj = 0;
			for(int j=0; j<M/2; j++) {
				map[i][j] = temp1[tempi][tempj++];
			}
			tempi++;
		}
		tempi = 0;
		for(int i=0; i<N/2; i++) {
			tempj = 0;
			for(int j=0; j<M/2; j++) {
				map[i][j] = temp2[tempi][tempj++];
			}
			tempi++;
		}
		tempi = 0;
		for(int i=0; i<N/2; i++) {
			tempj = 0;
			for(int j=M/2; j<M; j++) {
				map[i][j] = temp3[tempi][tempj++];
			}
			tempi++;
		}
		tempi = 0;
		for(int i=N/2; i<N; i++) {
			tempj = 0;
			for(int j=M/2; j<M; j++) {
				map[i][j] = temp4[tempi][tempj++];
			}
			tempi++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N M은 2이상 100 이하의 짝수
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		// map 생성
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// R개의 연산 순서대로 수행
		st = new StringTokenizer(br.readLine());
		while(R-->0) {
			int r = Integer.parseInt(st.nextToken());
			if(r == 1) {
				first();
			} else if(r == 2) {
				second();
			} else if(r == 3) {
				third();
			} else if(r == 4) {
				fourth();
			} else if(r == 5) {
				fifth();
			} else if(r == 6) {
				sixth();
			}
		}
		
		// 다 끝나고 결과 출력
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	
	}

}
