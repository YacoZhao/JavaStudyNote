package yc.no6;

import java.util.HashMap;

/**
 * 设计LFU缓存结构
 *
 */
public class Test460 {

    // 首先定义节点类型
    class Node{
        int key;
        int value;
        int times;
        Node up;
        Node down;

        public Node(int key, int value, int times){
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }

    //定义NodeList类，LFU包含一个由NodeList构成的双端链表
    class NodeList{
        Node head;
        Node tail;
        NodeList last;
        NodeList next;

        public NodeList(Node node){
            this.head = node;
            this.tail = node;
        }

        // 将一个节点node插入此nodeList的头步
        public void addNodeFromHead(Node newNode){
            newNode.down = this.head;
            this.head.up = newNode;
            this.head = newNode;
        }

        // 判断当前的nodeList是否为null
        public boolean isEmpty(){
            return this.head == null;
        }

        // 从当前的nodeList中删除一个node
        public void deleteNode(Node node){
            if(this.head == this.tail){
                this.head = null;
                this.tail = null;
            }else{
                if(this.head == node){
                    this.head = node.down;
                    this.head.up = null;
                }else if(this.tail == node){
                    this.tail = node.up;
                    this.tail.down = null;
                }else{
                    node.up.down = node.down;
                    node.down.up = node.up;
                }
            }
            node.up = null;
            node.down = null;
        }
    }

    class LFUCache {
        // 定义成员变量
        int capacity;
        int size;
        HashMap<Integer,Node> records;
        HashMap<Node,NodeList> heads;
        NodeList headList;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.records = new HashMap<Integer,Node>();
            this.heads = new HashMap<Node,NodeList>();
            this.headList = null;
        }

        public int get(int key) {
            int res  = -1;
            if(records.containsKey(key)){
                Node node = records.get(key);
                res = node.value;
                node.times++;
                NodeList curNodeList = heads.get(node);
                move(node,curNodeList);
            }
            return res;
        }

        public void put(int key, int value) {
            if(this.capacity == 0) {
                return;
            }
            // 如果当前的key指向的节点存在
            if(records.containsKey(key)){
                Node node = records.get(key);
                node.value = value;
                node.times++;
                NodeList curNodeList = heads.get(node);  // 获取当前的node存在的NodeList
                move(node,curNodeList);    // 将node移动到新的NodeList中，并调正curNodeList
            }
            // 如果当前的key指向的节点不存在
            else{
                //r如果容量超限
                if(this.size == this.capacity){
                    //删除headList的tail
                    Node tail = this.headList.tail;
                    this.headList.deleteNode(tail);
                    modifyHeadList(this.headList); // 更新当前的headList，如果为null调整
                    records.remove(tail.key);
                    heads.remove(tail);
                    this.size--;
                }
                // 创建新的Node进行插入
                Node node = new Node(key,value,1);
                if(this.headList == null){
                    this.headList = new NodeList(node);
                }else{
                    if(this.headList.head.times == node.times){
                        this.headList.addNodeFromHead(node);
                    }else{
                        NodeList newList = new NodeList(node);
                        newList.next = this.headList;
                        this.headList.last = newList;
                        this.headList = newList;
                    }
                }
                records.put(key,node);
                heads.put(node,this.headList);
                this.size++;
            }
        }

        private void move(Node node, NodeList oldNodeList){
            // 首先从原来的NodeList中删除node节点
            oldNodeList.deleteNode(node);
            NodeList preList = modifyHeadList(oldNodeList) ? oldNodeList.last : oldNodeList;  // 调整oldNodeList
            NodeList nextList = oldNodeList.next;
            if(nextList == null){
                // 新建一个Nodelist,把node扔进去
                NodeList newList = new NodeList(node);
                if(preList != null){
                    preList.next = newList;
                }
                newList.last = preList;
                if(this.headList == null){
                    this.headList = newList;
                }
                heads.put(node,newList);
            }else{
                if(nextList.head.times == node.times){
                    // 将node放到头步
                    nextList.addNodeFromHead(node);
                    heads.put(node,nextList);
                }else{
                    NodeList newList =  new NodeList(node);
                    if(preList != null){
                        preList.next = newList;
                    }
                    newList.last = preList;
                    newList.next = nextList;
                    nextList.last = newList;
                    if(this.headList == nextList){
                        this.headList = newList;
                    }
                    heads.put(node,newList);
                }
            }
        }

        private boolean modifyHeadList(NodeList nodeList){
            // 如果当前的nodeList为null，则要进行删除
            if(nodeList.isEmpty()){
                if(this.headList == nodeList){
                    this.headList = nodeList.next;
                    if(this.headList != null){
                        this.headList.last = null;
                    }
                }else{
                    nodeList.last.next = nodeList.next;
                    if(nodeList.next != null){
                        nodeList.next.last = nodeList.last;
                    }
                }
                return true;
            }
            //否则返回false
            return false;
        }
    }



/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
