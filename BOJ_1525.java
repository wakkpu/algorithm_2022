import java.io.*;
import java.util.*;

public class BOJ_1525 {

    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static HashMap<String, Integer> dict = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 2차원 퍼즐을 1차원으로 바꾼다.
        String start = "";
        for(int i=0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                start = start.concat(st.nextToken());
            }
        }
        //System.out.println("init: "+start);
        dict.put(start, 0);
        System.out.println(bfs(start));
    }

    public static int bfs(String str) {
        Queue<String> q = new LinkedList<>();
        q.offer(str);
        // BFS
        while(!q.isEmpty()) {
            String currState = q.poll();
            int currTime = dict.get(currState);

            // 퍼즐이 맞춰진 상태일 경우 dict에서 시간을 꺼내고 반환한다.
            if(currState.equals("123456780")) return currTime;

            // 현재 0의 위치를 알아낸다.
            int zero = currState.indexOf('0');

            // 몫 연산은 행, 나머지 연산은 열을 나타낸다.
            int si = zero%3;
            int sj = zero/3;

            // 4방 탐색
            for(int d=0; d<4; d++) {
                int ni = si+di[d];
                int nj = sj+dj[d];

                if(ni < 0 || ni > 2 || nj < 0 || nj > 2) continue;

                // 열 + 3*행은 좌표의 1차원 배열에서의 위치이다.
                int targetIdx = ni + 3*nj;
                char targetChar = currState.charAt(targetIdx);

                // 0의 위치와 다음 위치의 숫자를 swap한다.
                StringBuilder sb = new StringBuilder(currState);
                sb.setCharAt(targetIdx, '0');
                sb.setCharAt(zero, targetChar);
                String nextState = sb.toString();

                // dict에 없는(나온 적 없는 퍼즐의 상태) 경우에는
                // dict에 퍼즐의 상태와 시간을 갱신해 추가한다. q에도 넣는다.
                if(!dict.containsKey(nextState)) {
                    //System.out.println(currState+"->"+nextState);
                    dict.put(nextState, currTime+1);
                    q.offer(nextState);
                }
            }
        }
        // 찾을 수 없는 경우
        return -1;
    }
}
