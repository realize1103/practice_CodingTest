package programmers.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * 문제 설명
 *
 *고속도로를 이동하는 모든 차량이 고속도로를 이용하면서 단속용 카메라를 한 번은 만나도록 카메라를 설치하려고 합니다.
 * 고속도로를 이동하는 차량의 경로 routes가 매개변수로 주어질 때, 모든 차량이 한 번은 단속용 카메라를 만나도록 하려면 최소 몇 대의 카메라를 설치해야 하는지를 return 하도록 solution 함수를 완성하세요.
 * 제한사항
 * 차량의 대수는 1대 이상 10,000대 이하입니다.
 * routes에는 차량의 이동 경로가 포함되어 있으며 routes[i][0]에는 i번째 차량이 고속도로에 진입한 지점, routes[i][1]에는 i번째 차량이 고속도로에서 나간 지점이 적혀 있습니다.
 * 차량의 진입/진출 지점에 카메라가 설치되어 있어도 카메라를 만난것으로 간주합니다.
 * 차량의 진입 지점, 진출 지점은 -30,000 이상 30,000 이하입니다.
 * 입출력 예
 * routes	return
 * {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}}	2
 * 입출력 예 설명
 * -5 지점에 카메라를 설치하면 두 번째, 네 번째 차량이 카메라를 만납니다.
 * -15 지점에 카메라를 설치하면 첫 번째, 세 번째 차량이 카메라를 만납니다.
 */

public class WatchingCamera {
    public int solution(int[][] routes) {
        int answer = 1;

        Arrays.sort(routes, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });

        int min = -30001;
        int max = 30001;
        for (int[] route : routes) {
            System.out.println(Arrays.toString(route));
            int in = route[0];
            int out = route[1];

            if(in > max || out < min) {
                answer++;
                min = in;
                max = out;
            } else {
                min = in > min ? in : min;
                max = max > out ? out : max;
            }
            System.out.println(max+" "+min+" "+answer);
        }
        System.out.println("answer = " + answer);
        return answer;
    }
    public int solution1(int[][] routes) {
        int answer = 0; // 카메라의 갯수
        int camera = -30001; // 카메라의 위치
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));

        for (int[] route : routes) {
            System.out.println(Arrays.toString(route));
            if (camera < route[0]) {
                camera = route[1];
                answer++;
                System.out.println(camera);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        WatchingCamera watchingCamera= new WatchingCamera();

        long start = System.currentTimeMillis();
        //makeBigestNumber.solution("1924",2);
        //makeBigestNumber.solution("4177252841",4);
        watchingCamera.solution(new int[][]{{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}});
        long end = System.currentTimeMillis();

        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
    }
}
