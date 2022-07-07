package BOJ.DataStructure.Tree;

import java.io.*;
import java.util.*;

public class BOJ_6416 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t=1;
        out:
        while(true) {
            Map<Integer, List<Integer>> out = new HashMap<>(); // u->v로 나가는 노드 체크
            Map<Integer, List<Integer>> in = new HashMap<>(); // u->v로 들어오는 노드 체크

            int root = 0;
            boolean flag = true;

            st = new StringTokenizer(br.readLine());
            while(true) {
                if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                if(u == 0 && v == 0) break;
                if(u < 0 && v < 0) break out;

                if(!out.containsKey(u)) out.put(u, new ArrayList<>());
                out.get(u).add(v);

                if(!in.containsKey(v)) in.put(v, new ArrayList<>());
                in.get(v).add(u);
            }

            // 루트 노드는 하나만 존재해야 한다.
            for(Integer node: out.keySet()) {
                if(!in.containsKey(node)) {
                    if(root == 0) { // root 노드 1개
                        root = node;
                    } else { // root 노드 2개 이상
                        flag = false;
                        break;
                    }
                }
            }
            // 루트 노드 0개
            if(root == 0) flag = false;

            // 들어오는 간선은 하나만 존재해야 한다.
            for(Integer node: in.keySet()) {
                if(in.get(node).size() > 1) {
                    flag = false;
                    break;
                }
            }

            // 루트 노드에서 다른 노드로 가는 경로는 반드시 가능하며, 유일하다. -> 이 조건 안 넣었는데 맞네..

            // 노드의 개수가 0개이면 트리 가능.
            if(in.keySet().size() == 0 || out.keySet().size() == 0) flag = true;

            if(flag) {
                bw.write("Case "+(t++)+" is a tree.\n");
            } else {
                bw.write("Case "+(t++)+" is not a tree.\n");
            }
        }
        bw.flush();
    }
}
