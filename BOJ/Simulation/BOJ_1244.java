package BOJ.Simulation;

import java.io.*;
import java.util.*;

public class BOJ_1244 {
	
	static int numSwitches, numStudents;
	static int switches[], students[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		numSwitches = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		switches = new int[numSwitches+1];
		for(int i=1; i<numSwitches+1; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		numStudents = Integer.parseInt(st.nextToken());
		
		students = new int[numStudents][2];
		
		for(int i=0; i<numStudents; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			students[i][0] = a; // 성별
			students[i][1] = b; // 받은 스위치 번호
		}
		
		for(int i=0; i<numStudents; i++) {
			if(students[i][0] == 1) { // 남학생
				int num = students[i][1]; // 받은 번호
				
				int j = 1;
				while(num*j <= numSwitches) {
					if(switches[num*j] == 1) switches[num*j] = 0;
					else switches[num*j] = 1;
					j++;
				}
			} else { // 여학생
				int num = students[i][1];
				
				if(switches[num] == 1) switches[num] = 0;
				else switches[num] = 1;
				
				int left = num-1;
				int right = num+1;
				
				while(true) {
					if(left < 1 || right > numSwitches) break;
					
					if(switches[left] == switches[right]) {
						if(switches[left] == 0) switches[left] = 1;
						else switches[left] = 0;
						
						if(switches[right] == 0) switches[right] = 1;
						else switches[right] = 0;
						
						left--;
						right++;
					} else {
						break;
					}
				}
			}
		}
		
		for(int i=1; i<numSwitches+1; i++) {
			System.out.print(switches[i]+" ");
			if(i % 20 == 0) System.out.println();
		}
	}
}
