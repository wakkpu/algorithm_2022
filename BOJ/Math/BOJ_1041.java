package BOJ.Math;

import java.io.*;
import java.util.*;

public class BOJ_1041 {
    static class Dice {
        private int A, B, C, D, E, F;

        private Dice(Builder builder) {
            A = builder.A;
            B = builder.B;
            C = builder.C;
            D = builder.D;
            E = builder.E;
            F = builder.F;
        }

        public static class Builder {
            private int A, B, C, D, E, F;

            public Builder() {}
            public Builder A(int val) {
                A = val;
                return this;
            }
            public Builder B(int val) {
                B = val;
                return this;
            }
            public Builder C(int val) {
                C = val;
                return this;
            }
            public Builder D(int val) {
                D = val;
                return this;
            }
            public Builder E(int val) {
                E = val;
                return this;
            }
            public Builder F(int val) {
                F = val;
                return this;
            }
            public Dice build() {
                return new Dice(this);
            }
        }

        public int findOne() {
            int[] nums = new int[] {
                    Math.min(A, F),
                    Math.min(B, E),
                    Math.min(C, D)
            };
            return Arrays.stream(nums).min().getAsInt();
        }

        public int findTwo() {
            int[] nums = new int[] {
                    Math.min(A, F)+Math.min(B, E),
                    Math.min(B, E)+Math.min(C, D),
                    Math.min(A, F)+Math.min(C, D)
            };
            return Arrays.stream(nums).min().getAsInt();
        }

        public int findThree() {
            return Math.min(A, F)+Math.min(B, E)+Math.min(C, D);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] diceNums = new int[6];
        for(int i=0; i<6; i++) {
            diceNums[i] = Integer.parseInt(st.nextToken());
        }

        Dice dice = new Dice.Builder()
                .A(diceNums[0])
                .B(diceNums[1])
                .C(diceNums[2])
                .D(diceNums[3])
                .E(diceNums[4])
                .F(diceNums[5])
                .build();

        int one = dice.findOne();
        int two = dice.findTwo();
        int three = dice.findThree();

        long result = 0L;
        if(N == 1) {
            long max = 0L;
            long sum = 0L;
            for(int d: diceNums) {
                max = Math.max(max, d);
                sum += d;
            }
            result = sum-max;
        } else {
            result = 4L * three +
                    4L * ((N - 1L) + (N - 2L)) * two +
                    (4L * (N - 1L) * (N - 2L) + (N - 2L) * (N - 2L)) * one;
        }

        System.out.println(result);
    }
}
