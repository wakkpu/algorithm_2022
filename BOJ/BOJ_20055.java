package BOJ;

/*
 1번부터 2N-1번까지의 칸은 다음 번호의 칸이 있는 위치로 이동. 2N번은 1번으로 이동.
 i번 칸의 내구도는 A[i]이다.

 1번 칸은 "올리는 위치" N번 칸은 "내리는 위치"

 로봇이 내리는 위치에 도달하면 즉시 내림
 로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동하면 그 칸의 내구도는 1 감소.

 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동.
    만약 이동할 수 없다면 가만히 있는다. (로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며,
    그 칸의 내구도가 1 이상 남아있어야 한다)
 3. 올리는 위치에 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다
 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다.

 종료되었을 때 몇 번째 단계가 진행 중이었는지 구해보자.
 가장 처음 수행되는 단계는 1번째 단계이다
*/

import java.io.*;
import java.util.*;

public class BOJ_20055 {

    static int N, K;
    static int[] conveyor;
    static boolean[] isRobot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        conveyor = new int[2*N];
        isRobot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<2*N; i++) {
            conveyor[i] = Integer.parseInt(st.nextToken());
        }

        int stage = 0;
        while(true) {
            stage++;
            rollConveyor();
            moveRobots();
            putRobot();
            if(count() >= K) break;
        }
        System.out.println(stage);
    }

    public static void rollConveyor() {
        int temp = conveyor[2*N-1];
        for(int i=2*N-1; i>=1; i--) {
            conveyor[i] = conveyor[i-1];
        }
        conveyor[0] = temp;

        for(int i=N-1; i>=1; i--) {
            isRobot[i] = isRobot[i-1];
        }
        isRobot[0] = false;
        if(isRobot[N-1]) isRobot[N-1] = false;
    }

    public static void moveRobots() {
        for(int i=N-2; i>=0; i--) {
            if(!isRobot[i]) continue;

            if(conveyor[i+1] > 0 && !isRobot[i+1]) {
                isRobot[i] = false;
                isRobot[i+1] = true;
                conveyor[i+1]--;
            }
        }
        if(isRobot[N-1]) isRobot[N-1] = false;
    }

    public static void putRobot() {
        if(conveyor[0] > 0) {
            isRobot[0] = true;
            conveyor[0]--;
        }
    }

    public static int count() {
        int count = 0;
        for(int i=0; i<2*N; i++) {
            if(conveyor[i] == 0) {
                count++;
            }
        }
        return count;
    }
}
