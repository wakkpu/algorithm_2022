package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_7490 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while(T--> 0) {
            int N = Integer.parseInt(br.readLine());

            makeCombination(N-1, 1, "1");
            bw.write("\n");
        }
        bw.flush();
    }
    public static void makeCombination(int toChoose, int num, String choosed) throws IOException {
        if(toChoose == 0) {
//            System.out.println(choosed);
            if(checkZero(choosed)) {
                bw.write(choosed+"\n");
            }
            return;
        }

        makeCombination(toChoose-1, num+1, choosed+" "+(num+1));
        makeCombination(toChoose-1, num+1, choosed+"+"+(num+1));
        makeCombination(toChoose-1, num+1, choosed+"-"+(num+1));
    }

    public static boolean checkZero(String choosed) {
        choosed = choosed.replaceAll(" ", "");
        List<String> number = new ArrayList<>();
        List<String> plusminus = new ArrayList<>();

        int idx = 0, idx2 = 0;
        while(idx < choosed.length()) {
            if(choosed.charAt(idx) == '+') {
                number.add(choosed.substring(idx2, idx));
                idx2 = idx;
                plusminus.add("+");
            } else if(choosed.charAt(idx) == '-') {
                number.add(choosed.substring(idx2, idx));
                idx2 = idx;
                plusminus.add("-");
            }
            idx++;
        }
        number.add(choosed.substring(idx2, idx));

//        System.out.println(number);
//        System.out.println(plusminus);

        int j = 0, result = Integer.parseInt(number.get(0));
        for(int i=1; i<number.size(); i++) {
            if(plusminus.get(j).equals("+")) {
                result += Integer.parseInt(number.get(i).substring(1));
            } else {
                result -= Integer.parseInt(number.get(i).substring(1));
            }
            j++;
        }

        return result == 0;
    }
}
