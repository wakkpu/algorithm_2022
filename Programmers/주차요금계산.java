package Programmers;

import java.util.*;

public class 주차요금계산 {
    static class Time {
        int hour;
        int minute;

        public Time(String hour, String minute) {
            this.hour = Integer.parseInt(hour);
            this.minute = Integer.parseInt(minute);
        }
    }

    public int getUsedTime(Time inTime, Time outTime) {
        int t1 = inTime.hour * 60 + inTime.minute;
        int t2 = outTime.hour * 60 + outTime.minute;

        return t2 - t1;
    }

    public int[] solution(int[] fees, String[] records) {
        final int basicTime = fees[0];
        final int basicCharge = fees[1];
        final int unitTime = fees[2];
        final int unitCharge = fees[3];

        StringTokenizer st;

        Map<String, Integer> accTimes = new TreeMap<>();

        // 차량 번호 당 출입 시간 기록
        Map<String, Queue<String>> map = new TreeMap<>();
        for(String str: records) {
            st = new StringTokenizer(str);
            String time = st.nextToken(); // 출입 시각
            String number = st.nextToken(); // 차량 번호
            String IO = st.nextToken(); // 안씀
            if(!map.containsKey(number)) {
                map.put(number, new LinkedList<>());
            }
            map.get(number).offer(time);

            accTimes.put(number, 0);
        }

        for(String key: map.keySet()) {
            Queue<String> q = map.get(key);
            //System.out.println(q.toString());
            while(!q.isEmpty()) {
                if(q.size() >= 2) {
                    String in = q.poll();
                    st = new StringTokenizer(in, ":");
                    Time inTime = new Time(st.nextToken(), st.nextToken());

                    String out = q.poll();
                    st = new StringTokenizer(out, ":");
                    Time outTime = new Time(st.nextToken(), st.nextToken());

                    int usedTime = getUsedTime(inTime, outTime);

                    int val = accTimes.get(key);
                    accTimes.put(key, val+usedTime);

                } else {
                    String in = q.poll();
                    st = new StringTokenizer(in, ":");
                    Time inTime = new Time(st.nextToken(), st.nextToken());

                    String out = "23:59";
                    st = new StringTokenizer(out, ":");
                    Time outTime = new Time(st.nextToken(), st.nextToken());

                    int usedTime = getUsedTime(inTime, outTime);

                    int val = accTimes.get(key);
                    accTimes.put(key, val+usedTime);
                }
            }
        }

        int[] result = new int[accTimes.size()];

        int idx=0;
        for(Integer accTime: accTimes.values()) {
            //System.out.println(accTime);
            int charge = basicCharge;

            if(accTime > basicTime) {
                int additionalCharge = (int) ( Math.ceil((accTime - basicTime) / (double) unitTime) * unitCharge );
                charge += additionalCharge;
            }

            result[idx++] = charge;
        }

        return result;
    }
}
