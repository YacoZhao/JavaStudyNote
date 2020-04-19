package my_code_sum_up.code_test;

import java.util.*;

public class Record {

    public static HashMap<String, List<Integer>> map = new HashMap<>();
    public static int[] records = new int[200];

    public static void add(String type, int id){
        records[id] = 1;
        if(map.containsKey(type)){
            map.get(type).add(id);
        }else{
            List<Integer> list = new ArrayList<>();
            list.add(id);
            map.put(type,list);
        }
    }

    public static void print(HashMap<String, List<Integer>> map){
        for (String key : map.keySet()) {
            List<Integer> cur = map.get(key);
            System.out.println(key + ":");
            System.out.println(cur.toString());
        }
        System.out.println("当前总记录：" + Arrays.toString(records));
    }

    public static void main(String[] args) {
        add("动态规划",5);add("动态规划",10);add("动态规划",32);add("动态规划",44);add("动态规划",53);
        add("动态规划",62);add("动态规划",63);add("动态规划",64);add("动态规划",70);add("动态规划",72);
        add("动态规划",85);add("动态规划",87);add("动态规划",91);add("动态规划",95);add("动态规划",96);
        add("动态规划",97);add("动态规划",115);add("动态规划",120);add("动态规划",121);add("动态规划",122);
        add("动态规划",123);add("动态规划",131);add("动态规划",132);add("动态规划",139);add("动态规划",152);
        add("动态规划",174);add("动态规划",188);add("动态规划",198);

        add("回溯",17);add("回溯",22);add("回溯",37);add("回溯",39);add("回溯",40);
        add("回溯",46);add("回溯",47);add("回溯",51);add("回溯",52);add("回溯",60);
        add("回溯",77);add("回溯",78);add("回溯",79);add("回溯",89);add("回溯",90);
        add("回溯",93);add("回溯",126);add("回溯",131);add("回溯",140);


        print(map);

    }
}
