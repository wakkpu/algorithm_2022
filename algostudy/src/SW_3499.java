import java.io.*;
import java.util.*;

public class SW_3499 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			String[] cards = new String[n];
			for(int i=0; i<n; i++) {
				cards[i] = st.nextToken();
			}
			
			Queue<String> q1 = new LinkedList<>();
			Queue<String> q2 = new LinkedList<>();
			
			if(n%2 == 0) {
				for(int i=0; i<n/2; i++) {
					q1.add(cards[i]);
				}
	
				for(int i=n/2; i<n; i++) {
					q2.add(cards[i]);
				}
			} else {
				for(int i=0; i<n/2+1; i++) {
					q1.add(cards[i]);
				}
	
				for(int i=n/2+1; i<n; i++) {
					q2.add(cards[i]);
				}
			}
			
			System.out.print("#"+t+" ");
			while(!q1.isEmpty()) {
				System.out.print(q1.poll()+" ");
				if(!q2.isEmpty())
					System.out.print(q2.poll()+" ");
			}
			System.out.println();
		}
	}

}
