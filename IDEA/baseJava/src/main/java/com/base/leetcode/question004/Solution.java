package com.base.leetcode.question004;

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len=nums1.length+ nums2.length,a=0,b=0;
        for(int i=0,j=0,k=0;k<len/2+1;k++){
            b=a;
            if(i< nums1.length&&j< nums2.length){
                if(nums1[i]<=nums2[j]){
                    a=nums1[i];
                    i++;
                }
                else{
                    a=nums2[j];
                    j++;
                }
            }
            else if(i< nums1.length){
                a=nums1[i];
                i++;
            }
            else if(j< nums2.length){
                a=nums2[j];
                j++;
            }
        }

        if(len%2==0){
            return (a+b)/2.0;
        }
        else{
            return a;
        }
    }
}