package yc.al.zuo.advanced;

/**
 * 左神提供————创建一个搜索二叉树
 * @author code_yc
 * @version 1.0
 * @date 2020/4/21 12:45
 */
public class Class3_Code3_AbstractBinarySearchTree {

    // 首先定义一个节点的静态内部类
    public static class Node {
        public Node(Integer value, Node parent, Node left, Node right) {
            super();
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public Integer value;
        public Node parent;
        public Node left;
        public Node right;

        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }

    }

    //+++++++++++++++++++++++++++++++++++++ 定义成员变量 +++++++++++++++++++++++++++++++++++++++++++++++++++

    // 定义根节点
    public Node root;

    // 当前树的大小
    protected int size;

    //+++++++++++++++++++++++++++++++++++++ Public方法区： 供外界访问 +++++++++++++++++++++++++++++++++++++++++++++++++++
    // 查找指定的element在树中存在的位置
    public Node search(int element) {
        Node node = root;
        while (node != null && node.value != null && node.value != element) {
            if (element < node.value) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    // 向二叉搜索树中插入一个新的element的节点，并返回这个新创建的节点
    public Node insert(int element) {
        if (root == null) {
            root = createNode(element, null, null, null);
            size++;
            return root;
        }

        Node insertParentNode = null;
        Node searchTempNode = root;
        while (searchTempNode != null && searchTempNode.value != null) {
            insertParentNode = searchTempNode;
            if (element < searchTempNode.value) {
                searchTempNode = searchTempNode.left;
            } else {
                searchTempNode = searchTempNode.right;
            }
        }

        Node newNode = createNode(element, insertParentNode, null, null);
        if (insertParentNode.value > newNode.value) {
            insertParentNode.left = newNode;
        } else {
            insertParentNode.right = newNode;
        }

        size++;
        return newNode;
    }

    // 从二叉树中删除element对应的节点
    public Node delete(int element) {
        Node deleteNode = search(element);
        if (deleteNode != null) {
            return delete(deleteNode);
        } else {
            return null;
        }
    }

    // 检查此二叉搜索树中是否存在element节点
    public boolean contains(int element) {
        return search(element) != null;
    }

    // 获取当前二叉索树中的最小值
    public int getMinimum() {
        return getMinimum(root).value;
    }

    // 获取当前二叉索树中的最大值
    public int getMaximum() {
        return getMaximum(root).value;
    }

    // 获取当前element元素的后继节点值，也就是第一个比elemnt大的元素值
    public int getSuccessor(int element) {
        return getSuccessor(search(element)).value;
    }

    // 获取当前二叉树的节点个数
    public int getSize() {
        return size;
    }

    // 中序打印二叉树
    public void printTreeInOrder() {
        printTreeInOrder(root);
    }

    // 前序打印二叉树
    public void printTreePreOrder() {
        printTreePreOrder(root);
    }

    // 后序打印二叉树
    public void printTreePostOrder() {
        printTreePostOrder(root);
    }

    //+++++++++++++++++++++++++++++++++++++ protected方法区： 只允许子类和自己使用 +++++++++++++++++++++++++++++++++++++++++++++++++++
    // 创建一颗二叉树（包含所有属性）
    protected Node createNode(int value, Node parent, Node left, Node right) {
        return new Node(value, parent, left, right);
    }

    // 从二叉搜索树中删除指定的节点deleteNode
    protected Node delete(Node deleteNode) {
        if (deleteNode != null) {
            Node nodeToReturn = null;
            if (deleteNode.left == null) {
                nodeToReturn = transplant(deleteNode, deleteNode.right);
            } else if (deleteNode.right == null) {
                nodeToReturn = transplant(deleteNode, deleteNode.left);
            } else {
                // 找到待删除节点右子树中的最小节点
                Node successorNode = getMinimum(deleteNode.right);
                if (successorNode.parent != deleteNode) {
                    transplant(successorNode, successorNode.right);
                    successorNode.right = deleteNode.right;
                    successorNode.right.parent = successorNode;
                }
                transplant(deleteNode, successorNode);
                successorNode.left = deleteNode.left;
                successorNode.left.parent = successorNode;
                nodeToReturn = successorNode;
            }
            size--;
            return nodeToReturn;
        }
        return null;
    }

    // 获取以node为根节点的搜索二叉树中的最小节点
    protected Node getMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // 获取以node为根节点的搜索二叉树中的最大节点
    protected Node getMaximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // 获取当前节点的后继节点
    protected Node getSuccessor(Node node) {
        // if there is right branch, then successor is leftmost node of that
        // subtree
        if (node.right != null) {
            return getMinimum(node.right);
        } else { // otherwise it is a lowest ancestor whose left child is also
            // ancestor of node
            Node currentNode = node;
            Node parentNode = node.parent;
            while (parentNode != null && currentNode == parentNode.right) {
                // go up until we find parent that currentNode is not in right
                // subtree.
                currentNode = parentNode;
                parentNode = parentNode.parent;
            }
            return parentNode;
        }
    }

    //+++++++++++++++++++++++++++++++++++++ private方法区： 只允许自己使用 +++++++++++++++++++++++++++++++++++++++++++++++++++
    // 将newNode移动到指定的nodeToReplace位置
    private Node transplant(Node nodeToReplace, Node newNode) {
        if (nodeToReplace.parent == null) {
            this.root = newNode;
        } else if (nodeToReplace == nodeToReplace.parent.left) {
            nodeToReplace.parent.left = newNode;
        } else {
            nodeToReplace.parent.right = newNode;
        }
        if (newNode != null) {
            newNode.parent = nodeToReplace.parent;
        }
        return newNode;
    }

    // 中序打印二叉树
    private void printTreeInOrder(Node entry) {
        if (entry != null) {
            printTreeInOrder(entry.left);
            if (entry.value != null) {
                System.out.println(entry.value);
            }
            printTreeInOrder(entry.right);
        }
    }

    // 前序打印二叉树
    private void printTreePreOrder(Node entry) {
        if (entry != null) {
            if (entry.value != null) {
                System.out.println(entry.value);
            }
            printTreeInOrder(entry.left);
            printTreeInOrder(entry.right);
        }
    }

    // 后序打印二叉树
    private void printTreePostOrder(Node entry) {
        if (entry != null) {
            printTreeInOrder(entry.left);
            printTreeInOrder(entry.right);
            if (entry.value != null) {
                System.out.println(entry.value);
            }
        }
    }


    //+++++++++++++++++++++++++++++++++++++ 辅助功能： 打印树结构 +++++++++++++++++++++++++++++++++++++++++++++++++++

    public void printTree() {
        printSubtree(root);
    }

    public void printSubtree(Node node) {
        if (node.right != null) {
            printTree(node.right, true, "");
        }
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, "");
        }
    }

    private void printNodeValue(Node node) {
        if (node.value == null) {
            System.out.print("<null>");
        } else {
            System.out.print(node.value.toString());
        }
        System.out.println();
    }

    private void printTree(Node node, boolean isRight, String indent) {
        if (node.right != null) {
            printTree(node.right, true, indent + (isRight ? "        " : " |      "));
        }
        System.out.print(indent);
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
        System.out.print("----- ");
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, indent + (isRight ? " |      " : "        "));
        }
    }

}
