package programmers.stackAndQueue;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.
 * 또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.
 * 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.
 * 제한 사항
 * 작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
 * 작업 진도는 100 미만의 자연수입니다.
 * 작업 속도는 100 이하의 자연수입니다.
 * 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
 * 입출력 예
 * progresses	speeds	return
 * [93, 30, 55]	[1, 30, 5]	[2, 1]
 * [95, 90, 99, 99, 80, 99]	[1, 1, 1, 1, 1, 1]	[1, 3, 2]
 * 입출력 예 설명
 * 입출력 예 #1
 * 첫 번째 기능은 93% 완료되어 있고 하루에 1%씩 작업이 가능하므로 7일간 작업 후 배포가 가능합니다.
 * 두 번째 기능은 30%가 완료되어 있고 하루에 30%씩 작업이 가능하므로 3일간 작업 후 배포가 가능합니다. 하지만 이전 첫 번째 기능이 아직 완성된 상태가 아니기 때문에 첫 번째 기능이 배포되는 7일째 배포됩니다.
 * 세 번째 기능은 55%가 완료되어 있고 하루에 5%씩 작업이 가능하므로 9일간 작업 후 배포가 가능합니다.
 * 따라서 7일째에 2개의 기능, 9일째에 1개의 기능이 배포됩니다.
 * 입출력 예 #2
 * 모든 기능이 하루에 1%씩 작업이 가능하므로, 작업이 끝나기까지 남은 일수는 각각 5일, 10일, 1일, 1일, 20일, 1일입니다. 어떤 기능이 먼저 완성되었더라도 앞에 있는 모든 기능이 완성되지 않으면 배포가 불가능합니다.
 */

public class FunctionalDevelopment {
    int[] needDays;

    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        needDays = new int[progresses.length];
        List<Integer> listProgresses = Arrays.stream(progresses).boxed().collect(Collectors.toList());
        List<Integer> listSpeeds = Arrays.stream(speeds).boxed().collect(Collectors.toList());
        ArrayList<Integer> listAnswer = new ArrayList<>();

        this.calcNeedDays(progresses, speeds);
        List<Integer> listNeedDays = Arrays.stream(needDays).boxed().collect(Collectors.toList());

        //queueTask.add(listProgresses.get(0));
        while (!listProgresses.isEmpty()){
            int cnt = 0;
            for(int i = 0, length = listProgresses.size(); i < length; i++){
                int intProgressDay = listNeedDays.get(0);
                int intProgressThisTime = listProgresses.get(i)+(listSpeeds.get(i)*intProgressDay);
                if(i != 0 && intProgressThisTime < 100){
                    break;
                }

                if(intProgressThisTime >= 100){
                    cnt++;
                }
            }
            if(cnt > 0){
                listAnswer.add(cnt);
            }
            for(int i = 0 , length = cnt; i < length; i++){
                listProgresses.remove(0);
                listSpeeds.remove(0);
                listNeedDays.remove(0);
            }
            //System.out.println(listProgresses.toString());
            //System.out.println(listSpeeds.toString());
            //System.out.println(listAnswer.toString());

        }
        answer = listAnswer.stream().mapToInt(i->i).toArray();

        return answer;
    }
    private void calcNeedDays(int[] progresses, int[] speeds)
    {
        for(int i=0; i<progresses.length; i++)
        {
            double remainProgress = 100 - progresses[i];
            double fNeedDay = remainProgress / speeds[i];

            needDays[i] = (int)Math.ceil(fNeedDay);
        }
    }
    //간결 람다식
    public int[] solution1(int[] progresses, int[] speeds) {
        int[] dayOfend = new int[100];
        int day = -1;
        for(int i=0; i<progresses.length; i++) {
            while(progresses[i] + (day*speeds[i]) < 100) {
                day++;
            }
            dayOfend[day]++;
        }
        return Arrays.stream(dayOfend).filter(i -> i!=0).toArray();
    }
    //속도, 큐사용
    public int[] solution2(int[] progresses, int[] speeds) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < speeds.length; i++) {
            double remain = (100 - progresses[i]) / (double) speeds[i];
            int date = (int) Math.ceil(remain);

            if (!q.isEmpty() && q.peek() < date) {
                answerList.add(q.size());
                q.clear();
            }

            q.offer(date);
        }

        answerList.add(q.size());

        int[] answer = new int[answerList.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
    public static void main(String[] args) {
        FunctionalDevelopment functionalDevelopment = new FunctionalDevelopment();
        int[] progresses = {93, 30, 55};
        int[] progresses1 = {95, 90, 99, 99, 80, 99};
        int[] speeds = {1, 30, 5};
        int[] speeds1 = {1, 1, 1, 1, 1, 1};

        long start = System.currentTimeMillis();
        functionalDevelopment.solution(progresses,speeds);
        functionalDevelopment.solution(progresses1,speeds1);
        long end = System.currentTimeMillis();

        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
        start = System.currentTimeMillis();
        functionalDevelopment.solution1(progresses,speeds);
        functionalDevelopment.solution1(progresses1,speeds1);
        end = System.currentTimeMillis();

        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
        start = System.currentTimeMillis();
        functionalDevelopment.solution2(progresses,speeds);
        functionalDevelopment.solution2(progresses1,speeds1);
        end = System.currentTimeMillis();

        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );



        //mockTest.solution(answers1);
        //mockTest.solution1(answers);
        //mockTest.solution1(citations1);
    }
}
