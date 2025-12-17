class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> answer = new ArrayList<>();


        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] >= newInterval[0] && intervals[i][0] <= newInterval[1]) {
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            } else if (intervals[i][1] >= newInterval[0] && intervals[i][1] <= newInterval[1]) {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            } else if (intervals[i][0] <= newInterval[0] && intervals[i][1] >= newInterval[1]) {
                newInterval = intervals[i];
            } else {
                answer.add(intervals[i]);
            }
        }
        answer.add(newInterval);
        Collections.sort(answer, (a, b) -> a[0] - b[0]);
        int[][] ra = new int[answer.size()][2];

        for (int i = 0; i < answer.size(); i++) {
            ra[i] = answer.get(i);
        }
        return ra;
    }
}