package Programmers;

import java.util.*;

public class 표편집 {
    public static void main(String[] args) {
        표편집 Main = new 표편집();
        String result = Main.solution(8, 2,
                new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z"});
        System.out.println(result);
    }
    public String solution(int n, int k, String[] cmd) {
        StringTokenizer st;

        Stack<Integer> deleted = new Stack<>();
        int cursor = k;
        int chartSize = n;

        for(String c: cmd) {
            st = new StringTokenizer(c);
            String op = st.nextToken();

            int dist = 0;
            if(op.equals("D") || op.equals("U")) {
                dist = Integer.parseInt(st.nextToken());
            }

            switch(op) {
                case "U": // 현재 행에서 dist칸 위의 행을 선택
                    cursor -= dist;
                    break;
                case "D": // 현재 행에서 dist칸 아래의 행을 선택
                    cursor += dist;
                    break;
                case "C": // 현재 선택된 행을 삭제한 후, 아래 행을 선택. 단, 삭제된 행이 마지막 행인 경우 윗 행을 선택
                    chartSize--;
                    deleted.push(cursor);

                    if(cursor == chartSize) cursor--;
                    break;
                case "Z": // 가장 최근에 삭제된 행을 원래대로 복구. 단, 현재 선택된 행은 바뀌지 않음.
                    chartSize++;
                    int rewind = deleted.pop();

                    if(cursor >= rewind) cursor++;
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean[] isRemoved = new boolean[n];
        while(!deleted.isEmpty()) {
            isRemoved[deleted.pop()] = true;
        }
        for(boolean b: isRemoved) {
            if(b) {
                sb.append("X");
            } else {
                sb.append("O");
            }
        }
        return sb.toString();
    }
}
