package programmers.fullsearch;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * 문제 설명
 * 한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.
 * 각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.
 * 제한사항
 * numbers는 길이 1 이상 7 이하인 문자열입니다.
 * numbers는 0~9까지 숫자만으로 이루어져 있습니다.
 * 013은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
 * 입출력 예
 * numbers	return
 * 17	3
 * 011	2
 * 입출력 예 설명
 * 예제 #1
 * [1, 7]으로는 소수 [7, 17, 71]를 만들 수 있습니다.
 * 예제 #2
 * [0, 1, 1]으로는 소수 [11, 101]를 만들 수 있습니다.
 * 11과 011은 같은 숫자로 취급합니다.
 */

public class HowManyPrimeNumbers {
    public static HashSet<Integer> prime = new HashSet<>();
    public static int cnt = 0;
    public static boolean[] NUMBER = new boolean[10000000];
    public static int solution(String input){
        // StringBuilder를 통해 숫자를 만든다.
        StringBuilder sb = new StringBuilder();
        Arrays.fill(NUMBER, true);
        NUMBER[0] = NUMBER[1] = false;

        String[] numbers = input.split("");
        boolean[] picked = new boolean[numbers.length];
        makeNumber(numbers, picked, sb);

        for(int num : prime){
            if(isPrime(num))
                cnt++;
        }
        return cnt;
    }
    public static void makeNumber(String[] numbers, boolean[] picked, StringBuilder sb){
        // numbers : 숫자 후보
        // picked : 선택되었는 가를 보여주는 예시
        // 기저 사례: picked가 모두 true면 return
        int len = picked.length;
        boolean finished = true;
        for(boolean flag : picked){
            if(!flag){
                finished = false;
                break;
            }

        }
        if(finished) return;

        for(int i = 0; i < len; i++){
            if(!picked[i]){
                sb.append(numbers[i]);
                prime.add(Integer.parseInt(sb.toString()));
                picked[i] = true;
                makeNumber(numbers, picked, sb);
                picked[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static boolean isPrime(int num){
        // 에라토스테네스의 체를 이용
        for(int i = 2; i <= Math.sqrt(num); i++){
            for(int j = 2; j * i <= num ; j++){
                NUMBER[j * i] = false;
            }
        }
        return NUMBER[num];
    }

    public static void main(String[] args) {
        HowManyPrimeNumbers howManyPrimeNumbers = new HowManyPrimeNumbers();
        String numbers = "17123";
        howManyPrimeNumbers.solution(numbers);
    }
}
