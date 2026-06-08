/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function(s) {
    const map = [];

    let l = -1;
    let max = 0;

    for (let i = 0; i < s.length; i++) {
        const ch = s.charAt(i);
    
        if (map[ch] != null) {
            l = Math.max(map[ch], l);
        }
        map[ch] = i;
        max = Math.max(max, i - l);
    }
    return max;
};