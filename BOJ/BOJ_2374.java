package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2374 {

    static int N, maxNum;
    static int[] numbers;
    static int[] reps;
    static ArrayList<Integer> repList;
    static ArrayList<Integer> sortedreps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        maxNum = Integer.MIN_VALUE;
        numbers = new int[N];
        for(int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
            if(numbers[i] > maxNum) {
                maxNum = numbers[i];
            }
        }

        // make
        reps = new int[N];
        for(int i=0;i<N; i++) {
            reps[i] = i;
        }

        for(int i=1; i<N; i++) {
            union(i-1, i);
        }

        System.out.print("reps: ");
        for(int i=0; i<N; i++) {
            System.out.print(reps[i]+" ");
        }
        System.out.println();

        // reps의 root의 index
        repList = new ArrayList<>();
        sortedreps = new ArrayList<>();
        for(int i=0; i<N; i++) {
            if(!repList.contains(reps[i])) {
                repList.add(reps[i]);
                sortedreps.add(reps[i]);
            }
        }

        System.out.println("repList: "+repList.toString());

        // reps의 root를 numbers에 대해 정렬
        Collections.sort(sortedreps, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(numbers[o1], numbers[o2]);
            }
        });

        System.out.println("sorted reps: "+sortedreps.toString());

    }

    public static int find(int x) {
        if(x == reps[x]) return x;
        else return reps[x] = find(reps[x]);
    }

    public static void union(int x, int y) {
        int findX = find(x);
        int findY = find(y);

        if(findX != findY && numbers[x] == numbers[y]) {
            if(findX < findY) reps[y] = reps[x];
            else reps[x] = reps[y];
        }
    }
}
