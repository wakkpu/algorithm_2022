import java.io.*;
import java.util.*;

public class SW_1225 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		
		while(true) {
			String str = br.readLine();
			
			st = new StringTokenizer(str);
			
			int t = Integer.parseInt(st.nextToken());
			
			str = br.readLine();
			st = new StringTokenizer(str);
			
			Queue<Integer> q = new LinkedList<>();
			while(st.hasMoreTokens()) {
				q.add(Integer.parseInt(st.nextToken()));
			}
			
			int ch = 1;
			while(true) {
				int val = q.poll();
				val -= ch++;
				
				if(ch == 6)
					ch = 1;
				
				if(val <= 0) {
					q.add(0);
					break;
				} else {
					q.add(val);
				}
			}
			
			bw.write("#"+t+" ");
			while(!q.isEmpty()) {
				bw.write(q.poll()+" ");
			}
			bw.write("\n");
			bw.flush();
			
			if(!br.ready())
				return;
		}
	}
}
