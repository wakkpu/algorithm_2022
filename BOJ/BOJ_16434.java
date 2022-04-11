package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_16434 {
    /*
    용사는 세 종류의 스탯 가짐.
    maxHP: 최대 생명력. 1 이상이어야 하며 바뀌지 않음.
    curHP: 현재 생명력. maxHP보다 커질 수 없음. 처음에 maxHP로 시작함.
    attack: 공격력
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long ATK = Long.parseLong(st.nextToken());

        long[][] rooms = new long[N][3];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                rooms[i][j] = Long.parseLong(st.nextToken());
            }
        }

        // 피가 1 이상 남아야 클리어할 수 있음.
        // 적의 정보를 아니까 까일 피를 계산할 수 있음
        // -(최대로 까인 피) + 1하면 된다
        long userHP = 0;
        long minHP = Long.MAX_VALUE;
        for(long[] room: rooms) {
            if(room[0] == 1) {
                long atk = room[1];
                long hp = room[2];

                // 먼저 때려서 이기는 경우
                if(hp % ATK == 0) userHP -= atk * (hp / ATK - 1);
                else userHP -= atk * (hp / ATK);
            } else if(room[0] == 2) {
                ATK += room[1];
                if(userHP + room[2] > 0) userHP = 0; // maxHP 이상 회복될 수 없으므로
                else userHP += room[2];
            }
            minHP = Math.min(minHP, userHP); // 최대로 까인 피(최대로 까여도 되는 피) 저장
        }

        System.out.println(-minHP+1);
    }
}
