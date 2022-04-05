package SW;

import java.io.*;
import java.util.*;

public class SW_1228 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		int t = 1;
		while(true) {
			LinkedList<String> list = new LinkedList<>();
			// 원본 암호문의 길이 n
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			// 원본 암호문
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				list.add(st.nextToken());
			}
			
			// 명령어의 개수 m
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			
			// 명령어
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<m; i++) {
				String I = st.nextToken();
				int idx = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				
				for(int j=0; j<num; j++) {
					list.add(idx++, st.nextToken());
				}
			}
			
			System.out.print("#"+(t++)+" ");
			for(int i=0; i<10; i++) {
				System.out.print(list.poll()+" ");
			}
			System.out.println();
			
			if(!br.ready())
				return;
		}
	}
}
