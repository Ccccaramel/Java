package com.base.leetcode.question023;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {  // 371ms
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = new ListNode();
        if(lists.length==0||(lists.length==1&&lists[0]==null)){
            return null;
        }

        int mark=0;
        for(int i=0;i<lists.length;i++){
            if(lists[i]==null){
                mark++;
            }
        }
        if(mark==lists.length){
            return null;
        }

        /**
         * cur:通过遍历将获取到每一个链的最后一个节点,并保存
         * obj:遍历非 cur 所在链的其它链的各个节点,并与 cur 的值比较,并将符合条件的节点拼接至 cur
         * newLink: obj 所在的原链,在将其节点移至 cur 后需要将
         */
        ListNode cur,obj,newLink;

        while (lists.length!=1){

            mark=0;
            for(int i=0;i<lists.length;i++){
                if(lists[i]==null){
                    mark++;
                }
            }
            if(mark==lists.length-1){
                break;
            }
            int i=0;
            for(;i<lists.length;i++){

                cur=lists[i];
                if(cur==null){
                    continue;
                }

                for(int j=0;j<lists.length;j++){  // 遍历其它链

                    while(cur.next!=null){  // 找到链最后一个节点
                        cur=cur.next;
                    }

                    if(i==j||lists[j]==null){
                        continue;
                    }
                    obj=lists[j];
                    do{  // 遍历其中一条链多所有节点
                        if(cur.val<=obj.val){
                            cur.next=obj;  // 将 obj 节点链接至 cur 下
                            newLink=lists[j];  // 将 obj 原父节点与其断开,即置空
                            if(newLink.next==null||newLink.val==obj.val){  // 仅一个
                                lists[j]=null;
                                cur=cur.next;
                            }
                            else{
                                do{
                                    if(newLink.next.val== obj.val){  // 后面还有
                                        newLink.next=null;
                                        break;
                                    }
                                    newLink=newLink.next;
                                }while(newLink!=null);
                            }
                            break;
                        }
                        obj=obj.next;
                    }while(obj!=null);
                }
            }
        }
        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null){
                res = lists[i];
            }
        }

        return res;
    }
}
