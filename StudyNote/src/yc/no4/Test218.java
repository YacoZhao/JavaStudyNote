package yc.no4;

import java.util.*;

//218. 天际线问题
// 参考左神算法进阶班
public class Test218 {

    class Node{
        int s;
        int h;
        boolean isUp;

        public Node(int s, int h, boolean isUp){
            this.s = s;
            this.h = h;
            this.isUp = isUp;
        }
    }

    // 创建一个比较器
    class NodeCompare implements Comparator<Node>{
        public int compare(Node o1, Node o2){
            if(o1.s != o2.s){
                return o1.s - o2.s;
            }
            if(o1.isUp != o2.isUp){
                return o1.isUp ? -1 : 1;
            }
            return 0;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        // 将指定数组改造为Node信息
        Node[] nodes = new Node[buildings.length * 2];
        for(int i = 0; i < buildings.length; i++){
            nodes[2 * i] = new Node(buildings[i][0], buildings[i][2],true);
            nodes[2 * i + 1] = new Node(buildings[i][1], buildings[i][2],false);
        }
        Arrays.sort(nodes, new NodeCompare());
        // 维护两个TreeMap
        TreeMap<Integer,Integer> htMap = new TreeMap<Integer,Integer>();
        TreeMap<Integer,Integer> pmMap = new TreeMap<Integer,Integer>();
        for(int i = 0; i < nodes.length; i++){
            if(nodes[i].isUp){
                if(!htMap.containsKey(nodes[i].h)){
                    htMap.put(nodes[i].h, 1);
                }else{
                    htMap.put(nodes[i].h, htMap.get(nodes[i].h) + 1);
                }

            }else{
                if(htMap.get(nodes[i].h) == 1){
                    htMap.remove(nodes[i].h);
                }else{
                    htMap.put(nodes[i].h, htMap.get(nodes[i].h) - 1);
                }
            }
            // 更新pmMap
            if(htMap.isEmpty()){
                pmMap.put(nodes[i].s,0);
            }else{
                pmMap.put(nodes[i].s,htMap.lastKey());
            }
        }
        // 创建结果集，添加结果
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int height = 0;
        for(Integer position : pmMap.keySet()){
            int curMaxHeight = pmMap.get(position);
            if(height != curMaxHeight){
                start = position;
                height = curMaxHeight;
                List<Integer> curRes = new ArrayList<>();
                curRes.add(start);
                curRes.add(height);
                res.add(curRes);
            }
        }
        return res;
    }
}
