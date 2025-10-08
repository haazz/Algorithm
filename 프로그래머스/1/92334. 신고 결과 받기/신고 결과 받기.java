import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        HashMap<String, Integer> user = new HashMap<>();    
        HashMap<String, Set<String>> re = new HashMap<>();
        
        for (int i = 0; i < id_list.length; i++) {
            re.put(id_list[i], new HashSet<>());
            user.put(id_list[i], 0);
        }
        
        for (int i = 0; i < report.length; i++) {
            String[] ss = report[i].split(" ");
            re.get(ss[1]).add(ss[0]);
        }
        
        for (int i = 0; i < id_list.length; i++) {
            Set<String> ss = re.get(id_list[i]);
            
            if (ss.size() < k) {
                continue;
            }
            for (String s : ss) {
                user.put(s, user.get(s) + 1);;
            }
        }
        
        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            answer[i] = user.get(id_list[i]);
        }
        return answer;
    }
}