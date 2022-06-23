package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_21608 {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Map<Integer, List<Integer>> graph;
    static int[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new LinkedHashMap<>();
        for(int i=1; i<=N*N; i++) {
            st = new StringTokenizer(br.readLine());

            int std = Integer.parseInt(st.nextToken());

            graph.put(std, new ArrayList<>());
            for(int j=0; j<4; j++) {
                graph.get(std).add(Integer.parseInt(st.nextToken()));
            }
        }

        map = new int[N+1][N+1];
        for(Map.Entry<Integer, List<Integer>> entry: graph.entrySet()) {
            findBestSpot(entry.getKey());
        }

        long result = 0L;
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                long satis = getSatisfaction(i, j);
                if(satis == 0 || satis == 1) result += satis;
                else if(satis == 2) result += 10;
                else if(satis == 3) result += 100;
                else result += 1000;
            }
        }
        System.out.println(result);
    }

    public static void findBestSpot(int stdNum) {
        List<int[]> result = case1(stdNum);
        if(result.size() == 1) {
            int r = result.get(0)[0];
            int c = result.get(0)[1];
            map[r][c] = stdNum;
        } else {
            result = case2(result);
            if(result.size() == 1) {
                int r = result.get(0)[0];
                int c = result.get(0)[1];
                map[r][c] = stdNum;
            } else {
                result.sort(new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        int result = Integer.compare(o1[0], o2[0]);
                        if (result == 0) {
                            result = Integer.compare(o1[1], o2[1]);
                        }
                        return result;
                    }
                });
                int r = result.get(0)[0];
                int c = result.get(0)[1];
                map[r][c] = stdNum;
            }
        }
    }

    // 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
    public static List<int[]> case1(int stdNum) {
        List<int[]> result = new ArrayList<>();
        int max = 0;
        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                if(map[r][c] != 0) continue;

                int count = 0;
                for(int d=0; d<4; d++) {
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(!isIn(nr, nc)) continue;
                    if(graph.get(stdNum).contains(map[nr][nc])) count++;
                }

                if(count > max) {
                    result.clear();
                    result.add(new int[]{r, c});
                    max = count;
                } else if(count == max) {
                    result.add(new int[]{r, c});
                }
            }
        }
        return result;
    }

    // 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
    public static List<int[]> case2(List<int[]> input) {
        List<int[]> result = new ArrayList<>();
        int max = 0;
        for(int[] pos: input) {
            int r = pos[0];
            int c = pos[1];
            if (map[r][c] != 0) continue;

            int count = 0;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (!isIn(nr, nc)) continue;
                if (map[nr][nc] == 0) count++;
            }

            if (count > max) {
                result.clear();
                result.add(new int[]{r, c});
                max = count;
            } else if (count == max) {
                result.add(new int[]{r, c});
            }
        }

        return result;
    }

    public static int getSatisfaction(int r, int c) {
        int result = 0;
        for(int d=0; d<4; d++) {
            int nr = r+dr[d];
            int nc = c+dc[d];

            if(!isIn(nr, nc) || map[r][c] == 0) continue;

            if(graph.get(map[r][c]).contains(map[nr][nc])) result++;
        }
        return result;
    }

    public static boolean isIn(int r, int c) {
        return 1 <= r && r <= N && 1 <= c && c <= N;
    }
}
