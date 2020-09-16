package programmers.heap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * 문제 설명
 * 하드디스크는 한 번에 하나의 작업만 수행할 수 있습니다. 디스크 컨트롤러를 구현하는 방법은 여러 가지가 있습니다. 가장 일반적인 방법은 요청이 들어온 순서대로 처리하는 것입니다.
 * 예를들어
 * - 0ms 시점에 3ms가 소요되는 A작업 요청
 * - 1ms 시점에 9ms가 소요되는 B작업 요청
 * - 2ms 시점에 6ms가 소요되는 C작업 요청
 * 와 같은 요청이 들어왔습니다. 이를 그림으로 표현하면 아래와 같습니다.
 * Screen Shot 2018-09-13 at 6.34.58 PM.png
 * 한 번에 하나의 요청만을 수행할 수 있기 때문에 각각의 작업을 요청받은 순서대로 처리하면 다음과 같이 처리 됩니다.
 * Screen Shot 2018-09-13 at 6.38.52 PM.png
 * - A: 3ms 시점에 작업 완료 (요청에서 종료까지 : 3ms)
 * - B: 1ms부터 대기하다가, 3ms 시점에 작업을 시작해서 12ms 시점에 작업 완료(요청에서 종료까지 : 11ms)
 * - C: 2ms부터 대기하다가, 12ms 시점에 작업을 시작해서 18ms 시점에 작업 완료(요청에서 종료까지 : 16ms)
 * 이 때 각 작업의 요청부터 종료까지 걸린 시간의 평균은 10ms(= (3 + 11 + 16) / 3)가 됩니다.
 * 하지만 A → C → B 순서대로 처리하면
 * Screen Shot 2018-09-13 at 6.41.42 PM.png
 * - A: 3ms 시점에 작업 완료(요청에서 종료까지 : 3ms)
 * - C: 2ms부터 대기하다가, 3ms 시점에 작업을 시작해서 9ms 시점에 작업 완료(요청에서 종료까지 : 7ms)
 * - B: 1ms부터 대기하다가, 9ms 시점에 작업을 시작해서 18ms 시점에 작업 완료(요청에서 종료까지 : 17ms)
 * 이렇게 A → C → B의 순서로 처리하면 각 작업의 요청부터 종료까지 걸린 시간의 평균은 9ms(= (3 + 7 + 17) / 3)가 됩니다.
 * 각 작업에 대해 [작업이 요청되는 시점, 작업의 소요시간]을 담은 2차원 배열 jobs가 매개변수로 주어질 때, 작업의 요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법으로 처리하면 평균이 얼마가 되는지 return 하도록 solution 함수를 작성해주세요. (단, 소수점 이하의 수는 버립니다)
 * 제한 사항
 * jobs의 길이는 1 이상 500 이하입니다.
 * jobs의 각 행은 하나의 작업에 대한 [작업이 요청되는 시점, 작업의 소요시간] 입니다.
 * 각 작업에 대해 작업이 요청되는 시간은 0 이상 1,000 이하입니다.
 * 각 작업에 대해 작업의 소요시간은 1 이상 1,000 이하입니다.
 * 하드디스크가 작업을 수행하고 있지 않을 때에는 먼저 요청이 들어온 작업부터 처리합니다.
 * 입출력 예
 * jobs	return
 * [[0, 3], [1, 9], [2, 6]]	9
 * 입출력 예 설명
 * 문제에 주어진 예와 같습니다.
 * 0ms 시점에 3ms 걸리는 작업 요청이 들어옵니다.
 * 1ms 시점에 9ms 걸리는 작업 요청이 들어옵니다.
 * 2ms 시점에 6ms 걸리는 작업 요청이 들어옵니다.
 */

public class DiskController1 {
    public int solution(int[][] jobs) {
        int answer =0;
        int currentTime = 0;
        int allTime = 0;
        int visitedNum = 0;
        boolean[] isVisited = new boolean[jobs.length];
        PriorityQueue<Job> heap = new PriorityQueue();

        Arrays.sort(jobs,(job1,job2)->{
            if(job1[0]==job2[0])
                return Integer.compare(job1[1],job2[1]);
            else
                return Integer.compare(job1[0],job2[0]);
        });

        isVisited[0] = true;
        heap.add(new Job(jobs[0][0],jobs[0][1]));
        currentTime+=jobs[0][0];
        visitedNum++;

        while(!heap.isEmpty() || visitedNum != jobs.length){

            if(!heap.isEmpty()){
                Job currentJob = heap.poll();
                currentTime += currentJob.jobTime;
                allTime += (currentTime - currentJob.start);
            }
            else
                currentTime++;

            for(int i =0; i<jobs.length; i++){
                if(isVisited[i]) continue;
                if(currentTime>=jobs[i][0]){
                    isVisited[i] = true;
                    heap.add(new Job(jobs[i][0],jobs[i][1]));
                    visitedNum++;
                }
            }
        }

        answer = allTime / jobs.length;
        return answer;
    }

    public static void main(String[] args) {
        DiskController1 diskController = new DiskController1();
        int[][] jobs = {{0, 3}, {1, 9}, {2, 6}};
        int[][] job1 = {{0, 3}, {4, 3}, {10, 3}}; // 3
        int[][] job2 = {{0, 10}, {2, 3}, {9, 3}}; // 9
        int[][] job3 = {{1, 10}, {3, 3}, {10, 3}}; // 9
        int[][] job4 = {{0, 10}}; // 10
        int[][] job5 = {{0, 10}, {4, 10}, {5, 11}, {15, 2}};// 15
        int[][] job6 = {{0, 1}, {1, 2}, {500, 6}}; // 3
        int[][] job7 = {{0, 9}, {0, 4}, {0, 5}, {0, 7}, {0, 3}}; // 13
        int[][] job8 = {{0, 5}, {1, 2}, {5, 5}}; // 6
        diskController.solution(job5);
    }
}


class Job implements Comparable<Job>{

    int start;
    int jobTime;

    Job(int start,int jobTime){
        this.start = start;
        this.jobTime = jobTime;
    }

    @Override
    public int compareTo(Job job){
        if(this.jobTime>job.jobTime)
            return 1;
        else
            return -1;
    }

}
