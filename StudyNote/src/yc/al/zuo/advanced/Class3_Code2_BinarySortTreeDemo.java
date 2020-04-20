package yc.al.zuo.advanced;

/**
 * // 二叉排序树的简单实现
 * // 难点就在于删除节点的操作
 * // 如果待删除的节点左右孩子都有，那么将右子树上最小的孩子删除，然后将最小的元素赋值到当前位置就可以了
 * @author code_yc
 * @version 1.0
 * @date 2020/4/20 9:53
 */
public class Class3_Code2_BinarySortTreeDemo {

    // TODO 首先创建节点类
    static class TreeNode {
        Integer value;
        TreeNode left;
        TreeNode right;

        // 创建构造器
        public TreeNode(Integer value) {
            this.value = value;
        }

        // 指定一个元素value, 查找当前元素的父节点
        public TreeNode searchParent(int value) {
            // 如果当前节点的左右孩子有一个是，直接返回
            if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
                return this;
            } else {
                if (this.left != null && this.value > value) {
                    return this.left.searchParent(value);
                } else if (this.right != null && this.value < value) {
                    return this.right.searchParent(value);
                } else {
                    return null;   // 没有找到该元素的父节点
                }
            }
        }

        // 指定一个value，查找其在二叉树中对应的节点
        public TreeNode search(int value) {
            if (this.value == value) {
                return this;
            } else if (this.value > value) {
                return this.left == null ? null : this.left.search(value);
            } else if (this.value < value) {
                return this.right == null ? null : this.right.search(value);
            } else {
                return null; // 没有找到当前节点
            }
        }

        // 向此节点所在的树中添加节点
        public void add(TreeNode node) {
            if (node == null) return;
            if (this.value < node.value) {
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            } else if (this.value > node.value) {
                if (this.left == null) {
                    this.left = node;
                } else {
                    this.left.add(node);
                }
            } else{
                throw new RuntimeException("当前插入的节点在二叉树中已经存在了");
            }
        }
    }

    // TODO 创建二叉树的对象（其实只用节点类就可以实现）
    static class BinarySearchTree{
        TreeNode root;  // 创建二叉搜索树的根节点

        // 空参构造器
        public BinarySearchTree() {
            this.root = null;
        }

        // 获取当前二叉树的根节点
        public TreeNode getRoot() {
            return root;
        }

        // 向二叉树中添加元素
        public void insert(int value){
            if(this.root == null){
                root = new TreeNode(value);
            }else{
                this.root.add(new TreeNode(value));
            }
        }

        // 搜索一个值对应的节点
        public TreeNode search(int value){
            if(root == null) return null;
            return this.root.search(value);
        }

        // 查找当前value对应的值的父节点
        public TreeNode searchParent(int value){
            if(root == null) return null;
            return root.searchParent(value);
        }

        // 从二叉树中删除一个节点
        public TreeNode delete(int value){
            if(root == null) return null;
            TreeNode targetNode = search(value);
            if(targetNode != null){
                TreeNode parentNode = searchParent(value);
                // 首先如果当前根节点左右孩子都没有
                if(root.left == null && root.right == null) {
                    root = null;
                }else if(targetNode.left == null && targetNode.right == null){
                    // 如果当前待删除的节点是叶子点
                    if(parentNode.left != null && parentNode.left.value == value){
                        parentNode.left = null;
                    }else if(parentNode.right != null && parentNode.right.value == value){
                        parentNode.right = null;
                    }
                }else if(targetNode.left != null && targetNode.right != null){
                    // 如果待删除节点的两个孩子都有，首先获取当前右子树中的最小值
                    int min = delRightTreeMin(targetNode.right);
                    targetNode.value = min;
                }else {
                    // 如果待删除节点只有一个孩子
                    if(targetNode.left != null){
                        // 如果当前节点有左孩子，那么肯定没有有孩子
                        if(parentNode != null){
                            if(parentNode.left == targetNode){
                                parentNode.left = targetNode.left;
                            }else{
                                parentNode.right = targetNode.left;
                            }
                        }else{
                            root = targetNode.left;
                        }
                    }else{
                        // 如果当前节点只有右孩子
                        if(parentNode != null){
                            if(parentNode.left == targetNode){
                                parentNode.left = targetNode.right;
                            }else{
                                parentNode.right = targetNode.right;
                            }
                        }else{
                            root = targetNode.right;
                        }
                    }
                }
            }
            return targetNode;
        }

        // 删除并获取当前树中的最小节点
        public int delRightTreeMin(TreeNode node){
            TreeNode target = node;
            while(target.left != null){
                target = target.left;
            }
            delete(target.value);
            return target.value;
        }

    }
}
