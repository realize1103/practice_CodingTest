package programmers.dynamicProgramming;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * n개의 노드가 있는 그래프가 있습니다. 각 노드는 1부터 n까지 번호가 적혀있습니다. 1번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려고 합니다. 가장 멀리 떨어진 노드란 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드들을 의미합니다.
 * 노드의 개수 n, 간선에 대한 정보가 담긴 2차원 배열 vertex가 매개변수로 주어질 때, 1번 노드로부터 가장 멀리 떨어진 노드가 몇 개인지를 return 하도록 solution 함수를 작성해주세요.
 * 제한사항
 * 노드의 개수 n은 2 이상 20,000 이하입니다.
 * 간선은 양방향이며 총 1개 이상 50,000개 이하의 간선이 있습니다.
 * vertex 배열 각 행 [a, b]는 a번 노드와 b번 노드 사이에 간선이 있다는 의미입니다.
 * 입출력 예
 * n	vertex	return
 * 6	[[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]	3
 * 입출력 예 설명
 * 예제의 그래프를 표현하면 아래 그림과 같고, 1번 노드에서 가장 멀리 떨어진 노드는 4,5,6번 노드입니다.
 */
public class FuthestNode {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer>[] list = new ArrayList[n + 1];
        int[] visit = new int[n + 1];
        int max = 0;

        for(int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < edge.length; i++) {
            list[edge[i][0]].add(edge[i][1]);
            list[edge[i][1]].add(edge[i][0]);
        }

        q.add(1);
        visit[1] = 1;
        while(!q.isEmpty()) {
            int x = q.poll();
            for(int i = 0; i < list[x].size(); i++) {
                if(visit[list[x].get(i)] == 0) {
                    q.add(list[x].get(i));
                    visit[list[x].get(i)] = visit[x] + 1;
                    max = Math.max(visit[list[x].get(i)], max);
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            if(visit[i] == max) answer++;
        }
        return answer;
    }
}
/**
 * BFS(너비우선탐색)을 사용해 해결할 수 있는 경로가 가장 긴 단말 노드의 개수를 구하는 문제이다.
 *
 * 1번째 노드를 시작으로 인접한 각 노드를 방문하면서 방문한 노드의 경로 길이를 저장한다.
 *
 * 최종적으로 가장 긴 경로의 노드를 개수를 세는데
 *
 * 이 때, 문제는 2차원 배열을 사용하면 case8, 9에서 heap overflow가 발생한다.
 *
 * 따라서 Colllections Framework의 List 클래스를 사용해 구현한다.
 *
 * 비슷한 문제로는 SWEA의 단말노드 문제가 있다.
 *
 * https://dheldh77.tistory.com/253
 */