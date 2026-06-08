/**
 * @param {string[]} strs
 * @return {string[][]}
 */
var groupAnagrams = function(strs) {
    const map = {};

    for (let i = 0; i < strs.length; i++) {
        const key = strs[i].split("").sort().join("");
        if (map[key] == null) {
            map[key] = [];
        }
        map[key].push(strs[i]);
    }

    const res = [];

    for (let key in map) {
        res.push(map[key]);
    }
    return res;
};