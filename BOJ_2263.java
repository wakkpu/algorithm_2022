package jungol.language_coder;

import java.io.*;
import java.util.*;

public class BOJ_2263 {

	static int n;
	static int[] in;
	static int[] inIdx;
	static int[] post;
	
	public static void preOrder(int leftStart, int leftEnd, int rightStart, int rightEnd) {
		if(leftStart >= leftEnd || rightStart >= rightEnd)
			return;
		
		int root = post[rightEnd-1];
		System.out.print(root+" ");
		
		int rootIdx = inIdx[root];
		
		preOrder(leftStart, rootIdx, rightStart, rootIdx - leftStart + rightStart);
		preOrder(rootIdx+1, leftEnd, rootIdx - leftStart + rightStart, rightEnd-1);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		in = new int[n+1];
		inIdx = new int[n+1];
		post = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			in[i] = Integer.parseInt(st.nextToken());
			inIdx[in[i]] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			post[i] = Integer.parseInt(st.nextToken());
		}
		
		preOrder(0, n, 0, n);
	}

}
