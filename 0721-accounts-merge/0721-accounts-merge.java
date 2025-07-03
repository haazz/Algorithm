/*
계정 겹치는지 확인 이름 같고 이메일 같으면 같음
그럼 이름 같은 걸로 HashMap<String, HashMap<Integer, List<String>>>
이메일은 그 밑에 또 HashMap<Integer, List<String>>
아니면 List<String>을 TreeSet으로 그 다음에 ceiling 해서 log(n)으로 찾을 수 있음 근데 합치는 것도 log(n)이긴 한데
그리고 uf 하면 될듯
 */

class Solution {
    Map<String, Integer> ne = new HashMap<>();
    int[] uf;

    public int find(int x) {
        if (x == uf[x]) {
            return x;
        }
        return uf[x] = find(uf[x]);
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x <= y) {
            uf[y] = x;
        } else {
            uf[x] = y;
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        uf = new int[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) {
            uf[i] = i;
        }

        for (int i = 0; i < accounts.size(); i++) {
            List<String> acc = accounts.get(i);
            for (int j = 1; j < acc.size(); j++) {
                if (ne.get(acc.get(j)) == null) {
                    ne.put(acc.get(j), i);
                    continue;
                }
                union(ne.get(acc.get(j)), i);
            }
        }

        Map<Integer, List<String>> rMap = new HashMap<>();

        for (Map.Entry<String, Integer> n : ne.entrySet()) {
            int idx = find(n.getValue());
            if (!rMap.containsKey(idx)) {
                rMap.put(idx, new ArrayList<>());
            }
            rMap.get(idx).add(n.getKey());
        }

        List<List<String>> res = new ArrayList<>();

        for (int key : rMap.keySet()) {
            List<String> toList = rMap.get(key);
            Collections.sort(toList);
            toList.addFirst(accounts.get(key).get(0));
            res.add(toList);
        }

        return res;
    }
}