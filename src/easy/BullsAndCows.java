package easy;

import java.util.*;

/**
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.
 *
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows.
 *
 * Please note that both secret number and friend's guess may contain duplicate digits.
 *
 * Example 1:
 *
 * Input: secret = "1807", guess = "7810"
 *
 * Output: "1A3B"
 *
 * Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
 * Example 2:
 *
 * Input: secret = "1123", guess = "0111"
 *
 * Output: "1A1B"
 *
 * Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
 */

public class BullsAndCows {
    public String getHint(String secret, String guess) {

        if (secret.length() != guess.length()){
            return "";
        }
        String[] strArrSecret = secret.split("");
        String[] strArrGuess = guess.split("");
        int intACnt = 0,intBCnt = 0;
        int[] arrA = new int[10];
        int[] arrB = new int[10];


        for (int i = 0, length = strArrSecret.length; i < length; i++){

            if (strArrSecret[i].equals(strArrGuess[i])){
                intACnt++;
            }else{
                arrA[Integer.parseInt(strArrSecret[i])]++;
                arrB[Integer.parseInt(strArrGuess[i])]++;
            }
        }
        for(int i=0; i<10; i++){
            intBCnt += Math.min(arrA[i], arrB[i]);
        }

        return intACnt+"A"+intBCnt+"B";

    }

    public static void main(String[] args) {
        BullsAndCows bullsAndCows = new BullsAndCows();
        System.out.println(bullsAndCows.getHint("1122","2211"));
        //1A1B
    }
}
