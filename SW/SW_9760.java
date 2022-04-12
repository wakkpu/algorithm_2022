package SW;

import java.io.*;
import java.util.*;

public class SW_9760 {

    static final int A = 1;
    static final int T = 10;
    static final int J = 11;
    static final int Q = 12;
    static final int K = 13;

    static final int S = 1;
    static final int D = 2;
    static final int H = 3;
    static final int C = 4;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for(int t=1; t<=TC; t++) {
            int[] ranks = new int[1+13];
            int[] suits = new int[1+4];
            boolean four_of_a_kind = false, full_house = false, flush = false;
            boolean straight = false, three_of_a_kind = false, two_pair = false, one_pair = false;

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<5; i++) {
                String str = st.nextToken();
                char suit = str.charAt(0);
                char rank = str.charAt(1);

                if(suit == 'S') suits[S]++;
                else if(suit == 'D') suits[D]++;
                else if(suit == 'H') suits[H]++;
                else if(suit == 'C') suits[C]++;

                if(rank == 'A') ranks[A]++;
                else if(rank == 'T') ranks[T]++;
                else if(rank == 'J') ranks[J]++;
                else if(rank == 'Q') ranks[Q]++;
                else if(rank == 'K') ranks[K]++;
                else ranks[rank-'0']++;
            }

            for(int i=1; i<=13; i++) {
                if(ranks[i] == 4) {
                    four_of_a_kind = true;
                    break;
                }
            }

            for(int i=1; i<=13; i++) {
                for(int j=1; j<=13; j++) {
                    if((ranks[i] == 3 && ranks[j] == 2) || (ranks[i] == 2 && ranks[j] == 3)) {
                        full_house = true;
                        break;
                    }
                }
            }

            for(int i=1; i<=4; i++) {
                if (suits[i] == 5) {
                    flush = true;
                    break;
                }
            }

            for(int i=1; i<=9; i++) {
                boolean flag = true;
                for(int j=i; j<=i+4; j++) {
                    if(ranks[j] == 0) {
                        flag = false;
                    }
                }

                if(flag) {
                    straight = true;
                    break;
                }
            }

            for(int i=1; i<=13; i++) {
                if(ranks[i] == 3) {
                    three_of_a_kind = true;
                    break;
                }
            }

            for(int i=1; i<=13; i++) {
                for(int j=1; j<=13; j++) {
                    if((i != j) && (ranks[i] == 2 && ranks[j] == 2)) {
                        two_pair = true;
                        break;
                    }
                }
            }

            for(int i=1; i<=13; i++) {
                if(ranks[i] == 2) {
                    one_pair = true;
                    break;
                }
            }

            String result = "";
            if(straight && flush) {
                result = "Straight Flush";
            } else if(straight) {
                result = "Straight";
            } else if(flush) {
                result = "Flush";
            } else if(full_house) {
                result = "Full House";
            } else if(four_of_a_kind) {
                result = "Four of a Kind";
            } else if(three_of_a_kind) {
                result = "Three of a kind";
            } else if(two_pair) {
                result = "Two pair";
            } else if(one_pair) {
                result = "One pair";
            } else {
                result = "High card";
            }

            bw.write("#"+t+" "+result+"\n");
        }
        bw.flush();
        bw.close();
    }
}
