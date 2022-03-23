package BOJ;

import java.io.*;
import java.util.*;

/**
 * 질문: 왜 iList랑 jList가 초기화되지 않고 누적되는지. 왜 flag가 갱신이 안되는지 (아마 같은 문제인 듯)
 * @author owner
 *
 */
public class BOJ_17822 {

	static int N, M, T;
	static int[][] Circles;
	static int[][] Rolls;
	static boolean flag;
	
	public static void Roll(int t) {
		int xi = Rolls[t][0];
		int di = Rolls[t][1];
		int ki = Rolls[t][2];

		// i번째 원판 회전
		for (int i = 1; i < N + 1; i++) {
			// 번호가 xi의 배수인 i번째 원판을
			if (i % xi == 0) {
				// di가 0이면 시계방향으로 ki번 회전
				if (di == 0) {
					for (int k = 0; k < ki; k++) {
						int temp = Circles[i][M];
						for (int j = M; j > 1; j--) {
							Circles[i][j] = Circles[i][j - 1];
						}
						Circles[i][1] = temp;
					}
				// di가 1이면 반시계방향으로 ki번 회전
				} else if (di == 1) {
					for (int k = 0; k < ki; k++) {
						int temp = Circles[i][1];
						for (int j = 1; j < M; j++) {
							Circles[i][j] = Circles[i][j + 1];
						}
						Circles[i][M] = temp;
					}
				}
				// System.out.println(i+"번째 원판 회전 후: "+Arrays.toString(Circles[i]));
			}
		}
		// 회전 후 상태
		/*System.out.println((t+1)+"번째 회전 후");
		for (int i = 1; i < N + 1; i++) {
			System.out.println(Arrays.toString(Circles[i]));
		}*/
	}
	
	public static void Check() {
		Queue<Integer> iList = new LinkedList<>();
		Queue<Integer> jList = new LinkedList<>();
		flag = false;
		
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				int curr = Circles[i][j];
				if(curr != 0) {
					// (i, j)는 (i-1, j), (i+1, j)와 인접. 2 <= i <= N-1
					if (2 <= i && i <= N - 1) {
						if (curr == Circles[i - 1][j]) {
							iList.add(i); jList.add(j);
							iList.add(i-1); jList.add(j);
						}
						if (curr == Circles[i + 1][j]) {
							iList.add(i); jList.add(j);
							iList.add(i+1); jList.add(j);
						}
					}
					// (i, j)는 (i, j-1), (i, j+1)과 인접. 2 <= j <= M-1
					if (2 <= j && j <= M - 1) {
						if (curr == Circles[i][j - 1]) {
							iList.add(i); jList.add(j);
							iList.add(i); jList.add(j-1);
						}
						if (curr == Circles[i][j + 1]) {
							iList.add(i); jList.add(j);
							iList.add(i); jList.add(j+1);
						}
					}
					// (1, j)는 (2, j)와 인접
					if (i == 1) {
						if (curr == Circles[2][j]) {
							iList.add(i); jList.add(j);
							iList.add(2); jList.add(j);
						}
					}
					// (N, j)는 (N-1, j)와 인접
					if (i == N) {
						if (curr == Circles[N - 1][j]) {
							iList.add(i); jList.add(j);
							iList.add(N-1); jList.add(j);
						}
					}
					// (i, 1)은 (i, 2), (i, M)과 인접
					if (j == 1) {
						if (curr == Circles[i][2]) {
							iList.add(i); jList.add(j);
							iList.add(i); jList.add(2);
						}
						if (curr == Circles[i][M]) {
							iList.add(i); jList.add(j);
							iList.add(i); jList.add(M);
						}
					}
					// (i, M)은 (i, M-1), (i, 1)과 인접
					if (j == M) {
						if (curr == Circles[i][M - 1]) {
							iList.add(i); jList.add(j);
							iList.add(i); jList.add(M-1);
						}
						if (curr == Circles[i][1]) {
							iList.add(i); jList.add(j);
							iList.add(i); jList.add(1);
						}
					}
				}
			}
		}
		
		// 인접한 수가 있다면 변경.
		if(iList.size() >= 1 && jList.size() >= 1) {
			//System.out.println("변경할 i좌표: "+iList.toString());
			//System.out.println("변경할 j좌표: "+jList.toString());
			flag = true;
			// for(int i=0; i<iList.size(); i++) {
			while(!iList.isEmpty()) {
				Circles[iList.poll()][jList.poll()] = 0;
			}
			// System.out.println("polling 후 크기: "+iList.size());
		}
	}
	
	public static void after() {
		// 인접한 같은 수가 없다면 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
		if (flag == false) {
			double avg = 0;
			int num = 0;
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < M + 1; j++) {
					if (Circles[i][j] != 0) {
						avg += Circles[i][j];
						num++;
					}
				}
			}
			avg /= num;
			//System.out.println("평균: " + avg);
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < M + 1; j++) {
					if(Circles[i][j] != 0) {
						if (Circles[i][j] > avg) {
							Circles[i][j]--;
						} else if(Circles[i][j] < avg) {
							Circles[i][j]++;
						}
					}
				}
			}
			// 인접한 수 바꾼 후 상태
			/*System.out.println("인접한 같은 수 X");
			for (int i = 1; i < N + 1; i++) {
				System.out.println(Arrays.toString(Circles[i]));
			}*/
		} /*else {
			System.out.println("인접한 같은 수 O");
			for (int i = 1; i < N + 1; i++) {
				System.out.println(Arrays.toString(Circles[i]));
			}
		}*/
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		Circles = new int[N + 1][M + 1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < M + 1; j++) {
				Circles[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Rolls = new int[T][3];
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				Rolls[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// t번의 싸이클
		for(int t=0; t<T; t++) {
			
			Roll(t);
			
			Check();
			
			after();
		}

		// t번 회전 후 원판에 적힌 수의 합 출력
		int sol = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				sol += Circles[i][j];
			}
		}
		System.out.println(sol);
	}
}
