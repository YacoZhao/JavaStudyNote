package yc.al.zuo.advanced;

/**
 * 绝对平衡的AVL树实现——————继承带旋转的二叉搜索树
 * @author code_yc
 * @version 1.0
 * @date 2020/4/21 13:16
 */
public class Class3_Code3_AVLTree extends Class3_Code3_AbstractSelfBalancingBinarySearchTree{

    // 创建AVL树的节点类，继承Node，添加了新属性height，可以记录当前节点为根节点的树的高度
    protected static class AVLNode extends Node {
        public int height;

        public AVLNode(int value, Node parent, Node left, Node right) {
            super(value, parent, left, right);
        }
    }

    //++++++++++++++++++++++++++++++++++++方法区+++++++++++++++++++++++++++++++++++++++++++++

    //++++++++++++++++++++++++++++++++++++Public方法区： 供外界访问+++++++++++++++++++++++++++++++++++++++++++++
    // AVL树插入节点功能，相对二叉搜索树，增加了平衡操作
    @Override
    public Node insert(int element) {
        Node newNode = super.insert(element);
        rebalance((AVLNode)newNode);
        return newNode;
    }

    // 从AVL树种删除节点的擦偶走
    @Override
    public Node delete(int element) {
        Node deleteNode = super.search(element);
        if (deleteNode != null) {
            Node successorNode = super.delete(deleteNode);
            if (successorNode != null) {
                // if replaced from getMinimum(deleteNode.right) then come back there and update heights
                AVLNode minimum = successorNode.right != null ? (AVLNode)getMinimum(successorNode.right) : (AVLNode)successorNode;
                recomputeHeight(minimum);
                rebalance((AVLNode)minimum);
            } else {
                recomputeHeight((AVLNode)deleteNode.parent);
                rebalance((AVLNode)deleteNode.parent);
            }
            return successorNode;
        }
        return null;
    }

    //++++++++++++++++++++++++++++++++++++protect方法区： 子类和内部调用+++++++++++++++++++++++++++++++++++++++++++++
    // RL的两次旋转操作
    protected Node doubleRotateRightLeft(Node node) {
        node.right = avlRotateRight(node.right);
        return avlRotateLeft(node);
    }

    // LR的两次旋转操作
    protected Node doubleRotateLeftRight(Node node) {
        node.left = avlRotateLeft(node.left);
        return avlRotateRight(node);
    }

    // 创建新的节点
    @Override
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new AVLNode(value, parent, left, right);
    }


    //++++++++++++++++++++++++++++++++++++private方法区： 内部调用+++++++++++++++++++++++++++++++++++++++++++++
    // 重新平衡当前的AVL
    private void rebalance(AVLNode node) {
        while (node != null) {

            Node parent = node.parent;

            int leftHeight = (node.left == null) ? -1 : ((AVLNode) node.left).height;
            int rightHeight = (node.right == null) ? -1 : ((AVLNode) node.right).height;
            int nodeBalance = rightHeight - leftHeight;
            // rebalance (-2 means left subtree outgrow, 2 means right subtree)
            if (nodeBalance == 2) {
                if (node.right.right != null) {
                    node = (AVLNode)avlRotateLeft(node);
                    break;
                } else {
                    node = (AVLNode)doubleRotateRightLeft(node);
                    break;
                }
            } else if (nodeBalance == -2) {
                if (node.left.left != null) {
                    node = (AVLNode)avlRotateRight(node);
                    break;
                } else {
                    node = (AVLNode)doubleRotateLeftRight(node);
                    break;
                }
            } else {
                updateHeight(node);
            }

            node = (AVLNode)parent;
        }
    }

    // 左旋操作
    private Node avlRotateLeft(Node node) {
        Node temp = super.rotateLeft(node);

        updateHeight((AVLNode)temp.left);
        updateHeight((AVLNode)temp);
        return temp;
    }

    // 右旋操作
    private Node avlRotateRight(Node node) {
        Node temp = super.rotateRight(node);

        updateHeight((AVLNode)temp.right);
        updateHeight((AVLNode)temp);
        return temp;
    }

    // 更新当前节点所领导的树的高度
    private static final void updateHeight(AVLNode node) {
        int leftHeight = (node.left == null) ? -1 : ((AVLNode) node.left).height;
        int rightHeight = (node.right == null) ? -1 : ((AVLNode) node.right).height;
        node.height = 1 + Math.max(leftHeight, rightHeight);
    }

    // 重新计算当前树的高度
    private void recomputeHeight(AVLNode node) {
        while (node != null) {
            node.height = maxHeight((AVLNode)node.left, (AVLNode)node.right) + 1;
            node = (AVLNode)node.parent;
        }
    }

    // 返回两颗树中较高的树的高度
    private int maxHeight(AVLNode node1, AVLNode node2) {
        if (node1 != null && node2 != null) {
            return node1.height > node2.height ? node1.height : node2.height;
        } else if (node1 == null) {
            return node2 != null ? node2.height : -1;
        } else if (node2 == null) {
            return node1 != null ? node1.height : -1;
        }
        return -1;
    }

}
