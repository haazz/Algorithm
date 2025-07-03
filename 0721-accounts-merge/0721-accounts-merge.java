// ----------------------- with sep DSU class -------------------
// class Solution {
//     static class DisjointSet {
//         int[] parent, size;

//         public DisjointSet(int n) {
//             parent = new int[n];
//             size = new int[n];
//             for (int i = 0; i < n; i++) {
//                 parent[i] = i;
//                 size[i] = 1;
//             }
//         }

//         public int findUPar(int node) {
//             if (parent[node] == node) return node;
//             return parent[node] = findUPar(parent[node]);
//         }

//         public void unionBySize(int u, int v) {
//             int pu = findUPar(u);
//             int pv = findUPar(v);
//             if (pu == pv) return;

//             if (size[pu] < size[pv]) {
//                 parent[pu] = pv;
//                 size[pv] += size[pu];
//             } else {
//                 parent[pv] = pu;
//                 size[pu] += size[pv];
//             }
//         }
//     }

//     public List<List<String>> accountsMerge(List<List<String>> accounts) {
//         int n = accounts.size();
//         DisjointSet ds = new DisjointSet(n);
//         Map<String, Integer> mailToIndex = new HashMap<>();

//         // Step 1: Map each email to its first occurring account index and union
//         for (int i = 0; i < n; i++) {
//             List<String> account = accounts.get(i);
//             for (int j = 1; j < account.size(); j++) {
//                 String email = account.get(j);
//                 if (!mailToIndex.containsKey(email)) {
//                     mailToIndex.put(email, i);
//                 } else {
//                     ds.unionBySize(i, mailToIndex.get(email));
//                 }
//             }
//         }

//         // Step 2: Collect emails under the ultimate parent index
//         Map<Integer, TreeSet<String>> indexToEmails = new HashMap<>();
//         for (Map.Entry<String, Integer> entry : mailToIndex.entrySet()) {
//             String email = entry.getKey();
//             int rootIdx = ds.findUPar(entry.getValue());
//             indexToEmails.computeIfAbsent(rootIdx, x -> new TreeSet<>()).add(email);
//         }

//         // Step 3: Prepare the final merged account list
//         List<List<String>> result = new ArrayList<>();
//         for (Map.Entry<Integer, TreeSet<String>> entry : indexToEmails.entrySet()) {
//             int index = entry.getKey();
//             List<String> merged = new ArrayList<>();
//             merged.add(accounts.get(index).get(0)); // Correct name from root index
//             merged.addAll(entry.getValue());
//             result.add(merged);
//         }

//         return result;
//     }
// }

// ---------------------------- OR -------------------------------

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        int[] parent = new int[n];
        int[] size = new int[n];

        // Initialize DSU arrays
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // Step 1: Map emails and union accounts with shared emails
        Map<String, Integer> mailToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (!mailToIndex.containsKey(email)) mailToIndex.put(email, i);
                else unionBySize(i, mailToIndex.get(email), parent, size);
            }
        }

        // Step 2: Group emails by root parent index
        Map<Integer, TreeSet<String>> indexToEmails = new HashMap<>();
        for (Map.Entry<String, Integer> entry : mailToIndex.entrySet()) {
            String email = entry.getKey();
            int root = findParent(entry.getValue(), parent);
            if (!indexToEmails.containsKey(root)) indexToEmails.put(root, new TreeSet<>());
            indexToEmails.get(root).add(email);
        }

        // Step 3: Build result list
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<Integer, TreeSet<String>> entry : indexToEmails.entrySet()) {
            int index = entry.getKey();
            List<String> merged = new ArrayList<>();
            merged.add(accounts.get(index).get(0)); // Add name from root index
            merged.addAll(entry.getValue());
            result.add(merged);
        }

        return result;
    }

    private int findParent(int node, int[] parent) {
        if (parent[node] == node) return node;
        return parent[node] = findParent(parent[node], parent); // Path compression
    }

    private void unionBySize(int u, int v, int[] parent, int[] size) {
        int pu = findParent(u, parent);
        int pv = findParent(v, parent);
        if (pu == pv) return;

        if (size[pu] < size[pv]) {
            parent[pu] = pv;
            size[pv] += size[pu];
        } else {
            parent[pv] = pu;
            size[pu] += size[pv];
        }
    }
}


// class Solution {
//     public List<List<String>> accountsMerge(List<List<String>> accounts) {
//         Map<String, String> emailToName = new HashMap<>();
//         Map<String, String> parent = new HashMap<>();

//         // Step 1: Initialize parent and email-to-name map
//         for (List<String> account : accounts) {
//             String name = account.get(0);
//             for (int i = 1; i < account.size(); i++) {
//                 String email = account.get(i);
//                 parent.put(email, email);
//                 emailToName.put(email, name);
//             }
//         }

//         // Step 2: Union emails in the same account
//         for (List<String> account : accounts) {
//             String firstEmail = account.get(1);
//             for (int i = 2; i < account.size(); i++) {
//                 union(firstEmail, account.get(i), parent);
//             }
//         }

//         // Step 3: Group emails by their root parent
//         Map<String, TreeSet<String>> unions = new HashMap<>();
//         for (String email : parent.keySet()) {
//             String root = find(email, parent);
//             unions.computeIfAbsent(root, x -> new TreeSet<>()).add(email);
//         }

//         // Step 4: Prepare the result
//         List<List<String>> result = new ArrayList<>();
//         for (String root : unions.keySet()) {
//             ArrayList<String> mergedAccount = new ArrayList<>();
//             mergedAccount.add(emailToName.get(root));  // Add name
//             mergedAccount.addAll(unions.get(root));    // Add sorted emails
//             result.add(mergedAccount);
//         }

//         return result;
//     }

//     // Static Union-Find helper methods
//     public String find(String email, Map<String, String> parent) {
//         if (!parent.get(email).equals(email)) {
//             parent.put(email, find(parent.get(email), parent));
//         }
//         return parent.get(email);
//     }

//     public void union(String email1, String email2, Map<String, String> parent) {
//         String root1 = find(email1, parent);
//         String root2 = find(email2, parent);
//         if (!root1.equals(root2)) {
//             parent.put(root2, root1);
//         }
//     }
// }