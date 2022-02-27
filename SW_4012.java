import java.io.*;
import java.util.*;

public class SW_4012 {
	
	static int N;
	static int MAX;
	static int[][] synergy;
	
	public static int getSatis(int[] combi) {
		int satis = 0;
		for(int i=0; i<combi.length; i++) {
			for(int j=0; j<combi.length; j++) {
				satis += synergy[combi[i]][combi[j]];
			}
		}
		return satis;
	}
	
	public static void combi(int toChoose, int[] choosed, int startIdx) {
		if(toChoose == 0) {
			int[] otherChoosed = new int[N/2];
			for(int i=0, k=0; i<N; i++) {
				boolean contains = false;
				for(int j=0; i<choosed.length; j++) {
					if(choosed[j] == i) {
						contains = true;
						break;
					}
				}
				if(!contains) {
					otherChoosed[k++] = i;
				}
			}
			int satA = getSatis(choosed);
			int satB = getSatis(otherChoosed);
			MAX = Math.max(MAX, Math.abs(satA - satB));
			return;
		}
		
		for(int i=startIdx; i<N; i++) {
			choosed[choosed.length - toChoose] = i;
			combi(toChoose-1, choosed, i+1);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			synergy = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			combi(N/2, new int[N/2], 0);
			
			System.out.println(MAX);
		}
	}
}
