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
        ListNode ret = new ListNode(0),node=ret;
        int one = 0;
        while(l1!=null || l2!=null){
            int res = (l1!=null?l1.val:0)+(l2!=null?l2.val:0)+one;
            node.next = new ListNode(res%10);
            one = res/10;
            node = node.next;
            l1=(l1!=null?l1.next:null);
            l2=(l2!=null?l2.next:null);
        }
        if(one!=0) node.next = new ListNode(one);
        return ret.next;
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

// 007
public class Solution {
    public int reverse(int x) {
        if (x>0) return -reverse(-x);
        long ret=0;
        while (x<0){
            ret=ret*10+x%10;
            x/=10;
        }
        if (ret<1<<31) return 0;
        return (int)ret;
    }
}

//008
public class Solution {
    public int myAtoi(String str) {
        int flag=1,i=0;
        long ret=0;
        String l= str.trim();
        if (l.length()==0) return 0;
        if ('+'==l.charAt(0)) i++;
        else if ('-'==l.charAt(0)){i++;flag=-1;}
        while(i<l.length()){
            if (!Character.isDigit(l.charAt(i))) break;
            ret=ret*10+(l.charAt(i++)-'0');
            if (ret*flag>Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (ret*flag<Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }
        return (int) ret*flag;
    }
}

// 009
public class Solution {
    public boolean isPalindrome(int x) {
        int y=0,z=x;
        if (z<0) return false;
        while(z>0){
            y=y*10+z%10;
            z/=10;
        }
        return y==x;
    }
}

// 010
public class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length()+1 ][s.length()+1];
        dp[0][0]=true;
        for(int i=0;i<p.length();i++){
            if(i!=0) dp[i+1][0]=dp[i-1][0]&&p.charAt(i)=='*';
            for(int j=0;j<s.length();j++){
                if(p.charAt(i)=='.' || p.charAt(i)==s.charAt(j)) dp[i+1][j+1]=dp[i][j];
                else if(p.charAt(i)=='*'){
                    if(i==0) return false;
                    if(p.charAt(i-1)==s.charAt(j)||p.charAt(i-1)=='.')dp[i+1][j+1] = (dp[i-1][j+1]|dp[i][j+1]||dp[i+1][j]);
                    else dp[i+1][j+1] = dp[i-1][j+1];
                }
            }
        }
        return dp[p.length()][s.length()];
    }
}
