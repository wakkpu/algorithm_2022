package BOJ.Simulation;

import java.io.*;
import java.util.*;

public class BOJ_2309 {
		
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int sum = 0;
		int[] input = new int[9];
		for(int i=0; i<9; i++) {
			input[i] = Integer.parseInt(br.readLine());
			sum += input[i];
		}
		
		int num1 = 0;
		int num2 = 0;
		for(int i=0; i<8; i++) {
			for(int j=i+1; j<9; j++) {
				if(sum - (input[i]+input[j]) == 100) {
					num1 = i;
					num2 = j;
				}
			}
		}
		
		ArrayList<Integer> sol = new ArrayList<>();
		for(int i=0; i<9; i++) {
			if(i == num1 || i == num2) {
				continue;
			} else {
				sol.add(input[i]);
			}
		}
		
		Collections.sort(sol);
		for(int i=0; i<7; i++) {
			System.out.println(sol.get(i));
		}
		
	}

}
