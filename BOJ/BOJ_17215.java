package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_17215 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().replace('-', '0').toCharArray();

        // 입력값 테스트
//        System.out.println(Arrays.toString(input));

        // 각 프레임의 점수들
        // [0] : step, [1] : score
        List<List<int[]>> frames = new ArrayList<>();
        List<int[]> frame = new ArrayList<>();
        int step = 0;
        for (char c : input) {
            if (c == 'S') {
                frame.add(new int[]{step++, 10});
                frames.add(frame);
                frame = new ArrayList<>();
            } else if (c == 'P') {
                frame.add(new int[]{step++, 10 - frame.get(0)[1]});
                frames.add(frame);
                frame = new ArrayList<>();
            } else {
                frame.add(new int[]{step++, c - '0'});
                if (frame.size() == 2) {
                    frames.add(frame);
                    frame = new ArrayList<>();
                }
            }
        }
        if (frame.size() > 0) {
            frames.add(frame);
        }

        // 프레임 점수표 테스트
        /*for (List<int[]> fr : frames) {
            for (int[] arr : fr) {
                System.out.println(Arrays.toString(arr));
            }
        }*/

        int result = 0;
        for (int frameNumber = 0; frameNumber < frames.size(); frameNumber++) {
            if (frameNumber < 9) { // 9 프레임 이하
                for (int[] frameArray : frames.get(frameNumber)) { // frameArray[0] : step, frameArray[1] : score
                    if(input[frameArray[0]] == 'S') { // S면 다음, 다다음 기회의 점수를 또 받는다
                        result += frameArray[1];
                        if(input[frameArray[0]+1] == 'S') {
                            result += 10;
                        } else if(input[frameArray[0]+1] == 'P') { // S 이후에 P가 바로 올 수 없음.

                        } else {
                            result += (input[frameArray[0]+1] - '0');
                        }

                        if(input[frameArray[0]+2] == 'S') {
                            result += 10;
                        } else if(input[frameArray[0]+2] == 'P') {
                            result += 10 - (input[frameArray[0]+1] - '0');
                        } else {
                            result += (input[frameArray[0]+2] - '0');
                        }
                    } else if(input[frameArray[0]] == 'P') { // P면 다음 기회의 점수를 또 받는다
                        result += frameArray[1];
                        if(input[frameArray[0]+1] == 'S') {
                            result += 10;
                        } else if(input[frameArray[0]+1] == 'P') { // P 이후에 P가 바로 올 수 없음.

                        } else {
                            result += (input[frameArray[0]+1] - '0');
                        }
                    } else {
                        result += (input[frameArray[0]] - '0');
                    }
                }
            } else { // 9 프레임 이후 보너스 기회들
                for (int[] frameArray : frames.get(frameNumber)) {
                    result += frameArray[1];
                }
            }
        }
        System.out.println(result);
    }
}
