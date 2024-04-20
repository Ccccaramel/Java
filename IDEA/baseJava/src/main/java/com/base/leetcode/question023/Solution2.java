package com.base.leetcode.question023;

class Solution2 {  // 78ms
    public ListNode mergeKLists(ListNode[] lists) {
        // 判断是否为空
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

        ListNode cur,obj;  // cur:主链,最终合并后返回的链;obj:称其为"副链"吧,待与主链比较,并提取至主链的链

        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null){  // 找到一个不为空的链
                for(int j=i+1;j<lists.length;j++){  // 遍历其它链
                    obj=lists[j];
                    cur = lists[i];
                    if(lists[j]==null){  // 跳过 null 链
                        continue;
                    }
                    do{
                        if(lists[i].val>= obj.val){  // 副链比主链的头还小
                            lists[i] = new ListNode(obj.val,lists[i]);
                            cur = lists[i];
                            obj=obj.next;
                        }
                        else if(cur.next==null){  // 主链末尾,直接与副链当前节点链接
                            cur.next=obj;
                            break;
                        }
                        else if(cur.next.val >= obj.val){
                            cur.next = new ListNode(obj.val,cur.next);
                            cur=cur.next;
                            obj=obj.next;
                        }
                        else{
                            cur=cur.next;
                        }
                    }while (obj!=null);
                }
                return lists[i];
            }
        }
        return null;
    }
}
