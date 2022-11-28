package Programmers;

import java.util.*;

public class CandidateKey {
    static Set<String> subSets = new HashSet<>();
    static Set<String> candidateKey = new HashSet<>();
    static String[][] table;
    static int result = 0;

    public static void main(String[] args) {
        CandidateKey Main = new CandidateKey();
        System.out.println(Main.solution(new String[][]{
                {"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}
        }));
    }

    public int solution(String[][] relation) {
        table = new String[relation.length][relation[0].length];
        for(int r=0; r<relation.length; r++) {
            for(int c=0; c<relation[0].length; c++) {
                table[r][c] = relation[r][c];
            }
        }

        int col = table[0].length;

        makeSubSet(col, new boolean[col]);
        System.out.println("subsets: "+subSets);

        for(String subset: subSets) {
            if(isDuplicated(subset)) {
                System.out.println(subset+" is Duplicated");
            } else {
                System.out.println(subset+" is OK");
                findCandidateKey(subset);
            }
        }

        return result;
    }

    public void makeSubSet(int toChoose, boolean[] choosed) {
        if(toChoose == 0) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<choosed.length; i++) {
                if(choosed[i]) sb.append(i).append(" ");
            }
            subSets.add(sb.toString());
            return;
        }

        choosed[choosed.length - toChoose] = true;
        makeSubSet(toChoose-1, choosed);
        choosed[choosed.length - toChoose] = false;
        makeSubSet(toChoose-1, choosed);
    }

    public boolean isDuplicated(String subset) {
        for(String key: candidateKey) {
            if(key.contains(subset)) return true;
        }
        return false;
    }

    public void findCandidateKey(String subset) {
        Set<String> tuples = new HashSet<>();

        StringTokenizer st = new StringTokenizer(subset);
        List<Integer> list = new ArrayList<>();
        while(st.hasMoreTokens()) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        for(String[] row: table) {
            StringBuilder tuple = new StringBuilder();
            for(Integer num: list) {
                tuple.append(row[num]).append(" ");
            }

            if(tuples.contains(tuple)) {
                return;
            } else {
                tuples.add(tuple.toString());
            }
        }
        candidateKey.add(subset);
        result++;
    }
}
