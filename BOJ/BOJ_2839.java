package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2839 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int num = 0;
		while(N >= 0) {
			if(N%5 == 0) {
				num += N/5;
				System.out.println(num);
				break;
			} else {
				N -= 3;
				num++;
			}
			
			if(N < 0) {
				System.out.println(-1);
				break;
			}
		}
	}
}
