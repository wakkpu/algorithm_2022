package Programmers;

import java.util.*;

public class 오픈채팅방 {
    public String[] solution(String[] record) {
        StringTokenizer st;

        int len = record.length;
        String[] operation = new String[len];
        String[] userId = new String[len];
        Map<String, String> users = new HashMap<>(); // K: uid, V: nickname

        for(int i=0; i<len; i++) {
            st = new StringTokenizer(record[i]);
            operation[i] = st.nextToken();
            userId[i] = st.nextToken();

            if(!operation[i].equals("Leave")) {
                String nickname = st.nextToken();
                users.put(userId[i], nickname);
            }
        }

        List<String> temp = new ArrayList<>();
        for(int i=0; i<len; i++) {
            if(operation[i].equals("Enter")){
                temp.add(users.get(userId[i])+"님이 들어왔습니다.");
            } else if(operation[i].equals("Leave")){
                temp.add(users.get(userId[i])+"님이 나갔습니다.");
            }
        }
        String[] result = new String[temp.size()];
        for(int i=0; i<temp.size(); i++) {
            result[i] = temp.get(i);
        }
        return result;
    }
}
