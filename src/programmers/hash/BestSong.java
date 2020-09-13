package programmers.hash;

import java.util.*;

/**
 *
 * 문제 설명
 *스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
 * 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
 * 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
 * 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
 * 노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
 * 제한사항
 * genres[i]는 고유번호가 i인 노래의 장르입니다.
 * plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
 * genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
 * 장르 종류는 100개 미만입니다.
 * 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
 * 모든 장르는 재생된 횟수가 다릅니다.
 * 입출력 예
 * genres	plays	return
 * [classic, pop, classic, classic, pop]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]
 * 입출력 예 설명
 * classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.
 * 고유 번호 3: 800회 재생
 * 고유 번호 0: 500회 재생
 * 고유 번호 2: 150회 재생
 * pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.
 * 고유 번호 4: 2,500회 재생
 * 고유 번호 1: 600회 재생
 * 따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.
 *
 */

public class BestSong {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};

        class PlayInfo implements Comparable<PlayInfo> {
            int id, play;
            String genre;

            PlayInfo(int id, int play, String genre){
                this.id = id;
                this.play = play;
                this.genre = genre;
            }

            @Override
            public int compareTo(PlayInfo o){
                System.out.println("compareTo "+this.play+"  "+o.play);
                if(this.play == o.play){
                    return this.id - o.id;
                } else {
                    return -(this.play - o.play);
                }
            }
        }

        ArrayList<PlayInfo> alPlayList = new ArrayList<PlayInfo>();
        HashMap<String,Integer> hmSumPlayCntByGenre = new HashMap<>();
        HashMap<String,Integer> hmInputListLimit2 = new HashMap<>();

        for(int i = 0 ; i < genres.length ; ++i){
            int id = i;
            int play = plays[i];
            String genre = genres[i];

            alPlayList.add(new PlayInfo(id, play, genre));

            hmSumPlayCntByGenre.put(genres[i],hmSumPlayCntByGenre.getOrDefault(genres[i],0)+plays[i]);
        }


        Collections.sort(alPlayList, new Comparator<PlayInfo>(){
            @Override
            public int compare(PlayInfo s1, PlayInfo s2){
                System.out.println(s1.genre+"  "+s1.play+ " compare to "+ s2.genre+"  "+s2.play);
                if(s1.genre.equals(s2.genre)){
                    return s1.compareTo(s2);
                } else {
                    System.out.println(".sort genre not match : "+hmSumPlayCntByGenre.get(s1.genre) +"====="+ hmSumPlayCntByGenre.get(s2.genre));
                    return -(hmSumPlayCntByGenre.get(s1.genre) - hmSumPlayCntByGenre.get(s2.genre));
                }
            }
        });

        ArrayList<Integer> alAnswer = new ArrayList<Integer>();
        System.out.println("======Input in BestAlbum=========");
        for(PlayInfo song : alPlayList){
            hmInputListLimit2.put(song.genre,hmInputListLimit2.getOrDefault(song.genre,0)+1);
            //System.out.println("Input Cnt by genre"+hmInputListLimit2.get(song.genre));
            if(hmInputListLimit2.get(song.genre) > 2){
                continue;
            }
            System.out.println(song.genre + " "+song.play+" "+song.id);
            alAnswer.add(song.id);
        }

        answer = new int[alAnswer.size()];
        for(int i = 0 ; i < answer.length ; ++i){
            answer[i] = alAnswer.get(i);
            System.out.println(alAnswer.get(i));
        }

        return answer;
    }


    public static void main(String[] args) {
        BestSong bestSong = new BestSong();
        String[] genres = {"classic", "pop","pop", "classic", "classic","pop"};
        int[] plays = {500, 600, 600, 150, 800, 2500};
        bestSong.solution(genres,plays);
    }
}
