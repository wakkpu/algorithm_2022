package SW;

import java.io.*;
import java.util.*;

public class SW_3289 {
	
	static int N, M;
	static int[] rep;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			// Make-Set(x)
			rep = new int[N+1];
			for(int i=1; i<N+1; i++) {
				rep[i] = i;
			}
			
			System.out.print("#"+t+" ");
			M = Integer.parseInt(st.nextToken());
			for(int m=0; m<M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				if(a == 0) {
					Union(b, c);
				} else if(a == 1) {
					int x = Find(b);
					int y = Find(c);
					
					if(x == y) {
						System.out.print("1");
					} else {
						System.out.print("0");
					}
				}
			}
			System.out.println();
		}
	}
	
	public static int Find(int x) {
		if(x == rep[x])
			return x;
		
		else
			return rep[x] = Find(rep[x]);
	}
	
	public static void Union(int x, int y) {
		if(Find(x) == Find(y))
			return;
		
		else
			rep[Find(x)] = Find(y);
	}

}
