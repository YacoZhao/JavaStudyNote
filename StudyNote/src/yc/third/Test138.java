package yc.third;

import java.util.HashMap;

public class Test138 {
    //创建全局变量
    HashMap<Node,Node> map = new HashMap<>();

    /**
     * 递归的思想
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        //1. 边界条件的判断
        if(head == null){
            return null;
        }
        //如果已经存在了此节点，就不进行重复创建节点
        if(map.containsKey(head)){
            return map.get(head);
        }
        //没有创建节点，则再这里创建一个节点
        Node node = new Node(head.val,null,null);
        //向hashmap中存储，表示当前已经创建过该节点
        map.put(head,node);
        //给node节点添加属性
        node.next = copyRandomList(node.next);
        node.random = copyRandomList(node.random);
        //返回结果
        return node;
    }
}
