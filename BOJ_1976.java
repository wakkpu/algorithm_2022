import java.io.*;
import java.util.*;

public class BOJ_1976 {

    static int[] rep;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        rep = new int[N+1];
        for(int i=1; i<N+1; i++) {
            rep[i] = i;
        }

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++) {
                int connect = Integer.parseInt(st.nextToken());
                if(connect == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int[] toGo = new int[M];
        for(int i=0; i<M; i++) {
            toGo[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M-1; i++) {
            if(find(toGo[i]) != find(toGo[i+1])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    public static int find(int a) {
        if(rep[a] == a) return a;
        return rep[a] = find(rep[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            if(a > b) rep[a] = b;
            else rep[b] = a;
        }
    }
}
