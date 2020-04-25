package yc.al.zuo.advanced;

/**
 * 实现前缀树
 * @author code_yc
 * @version 1.0
 * @date 2020/4/21 16:38
 */
public class Class2_Code2_TrieTree {

    // 定义前缀树的节点
    public static class TrieNode{
        int path;   // 有多少字符串从这里经过
        int end;    // 有多少字符串从这里收尾
        TrieNode[] next;  // 存放下一个节点的位置，26位上一个数组，每个数组表示一个字符

        public TrieNode() {
            this.path = 0;
            this.end = 0;
            this.next = new TrieNode[26];
        }
    }

    // 创建前缀树
    public static class TrieTree{
        TrieNode root;   // 定义根节点

        //构造器
        public TrieTree() {
            this.root = new TrieNode();
        }

        // 向树种添加一个字符串str
        public void insert(String str){
            if(str == null) return;
            char[] chars = str.toCharArray();
            TrieNode node = root;
            int index = 0;  // 当前节点插入的位置
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if(node.next[index] == null){
                    node.next[index] = new TrieNode();
                }
                node = node.next[index];
                node.path++;
            }
            node.end++;
        }

        // 从前缀树种删除一个字符串
        public void delete(String str){
            if(search(str)){
                // 如果存在，执行操作
                char[] chars = str.toCharArray();
                TrieNode node = root;
                int index = 0;
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i] - 'a';
                    if(node.next[index].path-- == 1){
                        node.next[index] = null;
                        return;
                    }
                    node = node.next[index];
                }
                node.end--;
            }
        }

        // 从前缀树中搜索指定str字符串是否存在
        public boolean search(String str){
            if(str == null) return false;
            char[] chars = str.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if(node.next[index] == null){
                    return false;
                }
                node = node.next[index];
            }
            return node.end != 0;
        }

        // 有多少字符串以pre作为前缀
        public int prefixNumber(String pre) {
            if(pre == null) return 0;
            char[] chars = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if(node.next[index] == null){
                    return 0;   // 当前前缀不存在
                }
                node = node.next[index];
            }
            return node.path;
        }
    }

    public static void main(String[] args) {
        TrieTree trie = new TrieTree();
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
