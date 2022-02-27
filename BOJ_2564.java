import java.io.*;
import java.util.*;

public class BOJ_2564 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		int num = Integer.parseInt(br.readLine());

		int[][] pos = new int[num][2];
		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 동서남북
			int b = Integer.parseInt(st.nextToken()); // 위, 왼쪽에서부터 길이

			pos[i][0] = a;
			pos[i][1] = b;
		}

		int[] dong = new int[2]; // 동근이 동서남북, 길이
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		dong[0] = a;
		dong[1] = b;
		
		int[] len = new int[num]; // 각 가게에서부터 최소 거리

		for (int i = 0; i < num; i++) {
			if (dong[0] == 1) { // 동근이 북
				if (pos[i][0] == 1) { // 가게도 북
					len[i] = Math.abs(dong[1] - pos[i][1]);
				} else if (pos[i][0] == 2) { // 가게가 남쪽
					len[i] = Math.min(dong[1]+pos[i][1]+y, (x-dong[1])+(x-pos[i][1]+y));
				} else if (pos[i][0] == 3) {
					len[i] = Math.min(dong[1]+pos[i][1], (x-dong[1])+y+x+(y-pos[i][1]));
				} else {
					len[i] = Math.min((x-dong[1])+pos[i][1], dong[1]+y+x+(y-pos[i][1]));
				}
			} else if (dong[0] == 2) {
				if (pos[i][0] == 1) {
					len[i] = Math.min(dong[1]+pos[i][1]+y, (x-dong[1])+(x-pos[i][1]+y));
				} else if (pos[i][0] == 2) {
					len[i] = Math.abs(dong[1] - pos[i][1]);
				} else if (pos[i][0] == 3) {
					len[i] = Math.min(dong[1]+y-pos[i][1], (x-dong[1])+y+x+pos[i][1]);
				} else {
					len[i] = Math.min((x-dong[1])+y-pos[i][1], dong[1]+y+x+pos[i][1]);
				}
			} else if (dong[0] == 3) {
				if (pos[i][0] == 1) {
					len[i] = Math.min(dong[1]+pos[i][1], (y-dong[1])+x+y+(x-pos[i][1]));
				} else if (pos[i][0] == 2) {
					len[i] = Math.min(y-dong[1]+pos[i][1], dong[1]+y+x+x-pos[i][1]);
				} else if (pos[i][0] == 3) {
					len[i] = Math.abs(dong[1] - pos[i][1]);
				} else {
					len[i] = Math.min(dong[1]+x+pos[i][1], y-dong[1]+x+(y-pos[i][1]));
				}
			} else {
				if (pos[i][0] == 1) {
					len[i] = Math.min(dong[1]+x-pos[i][1], y-dong[1]+y+x+(pos[i][1]));
				} else if (pos[i][0] == 2) {
					len[i] = Math.min((y-dong[1])+x-pos[i][1], dong[1]+y+x+pos[i][1]);
				} else if (pos[i][0] == 3) {
					len[i] = Math.min(dong[1]+x+pos[i][1], y-dong[1]+x+(y-pos[i][1]));
				} else {
					len[i] = Math.abs(dong[1] - pos[i][1]);
				}
			}
		}
		
		int sum = 0;
		for(int i=0; i<num; i++) {
			sum += len[i];
		}
		System.out.println(sum);
	}
}
