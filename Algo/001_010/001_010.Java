// 001
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target-nums[i],i);
        }
        return new int[2];
    }
}

// 002
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum=l1.val+l2.val,one=sum/10;
        ListNode head,curr;
        head=curr=new ListNode(sum%10);
        while(null!=l1.next || null!=l2.next){
            if (null==l1.next) l1.next=new ListNode(0);
            if (null==l2.next) l2.next=new ListNode(0);
            l1=l1.next;
            l2=l2.next;
            sum=l1.val+l2.val+one;
            one=sum/10;
            curr.next=new ListNode(sum%10);
            curr=curr.next;
        }
        if (one>0) curr.next=new ListNode(one);
        return head;
    }
}

// 003
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int start=0,max=0;
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i=0;i<s.length();i++){
            if (map.containsKey(s.charAt(i))&&start<=map.get(s.charAt(i))){
                max=max>i-start?max:i-start;
                start=map.get(s.charAt(i))+1;
            }
            map.put(s.charAt(i),i);
        }
        max=max>s.length()-start?max:s.length()-start;
        return max;
    }
}

// 004
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m=nums1.length,n=nums2.length;
        int k = (m + n)/2;
        if ((m+n)%2==1) return helper(nums1,nums2,m,n,0,0,k+1);
        else return (helper(nums1,nums2,m,n,0,0,k)+helper(nums1,nums2,m,n,0,0,k+1))/2.0;
    }
    int helper(int[] nums1, int[] nums2, int m, int n, int a, int b, int k){
        if (m>n) return helper(nums2,nums1,n,m,b,a,k);
        if (m==0) return nums2[b+k-1];
        if (k==1) return nums1[a]<nums2[b]?nums1[a]:nums2[b];
        int ka,kb;
        ka=k/2<m?k/2:m;
        kb=k-ka;
        if (nums1[a+ka-1]<nums2[b+kb-1]) return helper(nums1,nums2,m-ka,n,a+ka,b,k-ka);
        else return helper(nums1,nums2,m,n-kb,a,b+kb,k-kb);
    }
}

// 005
public class Solution {
    public String longestPalindrome(String s) {
        int center=0,length=0,r=0,lenS=s.length();
        for (int i=0;i<lenS;i++){
            int tempC=((lenS-1)>>1)+(((i&1)==1)?1:-1)*((i+1)>>1);
            if (tempC-((length-1)>>1)<0 || tempC+(length>>1)>=lenS) break;
            r=0;
            while (tempC+r+1<lenS && tempC-r-1>=0 && s.charAt(tempC-r-1)==s.charAt(tempC+r+1)) r++;
            if (length<(r<<1)+1) {
                length=(r<<1)+1;
                center=tempC;
            }
            r=0;
            while (tempC+r+1<lenS && tempC-r>=0 && s.charAt(tempC-r)==s.charAt(tempC+r+1)) r++;
            if(length<(r<<1)){
                length=(r<<1);
                center=tempC;
            }
        }
        return s.substring(center-(length-1)/2,center+length/2+1);
    }
}
