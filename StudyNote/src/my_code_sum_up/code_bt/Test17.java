package my_code_sum_up.code_bt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//17. 电话号码的字母组合
// 方法一： 回溯算法
// 方法二： 两数相乘
// 方法三； 队列实现
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

    // 回溯算法
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(!digits.isEmpty()){
            backtrack(0,digits,new StringBuilder(),res);
        }
        return res;
    }

    // 回溯体
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

    //-------------------------------------------方法二： 定义相乘 ----------------------------------------------
    public List<String> letterCombinations2(String digits) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            ans = mul(ans,getList(digits.charAt(i) - '0'));
        }
        return ans;
    }

    private List<String> getList(int index){
        List<String> ans = new ArrayList<>();
        String cur = phones[index];
        for (int i = 0; i < cur.length(); i++) {
            ans.add(cur.charAt(i) + "");
        }
        return ans;
    }

    // 两数融合
    private List<String> mul(List<String> l1, List<String> l2) {
        if(l1.size() == 0) return l2;
        List<String> ans = new ArrayList<>();
        for (String s : l1) {
            for (String s1 : l2) {
                ans.add(s+s1);
            }
        }
        return ans;
    }

    // -------------------------方法三：队列实现--------------------------
    public List<String> letterCombinations3(String digits) {
        List<String> ans = new LinkedList<String>();
        if(!digits.equals("")){
            for (int i = 0; i < digits.length(); i++) {
                String cur = phones[digits.charAt(i) - '0'];
                if(ans.isEmpty()){
                    for (int j = 0; j < cur.length(); j++) {
                        ans.add(cur.charAt(j) + "");
                    }
                }else{
                    int size = ans.size();
                    for (int j = 0; j < size; j++) {
                        String now = ans.remove(0);
                        for (int k = 0; k < cur.length(); k++) {
                            ans.add(now+cur.charAt(k));
                        }
                    }
                }
            }
        }
        return ans;
    }
}
