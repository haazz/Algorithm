class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] lastWork = new int[26];
        int[] task = new int[26];

        for (int i = 0; i < tasks.length; i++) {
            task[tasks[i] - 'A']++;
        }

        
        int cur = 101;

        while (true) {
            boolean isEmpty = true;
            int update  = -1;
            int maxRemainTask = 0;
            int minRemainTask = Integer.MAX_VALUE;

            for (int i = 0; i < 26; i++) {
                if (maxRemainTask < task[i] && cur - lastWork[i] > n) {
                    update = i;
                    maxRemainTask = task[i];
                }
                if (task[i] != 0) {
                    isEmpty = false;
                    minRemainTask = Math.min(minRemainTask, n - (cur - lastWork[i]) + 1);
                }
                
            }

            if (isEmpty) {
                break;
            }

            if (update == -1) {
                cur += minRemainTask;
                System.out.println(minRemainTask);
                continue;
            }
            lastWork[update] = cur++;
            task[update]--;

        }
        return cur - 101;
    }
}