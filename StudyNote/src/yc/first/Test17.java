package yc.first;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//17. 电话号码的字母组合
public class Test17 {
    String[] phones = {
            " ",
            " ",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    //回溯算法
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(!digits.isEmpty()){
            backtrack(0,digits,new StringBuilder(),res);
        }
        return res;
    }

    //灰塑体
    private void backtrack(int i, String digits, StringBuilder track, List<String> res) {
        if(i == digits.length()){
            res.add(track.toString());
            track = new StringBuilder();
            return;
        }

        char c = digits.charAt(i);
        String cur_str = phones[c - '0'];
        for (int j = 0; j < cur_str.length(); j++) {
            track.append(cur_str.charAt(j));
            backtrack(i+1,digits,track,res);
            track.deleteCharAt(track.length() - 1);
        }
    }
}
