import java.io.*;

public class BOJ_9663 {

	static int N, count;
	static int[] col;
	
	public static boolean Available(int[] col, int row) {
		for(int i=0; i<row; i++) {
			if(col[i] == col[row]) return false;
			if(Math.abs(i - row) == Math.abs(col[i] - col[row])) return false; 
		}
		return true;
	}
	
	public static void NQueen(int[] col, int row) { 
		if(row == N-1) { 
			count++;
			return;
		}
		
		for(int i=0; i<N; i++) { 
			col[row+1] = i;
			if(Available(col, row+1)) {
				NQueen(col, row+1);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		count = 0;
		for(int i=0; i<N; i++) {
			col = new int[N];
			col[0] = i;
			NQueen(col, 0);
		}
		
		System.out.println(count);
	}

}
