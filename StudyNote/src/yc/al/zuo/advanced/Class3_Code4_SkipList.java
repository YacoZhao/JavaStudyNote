package yc.al.zuo.advanced;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 跳表
 * @author code_yc
 * @version 1.0
 * @date 2020/4/21 15:23
 */
public class Class3_Code4_SkipList {

    // 定义跳表的节点类
    public static class SkipListNode {
        public Integer value;                            // 属性一：该节点代表的值
        public ArrayList<SkipListNode> nextNodes;        // 属性二：该节点所的node列表

        public SkipListNode(Integer value) {             // 构造器
            this.value = value;
            nextNodes = new ArrayList<SkipListNode>();
        }
    }

    // 跳表的迭代器，进行跳表的遍历
    public static class SkipListIterator implements Iterator<Integer> {
        SkipList list;
        SkipListNode current;

        public SkipListIterator(SkipList list) {
            this.list = list;
            this.current = list.getHead();
        }

        public boolean hasNext() {
            return current.nextNodes.get(0) != null;
        }

        public Integer next() {
            current = current.nextNodes.get(0);
            return current.value;
        }
    }

    // 定义跳表类
    public static class SkipList{
        private SkipListNode head;         // 跳表的头节点
        private int maxLevel;              // 跳表的最大高度
        private int size;                  // 当前容量
        private static final double PROBABILITY = 0.5;   // 掷色子的概率

        // 构造器
        public SkipList() {
            size = 0;
            maxLevel = 0;
            head = new SkipListNode(null);
            head.nextNodes.add(null);
        }

        // 获取头节点
        public SkipListNode getHead() {
            return head;
        }

        // 向跳表中添加元素
        public void add(Integer newValue) {
            // 如果当前跳表里面不包含这个newValue
            if (!contains(newValue)) {
                size++;   //向跳表里面添加节点，size增长
                int level = 0;   // 随机出层数
                while (Math.random() < PROBABILITY) {
                    level++;
                }
                // 如果随机出来的level层数大于最大层数maxLevel,则maxLevel增加
                while (level > maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                // 创建该newValue对应的跳表
                SkipListNode newNode = new SkipListNode(newValue);
                SkipListNode current = head;
                do {
                    current = findNext(newValue, current, level); // 针对每一层，找到第一个比newValue小的地方
                    newNode.nextNodes.add(0, current.nextNodes.get(level));  // 将当前跳表接单的第一个加上current的下一个节点，以这种方式将跳表全部连接起来
                    current.nextNodes.set(level, newNode);   // 将current的当前层节点放上newValue的节点
                } while (level-- > 0);
            }
            // 如果跳表中存在此value，不进行任何操作
        }

        // 从跳表中删除一个节点
        public void delete(Integer deleteValue) {
            if (contains(deleteValue)) {
                SkipListNode deleteNode = find(deleteValue);
                size--;
                int level = maxLevel;
                SkipListNode current = head;
                do {
                    current = findNext(deleteNode.value, current, level);
                    if (deleteNode.nextNodes.size() > level) {  // 等到level高度降下来之后再做删除操作
                        // 删除操作直接取消连接就可以了
                        current.nextNodes.set(level, deleteNode.nextNodes.get(level));
                    }
                } while (level-- > 0);
            }
        }

        // 检查跳表中是否存在此value
        public boolean contains(Integer value) {
            // 首先根据指定的值查询value在跳表种存在的节点
            SkipListNode node = find(value);
            // 查到的节点值和当前的值一样，则表示已经查到了
            return node != null && node.value != null && equalTo(node.value, value);
        }

        // 从跳表中查询e，返回对应的跳表节点
        private SkipListNode find(Integer e) {
            // 给定元素e，查询其所在的跳表节点
            return find(e, head, maxLevel);
        }

        // 封装起来的查找方法
        private SkipListNode find(Integer e, SkipListNode current, int level) {
            do {
                current = findNext(e, current, level);
            } while (level-- > 0);
            return current;  // 一直查到最下面一层
        }

        // 查找当前层的
        private SkipListNode findNext(Integer e, SkipListNode current, int level) {
            // 首先获取当前层的下一个节点
            SkipListNode next = current.nextNodes.get(level);
            while (next != null) {
                Integer value = next.value;
                if (lessThan(e, value)) { // e < value  如果当前e小于下一层的节点值，则直接break，进入下一层
                    break;
                }
                current = next;
                next = current.nextNodes.get(level);
            }
            // 如果下一层为null,直接返回给父函数find(),进入下一层
            return current;
        }

        public int size() {
            return size;
        }

        // 实现跳表的遍历器
        public Iterator<Integer> iterator() {
            return new SkipListIterator(this);
        }

        /******************************************************************************
         * Utility Functions *
         ******************************************************************************/

        private boolean lessThan(Integer a, Integer b) {
            return a.compareTo(b) < 0;
        }

        private boolean equalTo(Integer a, Integer b) {
            return a.compareTo(b) == 0;
        }
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.add(5);
        skipList.add(4);
        skipList.add(8);
        skipList.add(1);
        skipList.add(3);
        skipList.add(2);
        skipList.add(9);

        // 遍历跳表（有序输出）
        Iterator<Integer> iterator = skipList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
