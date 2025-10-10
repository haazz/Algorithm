import java.util.*;
import java.util.Map.*;

class Solution {
    HashMap<String, Integer> in = new HashMap<>();
    TreeMap<String, Integer> times = new TreeMap<>();
    
    public int[] solution(int[] fees, String[] records) {
        for (int i = 0; i < records.length; i++) {
            String[] rs = records[i].split(" ");
            String[] ts = rs[0].split(":");
            int t = Integer.parseInt(ts[0]) * 60 + Integer.parseInt(ts[1]);
            if (rs[2].equals("IN")) {
                in.put(rs[1], t);
            } else {
                if (!times.containsKey(rs[1])) {
                    times.put(rs[1], 0);
                }
                times.put(rs[1], times.get(rs[1]) + (t - in.get(rs[1])));
                in.remove(rs[1]);
            }
        }
        
        for (String key : in.keySet()) {
            if (!times.containsKey(key)) {
                times.put(key, 0);
            }
            times.put(key, times.get(key) + (24 * 60 - 1 - in.get(key)));
        }
        
        int[] answer = new int[times.size()];
        int idx = 0;
        while (!times.isEmpty()) {
            Entry<String, Integer> en = times.pollFirstEntry();
            int time = en.getValue() - fees[0];
            answer[idx] = fees[1];
            if (time > 0) {
                answer[idx] += (time / fees[2]) * fees[3];
                if (time % fees[2] != 0) {
                    answer[idx] += fees[3];
                }
            }
            idx++;
        }
        return answer;
    }
}