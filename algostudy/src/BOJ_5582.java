

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// DP를 table이 아닌 list로 만들고 two pointer(i, j)로 접근
// -> table로 푸니까 간단히 풀어짐
public class BOJ_5582 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		int m = str1.length();
		int n = str2.length();
		
		int[][] DP = new int[n+1][m+1];
		
		int max = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(str2.charAt(i) == str1.charAt(j)) {
					DP[i+1][j+1] = DP[i][j] + 1;
					if(DP[i+1][j+1] > max)
						max = DP[i+1][j+1];
				}
			}
		}
		
		System.out.println(max);
	}
}
