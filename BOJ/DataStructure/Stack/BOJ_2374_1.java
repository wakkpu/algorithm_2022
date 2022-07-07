package BOJ.DataStructure.Stack;

import java.io.*;
import java.util.*;

public class BOJ_2374_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] numbers = new long[N];
        for(int i=0; i<N; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }

        Stack<Long> stack = new Stack<>();

        long count = 0;
        long max = 0;

        for(int i=0; i<N; i++) {
            max = Math.max(max, numbers[i]);
            if(stack.isEmpty()) { // 스택 비었으면
                stack.push(numbers[i]); // 걍 넣음
                //System.out.println("case 0: "+numbers[i]+" stack: "+ stack +" count: "+count);
            } else { // 스택에 뭐가 있으면 비교해서 넣어야 함
                long top = stack.peek();
                if(top < numbers[i]) { // 스택의 top보다 큰게 오면
                    count += (numbers[i] - stack.pop()); // 넣으려는 값과의 차 만큼 Add()
                    stack.push(numbers[i]);
                    //System.out.println("case 1: "+numbers[i]+" stack: "+ stack +" count: "+count);
                } else if(top > numbers[i]){ // 스택의 top보다 작은게 오면
                    stack.pop();
                    stack.push(numbers[i]);
                    //System.out.println("case 2: "+numbers[i]+" stack: "+ stack +" count: "+count);
                }
            }
        }

        long pop = stack.pop();
        count += (max - pop);
        //System.out.println("pop: "+pop+" count: "+count);

        System.out.print(count);
    }
}
