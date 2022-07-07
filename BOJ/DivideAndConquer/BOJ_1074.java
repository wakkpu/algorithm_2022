package BOJ.DivideAndConquer;

import java.io.*;
import java.util.*;

public class BOJ_1074 {

	static int num;
	
	public static void Z(int n, int r, int c) {
		if(n == 1) {
			return;
		}
		
		// 1사분면
		if(r < n/2 && c < n/2) {
			Z(n/2, r, c);
		}
		// 2사분면
		else if(r < n/2 && n/2 <= c) {
			num += n*n/4;
			Z(n/2, r, c-n/2);
		}
		// 4사분면
		else if(n/2 <= r && c < n/2) {
			num += (n*n/4) * 2;
			Z(n/2, r-n/2, c);
		}
		// 3사분면
		else {
			num += (n*n/4) * 3;
			Z(n/2, r-n/2, c-n/2);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		n = 1 << n;
		
		Z(n, r, c);
		
		System.out.println(num);
	}

}
