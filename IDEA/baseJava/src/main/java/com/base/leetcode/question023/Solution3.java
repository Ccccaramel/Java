package com.base.leetcode.question023;

// 新建一条链 res ,通过遍历,获取个各个链的头的 val ,找到最小值并生成节点添至 res 结尾,并将最小值所在的链的头换成子节点
class Solution3 {  // 211ms
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res= null,cur = null;
        // 判断是否为空
        if(lists.length==0||(lists.length==1&&lists[0]==null)){
            return null;
        }
        boolean allNull;
        int i,val=0,iMark=0;
        do{
            // 找到最小
            allNull=true;  // 是否全部为空,也可以通过它判断当前是否为第一个非链
            for(i=0;i<lists.length;i++){
                if(lists[i]==null){  // 副链是空的
                    continue;
                }
                else{
                    if(allNull||val>lists[i].val){
                        val=lists[i].val;
                        iMark=i;
                    }
                    allNull=false;
                }
            }

            if(allNull){
                break;
            }

            // 取出最小
            if(lists[iMark]!=null){
                lists[iMark]=lists[iMark].next;
            }
            else{
                lists[iMark]=null;
            }

            // 放入 res
            if(res==null){
                res=new ListNode(val);
                cur=res;
            }
            else{
                cur.next=new ListNode(val);
                cur=cur.next;
            }

        }while(!allNull);

        return res;
    }
}