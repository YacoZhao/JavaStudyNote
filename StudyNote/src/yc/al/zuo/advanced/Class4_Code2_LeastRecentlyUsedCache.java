package yc.al.zuo.advanced;

import java.util.HashMap;

// 4-2 LRU缓存结构
public class Class4_Code2_LeastRecentlyUsedCache {

    // 首先创建节点类，使其可以形成双向链表
    public static class Node<V>{
        V value;
        Node<V> last;
        Node<V> next;

        public Node(V value) {
            this.value = value;
        }
    }

    // 创建双向链表
    public static class DoubleLinkedList<V>{
        Node<V> head;
        Node<V> tail;

        public DoubleLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void addNode(Node<V> node){
        // 向链表中添加节点
            if(this.head == null){
                this.head = node;
                this.tail = node;
            }else{
                this.tail.next = node;
                node.last = this.tail;
                this.tail = node;
            }
        }

        // 将链表中的节点移动道链表最后
        public void moveNodeToTail(Node<V> node){
            if(this.tail == node){
                return;
            }
            if(this.head == node){
                this.head = this.head.next;
                this.head.last = null;
            }else{
                node.last.next = node.next;
                node.next.last = node.last;
            }
            node.next = null;
            node.last = this.tail;
            this.tail.next = node;
            this.tail = node;
        }

        // 将链表中的头节点删除
        public Node<V> removeHead(){
            if(this.head == null){
                return null;
            }
            Node<V> res = this.head;
            if(this.head == this.tail){
                this.head = null;
                this.tail = null;
            }else{
                this.head = res.next;
                res.next = null;
                this.head.last = null;
            }
            return res;
        }
    }

    // 创建LRU缓存结构
    public static class LRUCache<K,V>{
        HashMap<K,Node<V>> keyNodeMap;
        HashMap<Node<V>, K> nodeKeyMap;
        DoubleLinkedList<V> nodeList;
        int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            keyNodeMap = new HashMap<>();
            nodeKeyMap = new HashMap<>();
            nodeList = new DoubleLinkedList<>();
        }

        // 从LRU缓存结构中获取元素
        public V get(K key){
            V res = null;
            if(keyNodeMap.containsKey(key)){
                Node<V> node = keyNodeMap.get(key);
                res = node.value;
                this.nodeList.moveNodeToTail(node);
            }
            return res;
        }

        // 向缓存结构中添加元素
        public void set(K key, V value) {
            if(this.keyNodeMap.containsKey(key)){
                Node<V> node = keyNodeMap.get(key);
                node.value = value;
                this.nodeList.moveNodeToTail(node);
            }else{
                Node<V> node = new Node<V>(value);
                this.nodeList.addNode(node);
                if(this.keyNodeMap.size() == this.capacity){
                    Node<V> removeNode = this.nodeList.removeHead();
                    K k = this.nodeKeyMap.get(removeNode);
                    this.keyNodeMap.remove(k);
                    this.nodeKeyMap.remove(removeNode);
                }
                this.keyNodeMap.put(key,node);
                this.nodeKeyMap.put(node,key);
            }
        }
    }
}
