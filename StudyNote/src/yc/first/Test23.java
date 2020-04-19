package yc.first;

import my_code_sum_up.code_linkedlist.util.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Test23 {

    //方法一： 暴力穷举
    public ListNode mergeKLists(ListNode[] lists) {
        // 1. 首先统计出移动有多少个元素
        int sum = 0;
        for (ListNode c : lists) {
            while (c != null) {
                sum++;
                c = c.next;
            }
        }
        //  2. 创建存储所有元素的数组
        int[] nums = new int[sum];
        sum = 0;
        for (ListNode d : lists) {
            while (d != null) {
                nums[sum++] = d.val;
                d = d.next;
            }
        }
        //3. 将搜索出来的数组进行排序
        Arrays.sort(nums);
        //4. 遍历数组，生成结果链表
        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        for (int i = 0; i < nums.length; i++) {
            temp.next = new ListNode(nums[i]);
            temp = temp.next;
        }
        return dummy.next;
    }

/*
    //方法二：分治加归并
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return slove(lists, 0, lists.length - 1);
    }

    private ListNode slove(ListNode[] lists, int left, int right) {
        //1.结束情况
        if (left == right) {
            return lists[left];
        }

        //2. 分治
        int mid = (left + right) / 2;
        ListNode lNode = slove(lists, left, mid);
        ListNode rNode = slove(lists, mid + 1, right);

        //3. 融合
        return merge(lNode, rNode);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
*/


    //二刷

    /**
     * 链表二刷——采用空间换时间的方法——先将节点元素构成的数组排序，再依次重构链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists3(ListNode[] lists){
        //首先创建一个数组集合，将节点遍历加入集合
        ArrayList<Integer> nums = new ArrayList<>();
        for (ListNode node : lists) {
            while (node != null) {
                nums.add(node.val);
                node = node.next;
            }
        }
        //将数组排序
        nums.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        //遍历集合，构建新的链表
        ListNode res = new ListNode(0);
        ListNode cur = res;
        for (Integer num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        //返回结果
        return res.next;
    }

    // 二刷——— 分治+归并的思想(递归的思想)
    public ListNode mergeKLists2(ListNode[] lists){
        //首先进行边界条件的判断
        if(lists.length == 0){
            return null;
        }
        //二分下去进行递归搜索
        return binarySearch(lists,0,lists.length - 1);
    }

    private ListNode binarySearch(ListNode[] lists, int left, int right) {
        //递归结束条件
        if(left == right){
            return lists[left];
        }
        //找出数组的中间值
        int mid = (left + right) / 2;
        ListNode left_node = binarySearch(lists,left,mid);
        ListNode right_node = binarySearch(lists,mid + 1,right);
        //将左右链表融合，返回最终的链表
        return merger(left_node,right_node);
    }

    private ListNode merger(ListNode left_node, ListNode right_node) {
        if(left_node == null){
            return right_node;
        }
        if(right_node == null){
            return left_node;
        }
        if(left_node.val < right_node.val){
            left_node.next = merger(left_node.next,right_node);
            return left_node;
        }else{
            right_node.next = merger(left_node,right_node.next);
            return right_node;
        }
    }
}
