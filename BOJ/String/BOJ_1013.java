package BOJ.String;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class BOJ_1013 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        String pattern = "(100+1+|01)+";

        while(T-->0) {
            String str = br.readLine();
            boolean regex = Pattern.matches(pattern, str);
            if(regex) bw.write("YES\n");
            else bw.write("NO\n");
        }

        bw.flush();
        bw.close();
    }
}
