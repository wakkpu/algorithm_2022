

import java.io.*;
import java.util.*;

public class BOJ_2456 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] first = new int[n];
		int[] second = new int[n];
		int[] third = new int[n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			first[i] = Integer.parseInt(st.nextToken());
			second[i] = Integer.parseInt(st.nextToken());
			third[i] = Integer.parseInt(st.nextToken());
		}
		
		int firstSum = 0;
		int secondSum = 0;
		int thirdSum = 0;
		
		int firstPow = 0;
		int secondPow = 0;
		int thirdPow = 0;
		
		for(int i=0; i<n; i++) {
			firstSum += first[i];
			firstPow += first[i]*first[i];
			
			secondSum += second[i];
			secondPow += second[i]*second[i];
			
			thirdSum += third[i];
			thirdPow += third[i]*third[i];
		}
		
		int max = Math.max(firstSum, Math.max(secondSum, thirdSum));
		
		if(firstSum > secondSum && firstSum > thirdSum) {
			System.out.print("1 "+max);
		}
		
		if(secondSum > firstSum && secondSum > thirdSum) {
			System.out.print("2 "+max);
		}
		
		if(thirdSum > firstSum && thirdSum > secondSum) {
			System.out.print("3 "+max);
		}
		
		if(firstSum == secondSum && firstSum > thirdSum) {
			if(firstPow > secondPow) {
				System.out.print("1 "+max);
			} else if(firstPow < secondPow) {
				System.out.print("2 "+max);
			} else {
				System.out.println("0 "+max);
			}
		}
		
		if(firstSum == thirdSum && firstSum > secondSum) {
			if(firstPow > thirdPow) {
				System.out.print("1 "+max);
			} else if(firstPow < thirdPow) {
				System.out.print("3 "+max);
			} else {
				System.out.println("0 "+max);
			}
		}
		
		if(secondSum == thirdSum && secondSum > firstSum) {
			if(secondPow > thirdPow) {
				System.out.print("2 "+max);
			} else if(secondPow < thirdPow) {
				System.out.print("3 "+max);
			} else {
				System.out.println("0 "+max);
			}
		}
		
		if(firstSum == secondSum && secondSum == thirdSum) {
			if(firstPow > secondPow && firstPow > thirdPow) {
				System.out.print("1 "+max);
				return;
			}
			if(secondPow > firstPow && secondPow > thirdPow) {
				System.out.print("2 "+max);
				return;
			}
			if(thirdPow > firstPow && thirdPow > secondPow) {
				System.out.print("3 "+max);
				return;
			}
			System.out.println("0 "+max);
		}
	}
}
