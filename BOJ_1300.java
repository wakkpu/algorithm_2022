import java.io.*;

public class BOJ_1300 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		long left = 1;
		long right = K;
		long result = 0;
		
		while(left <= right) {
			long mid = (left+right) / 2;
			
			// mid 이하의 수의 갯수
			long count = 0;
			for(int i=1; i<=N; i++) {
				count += Math.min(mid/i, N); // i번째 열에서 mid보다 작은 수의 갯수
			}
			
			if(count < K) {
				left = mid+1;
			} else {
				right = mid-1;
				result = mid;
			}
		}
		System.out.println(result);

	}
}
