package Programmers;

import java.util.*;

class CheckWall {

    static int[] distance, weakpoint;
    static boolean[] visited, isWeak;
    static List<List<Integer>> friendPerm, weakPerm;
    static int N;

    public int solution(int n, int[] weak, int[] dist) {
        visited = new boolean[dist.length];
        distance = new int[dist.length];
        for(int i=0; i<dist.length; i++) {
            distance[i] = dist[i];
        }
        friendPermutation(new ArrayList<>());

        visited = new boolean[weak.length];
        weakpoint = new int[weak.length];
        for(int i=0; i<weak.length; i++) {
            weakpoint[i] = weak[i];
        }
        weakPermutation(new ArrayList<>());

        isWeak = new boolean[n];
        for(int w: weak) {
            isWeak[w] = true;
        }
        N = n;

        int min =Integer.MAX_VALUE;
        for(List<Integer> dists: friendPerm) {
            for(List<Integer> starts: weakPerm) {
                boolean[] temp = new boolean[isWeak.length];
                System.arraycopy(isWeak, 0, temp, 0, isWeak.length);
                for(Integer s: starts) {
                    int count = 0;
                    for(Integer d: dists) {
                        count++;
//                        boolean[] clockWise(s, d, true);
                        if(isEnd()) {
                            min = Math.min(min, count);
                            break;
                        }
                    }
                }
            }
        }

        if(min == Integer.MAX_VALUE) return -1;
        return min;
    }

    public boolean isEnd() {
        for(boolean weak: isWeak) {
            if(weak) return false;
        }
        return true;
    }

    public boolean[] clockWise(int start, int dist, boolean inOrder, boolean[] src) {
        boolean[] result = new boolean[src.length];
        System.arraycopy(src, 0, result, 0, src.length);
        for(int i=0; i<dist; i++) {
            result[(start+i)%N] = inOrder;
        }
        return result;
    }

    public boolean[] counterClock(int start, int dist, boolean inOrder, boolean[] src) {
        boolean[] result = new boolean[src.length];
        System.arraycopy(src, 0, result, 0, src.length);
        for(int i=0; i<dist; i++) {
            int pos = start-i;
            if(pos < 0) pos = N-1;
            result[pos] = inOrder;
        }
        return result;
    }

    public void friendPermutation(List<Integer> perm) {
        if(perm.size() == distance.length) {
            friendPerm.add(perm);
            return;
        }

        for(int i=0; i<distance.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                perm.add(distance[i]);
                friendPermutation(perm);
                perm.remove(perm.size()-1);
                visited[i] = false;
            }
        }
    }

    public void weakPermutation(List<Integer> weaks) {
        if(weaks.size() == weakpoint.length) {
            weakPerm.add(weaks);
            return;
        }

        for(int i=0; i<weakpoint.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                weaks.add(weakpoint[i]);
                weakPermutation(weaks);
                weaks.remove(weaks.size()-1);
                visited[i] = false;
            }
        }
    }
}