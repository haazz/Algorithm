/*
coin 2개를 쓸 수 있을때 안 쓰고 1개씩만 써서 아껴서 더 오래갈 수 있는 경우의 수 확인해 보기
*/
import java.util.*;
import java.io.*;

class Solution {
    public int solution(int coin, int[] cards) {
        Set<Integer> hand = new HashSet<>();
        Set<Integer> trash = new HashSet<>();
        int answer = 1;
        int can = 0;
        int N = cards.length;
        
        for (int i = 0; i < N / 3; i++) {
            if (hand.contains(cards[i])) {
                hand.remove(cards[i]);
                can++;
                continue;
            }
            hand.add(N + 1 - cards[i]);
        }
        
        int oneCoin = 0;
        int twoCoin = 0;
        
        for (int i = N / 3; i < N; i += 2) {
            if (hand.contains(cards[i])) {
                oneCoin++;
            } else if (trash.contains(cards[i])) {
                trash.remove(cards[i]);
                twoCoin++;
            } else {
                trash.add(N + 1 - cards[i]);
            }
            
            if (hand.contains(cards[i + 1])) {
                oneCoin++;
            } else if (trash.contains(cards[i + 1])) {
                trash.remove(cards[i + 1]);
                twoCoin++;
            } else {
                trash.add(N + 1 - cards[i + 1]);
            }
            
            if (can <= 0) {
                if (coin <= 0 || (coin == 1 && oneCoin == 0) || (oneCoin == 0 && twoCoin == 0)) {
                    return answer;
                }
                if (oneCoin == 0) {
                    twoCoin--;
                    coin -= 2;
                    can++;
                } else {
                    oneCoin--;
                    coin--;
                    can++;
                }
            }
            can--;
            answer++;
        }
        return answer;
    }
}