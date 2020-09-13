package programmers.fullsearch;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * 문제 설명
 *
 * Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.
 * carpet.png
 * Leo는 집으로 돌아와서 아까 본 카펫의 노란색과 갈색으로 색칠된 격자의 개수는 기억했지만, 전체 카펫의 크기는 기억하지 못했습니다.
 * Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.
 * 제한사항
 * 갈색 격자의 수 brown은 8 이상 5,000 이하인 자연수입니다.
 * 노란색 격자의 수 yellow는 1 이상 2,000,000 이하인 자연수입니다.
 * 카펫의 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다.
 * 입출력 예
 * brown	yellow	return
 * 10	2	[4, 3]
 * 8	1	[3, 3]
 * 24	24	[8, 6]
 */

public class Carpet {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        int intSum = brown+yellow;
        int intWeight = (int)Math.ceil(intSum/2.0);
        int intHeight = 0;
        for(int i = 1, length = intWeight; i < length; i++){
            intWeight = i;
            intHeight = (yellow%i==0) ? yellow/i:yellow/i+1;

            if(2*intWeight + 2*intHeight + 4 == brown) break;
        }
        answer = new int[]{Math.max(intWeight, intHeight)+2, Math.min(intWeight, intHeight)+2};
        return answer;

    }
    public static void main(String[] args) {
        Carpet carpet = new Carpet();
        long start = System.currentTimeMillis();
        carpet.solution(8,1);
        carpet.solution(10,2);
        carpet.solution(24,24);
        long end = System.currentTimeMillis();

        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
        start = System.currentTimeMillis();
        //carpet.solution1(answers);
        end = System.currentTimeMillis();

        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );



        //mockTest.solution(answers1);
        //mockTest.solution1(answers);
        //mockTest.solution1(citations1);
    }
}
