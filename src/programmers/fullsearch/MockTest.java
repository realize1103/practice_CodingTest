package programmers.fullsearch;

import java.lang.reflect.Array;
import java.util.*;

/**
 *
 * 문제 설명
 *
 * 수포자는 수학을 포기한 사람의 준말입니다. 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다. 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.
 * 1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
 * 2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
 * 3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
 * 1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때, 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.
 * 제한 조건
 * 시험은 최대 10,000 문제로 구성되어있습니다.
 * 문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
 * 가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.
 * 입출력 예
 * answers	return
 * [1,2,3,4,5]	[1]
 * [1,3,2,4,2]	[1,2,3]
 * 입출력 예 설명
 * 입출력 예 #1
 * 수포자 1은 모든 문제를 맞혔습니다.
 * 수포자 2는 모든 문제를 틀렸습니다.
 * 수포자 3은 모든 문제를 틀렸습니다.
 * 따라서 가장 문제를 많이 맞힌 사람은 수포자 1입니다.
 * 입출력 예 #2
 * 모든 사람이 2문제씩을 맞췄습니다.
 */

public class MockTest {
    public int[] solution(int[] answers) {//정답보다 길고 복잡하지만 실행시간은 더 빠름.
        int[] answer = {};
        int[] answer1 = {1,2,3,4,5};
        int[] answer2 = {2,1,2,3,2,4,2,5};
        int[] answer3 = {3,3,1,1,2,2,4,4,5,5};

        ArrayList<int[]> arrayList = new ArrayList<int[]>();
        arrayList.add(answer1);
        arrayList.add(answer2);
        arrayList.add(answer3);
        HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
        int intMax = 0;
        for(int j = 0, lengthJ = arrayList.size(); j < lengthJ; j++){
            //System.out.println("=========");
            int cnt = 0;
            for(int i = 0 , length = answers.length ; i < length ; i++){
                int[] temp = (int[]) arrayList.get(j);
                int intTemp = temp[(i%temp.length)];
                //System.out.println(intTemp);
                if(answers[i] == intTemp){
                    cnt++;
                }
            }
            if(intMax <= cnt){
                intMax = cnt;
            }
            hashMap.put(j,cnt);
        }
        ArrayList<Integer> winner = new ArrayList<Integer>();
        for(int i = 0, length = hashMap.size(); i<length; i++){
            if(intMax == hashMap.get(i)){
               winner.add((i+1));
            }
        }

        answer = new int[winner.size()];
        for(int i = 0 ; i < answer.length ; ++i){
            answer[i] = winner.get(i);
            //System.out.println(winner.get(i));
        }

        //answer = new int[hashMap.size()];

        return answer;
    }

    public int[] solution1(int[] answer) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] score = new int[3];
        for(int i=0; i<answer.length; i++) {
            if(answer[i] == a[i%a.length]) {score[0]++;}
            if(answer[i] == b[i%b.length]) {score[1]++;}
            if(answer[i] == c[i%c.length]) {score[2]++;}
        }
        int maxScore = Math.max(score[0], Math.max(score[1], score[2]));
        ArrayList<Integer> list = new ArrayList<>();
        if(maxScore == score[0]) {list.add(1);}
        if(maxScore == score[1]) {list.add(2);}
        if(maxScore == score[2]) {list.add(3);}
        return list.stream().mapToInt(i->i.intValue()).toArray();
    }
    public static void main(String[] args) {
        MockTest mockTest = new MockTest();
        int[] answers = {1,2,3,4,5,2,3,4,1,2,3,4,2,3,3,4,2,3,1,1,3,2,};
        int[] answers1 = {1,3,2,4,2};
        long start = System.currentTimeMillis();
        mockTest.solution(answers);
        long end = System.currentTimeMillis();

        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
        start = System.currentTimeMillis();
        mockTest.solution1(answers);
        end = System.currentTimeMillis();

        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );



        //mockTest.solution(answers1);
        //mockTest.solution1(answers);
        //mockTest.solution1(citations1);
    }
}
