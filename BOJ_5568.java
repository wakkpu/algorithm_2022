package jungol.language_coder;

import java.io.*;
import java.util.*;

public class BOJ_5568 {

	static int N, K;
	static ArrayList<String> cards;
	static boolean[] visited;
	static HashSet<String> numbers;
	
	public static void perm(int cnt, String num) {
		if(cnt == K) {
			numbers.add(num);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i] == true)
				continue;
			
			visited[i] = true;
			perm(cnt+1, num+cards.get(i));
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		cards = new ArrayList<>();
		visited = new boolean[N+1];
		numbers = new HashSet<>();
		
		for(int i=0; i<N; i++) {
			cards.add(br.readLine());
		}
		
		perm(0, "");
		System.out.println(numbers.size());
		
	}

}
