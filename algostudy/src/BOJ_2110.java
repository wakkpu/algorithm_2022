import java.io.*;
import java.util.*;

public class BOJ_2110 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] homes = new int[N];
		for(int i=0; i<N; i++) {
			homes[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(homes);
		
		int left = 0;
		int right = N-1;
		int result = 0;
		
		while(left <= right) {
			
			int mid = (left + right) / 2;
			
			for(int i=left; i<=right; i++) {
				
			}
		}
	}

}
