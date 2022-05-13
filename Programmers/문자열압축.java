package Programmers;

public class 문자열압축 {
    public int solution(String s) {
        int result = Integer.MAX_VALUE;
        int len = s.length();

        if(len == 1) return 1;

        for(int i=1; i<=len; i++) {
            String pattern = s.substring(0, i);
            StringBuilder temp = new StringBuilder();
            int count = 1;
            for(int j=i; j<=len-i; j+=i) {
                if(pattern.equals(s.substring(j, j+i))) {
                    count++;
                } else {
                    if(count > 1) {
                        temp.append(count);
                    }
                    temp.append(pattern);

                    pattern = s.substring(j, j+i);
                    count = 1;
                }
            }
            if(count > 1) {
                temp.append(count);
            }
            temp.append(pattern);

            result = Math.min(result, temp.length()+s.length()%i);
        }
        return result;
    }
}
