import java.io.*;
import java.util.*;

public class BOJ_1759 {

	static int L, C;
	static String[] table;

	public static void combi(int toChoose, String[] choosed, int startIdx) {
		if(toChoose == 0) {
			StringBuilder sb = new StringBuilder();
			int numConso = 0;
			int numVowel = 0;
			for(int i=0; i<choosed.length; i++) {
				if(choosed[i].equals("a") || choosed[i].equals("e") || choosed[i].equals("i") || choosed[i].equals("o") || choosed[i].equals("u")) {
					numVowel++;
				} else {
					numConso++;
				}
				sb.append(choosed[i]);
			}
			if(2 <= numConso && 1 <= numVowel) {
				System.out.println(sb);
			}
			return;
		}
		
		for(int i=startIdx; i<C; i++) {
			choosed[choosed.length - toChoose] = table[i];
			combi(toChoose-1, choosed, i+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		table = new String[C];
		for (int i = 0; i < C; i++) {
			table[i] = st.nextToken();
		}
		Arrays.sort(table);

		combi(L, new String[L], 0);
		
		// 암호는 서로 다른 L개의 알파벳으로 구성. 암호의 후보는 총 C개
		// 최소 1개의 모음(a, e, i, o, u)과 최소 2개의 자음으로 구성
		// 암호를 이루는 알파벳이 증가하는 순서로 배열
		
		/*
		 * 이진 트리 구현
		 * 트리의 깊이는 table의 원소별로 타고 들어감
		 * 왼쪽 자식 노드는 해당 table의 원소를 뽑는 경우
		 * 오른쪽 자식 노드는 해당 table의 원소를 뽑지 않는 경우
		 * 문제의 조건을 만족하는 경우 return;
		 */
		
	}
}
