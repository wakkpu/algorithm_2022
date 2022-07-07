package BOJ.Simulation;

import java.io.*;
import java.util.*;

public class BOJ_2742 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		while(n > 0) {
			System.out.println(n--);
		}
	}
}
