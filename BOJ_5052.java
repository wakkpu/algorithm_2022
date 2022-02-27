import java.io.*;
import java.util.*;

public class BOJ_5052 {
	
	static String[] numbers;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			
			numbers = new String[N];
			for(int i=0; i<N; i++) {
				numbers[i] = br.readLine();
			}
			Arrays.sort(numbers);
			
			if(checkDuplicate()) {
				System.out.println("NO");
			} else {
				System.out.println("YES");
			}
		}
	}
	
	public static boolean checkDuplicate() {
		for(int i=0; i<numbers.length-1; i++) {
			if(numbers[i+1].startsWith(numbers[i])) {
				return true;
			}
		}
		return false;
	}
}
