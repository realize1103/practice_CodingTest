package programmers.stackAndQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * 문제 설명
 *
 * 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.
 * 제한사항
 * prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
 * prices의 길이는 2 이상 100,000 이하입니다.
 * 입출력 예
 * prices	return
 * [1, 2, 3, 2, 3]	[4, 3, 1, 1, 0]
 * 입출력 예 설명
 * 1초 시점의 ₩1은 끝까지 가격이 떨어지지 않았습니다.
 * 2초 시점의 ₩2은 끝까지 가격이 떨어지지 않았습니다.
 * 3초 시점의 ₩3은 1초뒤에 가격이 떨어집니다. 따라서 1초간 가격이 떨어지지 않은 것으로 봅니다.
 * 4초 시점의 ₩2은 1초간 가격이 떨어지지 않았습니다.
 * 5초 시점의 ₩3은 0초간 가격이 떨어지지 않았습니다.
 */

public class StockPrices {
    public int[] solution(int[] prices) {
        int[] answer = {};
        answer = new int[prices.length];
        for(int i = 0, length = prices.length ; i < length ; i++){
            int intDuringTime = 0;
            for(int j = (i+1), lengthJ = prices.length ; j < lengthJ ; j++){
                intDuringTime++;
                if(prices[i] > prices[j]) {
                    break;
                }
            }
            answer[i] = intDuringTime;
        }
        return answer;
    }

    public int[] solution1(int[] prices) {
        Stack<Integer> beginIdxs = new Stack<>();
        int i=0;
        int[] terms = new int[prices.length];

        beginIdxs.push(i);
        for (i=1; i<prices.length; i++) {
            while (!beginIdxs.empty() && prices[i] < prices[beginIdxs.peek()]) {
                int beginIdx = beginIdxs.pop();
                terms[beginIdx] = i - beginIdx;
            }
            beginIdxs.push(i);
        }
        while (!beginIdxs.empty()) {
            int beginIdx = beginIdxs.pop();
            terms[beginIdx] = i - beginIdx - 1;
        }

        return terms;
    }
    public static void main(String[] args) {
        StockPrices stockPrices = new StockPrices();
        int[] prices = {1,2,3,2,3};
        int[] answers1 = {1,3,2,4,2};
        long start = System.currentTimeMillis();
        stockPrices.solution(prices);
        long end = System.currentTimeMillis();

        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );



        //mockTest.solution(answers1);
        //mockTest.solution1(answers);
        //mockTest.solution1(citations1);
    }
}
