import java.io.*;
import java.util.*;

public class BOJ_2110 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] homes = new int[N];
		for(int i=0; i<N; i++) {
			homes[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(homes);

		// 간격에 대한 이진 탐색
		
		int left = 1; // 최소 간격
		int right = homes[N-1] - homes[0]; // 최대 간격
		
		int result = 0;
		
		while(left <= right) {
			
			int mid = (left + right) / 2;
			int first = homes[0];
			int count = 1; // 일단 하나 설치하므로 1부터

			for(int i=1; i<N; i++) {
				if(homes[i] - first >= mid) { // 간격보다 클 때 공유기 설치 (최대한 넓게 설치해야 하므로)
					count++;
					first = homes[i]; // 그 집에 공유기 설치
				}
			}

			// C개 이상 설치했다면 간격 늘림
			if(count >= C) {
				result = mid;
				left = mid+1;
			} else { // C개 미만 설치했다면 간격 줄임
				right = mid-1;
			}
		}
		System.out.println(result);
	}

}
