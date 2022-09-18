package Programmers;

import java.util.*;

public class Network {
    public int solution(int n, int[][] computers) {
        // i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j] = 1
        // computer[i][i]는 항상 1

        int[] visited = new int[n+1]; // 0 : 네트워크 X , 네트워크의 번호
        int num = 1;
        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<n; i++) {
            if(visited[i] == 0) {
                q.offer(i);
                visited[i] = num++;

                while(!q.isEmpty()) {
                    int curr = q.poll();

                    for(int adj=0; adj<n; adj++) {
                        if(curr != adj && computers[curr][adj] == 1 && visited[adj] == 0) {
                            q.offer(adj);
                            visited[adj] = num;
                        }
                    }
                }
            }
        }
        return num-1;
    }
}
