import java.util.*;
/**
- today와 수집일자 + 유효기간을 비교
- integer로 날짜를 다 만들어버리면 되지 않을까?
- 모든 달은 28일까지 있다고 가정한다.
- 파기해야할 개인정보 idx + 1을 오름차순으로 리턴
*/

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        long tod = toLong(today);
        
        HashMap<String, Integer> hm = new HashMap<>();
        
        for (int i = 0; i < terms.length; i++) {
            String[] tes = terms[i].split(" ");
            hm.put(tes[0], Integer.parseInt(tes[1]) * 28);
        }
        
        for (int i = 0; i < privacies.length; i++) {
            String[] ps = privacies[i].split(" ");
            if (toLong(ps[0]) + hm.get(ps[1]) <= tod) {
                answer.add(i + 1);
            }
        }
        int[] ra = new int[answer.size()];
        for (int i = 0; i < answer.size() ; i++) {
            ra[i] = answer.get(i);
        }
        return ra;
    }
    
    public long toLong(String days) {
        String[] ts = days.split("\\.");
        long tod = Integer.parseInt(ts[0]) * 28 * 12 +
            Integer.parseInt(ts[1]) * 28 +
            Integer.parseInt(ts[2]);
        return tod;
    }
}