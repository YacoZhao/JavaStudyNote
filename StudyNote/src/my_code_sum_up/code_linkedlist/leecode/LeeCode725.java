package my_code_sum_up.code_linkedlist.leecode;

import my_code_sum_up.code_linkedlist.util.ListNode;

// 分割链表
public class LeeCode725 {
    public ListNode[] splitListToParts(ListNode root, int k) {
        if (k == 1) return new ListNode[]{root};
        if (root == null) return new ListNode[k];
        //遍历走一遍，找出链表的长度
        int n = 0;
        ListNode cur = root;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        return process(root, n, k);
    }

    // 分割链表添值
    private ListNode[] process(ListNode root, int n, int k) {
        ListNode[] res = new ListNode[k];
        int[] ans = new int[k];
        int v = n / k;
        int s = n - (v * k);
        for (int i = 0; i < ans.length; i++) {
            ans[i] = v;
        }
        for (int i = 0; i < s; i++) {
            ans[i]++;
        }
        ListNode cur = root;
        for (int i = 0; i < res.length; i++) {
            if (ans[i] == 0) {
                continue;
            }
            while (ans[i] != 1) {
                ans[i]--;
                cur = cur.next;
            }
            ListNode next = cur.next;
            cur.next = null;
            res[i] = root;
            root = next;
            cur = root;
        }
        return res;
    }
}
