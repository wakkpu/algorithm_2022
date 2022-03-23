package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_6603 {

	static ArrayList<String> numbers;
	
	public static void comb(int cnt, int start) {
		
		for(int i=start; i<numbers.size(); i++) {
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			numbers = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			String tok = st.nextToken();
			
			if(tok.equals("0")) break;
			while(st.hasMoreTokens()) {
				tok = st.nextToken();
				numbers.add(tok);
			}
			Collections.sort(numbers);
			
			
		}
	}
}
