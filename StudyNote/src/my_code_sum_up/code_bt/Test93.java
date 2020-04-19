package my_code_sum_up.code_bt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// 93. 复制IP地址
// 方法————回溯/DFS
public class Test93 {

    //----------------------DFS------------------------------------
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        if(s.length() <= 12 && s.length() >= 4){
            dfs(ans,s,0,0,new StringBuilder());
        }
        return ans;
    }

    /**
     *
     * @param ans    返回结果集
     * @param s      待搜索字符串
     * @param index  当前搜索的位置
     * @param count  当前已近到了第几层
     * @param path   搜索记录
     */
    private void dfs(List<String> ans, String s, int index, int count, StringBuilder path) {
        // 如果剩余的字符串不够拼出有效ip地址，则直接返回
        if(s.length() - index > 3 * (4 - count) || s.length() < 4 - count){
            return;
        }
        // 如果当前index已经到了字符串结尾
        if(index == s.length()){
            // 如果count已经匹配了4层，则加入结果集,否则直接返回
            if(count == 4){
                ans.add(path.toString().substring(0,path.length() - 1));
            }
            return;
        }
        // 如果index超限，或者count提前达到了4层，那么直接返回
        if(index > s.length() || count == 4) {
            return;
        }
        // 保存当前的字符串path，分别进行加1.加2，加3位的操作
        StringBuilder before = new StringBuilder(path);
        //加1位操作
        path.append(s.charAt(index)).append('.');
        dfs(ans,s,index+1,count+1,path);
        // 如果当前位置以0开头，直接返回（2位和3位的时候，头不可以位0）
        if(s.charAt(index) == '0'){
            return;
        }
        //加2位操作
        if(index+1 < s.length()){
            path = new StringBuilder(before);    //回溯到原来的字符串
            path.append(s.substring(index, index + 2)).append('.');
            dfs(ans,s,index+2,count+1,path);
        }
        //加3位操作
        if(index + 2 < s.length()){
            // 加3位的时候，必须考虑当前添加字符是否越界
            path = new StringBuilder(before);
            int temp = Integer.parseInt(s.substring(index,index+3));
            if(temp >= 0 && temp <= 255){
                path.append(s.substring(index, index + 3)).append('.');
                dfs(ans,s,index+3,count+1,path);
            }
        }
    }
}
