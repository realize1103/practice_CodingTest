package programmers.greedy;

import java.util.Stack;

/**
 *
 * 문제 설명
 *
 * 어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.
 * 예를 들어, 숫자 1924에서 수 두 개를 제거하면 [19, 12, 14, 92, 94, 24] 를 만들 수 있습니다. 이 중 가장 큰 숫자는 94 입니다.
 * 문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다. number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.
 * 제한 조건
 * number는 1자리 이상, 1,000,000자리 이하인 숫자입니다.
 * k는 1 이상 number의 자릿수 미만인 자연수입니다.
 * 입출력 예
 * number	k	return
 * 1924	2	94
 * 1231234	3	3234
 * 4177252841	4	775841
 */

public class MakeBigestNumber {
    public String solution(String number, int k) {
        String answer = "";
        StringBuilder sbNumber = new StringBuilder();
        sbNumber.append(number);
        for(int i = 0; i < k; i++){
            System.out.println(" ======== ");
            for(int j = 1, length = sbNumber.length(); j<length;j++){
                //System.out.println(sbNumber.charAt(j-1) +"  "+ sbNumber.charAt(j));
                if(sbNumber.charAt(j-1) < sbNumber.charAt(j)){
                    sbNumber.deleteCharAt(j-1);
                    break;
                }
                if(j == length-1){
                    sbNumber.deleteCharAt(j);
                }
            }
        }
        System.out.println(sbNumber.toString());
        answer = sbNumber.toString();
        return answer;
    }
    //스택사용의 좋은예
    public String solution2(String number, int k) {
        char[] result = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }
            stack.push(c);
        }
        for (int i=0; i<result.length; i++) {
            result[i] = stack.get(i);
        }
        return new String(result);
    }
    public String solution1(String number, int k) {
        String answer = "";
        int intStart = 0, intIdx = 0;
        char chMaxNum = 0;
        for(int i=0;i<number.length() - k;i++)
        {
            chMaxNum = number.charAt(intStart);

            intIdx = intStart;
            for(int j=intStart; j<=i+k;j++) {
                if(chMaxNum < number.charAt(j)){
                    chMaxNum = number.charAt(j);
                    intIdx = j;
                    break;
                }
            }
            intStart = intIdx +1;
            answer += chMaxNum;

        }
        //System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        MakeBigestNumber makeBigestNumber= new MakeBigestNumber();

        long start = System.currentTimeMillis();
        //makeBigestNumber.solution("1924",2);
        //makeBigestNumber.solution("4177252841",4);
        makeBigestNumber.solution("987654321",8);

        long end = System.currentTimeMillis();

        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
    }
}
