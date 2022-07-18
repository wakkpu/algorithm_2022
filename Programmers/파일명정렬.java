package Programmers;

import java.util.*;

public class 파일명정렬 {
    static class File implements Comparable<File> {
        int index;
        String head;
        String number;
        String tail;

        public File(int index, String file) {
            this.index = index;

            int idx = 0; // 숫자 파트의 시작
            int idx2 = 0; // 숫자 파트의 끝
            boolean first = false;
            boolean second = false;

            for(int i=0; i<file.length(); i++) {
                if(!first) {
                    if(Character.isDigit(file.charAt(i))) {
                        idx = i;
                        first = true;
                    }
                }
                if(first && !second) {
                    if(!Character.isDigit(file.charAt(i))) {
                        idx2 = i;
                        second = true;
                    }
                }
            }
            if(idx2 == 0) idx2 = file.length();
//            System.out.println("idx: "+idx+" idx2: "+idx2);
            this.head = file.substring(0, idx);
//            System.out.print(this.head+" ");
            this.number = file.substring(idx, idx2);
//            System.out.print(this.number+" ");
            this.tail = file.substring(idx2);
//            System.out.println(this.tail);
        }

        @Override
        public String toString() {
            return head+number+tail;
        }

        @Override
        public int compareTo(File o) {
            int result = (this.head.toLowerCase()).compareTo(o.head.toLowerCase());
            if(result == 0) {
                result = Integer.compare(Integer.parseInt(this.number), Integer.parseInt(o.number));
                if(result == 0) {
                    result = Integer.compare(this.index, o.index);
                }
            }
            return result;
        }
    }

    public String[] solution(String[] files) {
        File[] before = new File[files.length];
        for(int i=0; i<files.length; i++) {
            before[i] = new File(i, files[i]);
        }
        Arrays.sort(before);

        String[] result = new String[files.length];
        for(int i=0; i<files.length; i++) {
            result[i] = before[i].toString();
        }
        return result;
    }

    public static void main(String[] args) {
        파일명정렬 Main = new 파일명정렬();
        Main.solution(
                new String[]{
                        "foo9.txt", "foo01bar020.zip", "F-15"
                }
        );
    }
}
