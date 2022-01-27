package jungol.language_coder;

import java.io.*;
import java.util.*;

public class BOJ_2812 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<>();
		
		String str = br.readLine();
		int n = Integer.parseInt(str.split(" ")[0]);
		int k = Integer.parseInt(str.split(" ")[1]);
		
		int count = 0;
		str = br.readLine();
		for(int i=0; i<n; i++) {
			while(!stack.isEmpty() && count < k && stack.peek() < str.charAt(i)) {
				stack.pop();
				count++;
			}
			stack.push(str.charAt(i));
		}
		
		// 이걸 놓쳤네 ㅠ k개만큼 빼지 못한 경우 stack에서 억지로 빼야함
		while(stack.size() != n-k) {
			stack.pop();
		}
		
		StringBuilder sol = new StringBuilder();
		
		while(!stack.isEmpty()) {
			sol.append(stack.pop());
		}
		
		System.out.print(sol.reverse());
	}
}
