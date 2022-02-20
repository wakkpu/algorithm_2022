

import java.io.*;
import java.util.*;

public class BOJ_1043 {

	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 사람의 수
		int m = Integer.parseInt(st.nextToken()); // 파티의 수 -> 거짓말할 수 있는 파티의 수
		
		st = new StringTokenizer(br.readLine());
		int numTruth = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수
		
		if(numTruth == 0) { // 진실을 아는 사람의 수가 0이면 종료
			System.out.println(m);
			return;
		}
		
		// 진실을 아는 사람들의 번호를 입력
		ArrayList<Integer> firstTruth = new ArrayList<Integer>();
		for(int i=0; i<numTruth; i++) {
			firstTruth.add(Integer.parseInt(st.nextToken()));
		}
		
		// 파티마다 오는 사람의 수와 번호
		ArrayList<ArrayList<Integer>> party = new ArrayList<ArrayList<Integer>>();
		// 진실을 알게 된 사람들 (처음부터 알고 있던 사람 포함)
		HashSet<Integer> lateTruth = new HashSet<Integer>();
		
		for(int i=0; i<m; i++) {
			ArrayList<Integer> p = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			// 파티에 오는 사람의 수
			int numParty = Integer.parseInt(st.nextToken());
			for(int j=0; j<numParty; j++) {
				// 사람 수 만큼 파티에 오는 사람의 번호
				int person = Integer.parseInt(st.nextToken());
				lateTruth.add(person);
				p.add(person);
			}
			party.add(p);
		}
		
		// 진실 알게 된 사람들을 파티의 사람들과 비교. 파티에 진실을 알게 된 사람들이 포함됐다면 그 파티에서 거짓말 못함
		Iterator<Integer> it = lateTruth.iterator();
		
		
		System.out.println(m);
	}
}
