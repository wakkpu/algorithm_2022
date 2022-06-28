package Programmers;

import java.util.*;

/*
    1. 어피치가 n발을 다 쏜 후 라이언이 n발을 쏜다.
    2. 점수를 계산한다.
        1. 0 ~ 10점의 과녁 점수가 있음.
        2. 만약 k(1 <= k <= 10)점을 어피치가 a발 맞히고 라이언이 b발 맞힌 경우
        더 많은 화살을 k점에 맞힌 선수가 k점을 가져간다. a = b인 경우 어피치가
        점수를 가져간다. a = b = 0인 경우에는 아무도 k점을 가져가지 못함.
        3. 최종 점수가 더 높은 선수를 우승자로 결정한다. 단, 최종 점수가 같은 경우
        어피치가 우승한다.

     현재 어피치가 n발을 쏜 상황이고, 라이언이 쏠 차례.
     라이언은 어피치를 가장 큰 점수차로 이기기 위해서 n발의 화살을 어떤 과녁 점수에
     맞혀야 하는지 구하려 한다. 화살의 개수를 담은 자연수 n, 어피치가 맞힌 과녁의
     점수를 10점부터 0점까지 순서대로 담은 정수 배열 info가 매개변수로 주어진다.
     이때, 라이언이 가장 큰 점수 차이로 우승하기 위해 n발의 화살을 어떤 과녁 점수에
     맞혀야 하는지를 10점부터 0점까지 순서대로 정수 배열에 담아 return 하도록.
     만약 라이언이 우승할 수 없는 경우 [-1]을 return한다.

     가장 낮은 점수를 맞힌 개수가 같을 경우 계속해서 그 다음으로 낮은 점수를 더 많이
     맞힌 경우를 return한다.
 */
public class 양궁대회 {
    public static int maxDiff = -1;
    public static int[] ryan;
    public static int[] result;
    public static int[] apeach;
    public static int N = 11;

    public static void main(String[] args) {
        양궁대회 MAIN = new 양궁대회();
        MAIN.solution(5, new int[]{2,1,1,1,0,0,0,0,0,0,0});
        System.out.println(maxDiff +" "+Arrays.toString(result));
    }

    public int[] solution(int n, int[] info) {
        ryan = new int[N];
        result = new int[N];
        apeach = new int[N];

        for(int i=0; i<N; i++) {
            apeach[i] = info[i];
        }

        dfs(n, 0, 0);

        if(maxDiff == -1) {
            return new int[]{-1};
        } else {
            return result;
        }
    }

    public void dfs(int n, int depth, int startIdx) {
        if(depth == n) {
            checkCount();
            return;
        }

        for(int i=startIdx; i<N; i++) {
            ryan[i]++;
            dfs(n, depth+1, i);
            ryan[i]--;
        }
    }

    public void checkCount() {
        int apeachScore = 0;
        int ryanScore = 0;

        for(int i=0; i<N; i++) {
            if(ryan[i] == 0 && apeach[i] == 0) continue;

            if(ryan[i] > apeach[i]) {
                ryanScore += (N-1-i);
            } else {
                apeachScore += (N-1-i);
            }
        }

        int diff = (ryanScore - apeachScore);

        if(diff > 0) {
            if(diff > maxDiff) {
                maxDiff = diff;
                for(int i=0; i<N; i++) {
                    result[i] = ryan[i];
                }
            } else if(diff == maxDiff) {
                boolean flag = false;

                for(int i=N-1; i>=0; i--) {
                    if(ryan[i] == result[i]) {
                        continue;
                    } else if (ryan[i] < result[i]) {
                        break;
                    } else {
                        flag = true;
                        break;
                    }
                }

                if(flag) {
                    for(int i=0; i<N; i++) {
                        result[i] = ryan[i];
                    }
                }
            }
        }
    }
}
