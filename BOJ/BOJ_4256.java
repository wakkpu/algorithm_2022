package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_4256 {
	
	static int n, t;
	static int[] pre;
	static int[] in;
	
	public static void postOrder(int node, int start, int end) {
		if(node >= n)
			return;
		
		for(int i=start; i< end; i++) {
			if(in[i] == pre[node]) {
				postOrder(node+1, start, i);
				postOrder(node-start+i+1, i+1, end);
				System.out.printf(pre[node]+" ");
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens = new StringTokenizer(input.readLine());
		t = Integer.parseInt(tokens.nextToken());
		
		for(int i=0; i<t; i++) {
			tokens = new StringTokenizer(input.readLine());
			n = Integer.parseInt(tokens.nextToken());
			
			pre = new int[n];
			in = new int[n];
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<n; j++) {
				pre[j] = Integer.parseInt(tokens.nextToken());
			}
			tokens = new StringTokenizer(input.readLine());
			for(int j=0; j<n; j++) {
				in[j] = Integer.parseInt(tokens.nextToken());
			}
			postOrder(0, 0, n);
			System.out.println();
		}
		
	}

}
