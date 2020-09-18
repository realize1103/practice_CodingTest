package programmers.dynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * 문제 설명
 *     7
 *    3 8
 *   8 1 0
 *  2 7 4 4
 * 4 5 2 6 5
 *
 * 위와 같은 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이 가장 큰 경우를 찾아보려고 합니다. 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다. 예를 들어 3에서는 그 아래칸의 8 또는 1로만 이동이 가능합니다.
 * 삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때, 거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.
 * 제한사항
 * 삼각형의 높이는 1 이상 500 이하입니다.
 * 삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수입니다.
 * 입출력 예
 * triangle	result
 * {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}	30
 */

public class Triangle {

    public int solution(int[][] triangle) {
        int answer = 0;
        int intBeforeLineLength = 0;
        int intTriangleLength = triangle.length;
        for(int i = 1, length = intTriangleLength; i < length ; i++){
            intBeforeLineLength = triangle[i-1].length;
            for(int j = 0, lengthJ = triangle[i].length; j < lengthJ; j++){
                int intComp1 = (j-1)<0 ? triangle[i-1][0] : triangle[i-1][j-1];
                int intComp2 = (j>=intBeforeLineLength) ? triangle[i-1][j-1]:triangle[i-1][j];
                int intAddNUm = Math.max(intComp1,intComp2);
                triangle[i][j] += intAddNUm;
                if(i == intTriangleLength-1 && answer < triangle[i][j]){
                    answer = triangle[i][j];
                }
                //System.out.println("triangle[i] = " + triangle[i][j]+ " intComp1 = " + intComp1+" intComp2 = " + intComp2);
            }
        }
        //System.out.println("answer = " + answer);
        return answer;
    }
    public int solution1(int[][] triangle) {
        for (int i = 1; i < triangle.length; i++) {
            triangle[i][0] += triangle[i-1][0];
            triangle[i][i] += triangle[i-1][i-1];
            for (int j = 1; j < i; j++)
                triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
        }

        return Arrays.stream(triangle[triangle.length-1]).max().getAsInt();
    }
    public int solution2(int[][] triangle) {

        for(int i =triangle.length-2;i>=0;i--){
            for(int j=0;j<triangle[i].length;j++){
                triangle[i][j]=triangle[i+1][j]>triangle[i+1][j+1]?triangle[i][j]+triangle[i+1][j]: triangle[i][j]+ triangle[i+1][j+1];
            }
        }
        return triangle[0][0];
    }

    public static void main(String[] args) {
        Triangle triangle = new Triangle();

        long start = System.currentTimeMillis();
        triangle.solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}});
        long end = System.currentTimeMillis();

        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );

        start = System.currentTimeMillis();
        triangle.solution1(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}});
        end = System.currentTimeMillis();

        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );

        start = System.currentTimeMillis();
        triangle.solution2(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}});
        end = System.currentTimeMillis();

        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
    }
}
