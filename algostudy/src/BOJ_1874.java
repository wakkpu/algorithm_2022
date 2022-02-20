

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class BOJ_1874 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// 입력: 정답 수열의 크기
		int n = input.nextInt();
		
		// 입력: 정답 수열 arr
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = input.nextInt();
		}
		
		input.close();
		
		// 사용할 stack
		Stack<Integer> stack = new Stack<>();
		
		// 출력할 String -> String 사용하면 메모리 초과가 난다고 한다. ArrayList로 변경하여 해결
		ArrayList<String> str = new ArrayList<>();
		
		// push값은 1부터 오름차순만 가능 -> push n번으로 정답 수열을 만들 수 있는가?
		int val = 1;
		
		// pop은 언제 해야하는가? -> arr[i]의 값이 stack의 peek와 같아졌을 때
		// push는 언제 해야하는가? -> val가 arr[i] 이하일 때
		for(int i=0; i<n; i++) {
			while(val <= arr[i]) {
				stack.push(val++);
				str.add("+");
			}
			
			if(stack.peek() == arr[i]) {
				stack.pop();
				str.add("-");
			} else {
				System.out.println("NO");
				return;
			}
		}
		for(int i=0; i<str.size(); i++) {
			System.out.println(str.get(i));
		}
	}
}
