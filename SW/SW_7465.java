package SW;

import java.io.*;
import java.util.*;

public class SW_7465 {
	
	static int N, M;
	static int rep[];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			rep = new int[N+1];
			for(int i=1; i<N+1; i++) {
				rep[i] = i;
			}
			
			for(int m=0; m<M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				Union(a, b);
			}
			
			HashSet<Integer> set = new HashSet<>();
			for(int i=1; i<N+1; i++) {
				set.add(Find(i));
			}
			
			System.out.println("#"+t+" "+set.size());
		}

	}
	
	public static int Find(int x) {
		
		if(x == rep[x]) return x;
		
		else return rep[x] = Find(rep[x]);
	}
	
	public static void Union(int x, int y) {
		if(Find(x) == Find(y))
			return;
		
		else
			rep[Find(x)] = Find(y);
	}

}
