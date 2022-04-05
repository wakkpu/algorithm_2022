package SW;

import java.io.*;
import java.util.*;

public class SW_5644 {
	
	static int[] dy = {0, -1, 0, 1, 0};
	static int[] dx = {0, 0, 1, 0, -1};
	static BC[] bc;
	
	static class BC {
		int x;
		int y;
		int c;
		int p;
		
		public BC(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
		
		@Override
		public String toString() {
			return "BC [x=" + x + ", y=" + y + ", c=" + c + ", p=" + p + "]";
		}
	}
	
	static class User {
		public int x;
		public int y;
		
		public User(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return "User [x=" + x + ", y=" + y + "]";
		}

		public void move(int dir) {
			this.x += dx[dir];
			this.y += dy[dir];
		}
		
		// i번째 BC와 연결가능한지 확인
		public boolean check(int i) {
			BC curr = bc[i];
			return Math.abs(this.x - curr.x) + Math.abs(this.y - curr.y) <= curr.c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 총 move 횟수
			int A = Integer.parseInt(st.nextToken()); // BC의 갯수
			
			st = new StringTokenizer(br.readLine());
			int[] user1Move = new int[M]; // user1의 move 정보 저장
			for(int m=0; m<M; m++) {
				user1Move[m] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			int[] user2Move = new int[M]; // user2의 move 정보 저장
			for(int m=0; m<M; m++) {
				user2Move[m] = Integer.parseInt(st.nextToken());
			}
			
			bc = new BC[A]; // BC의 정보 저장
			for(int a=0; a<A; a++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bc[a] = new BC(x-1, y-1, c, p);
			}
			
			// 사용자1은 (0, 0), 사용자2는 (9, 9)에서 시작함.
			User user1 = new User(0, 0);
			User user2 = new User(9, 9);
			int sum = 0; // 두 사용자의 충전량 합
			
			for(int i=0; i<M; i++) {
				boolean[] connect1 = new boolean[A];
				boolean[] connect2 = new boolean[A];
				
				// 각 BC에 대해 충전 가능한지 체크.
				for(int j=0; j<A; j++) {
					connect1[j] = user1.check(j);
					connect2[j] = user2.check(j);
				}
				// 충전 가능하다면 충전
				// 한 개의 BC에 둘 연결 시 -> 충전량 절반
				int max = 0;
				for(int j=0; j<A; j++) { // user1에 대해
					for(int k=0; k<A; k++) { // user2에 대해
						int temp = 0;
						if(j == k && connect1[j] && connect2[k]) {
							temp = bc[j].p; // bc[j]와 bc[k]가 어차피 같음
						} else {
							if(connect1[j]) {
								temp += bc[j].p;
							}
							if(connect2[k]) {
								temp += bc[k].p;
							}
						}
						max = Math.max(temp, max);
					}
				}
				sum += max;
				
				// 매 초 user의 move 정보를 받아 user 이동.
				user1.move(user1Move[i]);
				user2.move(user2Move[i]);
			}
			
			// 검사하고 이동하므로 마지막 위치에서 다시 검사해줘야 함
			boolean[] connect1 = new boolean[A];
			boolean[] connect2 = new boolean[A];
			
			// 각 BC에 대해 충전 가능한지 체크.
			for(int j=0; j<A; j++) {
				connect1[j] = user1.check(j);
				connect2[j] = user2.check(j);
			}
			// 충전 가능하다면 충전
			// 한 개의 BC에 둘 연결 시 -> 충전량 절반
			int max = 0;
			for(int j=0; j<A; j++) { // user1에 대해
				for(int k=0; k<A; k++) { // user2에 대해
					int temp = 0;
					if(j == k && connect1[j] && connect2[k]) {
						temp = bc[j].p; // bc[j]와 bc[k]가 어차피 같음
					} else {
						if(connect1[j]) {
							temp += bc[j].p;
						}
						if(connect2[k]) {
							temp += bc[k].p;
						}
					}
					max = Math.max(temp, max);
				}
			}
			sum += max;
			
			// 테케 하나 완료. 결과 출력
			System.out.println("#"+t+" "+(sum));
		}
	}
}
