package com.base.leetcode.question023;

class Solution5 {  // 153ms
    public ListNode mergeKLists(ListNode[] lists) {
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
        int v = lists[j].val;
        lists[j] = lists[j].next;
        return new ListNode(v, mergeKLists(lists));
    }
}