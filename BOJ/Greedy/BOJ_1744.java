package BOJ.Greedy;

import java.io.*;
import java.util.*;

public class BOJ_1744 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> pos = new ArrayList<Integer>();
		ArrayList<Integer> neg = new ArrayList<Integer>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			if(num > 0) {
				pos.add(num);
			}
			else {
				neg.add(num);
			}
		}
		
		pos.sort(Comparator.reverseOrder());
		neg.sort(Comparator.naturalOrder());
		
		int sol = 0;
		
		int i = 0;
		while(i < pos.size()) {
			if(i+1 < pos.size()) {
				if(pos.get(i) == 1 || pos.get(i+1) == 1) {
					sol += (pos.get(i) + pos.get(i+1));	
				}
				else {
					sol += (pos.get(i) * pos.get(i+1));
				}
				i += 2;
			}
			else {
				sol += pos.get(i);
				i++;
			}
		}
		
		int j = 0;
		while(j < neg.size()) {
			if(j+1 < neg.size()) {
				sol += (neg.get(j) * neg.get(j+1));
				j += 2;
			}
			else {
				sol += neg.get(j);
				j++;
			}
		}
		
		System.out.println(sol);
	}
}
