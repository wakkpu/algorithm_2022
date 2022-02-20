import java.io.*;
import java.util.*;

public class BOJ_17471 {
	
	static int N;
	static int minDiff = Integer.MAX_VALUE;
	static ArrayList<Integer>[] list;
	static Node[] nodes;
	
	static class Node {
		int num;
		int popul;
		
		public Node(int num, int popul) {
			this.num = num;
			this.popul = popul;
		}
	}
	
	// 조합으로 지역 뽑음
	public static void comb(int toChoose, ArrayList<Integer> choosed, int startIdx) {
		if(toChoose == 0) {
			calcPopulDiff(choosed);
			return;
		}
		
		for(int i=startIdx; i<=N; i++) {
			choosed.add(i);
			comb(toChoose-1, choosed, startIdx+1);
			choosed.remove(Integer.valueOf(i));
		}
	}
	
	// 뽑은 지역이 연결되었는지 확인
	public static boolean isConnected(int num, ArrayList<Integer> choosed, int size) {
		
		int count = 1;
		
		boolean[] visited = new boolean[N+1];
		visited[num] = true;
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(num);
		while(!q.isEmpty()) {
			int start = q.poll();
			
			for(int i: list[start]) {
				if(!visited[i] && choosed.contains(i)) {
					visited[i] = true;
					count++;
					q.offer(i);
				}
			}
		}
		
		if(count == size) return true;
		else return false;
	}
	
	// 뽑은 지역과 안 뽑은 지역의 인구수 차이 계산
	public static void calcPopulDiff(ArrayList<Integer> choosed) {
		if(!isConnected(nodes[choosed.get(0)].num, choosed, choosed.size())) return;
		
		ArrayList<Integer> notChoosed = new ArrayList<>();
		for(int i=1; i<N+1; i++) {
			if(choosed.contains(i)) continue;
			else notChoosed.add(i);
		}
		
		if(!isConnected(nodes[notChoosed.get(0)].num, notChoosed, notChoosed.size())) return;
		
		int a = 0;
		for(int i=0; i<choosed.size(); i++) {
			a += nodes[choosed.get(i)].popul;
		}
		
		int b = 0;
		for(int i=0; i<notChoosed.size(); i++) {
			b += nodes[notChoosed.get(i)].popul;
		}
		
		int diff = Math.abs(a - b);
		minDiff = Math.min(diff, minDiff);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 첫째 줄
		
		nodes = new Node[N+1];
		st = new StringTokenizer(br.readLine()); // 둘째줄
		for(int i=1; i<N+1; i++) {
			int population = Integer.parseInt(st.nextToken());
			nodes[i] = new Node(i, population);
		}
		
		list = new ArrayList[N+1];
		// list[i]은 i번째 node와 연결된 node들의 번호
		for(int from=1; from<N+1; from++) {
			list[from] = new ArrayList<>();
			st = new StringTokenizer(br.readLine()); // 셋째줄부터 N개의 줄
			int num = Integer.parseInt(st.nextToken()); // 첫번째 수 만큼 loop
			for(int j=0; j<num; j++) {
				int to = Integer.parseInt(st.nextToken());
				list[from].add(to);
			}
		}
		
		for(int i=1; i<=N/2; i++) {
			comb(i, new ArrayList<>(), 1);
		}
		
		if(minDiff == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(minDiff);
		
	}
}
