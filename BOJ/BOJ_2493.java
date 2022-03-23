package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2493 {

	static class Tower {
		int num;
		int height;

		Tower(int num, int height) {
			this.num = num;
			this.height = height;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		// 탑의 개수 n 입력
		int n = Integer.parseInt(input.readLine());
		
		StringTokenizer tokens = new StringTokenizer(input.readLine());

		Stack<Tower> stack = new Stack<>();
		// 입력의 각 원소에 대해 시행
		for(int i=1; i<=n; i++) {
			int height = Integer.parseInt(tokens.nextToken());
			
			// 스택에 뭔가 있다면
			while(!stack.isEmpty()) {
				// 현재 원소가 스택의 맨 윗값 높이보다 작으면 신호 수신
				if(stack.peek().height >= height) {
					System.out.print(stack.peek().num + " ");
					break;
				// 현재 원소가 더 크면 수신 불가. 스택의 값 필요 없으므로 치움
				} else {
					stack.pop();
				}
			}
			if(stack.isEmpty()) {
				System.out.print("0 ");
			}
			// 현재 원소 push
			stack.push(new Tower(i, height));
		}
	}
}
