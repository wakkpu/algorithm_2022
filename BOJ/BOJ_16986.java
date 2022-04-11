package BOJ;

import java.io.*;
import java.util.*;

/*
    1. A, B, C는 필요한 승수와 경기 진행 순서를 미리 합의함 -> 지우, 경희, 민호
    2. 만약 두 사람이 같은 손동작을 내어 무승부가 발생할 경우 경기 진행 순서상 뒤인 사람이 이긴다.
    3. 이전 경기의 승자와 이전 경기에 참여하지 않은 사람이 경기를 진행해 승자를 결정한다.
    4. 특정 사람이 미리 합의된 승수를 달성할때까지 3을 반복한다.
    5. 합의된 승수를 최초로 달성한 사람이 우승한다.
 */
public class BOJ_16986 {

    static int N, K;
    static boolean flag;

    static int[][] graph;

    static int[] kh = new int[20];
    static int[] mh = new int[20];
    static int[] jw;

    static final int JW = 1;
    static final int KH = 2;
    static final int MH = 3;

    // 3, 4, 7
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<20; i++) {
            kh[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<20; i++) {
            mh[i] = Integer.parseInt(st.nextToken());
        }

        jw = new int[N+1];
        makeJW(N, new boolean[N+1]);

        if(flag) System.out.println(1);
        else System.out.println(0);
    }

    public static void makeJW(int toChoose, boolean[] visited) {
        if(toChoose == 0) {
            if(simulate()) {
                flag = true;
            }
            return;
        }

        for(int i=1; i<=N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                jw[N - toChoose] = i;
                makeJW(toChoose-1, visited);
                visited[i] = false;
            }
        }
    }

    /*
    1. A, B, C는 필요한 승수와 경기 진행 순서를 미리 합의함 -> 지우, 경희, 민호
    2. 만약 두 사람이 같은 손동작을 내어 무승부가 발생할 경우 경기 진행 순서상 뒤인 사람이 이긴다.
    3. 이전 경기의 승자와 이전 경기에 참여하지 않은 사람이 경기를 진행해 승자를 결정한다.
    4. 특정 사람이 미리 합의된 승수를 달성할때까지 3을 반복한다.
    5. 합의된 승수를 최초로 달성한 사람이 우승한다.
    */
    public static boolean simulate() {
        //System.out.println(Arrays.toString(jw));
        int jwIdx = 0;
        int khIdx = 0;
        int mhIdx = 0;
        boolean[] played = new boolean[4];
        int[] wins = new int[4];
        // jw vs kh
        if(graph[jw[jwIdx]][kh[khIdx]] == 2) { // jw win
            wins[JW]++;
            played[KH] = true;
        } else { // kh win
            wins[KH]++;
            played[JW] = true;
        }
        jwIdx++;
        khIdx++;

        while(true) {
            if(wins[JW] == K) {
                if(wins[KH] >= K || wins[MH] >= K) return false;
                else return true;
            }
            if(jwIdx >= N) return false;

            if(!played[JW] && !played[KH] && played[MH]) { // jw vs kh
                if(graph[jw[jwIdx]][kh[khIdx]] == 2) { // jw win
                    wins[JW]++;
                    played[KH] = true;
                } else { // kh win
                    wins[KH]++;
                    played[JW] = true;
                }
                played[MH] = false; // mh next round
                jwIdx++;
                khIdx++;
            } else if(!played[JW] && played[KH] && !played[MH]) { // jw vs mh
                if(graph[jw[jwIdx]][mh[mhIdx]] == 2) { // jw win
                    wins[JW]++;
                    played[MH] = true;
                } else { // mh win
                    wins[MH]++;
                    played[JW] = true;
                }
                played[KH] = false; // kh next round
                jwIdx++;
                mhIdx++;
            } else if(played[JW] && !played[KH] && !played[MH]) { // kh vs mh
                if(graph[kh[khIdx]][mh[mhIdx]] == 2) { // kh win
                    wins[KH]++;
                    played[MH] = true;
                } else { // mh win
                    wins[MH]++;
                    played[KH] = true;
                }
                played[JW] = false; // jw next round
                khIdx++;
                mhIdx++;
            }
        }
    }
}
