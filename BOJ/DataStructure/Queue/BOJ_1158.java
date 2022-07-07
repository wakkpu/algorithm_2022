package BOJ.DataStructure.Queue;

import java.io.*;
import java.util.*;

public class BOJ_1158 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<=n; i++) {
			q.add(i);
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		int term = 1;
		while(!q.isEmpty()) {
			if(term == k) {
				list.add(q.poll());
				term = 1;
			} else {
				q.add(q.poll());
				term++;
			}
			//System.out.println(term+" "+q.toString());
		}
		
		System.out.print("<");
		for(int i=0; i<list.size()-1; i++) {
			System.out.print(list.get(i)+", ");
		}
		System.out.print(list.get(list.size()-1)+">");
	}

}
