/*
계정 겹치는지 확인 이름 같고 이메일 같으면 같음
그럼 이름 같은 걸로 HashMap<String, HashMap<Integer, List<String>>>
이메일은 그 밑에 또 HashMap<Integer, List<String>>
아니면 List<String>을 TreeSet으로 그 다음에 ceiling 해서 log(n)으로 찾을 수 있음 근데 합치는 것도 log(n)이긴 한데
그리고 uf 하면 될듯
 */

class Solution {
    Map<String, Map<String, Integer>> ne = new HashMap<>();
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
            String name = acc.get(0);
            for (int j = 1; j < acc.size(); j++) {
                if (ne.get(name) == null) {
                    ne.put(name, new HashMap<>());
                }
                if (ne.get(name).get(acc.get(j)) == null) {
                    ne.get(name).put(acc.get(j), i);
                    continue;
                }
                union(ne.get(name).get(acc.get(j)), i);
            }
        }

        Map<Integer, Set<String>> rMap = new HashMap<>();

        for (int i = 0; i < accounts.size(); i++) {
            if (rMap.get(find(i)) == null) {
                rMap.put(find(i), new HashSet<>());
            }
            for (int j = 1; j < accounts.get(i).size(); j++) {
                rMap.get(find(i)).add(accounts.get(i).get(j));
            }
        }

        List<List<String>> res = new ArrayList<>();

        for (int key : rMap.keySet()) {
            List<String> toList = new ArrayList<>(rMap.get(key));
            Collections.sort(toList);
            toList.add(0, accounts.get(key).get(0));
            res.add(toList);
        }

        return res;
    }
}