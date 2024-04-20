package com.base.leetcode.question023;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        running();

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;

        System.out.println("timeElapsed:"+timeElapsed);
}
    private static void running(){
        Solution7 solution = new Solution7();

//        lists = [[1,4,5],[1,3,4],[2,6]]
//        ListNode l1_3 = new ListNode(5);
//        ListNode l1_2 = new ListNode(4,l1_3);
//        ListNode l1_1 = new ListNode(1,l1_2);
//
//        ListNode l2_3 = new ListNode(4);
//        ListNode l2_2 = new ListNode(3,l2_3);
//        ListNode l2_1 = new ListNode(1,l2_2);
//
//        ListNode l3_2 = new ListNode(6);
//        ListNode l3_1 = new ListNode(2,l3_2);
//        ListNode[] l = new ListNode[3];
//
//        l[0]=l1_1;
//        l[1]=l2_1;
//        l[2]=l3_1;
//        ListNode r = solution.mergeKLists(l);
//        System.out.println("res:"+r);

//        User user1=new User();
//        user1.setAge(1);
//
//        User user2=user1;
//
//        user2.setAge(2);
//
//        System.out.println(">>"+user1.getAge());

        ListNode[] l = new ListNode[2];

        ListNode l1_1 = new ListNode(1);

        ListNode l2_1 = new ListNode();

        l[0]=l1_1;
        l[1]=l2_1;

        ListNode r = solution.mergeKLists(l);
        System.out.println("res:"+r);

//        ListNode[] l = new ListNode[2];
//
//        ListNode l1_1 = new ListNode();
//
//        ListNode l2_1 = new ListNode();
//
//        l[0]=l1_1;
//        l[1]=l2_1;
//
//        System.out.println("res:"+solution.mergeKLists(l));

//        [[-1,1],[-3,1,4],[-2,-1,0,2]]

//        ListNode l1_2 = new ListNode(1);
//        ListNode l1_1 = new ListNode(-1,l1_2);
//
//        ListNode l2_3 = new ListNode(4);
//        ListNode l2_2 = new ListNode(1,l2_3);
//        ListNode l2_1 = new ListNode(-3,l2_2);
//
//        ListNode l3_4 = new ListNode(2);
//        ListNode l3_3 = new ListNode(0,l3_4);
//        ListNode l3_2 = new ListNode(-1,l3_3);
//        ListNode l3_1 = new ListNode(-2,l3_2);
//        ListNode[] l = new ListNode[3];
//
//        l[0]=l1_1;
//        l[1]=l2_1;
//        l[2]=l3_1;
//
//        System.out.println("res:"+solution.mergeKLists(l));

//        [[1],[0]]

//        ListNode l1_1 = new ListNode(1);
//        ListNode l2_1 = new ListNode(0);
//
//        ListNode[] l = new ListNode[2];
//
//        l[0]=l1_1;
//        l[1]=l2_1;
//
//        System.out.println("res:"+solution.mergeKLists(l));
    }

}
class User{
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}