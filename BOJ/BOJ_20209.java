package BOJ;

/*
 * 선분 위에 여러 개의 큐브가 일렬로 놓여 있고, 이 큐브 중 특정 큐브와 연결된 스위치들이 여러 개 존재함.
 * i번 스위치를 한 번 누르면 해당 스위치와 연결된 모든 큐브 위의 숫자가 각각 i만큼 증가함.
 * 큐브 위의 숫자는 0, 1, 2, 3, 4만 존재할 수 있으면 큐브 위의 숫자 K가 4를 초과하면 K를 5로 나눈 나머지로 초기화됨.
 *
 * 스위치는 한 번에 하나만 누를 수 있음.
 * 같은 번호의 큐브가 한 스위치에 여러 번 연결되어 있는 경우는 없음.
 * 각 스위치를 누를 수 있는 횟수의 제한은 없음.
 * 큐브 위에 쓰여 있는 모든 숫자가 동일해지는 순간 게임은 종료됨.
 *
 * 숫자를 모두 동일하게 만들기 위해 눌러야 하는 스위치의 최소 횟수를 구하라.
 * 아무리 눌러도 모든 큐브의 숫자를 동일하게 만들 수 없는 경우에는 -1.
 */
import java.util.*;
import java.io.*;

public class BOJ_20209 {
    static class State {
        int count;
        int[] cubes;

        public State(int count, int[] cubes) {
            this.count = count;
            this.cubes = cubes;
        }
    }

    static int N, K;
    static LinkedList<Integer>[] switches;
    static int minPush = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] cubes = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            cubes[i] = Integer.parseInt(st.nextToken());
        }

        switches = new LinkedList[K+1];
        for(int i=0; i<=K; i++) {
            switches[i] = new LinkedList<>();
        }

        for(int i=1; i<=K; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            while(len-->0) {
                switches[i].offer(Integer.parseInt(st.nextToken()));
            }
        }

        State start = new State(0, cubes);

        bfs(start);

        if(minPush == Integer.MAX_VALUE) bw.write("-1");
        else bw.write(String.valueOf(minPush));

        bw.flush();
        bw.close();
    }

    public static void bfs(State start) {
        Queue<State> q = new LinkedList<>();
        q.offer(start);

        HashSet<String> visited = new HashSet<>();
        visited.add(toStr(start.cubes));

        while(!q.isEmpty()) {
            State curr = q.poll();
            int count = curr.count;
            int[] cubes = curr.cubes;

            if(isSame(cubes)) {
                minPush = Math.min(count, minPush);
                return;
            }

            for(int i=1; i<=K; i++) { // i번 스위치
                LinkedList<Integer> swtch = switches[i];

                int[] next = Arrays.copyOf(cubes, cubes.length);
                for(int j: swtch) { // i번 스위치에 연결된 j번째 큐브들의 숫자 증가.
                    next[j] = (next[j] + i) % 5;
                }

                if(visited.contains(toStr(next))) continue;

                visited.add(toStr(next));
                q.offer(new State(count+1, next));
            }
        }
    }

    public static String toStr(int[] cubes) {
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            sb.append(cubes[i]);
        }
        return sb.toString();
    }

    public static boolean isSame(int[] cubes) {
        boolean flag = true;
        int x = cubes[1];

        for(int i=2; i<cubes.length; i++) {
            if (x != cubes[i]) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
