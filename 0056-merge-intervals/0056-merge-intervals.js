/**
 * @param {number[][]} intervals
 * @return {number[][]}
 */
var merge = function(intervals) {
    const res = [];
    intervals.sort((a, b) => {
        if (a[0] === b[0]) {
            return a[1] - b[1];
        }
        return a[0] - b[0];
    });

    for (let i = 0; i < intervals.length; i++) {
        if (res.length == 0) {
            res.push(intervals[i]);
            continue;
        }
        const pl = res[res.length - 1][0];
        const pr = res[res.length - 1][1];

        if (pl <= intervals[i][0] && pr >= intervals[i][0]) {
            res[res.length - 1][1] = Math.max(pr, intervals[i][1]);
        } else {
            res.push(intervals[i]);
        }
    }
    return res;
};