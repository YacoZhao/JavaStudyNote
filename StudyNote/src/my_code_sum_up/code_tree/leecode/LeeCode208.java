package my_code_sum_up.code_tree.leecode;


// 实现前缀树
class LeeCode208_Trie {

    class Node {
        int end;     // 只需要记录end即可

        Node[] next;

        public Node() {
            this.end = 0;
            this.next = new Node[26];
        }
    }

    private Node root;

    /**
     * Initialize your data structure here.
     */
    public LeeCode208_Trie() {
        this.root = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null) return;
        char[] s = word.toCharArray();
        Node node = root;
        int index = 0;
        for (int i = 0; i < s.length; i++) {
            index = s[i] - 'a';
            if (node.next[index] == null) {
                node.next[index] = new Node();
            }
            node = node.next[index];
        }
        node.end++;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        if (word == null) return true;
        char[] s = word.toCharArray();
        Node node = root;
        int index = 0;
        for (int i = 0; i < s.length; i++) {
            index = s[i] - 'a';
            if (node.next[index] == null) {
                return false;
            }
            node = node.next[index];
        }
        return node.end > 0;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (prefix == null) return true;
        char[] s = prefix.toCharArray();
        Node node = root;
        int index = 0;
        for (int i = 0; i < s.length; i++) {
            index = s[i] - 'a';
            if (node.next[index] == null) {
                return false;
            }
            node = node.next[index];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */


