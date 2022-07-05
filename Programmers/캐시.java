package Programmers;

import java.util.*;

public class 캐시 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if(cacheSize == 0) {
            return cities.length * 5;
        }

        Set<String> cache = new HashSet<>();
        Map<String, Integer> timestamp = new HashMap<>();
        for(int i=0; i<cities.length; i++) {
            String str = cities[i].toLowerCase();
            timestamp.put(str, i);

            if(cache.contains(str)) {
                answer += 1;
            } else {
                if(cache.size() == cacheSize) {
                    int leastRecent = Integer.MAX_VALUE;
                    String toRemove = "";
                    for(String data: cache) {
                        if(leastRecent > timestamp.get(data)) {
                            leastRecent = timestamp.get(data);
                            toRemove = data;
                        }
                    }
                    cache.remove(toRemove);
                }
                cache.add(str);
                answer += 5;
            }
        }
        return answer;
    }
}
