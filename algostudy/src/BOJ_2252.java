import java.io.*;
import java.util.*;

public class BOJ_2252 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] depth = new int[N+1];
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<=N+1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			depth[to]++;
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1; i<depth.length; i++) {
			if(depth[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int std = q.poll();
			System.out.print(std+" ");
			
			List<Integer> list = graph.get(std);
			
			for(int i=0; i<list.size(); i++) {
				int temp = list.get(i);
				depth[temp]--;
				if(depth[temp] == 0) {
					q.offer(temp);
				}
			}
		}
	}
}
