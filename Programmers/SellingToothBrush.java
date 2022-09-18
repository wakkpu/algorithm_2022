package Programmers;

import java.util.*;

public class SellingToothBrush {
    /**
     *
     * @param enroll 각 판매원의 이름을 담은 배열
     * @param referral 각 판매원을 다단계 조직에 참여시킨 다른 판매원의 이름
     * @param seller 판매량 집계 데이터의 판매원 이름을 나열한 배열
     * @param amount 판매량 집계 데이터의 판매 수량을 나열한 배열 amount
     * @return 각 판매원에게 배분된 이익금의 총합을 계산하여, 입력으로 주어진 enroll에 이름이 포함된 순서에 따라 나열
     */

    /*
     모든 판매원은 칫솔의 판매에 의해 발생하는 이익에서 10%를 계산해 추천인에게 배분한다.
     모든 판매원은 자신이 칫솔 판매에서 발생한 이익 뿐만 아니라, 자신이 조직에 추천하여 가입시킨 판매원에게서 발생하는 이익의 10%까지 자신의 이익.
     자신에게 발생하는 이익 또한 자신의 추천인에게 분배.
     10%를 계산할 때는 원 단위에서 절사하며, 10%를 계산한 금액이 1원 미만인 경우에는 자신이 가진다.
     칫솔 한 개를 판매하여 얻어지는 이익은 100원이다.
     */

    static Map<String, Info> map = new LinkedHashMap<>();

    public class Info {
        String boss;
        int money;

        public Info(String boss, int money) {
            this.boss = boss;
            this.money = money;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "boss='" + boss + '\'' +
                    ", money=" + money +
                    '}';
        }
    }

    public static void main(String[] args) {
        SellingToothBrush Main = new SellingToothBrush();
        Main.solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10}
        );
    }

    public static void goUp(String name, int money) {
        String bossName = map.get(name).boss;

        int bossMoney = money/10;
        int myMoney = money - bossMoney;

        map.get(name).money += myMoney;
        if(!bossName.equals("-")) {
            if(bossMoney < 1) return;
            goUp(bossName, bossMoney);
        }
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for(int i=0; i<enroll.length; i++) {
            map.put(enroll[i], new Info(referral[i], 0));
        }

        for(int i=0; i<seller.length; i++) {
            System.out.println("start from "+seller[i]);
            goUp(seller[i], 100*amount[i]); // 칫솔 판매했으니 상납.
        }

        int idx = 0;
        int[] result = new int[enroll.length];
        for(Map.Entry<String, Info> entry: map.entrySet()) {
            result[idx++] = entry.getValue().money;
        }
        System.out.println(Arrays.toString(result));
        return result;
    }
}
