package Programmers;

import java.util.*;

public class NewsClustering {

    public static void main(String[] args) {
        NewsClustering Main = new NewsClustering();
//        System.out.println(Main.solution("FRANCE", "french"));
//        System.out.println(Main.solution("handshake", "shake hands"));
//        System.out.println(Main.solution("aa1+aa2", "AAAA12"));
//        System.out.println(Main.solution("E=M*C^2", "e=m*c^2"));
        System.out.println(Main.solution("A+C", "DEF"));
    }

    public int solution(String str1, String str2) {
        // str1의 원소들 중 알파벳 대문자나 소문자로 구성된 것들만 넣는다
        Map<String, Integer> map1 = new HashMap<>();
        for(int i=0; i<str1.length()-1; i++) {
            String substr1 = str1.substring(i, i+2);
            if(('a' <= substr1.charAt(0) && substr1.charAt(0) <= 'z') || ('A' <= substr1.charAt(0) && substr1.charAt(0) <= 'Z')) {
                if (('a' <= substr1.charAt(1) && substr1.charAt(1) <= 'z') || ('A' <= substr1.charAt(1) && substr1.charAt(1) <= 'Z')) {
                    if(!map1.containsKey(substr1.toLowerCase())) {
                        map1.put(substr1.toLowerCase(), 0);
                    }
                    map1.put(substr1.toLowerCase(), map1.get(substr1.toLowerCase())+1);
//                    System.out.println(substr1.toLowerCase());
                }
            }
        }
//        System.out.println();

        // str2의 원소들 중 알파벳 대문자나 소문자로 구성된 것들만 넣는다.
        Map<String, Integer> map2 = new HashMap<>();
        for(int i=0; i<str2.length()-1; i++) {
            String substr2 = str2.substring(i, i+2);
            if(('a' <= substr2.charAt(0) && substr2.charAt(0) <= 'z') || ('A' <= substr2.charAt(0) && substr2.charAt(0) <= 'Z')) {
                if (('a' <= substr2.charAt(1) && substr2.charAt(1) <= 'z') || ('A' <= substr2.charAt(1) && substr2.charAt(1) <= 'Z')) {
                    if(!map2.containsKey(substr2.toLowerCase())) {
                        map2.put(substr2.toLowerCase(), 0);
                    }
                    map2.put(substr2.toLowerCase(), map2.get(substr2.toLowerCase())+1);
//                    System.out.println(substr2.toLowerCase());
                }
            }
        }
//        System.out.println();

        Map<String, Integer> union = new HashMap<>();
        for(Map.Entry<String, Integer> entry: map1.entrySet()) {
            if(!union.containsKey(entry.getKey())) {
                union.put(entry.getKey(), 0);
            }
            union.put(entry.getKey(), Math.max(entry.getValue(), union.get(entry.getKey())));
        }

        for(Map.Entry<String, Integer> entry: map2.entrySet()) {
            if(!union.containsKey(entry.getKey())) {
                union.put(entry.getKey(), 0);
            }
            union.put(entry.getKey(), Math.max(entry.getValue(), union.get(entry.getKey())));
        }

//        for(Map.Entry<String, Integer> entry: union.entrySet()) {
//            System.out.println(entry.toString());
//        }
//        System.out.println();

        Map<String, Integer> intersection = new HashMap<>();
        for(Map.Entry<String, Integer> entry: map1.entrySet()) {
            if(map2.containsKey(entry.getKey())) {
                intersection.put(entry.getKey(), Math.min(entry.getValue(), map2.get(entry.getKey())) );
            }
        }

//        for(Map.Entry<String, Integer> entry: intersection.entrySet()) {
//            System.out.println(entry.toString());
//        }
//        System.out.println();

        int unionSize = 0;
        for(Map.Entry<String, Integer> entry: union.entrySet()) {
            unionSize += entry.getValue();
        }

        int intersectionSize = 0;
        for(Map.Entry<String, Integer> entry: intersection.entrySet()) {
            intersectionSize += entry.getValue();
        }

        if(unionSize == 0) return 65536;

        return 65536 * intersectionSize / unionSize;
    }
}
