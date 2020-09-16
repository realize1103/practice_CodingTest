package programmers.Greedy;

import java.util.Arrays;

/**
 * n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때, 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return 하도록 solution을 완성하세요.
 * 다리를 여러 번 건너더라도, 도달할 수만 있으면 통행 가능하다고 봅니다. 예를 들어 A 섬과 B 섬 사이에 다리가 있고, B 섬과 C 섬 사이에 다리가 있으면 A 섬과 C 섬은 서로 통행 가능합니다.
 * 제한사항
 * 섬의 개수 n은 1 이상 100 이하입니다.
 * costs의 길이는 ((n-1) * n) / 2이하입니다.
 * 임의의 i에 대해, costs[i][0] 와 costs[i] [1]에는 다리가 연결되는 두 섬의 번호가 들어있고, costs[i] [2]에는 이 두 섬을 연결하는 다리를 건설할 때 드는 비용입니다.
 * 같은 연결은 두 번 주어지지 않습니다. 또한 순서가 바뀌더라도 같은 연결로 봅니다. 즉 0과 1 사이를 연결하는 비용이 주어졌을 때, 1과 0의 비용이 주어지지 않습니다.
 * 모든 섬 사이의 다리 건설 비용이 주어지지 않습니다. 이 경우, 두 섬 사이의 건설이 불가능한 것으로 봅니다.
 * 연결할 수 없는 섬은 주어지지 않습니다.
 * 입출력 예
 * n	costs	return
 * 4	[[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]]	4
 * 입출력 예 설명
 * costs를 그림으로 표현하면 다음과 같으며, 이때 초록색 경로로 연결하는 것이 가장 적은 비용으로 모두를 통행할 수 있도록 만드는 방법입니다.
 *
 * 크루스칼 알고리즘(Kruskal Algorithm)
 * 가장 적은 비용으로 모든 노드를 연결하기 위해서 사용하는 알고리즘이다.
 *
 * https://gmlwjd9405.github.io/2018/08/29/algorithm-kruskal-mst.html
 *
 * 탐욕적인 방법(greedy method) 을 이용하여 네트워크(가중치를 간선에 할당한 그래프)의 모든 정점을 최소 비용으로 연결하는 최적 해답을 구하는 것
 *
 * 탐욕적인 방법
 * 결정을 해야 할 때마다 그 순간에 가장 좋다고 생각되는 것을 선택함으로써 최종적인 해답에 도달하는 것
 * 탐욕적인 방법은 그 순간에는 최적이지만, 전체적인 관점에서 최적이라는 보장이 없기 때문에 반드시 검증해야 한다.
 * 다행히 Kruskal 알고리즘은 최적의 해답을 주는 것으로 증명되어 있다.
 * MST(최소 비용 신장 트리) 가 1) 최소 비용의 간선으로 구성됨 2) 사이클을 포함하지 않음 의 조건에 근거하여 각 단계에서 사이클을 이루지 않는 최소 비용 간선을 선택 한다.
 * Kruskal 알고리즘의 동작
 *
 * 그래프의 간선들을 가중치의 오름차순으로 정렬한다.
 * 정렬된 간선 리스트에서 순서대로 사이클을 형성하지 않는 간선을 선택한다.
 * 즉, 가장 낮은 가중치를 먼저 선택한다.
 * 사이클을 형성하는 간선을 제외한다.
 * 해당 간선을 현재의 MST(최소 비용 신장 트리)의 집합에 추가한다.
 * https://gmlwjd9405.github.io/2018/08/29/algorithm-kruskal-mst.html
 */

public class ConncetIsland {
    public int solution(int n, int[][] costs) {
        int answer = 0;

        Arrays.sort(costs,(o1, o2)->{
            return o1[2]-o2[2];
        });

        // 부모노드를 기억한다.
        int[] parent = new int[n];

        // 초기 값 지정.
        for(int i=0; i<n; i++) {
            parent[i]=i;
        }

        for( int[] cost : costs ) {

            int from = cost[0];
            int to = cost[1];
            int value = cost[2];

            // 두 정점의 부모노드가 같다면, 이미 연결이 되었으므로 보지않는다.
            if( connectCheck(parent,from,to) ) continue;
            else {
                // 그렇지 않다면, 가중치를 늘려주고 부모노드를 update해준다.
                answer+=value;
                union(parent,from,to);
            }
        }

        System.out.println("answer = " + answer);
        return answer;
    }

    private static void union(int[] parent, int from, int to) {
        from = getParent(parent,from);
        to = getParent(parent,to);

        if (from < to)
            parent[to] = from;
        else
            parent[from] = to;
    }

    private static boolean connectCheck(int[] parent, int from, int to) {

        from = getParent(parent,from);
        to = getParent(parent,to);

        return from==to;
    }

    private static int getParent(int[] parent, int edge) {
        if(parent[edge]==edge) return edge;
        return getParent(parent, parent[edge]);
    }

    public static void main(String[] args) {
        ConncetIsland conncetIsland = new ConncetIsland();
        conncetIsland.solution(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}});
    }
}
