package BOJ.Greedy;

import java.io.*;
import java.util.*;

public class BOJ_1931 {

	static class Meet implements Comparable<Meet> {

		public int start;
		public int end;

		public Meet(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meet o) {
			if(this.end == o.end)
				return Integer.compare(this.start, o.start);
			return Integer.compare(this.end, o.end);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 
		 int n = Integer.parseInt(st.nextToken());
		 
		 Meet[] meet = new Meet[n];
		 
		 for(int i=0; i<n; i++) {
			 st = new StringTokenizer(br.readLine());
			 meet[i] = new Meet(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		 }
		 
		 Arrays.sort(meet);
		 
		 /*
		 for(int i=0; i<n; i++) {
			 System.out.println(meet[i].start+" "+meet[i].end);
		 }*/
		 
		 int count = 0;
		 
		 // 가장 늦은 종료 시각
		 int latest = 0;
		 for(int i=0; i<n; i++) {
			 if(latest <= meet[i].start) {
				 latest = meet[i].end;
				 count++;
			 }
		 }
		 System.out.println(count);
	}
}
