package yc.al.zuo.advanced;

import netscape.security.UserTarget;

import java.util.List;
import java.util.Stack;

/**
 * 二叉树的多种遍历方法简单实现
 * @author code_yc
 * @version 1.0
 * @date 2020/4/20 7:54
 */
public class Class3_Code1_MorrisTraversal {

    // 首先定义二叉树
    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    // TODO 二叉树Morris遍历轨迹
    public static void morrisProcess(TreeNode head){
        if(head == null) return;
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while(cur1 != null){
            cur2 = cur1.left;
            if(cur2 != null){
                while(cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if(cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }
                if(cur2.right == cur1){
                    cur2.right = null;
                }
            }
            cur1 = cur1.right;
        }
    }

    // TODO Morris实现二叉树的前序遍历
    public static void morrisPre(TreeNode head){
        System.out.println("----Morris前序遍历----");
        if(head == null) return;
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while(cur1 != null){
            cur2 = cur1.left;
            if(cur2 != null){
                while(cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if(cur2.right == null){
                    cur2.right = cur1;
                    System.out.printf(cur1.value + " - ");
                    cur1 = cur1.left;
                    continue;
                }
                if(cur2.right == cur1){
                    cur2.right = null;
                }
            }else{
                System.out.printf(cur1.value + " - ");
            }
            cur1 = cur1.right;
        }
        System.out.println();
    }

    // TODO Morris实现二叉树的中序遍历
    public static void morrisIn(TreeNode head){
        System.out.println("----Morris中序遍历----");
        if(head == null) return;
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while(cur1 != null){
            cur2 = cur1.left;
            if(cur2 != null){
                while(cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if(cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }
                if(cur2.right == cur1){
                    cur2.right = null;
                }
            }
            System.out.printf(cur1.value + " - ");
            cur1 = cur1.right;
        }
        System.out.println();
    }

    // TODO Morris实现二叉树的后序遍历
    public static void morrisPos(TreeNode head){
        System.out.println("----Morris后序遍历----");
        if(head == null) return;
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while(cur1 != null){
            cur2 = cur1.left;
            if(cur2 != null){
                while(cur2.right != null && cur2.right != cur1){
                    cur2 = cur2.right;
                }
                if(cur2.right == null){
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                }
                if(cur2.right == cur1){
                    cur2.right = null;
                    // 在每次cur1节点右移之后，反装他左子节点所在的链表，反转打印
                    printEdge(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        printEdge(head);
        System.out.println();
    }

    // TODO 逆序打印节点所在的右子链路
    public static void printEdge(TreeNode node){
        if(node == null) return;
        if(node.right == null){
            System.out.printf(node.value + " - ");
            return;
        }
        // 首先将节点所在的链路反转
        TreeNode tail = reverse(node);
        TreeNode cur = tail;
        while(cur != null){
            System.out.printf(cur.value + " - ");
            cur = cur.right;
        }
        // 再价格链路反转回去
        reverse(tail);
    }

    // TODO 反转链表
    public static TreeNode reverse(TreeNode node){
        TreeNode pre = null;
        TreeNode next = null;
        while(node != null){
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    //----------------------------------测试集一：使用递归实现二叉树的各种遍历 -------------------------------------------
    // 前序遍历
    public static void recursivePre(TreeNode head){
        if(head != null){
            System.out.printf(head.value + " - ");
            recursivePre(head.left);
            recursivePre(head.right);
        }
    }

    // 中序遍历
    public static void recursiveIn(TreeNode head){
        if(head != null){
            recursiveIn(head.left);
            System.out.printf(head.value + " - ");
            recursiveIn(head.right);
        }
    }

    // 后序遍历
    public static void recursivePos(TreeNode head){
        if(head != null){
            recursivePos(head.left);
            recursivePos(head.right);
            System.out.printf(head.value +  " - ");
        }
    }

    //----------------------------------测试集二：使用循环低迭代实现二叉树的各种遍历 -------------------------------------------
    // 前序遍历
    public static void iteratePre(TreeNode head){
        System.out.println(" -----迭代前序遍历------ ");
        if(head != null){
            // 创建辅助栈，存储中间节点
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(head);
            while(!stack.isEmpty()){
                head = stack.pop();
                System.out.printf(head.value + " - ");
                if(head.right != null){
                    stack.push(head.right);
                }
                if(head.left != null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    // 中序遍历
    public static void iterateIn(TreeNode head){
        System.out.println("----迭代中序遍历----");
        if(head != null){
            Stack<TreeNode> stack = new Stack<>();
            while(head != null || !stack.isEmpty()){
                if(head != null){
                    stack.push(head);
                    head = head.left;
                }else{
                    head = stack.pop();
                    System.out.printf(head.value + " - ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    // 后序遍历
    public static void iteratePos(TreeNode head){
        System.out.println("----迭代后序遍历----");
        if(head != null){
            Stack<Integer> data = new Stack<>();
            Stack<TreeNode> stack = new Stack<>();
            stack.push(head);
            while(!stack.isEmpty()){
                head = stack.pop();
                data.push(head.value);
                if(head.left != null){
                    stack.push(head.left);
                }
                if(head.right != null){
                    stack.push(head.right);
                }
            }
            while(!data.isEmpty()){
                System.out.printf(data.pop() + " - ");
            }
        }
        System.out.println();
    }

    //----------------------------------测试集三 : 使用主函数测试方法的正确性 -------------------------------------------
    // for test -- print tree
    public static void printTree(TreeNode head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(4);
        head.left = new TreeNode(2);
        head.right = new TreeNode(6);
        head.left.left = new TreeNode(1);
        head.left.right = new TreeNode(3);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(7);
        printTree(head);

        // 测试方法
        System.out.println("测试Morris遍历");
        morrisPre(head);
        morrisIn(head);
        morrisPos(head);

        System.out.println();
        System.out.println("----------------------------");

        // 测试递归方法
        System.out.println("测试递归遍历");
        System.out.println("---递归前序遍历---");
        recursivePre(head);
        System.out.println();
        System.out.println("---递归中序遍历---");
        recursiveIn(head);
        System.out.println();
        System.out.println("---递归后序遍历---");
        recursivePos(head);
        System.out.println();

        System.out.println();
        System.out.println("-----------------------------");

        // 测试循环的遍历方法
        System.out.println("测试迭代遍历");
        iteratePre(head);
        iterateIn(head);
        iteratePos(head);
    }
}
