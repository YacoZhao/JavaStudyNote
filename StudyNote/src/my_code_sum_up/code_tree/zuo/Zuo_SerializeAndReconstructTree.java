package my_code_sum_up.code_tree.zuo;

import my_code_sum_up.code_tree.util.PrintTree;
import my_code_sum_up.code_tree.util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 左神基础班————二叉树的序列化，可以非常直观的区别两个二叉树
 */
public class Zuo_SerializeAndReconstructTree {

    // 先序序列化二叉树
    public static String serialByPre(TreeNode head) {
        if (head == null) return "#_";
        String res = "";
        res += head.val + "_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    // 先序反序列化二叉树
    public static TreeNode reconByPreString(String preStr) {
        String[] strings = preStr.split("_");
        Queue<String> queue = new LinkedList<String>(Arrays.asList(strings));
        return reconPreOrder(queue);
    }

    private static TreeNode reconPreOrder(Queue<String> queue) {
        String str = queue.poll();
        if (str.equals("#")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(str));
        node.left = reconPreOrder(queue);
        node.right = reconPreOrder(queue);
        return node;
    }

    // 按层序列化二叉树
    public static String serialByLevel(TreeNode head) {
        if (head == null) return "#_";
        String res = "";
        res += head.val + "_";
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                queue.add(head.left);
                res += head.left.val + "_";
            } else {
                res += "#_";
            }
            if (head.right != null) {
                queue.add(head.right);
                res += head.right.val + "_";
            } else {
                res += "#_";
            }
        }
        return res;
    }

    // 按层反序列化二叉树
    public static TreeNode reconByLevelString(String levelStr) {
        String[] strings = levelStr.split("_");
        int index = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode head = generateNode(strings[index++]);
        if (head != null) {
            queue.add(head);
        }
        TreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(strings[index++]);
            node.right = generateNode(strings[index++]);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }

    private static TreeNode generateNode(String string) {
        return string.equals("#") ? null : new TreeNode(Integer.parseInt(string));
    }

    public static void main(String[] args) {
        TreeNode head = null;
        PrintTree.printTree(head);

        String pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        PrintTree.printTree(head);

        String level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        PrintTree.printTree(head);

        System.out.println("====================================");

        head = new TreeNode(1);
        PrintTree.printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        PrintTree.printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        PrintTree.printTree(head);

        System.out.println("====================================");

        head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.right.right = new TreeNode(5);
        PrintTree.printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        PrintTree.printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        PrintTree.printTree(head);

        System.out.println("====================================");

        head = new TreeNode(100);
        head.left = new TreeNode(21);
        head.left.left = new TreeNode(37);
        head.right = new TreeNode(-42);
        head.right.left = new TreeNode(0);
        head.right.right = new TreeNode(666);
        PrintTree.printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        PrintTree.printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        PrintTree.printTree(head);

        System.out.println("====================================");

    }
}

