package com.base.leetcode.question023;

class Solution7 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode cur;
        int i = 0, j = 0;
        boolean allNull = true;
        for (; i < lists.length; i++) {
            if (lists[i] == null) {
                continue;
            }
            if (allNull || lists[i].val <= lists[j].val) {
                j = i;
                allNull = false;
            }
        }
        if (allNull) {
            return null;
        }

        cur=lists[j];
        lists[j] = lists[j].next;
        cur.next=mergeKLists(lists);
        return cur;
    }
}