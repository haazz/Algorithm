class Solution {
    public String solution(String[] survey, int[] choices) {
        int[] su = new int[4];
        
        for (int i = 0; i < survey.length; i++) {
            char a = survey[i].charAt(0);
            char b = survey[i].charAt(1);
            if (a == 'R') {
                su[0] += (choices[i] - 4) * - 1;
            } else if (a == 'T') {
                su[0] += choices[i] - 4;
            } else if (a == 'C') {
                su[1] += (choices[i] - 4) * - 1;                
            } else if (a == 'F') {
                su[1] += choices[i] - 4;
            } if (a == 'J') {
                su[2] += (choices[i] - 4) * - 1;
            } else if (a == 'M') {
                su[2] += choices[i] - 4;
            } if (a == 'A') {
                su[3] += (choices[i] - 4) * - 1;
            } else if (a == 'N') {
                su[3] += choices[i] - 4;
            }
        }
        
        StringBuilder answer = new StringBuilder();
        
        if (su[0] >= 0) {
            answer.append('R');
        } else {
            answer.append('T');
        }
        
        if (su[1] >= 0) {
            answer.append('C');
        } else {
            answer.append('F');
        }
        
        if (su[2] >= 0) {
            answer.append('J');
        } else {
            answer.append('M');
        }
        
        if (su[3] >= 0) {
            answer.append('A');
        } else {
            answer.append('N');
        }
        
        return answer.toString();
    }
}