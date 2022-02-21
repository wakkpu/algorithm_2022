import java.io.*;
import java.util.*;

public class SW_9229 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int[] snack = new int[n];
			for(int i=0; i<n; i++) {
				snack[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = -1;
			
			for(int i=0; i<n-1; i++) {
				for(int j=i+1; j<n; j++) {
					int curr = snack[i]+snack[j];
					if(curr > m) continue;
					else max = Math.max(max, curr);
				}
			}
			
			System.out.println("#"+t+" "+max);
		}
	}

}
