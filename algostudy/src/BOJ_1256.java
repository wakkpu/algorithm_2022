

import java.io.*;
import java.util.*;

public class BOJ_1256 {
	
	static ArrayList<String> dict;
	/*
	 * kthString(numA, numZ) = A+kthString(numA, numZ-1) + Z+kthString(numA-1, numZ) ?
	 */
	public static String kthString(int numA, int numZ) {
		if(numA == 0)
			return "z";
		
		if(numZ == 0)
			return "a";
		
		return kthString(numA, numZ-1)+kthString(numA-1, numZ);
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // a의 개수
		int m = Integer.parseInt(st.nextToken()); // z의 개수
		int k = Integer.parseInt(st.nextToken()); // 찾고자 하는 문자열의 번호
		
		dict = new ArrayList<>();
		
	}

}
