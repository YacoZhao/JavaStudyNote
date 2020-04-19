package yc.first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//排序数组分组
public class Test49 {
    /**
     * 方法一： 先将字符串排序，用排序后的字符串作为键，遍历添加
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // 首先进行边界条件判断
        if(strs.length == 0){
            return new ArrayList<>();
        }
        HashMap<String,ArrayList<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = String.valueOf(chars);
            if(!map.containsKey(s)){
                map.put(s,new ArrayList<>());
            }
            map.get(s).add(str);
        }
        return new ArrayList<>(map.values());
    }
}
