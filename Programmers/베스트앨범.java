package Programmers;

import java.util.*;

public class 베스트앨범 {
    static class Song implements Comparable<Song> {
        String genre;
        int play;
        int unique;

        public Song(String genre, int play, int unique) {
            this.genre = genre;
            this.play = play;
            this.unique = unique;
        }

        @Override
        public int compareTo(Song o) {
            if(this.play == o.play) {
                return this.unique - o.unique;
            } else {
                return o.play - this.play;
            }
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        List<Integer> result = new ArrayList<>();

        Map<String, Integer> dict = new HashMap<>();
        Map<String, List<Song>> songs = new HashMap<>();
        for(int i=0; i<genres.length; i++) {
            if(!dict.containsKey(genres[i])) {
                dict.put(genres[i], plays[i]);
            } else {
                dict.put(genres[i], dict.get(genres[i])+plays[i]);
            }

            if(!songs.containsKey(genres[i])) {
                songs.put(genres[i], new ArrayList<>());
            }
            songs.get(genres[i]).add(new Song(genres[i], plays[i], i));
        }

        List<String> keySet = new ArrayList<>(dict.keySet());
        keySet.sort((o1, o2) -> dict.get(o2).compareTo(dict.get(o1)));

        for(String key: keySet) {
            if(songs.get(key).size() == 1) {
                result.add(songs.get(key).get(0).unique);
                continue;
            }
            List<Song> values = songs.get(key);
            Collections.sort(values);

            int idx=0;
            for(Song song: values) {
                if(idx == 2) break;
                result.add(song.unique);
                idx++;
            }
        }

        int[] sol = new int[result.size()];
        for(int i=0; i<result.size(); i++) {
            sol[i] = result.get(i);
        }
        return sol;
    }

    public static void main(String[] args) {

    }
}
