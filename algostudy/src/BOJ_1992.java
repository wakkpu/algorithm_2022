import java.io.*;

public class BOJ_1992 {

	static int N;
	static char[][] map;
	
	public static void QuadTree(StringBuilder sb, int size, int r, int c) {
		if(size == 1) {
			sb.append(map[r][c]);
			return;
		}
		
		boolean flag = true;
		char first = map[r][c];
		for(int i=r; i<r+size; i++) {
			for(int j=c; j<c+size; j++) {
				if(first != map[i][j]) {
					flag = false;
				}
			}
		}
		
		if(flag == true) {
			sb.append(first);
			return;
		}
		
		sb.append("(");
		QuadTree(sb, size/2, r, c);
		QuadTree(sb, size/2, r, c+size/2);
		QuadTree(sb, size/2, r+size/2, c);
		QuadTree(sb, size/2, r+size/2, c+size/2);
		sb.append(")");
		return;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		/*for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}*/
		
		sb = new StringBuilder();
		QuadTree(sb, N, 0, 0);
		System.out.println(sb);
	}

}
