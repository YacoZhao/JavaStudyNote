package yc.al.zuo.base;

/**
 * 基础班7 —————— 前缀树
 * @author code_yc
 * @version 1.0
 * @date 2020/4/24 19:13
 */
public class Class7_Code1_TrieTree {

    /**
     * 前缀树的节点类
     */
    public static class TrieNode{
        int path;     //用于记录有多少个字符串经过
        int end;      //用于记录以此节点结尾的字符串有多少个
        TrieNode[] next;  // 存放下一个节点的数组，节点的位置下标表示当前路径上存放的元素值

        public TrieNode(){
            path = 0;
            end = 0;
            next = new TrieNode[26];      // 保存26个英文字母
        }
    }

    /**
     * 前缀树的实体类
     */
    public static class Trie{
        private TrieNode root;    //根节点

        // 空参构造器
        public Trie(){
            root = new TrieNode();
        }

        // 插入元素
        public void insert(String str) {
            if(str == null) return;  // 空字符串则直接返回
            char[] chars = str.toCharArray();
            TrieNode node = root;   // 每次插入都是从根节点开始向下插入
            int index = 0;          // index存放当前节点的插入位置
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                // 如果当前节点的插入位置为空，则建一个新的节点
                if(node.next[index] == null){
                    node.next[index] = new TrieNode();
                }
                node = node.next[index];     // 将当前节点跳到指定索引位置
                node.path++;    //每经过一次节点，则将节点的path自增
            }
            node.end++;         //遍历结束之后，用end指针表示添加多少了相同类型的字符串
        }

        // 删除元素
        public void delete(String str) {
            if(str == null) return;
            char[] chars = str.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if(--node.next[index].path == 0){
                    // 如果某一个位置的path只用过一次，那么直接将此索引位置的节点对象直接置为null，那么后面所有的字符都回被删除
                    node.next[index] = null;        //直接将这里置为空，那么后面的元素都不会继续用到，所以从这里直接给截断
                    return;
                }
                node = node.next[index];
            }
            node.end--;
        }

        // 查找待查找元素在前缀树中出现的次数
        public int search(String str) {
            if(str == null) return 0;
            char[] chars = str.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if(node.next[index] == null){
                    return 0; // 查找的字符串在前缀树中不存在
                }
                node = node.next[index];
            }
            return node.end;
        }

        // 查看有多少字符串以此字符串为前缀
        public int prefixNumber(String str) {
            if(str == null) return 0;
            char[] chars = str.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if(node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }
            return node.path;
        }

        public static void main(String[] args) {
            Trie trie = new Trie();
            System.out.println(trie.search("zuo"));
            trie.insert("zuo");
            System.out.println(trie.search("zuo"));
            trie.delete("zuo");
            System.out.println(trie.search("zuo"));
            trie.insert("zuo");
            trie.insert("zuo");
            trie.delete("zuo");
            System.out.println(trie.search("zuo"));
            trie.delete("zuo");
            System.out.println(trie.search("zuo"));
            trie.insert("zuoa");
            trie.insert("zuoac");
            trie.insert("zuoab");
            trie.insert("zuoad");
            trie.delete("zuoa");
            System.out.println(trie.search("zuoa"));
            System.out.println(trie.prefixNumber("zuo"));
        }
    }
}
