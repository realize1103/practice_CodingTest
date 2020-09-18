package programmers.gragh;

import java.util.Arrays;

/**
 * n명의 권투선수가 권투 대회에 참여했고 각각 1번부터 n번까지 번호를 받았습니다. 권투 경기는 1대1 방식으로 진행이 되고, 만약 A 선수가 B 선수보다 실력이 좋다면 A 선수는 B 선수를 항상 이깁니다. 심판은 주어진 경기 결과를 가지고 선수들의 순위를 매기려 합니다. 하지만 몇몇 경기 결과를 분실하여 정확하게 순위를 매길 수 없습니다.
 * 선수의 수 n, 경기 결과를 담은 2차원 배열 results가 매개변수로 주어질 때 정확하게 순위를 매길 수 있는 선수의 수를 return 하도록 solution 함수를 작성해주세요.
 * 제한사항
 * 선수의 수는 1명 이상 100명 이하입니다.
 * 경기 결과는 1개 이상 4,500개 이하입니다.
 * results 배열 각 행 [A, B}는 A 선수가 B 선수를 이겼다는 의미입니다.
 * 모든 경기 결과에는 모순이 없습니다.
 * 입출력 예
 * n	results	return
 * 5	{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}	2
 * 입출력 예 설명
 * 2번 선수는 [1, 3, 4] 선수에게 패배했고 5번 선수에게 승리했기 때문에 4위입니다.
 * 5번 선수는 4위인 2번 선수에게 패배했기 때문에 5위입니다.
 */

public class Order {
    int INF=987654321;//방문불가를 뜻함
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] scores=new int[n+1][n+1];
        int win, lose;
        //배열 초기화
        for(int[] score:scores){
            Arrays.fill(score, INF);
        }
        //대각선을 0
        for(int i=0;i<scores.length;i++){
            for(int j=0;j<scores.length;j++){
                if(i==j) scores[i][j]=0;
            }
        }
        System.out.println("=================");
        for (int[] result : scores) {
            System.out.println("scores = " + Arrays.toString(result));
        }
        //한방향 그래프 win->lose
        for(int[] result:results){
            win=result[0];
            lose=result[1];
            scores[win][lose]=1;
        }
        System.out.println("=================");
        for (int[] result : scores) {
            System.out.println("scores = " + Arrays.toString(result));
        }
        //scores[i][j]로 가는 최단경로 저장
        //3개 for문을 이용해 최단 경로를 찾는다. 이때 k는 거쳐가는 꼭짓점, i는 출발하는 꼭짓점, j는 도착하는 꼭짓점이다.
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(scores[i][j]>scores[i][k]+scores[k][j]){
                        scores[i][j]=scores[i][k]+scores[k][j];
                    }
                }
            }
        }
        System.out.println("=================");
        for (int[] result : scores) {
            System.out.println("scores = " + Arrays.toString(result));
        }
        // for(int[] score:scores){
        //     System.out.println(Arrays.toString(score));
        // }
        //선수들이 게임을 한 적이 있는지 확인
        boolean[] flag=new boolean[n+1];
        Arrays.fill(flag, true);
        for(int i=1;i<=n;i++){//사람 i 기준
            for(int j=1;j<=n;j++){//나머지 j선수들과 게임한적 있는지 체크
                if(i==j) continue;//나자신과 게임을 뜻하므로 패스
                if(scores[i][j]==INF&&scores[j][i]==INF){//경로가 존재하지 않으면(i와 j가 게임하지 않았다면)
                    flag[i]=false;
                    break;//모두와 게임을 해야하므로
                }
            }
        }

        System.out.println("scores = " + Arrays.toString(flag));
        // System.out.println(Arrays.toString(flag));
        for(int i=1;i<flag.length;i++){
            if(flag[i]) answer++;
        }
        return answer;
    }


    public static void main(String[] args) {
        Order order = new Order();
        order.solution(4, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}});
    }
}
/**
 * 설명
 * 플로이드 와샬 알고리즘을 적용한다. 실행결과
 * 그래프에서 모든 꼭짓점 사이의 최단 경로의 거리를 구하는 알고리즘이다.
 * 이 알고리즘을 이용하면 최단 경로를 알 수 있을 뿐만 아니라 연결되지 않은 경로(갈 수 없는 경로)도 알 수 있다.
 * 연결되지 않았다는 뜻은 선수 i, j의 승패를 알 수 없다는 뜻이다.
 * 직접 연결되지 않았지만 선수 k로 거쳐서 i, j가 연결된다면 선수의 승패를 알 수 있다(i, j가 게임하진 않았지만, i가 k한테 이겼고, k가 j한테 이겼다면 i는 j한테 이김을 알 수 있다).
 * 따라서 i, j 사이에 k를 거쳐가는 방법을 모두 찾아 최단경로를 결정하는 플로이드 와샬 알고리즘을 이용하면 선수들의 승패를 확인할 수 있다.
 * 2차원 배열에 플로이드 와샬 알고리즘을 결과값을 저장한다.
 * int[][] scores로 n+1길이만큼 선언한다.
 * 선수는 1부터 시작하므로 배열을 n+1만큼 생성하여 0은 사용하지 않겠다.
 * 내가 나와 게임하는 경우는 없으므로 대각선은 0으로 초기화한다.
 * 나머지 값들은 INF에 적당히 큰 수를 넣어 이 값으로 저장한다.
 * 이때 큰 수를 넣는다고 Integer.MAX_VALUE를 넣으면 안된다. 실행결과 최단경로를 계산하기 위해 값을 서로 더하는데, int의 최댓값을 둘 다 더할 경우 오버플로우로 음수가 발생한다.
 * win->lose 방향으로 한 방향 그래프를 만든다.
 * 3개 for문을 이용해 최단 경로를 찾는다. 이때 k는 거쳐가는 꼭짓점, i는 출발하는 꼭짓점, j는 도착하는 꼭짓점이다.
 * 즉, 선수 i, j의 승패를 알고자 할 때, i, j가 선수 k와 게임한 적이 있다면 해당 거리를 저장한다(INF에서 최단경로 거리값으로 저장되었으므로 선수 i, j간의 승패여부를 알 수 있다는 뜻).
 * 승패를 알 수 없는 선수들을 찾는다.
 * boolean[] flag로 선수의 순위를 알 수 있는지에 대한 여부를 저장한다.
 * 일단 모두가 알 수 있다고 가정한 후, scores를 확인하면서 알 수 없는 선수에게 false를 준다. 실행결과 (0은 사용하지 않고, 대각선은 나자신이므로 빗금처리 했다.)
 * 선수 i를 기준으로, 선수 j와 승패를 알 수 있는지 확인한다. scores[i][j]와 scores[j][i]가 모두 INF면 선수 i, j간에 경로가 없다는 뜻이다. 즉, 게임을 한 적이 없고, 다른 선수를 통해서도 승패를 알 수 없으므로 flag[i]를 false로 바꾼다.
 * 선수 i는 나를 제외한 n-1명과 방문할 수 있어야 승패를 알 수 있다. 따라서 하나라도 경로가 존재하지 않는다면 i의 선수 승패는 알 수 없으므로 break로 i에 대한 탐색을 종료한다.
 * 마지막으로 flage의 true 갯수를 리턴하면 그것이 정답이다.
 */