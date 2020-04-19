package yc.al.zuo.advanced;

import java.util.HashMap;

// 4-3  LFU缓存结构
public class Class4_Code3_LFU {

    // 节点类
    public static class Node{
        Integer key;
        Integer value;
        Integer times;
        Node up;
        Node down;

        public Node(int key, int value, int times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }

    // 双端链表类
    public static class NodeList{
        public Node head;         // 头节点
        public Node tail;         // 尾节点
        public NodeList last;     // 上一个NodeList
        public NodeList next;     // 下一个NodeList

        // 构造器
        public NodeList(Node node) {
            head = node;
            tail = node;
        }

        // 将节点加入当前的NodeList的头部
        public void addNodeFromHead(Node newHead) {
            newHead.down = head;
            head.up = newHead;
            head = newHead;
        }

        // 判断当前的NodeList是否为null
        public boolean isEmpty() {
            return head == null;
        }

        // 从当前的NodeList中删除一个node
        public void deleteNode(Node node) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                if (node == head) {
                    head = node.down;
                    head.up = null;
                } else if (node == tail) {
                    tail = node.up;
                    tail.down = null;
                } else {
                    node.up.down = node.down;
                    node.down.up = node.up;
                }
            }
            node.up = null;
            node.down = null;
        }

    }

    // LFU缓存结构
    public static class LFUCache{
        private int capacity;     // 定义LFU缓存结构的最大容量
        private int size;         // 用于表示当前的数据总量
        private HashMap<Integer, Node> records;  // records用于记录key值和其对应的Node节点
        private HashMap<Node, NodeList> heads;   // heads用于记录Node存在与哪一个NodeList中
        private NodeList headList;               // 当前的头步NodeList

        // 构造器，给定最大容量
        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.records = new HashMap<>();
            this.heads = new HashMap<>();
            headList = null;
        }

        // 向LFU缓存结构中添加一个数据
        public void set(int key, int value) {
            if (records.containsKey(key)) {
                // 如果当前key指向的节点已经存在，那么直接修改节点的value
                Node node = records.get(key);
                node.value = value;
                node.times++;
                NodeList curNodeList = heads.get(node);   // 找到当前node存在的NodeLIst
                move(node, curNodeList);   // 将node节点移动到新的NodeList中
            } else {
                // 如过当前的节点不存在，那么要创建新的节点
                if (size == capacity) {
                    // 如果超限了，那么删除headList中的tail节点
                    Node node = headList.tail;
                    headList.deleteNode(node);
                    modifyHeadList(headList);  // 更新当前的headList
                    records.remove(node.key);  // 节点记录删除
                    heads.remove(node);        // 节点记录删除
                    size--;
                }
                // 创建新的Node进行插入
                Node node = new Node(key, value, 1);
                if (headList == null) {
                    headList = new NodeList(node);
                } else {
                    if (headList.head.times.equals(node.times)) {
                        headList.addNodeFromHead(node);
                    } else {
                        NodeList newList = new NodeList(node);
                        newList.next = headList;
                        headList.last = newList;
                        headList = newList;
                    }
                }
                records.put(key, node);
                heads.put(node, headList);
                size++;
            }
        }

        // 将node移动到新的NodeList中
        private void move(Node node, NodeList oldNodeList) {
            oldNodeList.deleteNode(node);   // 首先从原来的NOdeList中将此node删除
            NodeList preList = modifyHeadList(oldNodeList) ? oldNodeList.last
                    : oldNodeList;  // 如果删除node之后的oldNodeList为null就会被删除，那么返回oldNodeList的上一个NodeList,否则返回他自己，因为没有被删除
            NodeList nextList = oldNodeList.next; // 不管有没有被删除，都返回oldNodeList的下一个
            if (nextList == null) {
                // 如果下一个为null，则新建一个NOdeList，把node扔进去
                NodeList newList = new NodeList(node);
                if (preList != null) {
                    preList.next = newList;   // 让preList指向这个新创建的nodeList
                }
                newList.last = preList;
                if (headList == null) {  // 如果headList为null，则headList指向的就是newList
                    headList = newList;
                }
                heads.put(node, newList);  // 存入node的新位置
            } else {
                if (nextList.head.times.equals(node.times)) {   // 如果nextList的次数和当前node的修改次数相等
                    nextList.addNodeFromHead(node);              // 则直接加到nextList的头部
                    heads.put(node, nextList);
                } else {
                    NodeList newList = new NodeList(node);
                    if (preList != null) {
                        preList.next = newList;
                    }
                    newList.last = preList;
                    newList.next = nextList;
                    nextList.last = newList;
                    if (headList == nextList) {
                        headList = newList;
                    }
                    heads.put(node, newList);
                }
            }
        }

        // return whether delete this head（是否需要删除head）
        private boolean modifyHeadList(NodeList nodeList) {
            // 如果给定的nodeList为null（那么就要从LFU结构上删除此位置的nodeList）
            if (nodeList.isEmpty()) {
                // 首先如果此位置是头部链表
                if (headList == nodeList) {
                    headList = nodeList.next;
                    if (headList != null) {
                        headList.last = null;
                    }
                }
                // 如果不是头部的情况
                else {
                    nodeList.last.next = nodeList.next;
                    if (nodeList.next != null) {
                        nodeList.next.last = nodeList.last;
                    }
                }
                // 修改了就返回true
                return true;
            }
            // 如果没有修改则返回false
            return false;
        }

        public int get(int key) {
            if (!records.containsKey(key)) {
                return -1;
            }
            Node node = records.get(key);
            node.times++;
            NodeList curNodeList = heads.get(node);
            move(node, curNodeList);
            return node.value;
        }
    }
}
