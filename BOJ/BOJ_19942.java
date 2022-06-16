package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_19942 {

    static class Food {
        int number;
        int protein;
        int fat;
        int carbon;
        int vitamin;
        int cost;

        public Food(int number, int protein, int fat, int carbon, int vitamin, int cost) {
            this.number = number;
            this.protein = protein;
            this.fat = fat;
            this.carbon = carbon;
            this.vitamin = vitamin;
            this.cost = cost;
        }
    }

    static int N, mp, mf, ms, mv, minCost;
    static Queue<Integer> q = new LinkedList<>();
    static Food[] foods;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());

        foods = new Food[N];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());

            int protein = Integer.parseInt(st.nextToken());
            int fat = Integer.parseInt(st.nextToken());
            int carbon = Integer.parseInt(st.nextToken());
            int vitamin = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            foods[i-1] = new Food(i, protein, fat, carbon, vitamin, cost);
        }

        minCost = Integer.MAX_VALUE;
        dfs(0, 0, 0, 0, 0, 0, new ArrayList<>());

        if(minCost == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(minCost+"\n");
            while(!q.isEmpty()) {
                bw.write(q.poll()+" ");
            }
        }
        bw.flush();
    }

    public static boolean ok(int protein, int fat, int carbon, int vitamin) {
        return protein >= mp && fat >= mf && carbon >= ms && vitamin >= mv;
    }

    public static void dfs(int startIdx, int protein, int fat, int carbon, int vitamin, int cost, List<Integer> choosed) {
        if(cost > minCost) return;

        if(ok(protein, fat, carbon, vitamin)) {
            if(cost < minCost) {
                minCost = cost;
                q.clear();
                for(Integer ch: choosed) {
                    q.offer(ch);
                }
            }
            return;
        }

        for(int i=startIdx; i<N; i++) {
            Food food = foods[i];
            choosed.add(food.number);
            dfs(
                i+1,
                protein+food.protein,
                fat+food.fat,
                carbon+food.carbon,
                vitamin+ food.vitamin,
                cost+food.cost,
                choosed
            );
            choosed.remove(Integer.valueOf(food.number));
        }
    }
}
