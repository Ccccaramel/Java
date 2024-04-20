package com.base.leetcode.question023;
// 直接合并所有链,然后排序
class Solution4 {  // 166ms
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode cur = null,obj;  // cur:主链,最终合并后返回的链;obj:称其为"副链"吧,待与主链比较,并提取至主链的链


        int i=0;
        // 合并成一条链
        for(;i<lists.length;i++){
            if(lists[i]!=null){  // 找到一个不为空的链,并当作主链
                cur=lists[i];
                while(cur.next!=null) {
                    cur=cur.next;
                }  // 找到主链最后一个节点
                for(int j=i+1;j<lists.length;j++){
                    if(lists[j]!=null){
                        cur.next=lists[j];  // 将副链与主链拼接
                        do{
                            cur=cur.next;
                        }while (cur.next!=null);
                    }
                }
                break;
            }
        }

        if(i==lists.length){
            return null;
        }
        cur=lists[i];
        if(cur==null){
            return null;
        }

        // 排序
        int t=0;
        boolean order;
        do{
            order=true;
            obj=cur;
            while(obj.next!=null){
                if(obj.val>obj.next.val){
                    t= obj.val;
                    obj.val=obj.next.val;
                    obj.next.val=t;
                    order=false;
                }
                obj=obj.next;
            };
        }while (!order);
        return cur;
    }
}
