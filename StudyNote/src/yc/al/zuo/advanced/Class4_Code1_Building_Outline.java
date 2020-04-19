package yc.al.zuo.advanced;

import java.util.*;

// 4-1 大楼轮廓线问题
public class Class4_Code1_Building_Outline {

    // 创建Node节点
    static class Node {
        boolean isUp;   // true表示上去了， false表示下来了
        int posi;   // 当前节点的位置
        int height;

        public Node(boolean isUp, int posi, int height) {
            this.isUp = isUp;
            this.posi = posi;
            this.height = height;
        }
    }

    // 创建一个比较器
    static class MyComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            if (o1.posi != o2.posi) {
                return o1.posi - o2.posi;
            }
            if (o1.isUp != o2.isUp) {
                return o1.isUp ? -1 : 1;  // 使得上去的在后面，下来的在前面
            }
            return 0;
        }
    }

    // 主函数————给定每栋楼的轮廓线，输出整体的轮廓线
    public static List<List<Integer>> buildingOutline(int[][] matrix) {
        // 将指定的matrix数组改造位Node数组
        Node[] nodes = new Node[matrix.length * 2];
        for (int i = 0; i < matrix.length; i++) {
            nodes[i * 2] = new Node(true, matrix[i][0], matrix[i][2]);
            nodes[i * 2 + 1] = new Node(false, matrix[i][1], matrix[i][2]);
        }
        Arrays.sort(nodes, new MyComparator());

        // 然后使用两个TreeMap，找出每个位置的最大高度
        TreeMap<Integer,Integer> htMap = new TreeMap<>();   // 存储每种高度存在的次数
        TreeMap<Integer,Integer> pmMap = new TreeMap<>();   // 存储每个位置当前的最大高度
        for (int i = 0; i < nodes.length; i++) {
            int curH = nodes[i].height;
            if(nodes[i].isUp){
                if(!htMap.containsKey(curH)){
                    htMap.put(curH,1);
                }else{
                    htMap.put(curH,htMap.get(curH) + 1);
                }
            }else{
                if(htMap.containsKey(curH)){
                    if(htMap.get(curH) == 1){
                        htMap.remove(curH);
                    }else{
                        htMap.put(curH,htMap.get(curH) - 1);
                    }
                }
            }
            // 更新好了htMap之后就是更新pmMap,当前位置的最大高度
            if(htMap.isEmpty()){
                pmMap.put(nodes[i].posi,0);  // 当前位置的最大高度为0
            }else{
                pmMap.put(nodes[i].posi,htMap.lastKey());   // 返回当前htMap中最高的键
            }
        }
        // 循环结束后，根据pmMap来重新描绘轮廓线
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int height = 0;
        for (Integer posi : pmMap.keySet()) {
            int curMaxHeight = pmMap.get(posi);
            if(height != curMaxHeight){
                if(height != 0){
                    List<Integer>  newRecord = new ArrayList<>();
                    newRecord.add(start);
                    newRecord.add(posi);
                    newRecord.add(height);
                    res.add(newRecord);
                }
                start = posi;
                height = curMaxHeight;
            }
        }
        return res;
    }
}
