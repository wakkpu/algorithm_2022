package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
            String function = br.readLine();

            int length = Integer.parseInt(br.readLine());

            String array = br.readLine();
            array = array.substring(1, array.length()-1);

            st = new StringTokenizer(array, ",");

            Deque<String> dq = new ArrayDeque<>();
            while(st.hasMoreTokens()) {
                dq.add(st.nextToken());
            }

            boolean first = true;
            boolean error = false;
            for(int i=0; i<function.length(); i++) {
                char fn = function.charAt(i);

                if(fn == 'D') {
                    if(dq.size() <= 0) {
                        bw.write("error\n");
                        error = true;
                        break;
                    } else {
                       if(first) {
                           dq.removeFirst();
                       } else {
                           dq.removeLast();
                       }
                    }
                }

                if(fn == 'R') {
                    first = !first;
                }
            }

            StringBuilder sb = new StringBuilder();

            if(!error) {
                if(dq.size() > 0) {
                    sb.append("[");

                    int num = dq.size()-1;
                    if(first) {
                        for(int i=0; i<num; i++) {
                            sb.append(dq.pollFirst()).append(",");
                        }
                        sb.append(dq.pollFirst()).append("]\n");
                    } else {
                        for(int i=0; i<num; i++) {
                            sb.append(dq.pollLast()).append(",");
                        }
                        sb.append(dq.pollLast()).append("]\n");
                    }
                } else {
                    sb.append("[]\n");
                }
            }
            bw.write(sb.toString());
        }
        bw.flush();
        bw.close();
    }
}
