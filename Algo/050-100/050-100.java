//053
public class Solution {
    public int maxSubArray(int[] nums) {
        int max=nums[0],sum=0;
        for(int i:nums){
            sum+=i;
            if(i>sum) sum=i;
            if(sum>max) max=sum;
        }
        return max;
    }
}
 // 054
 public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret= new ArrayList<>();
        if (matrix.length==0) return ret;
        int M=matrix[0].length-1,N=matrix.length-1,m=0,n=0,total=M*N+M+N+1,i,j;
        while(m<=M&&n<=N){
            for(j=m;j<=M;j++) ret.add(matrix[n][j]);
            if(++n>N) break;
            for(i=n;i<=N;i++) ret.add(matrix[i][M]);
            if(m>--M) break;
            for(j=M;j>=m;j--) ret.add(matrix[N][j]);
            //if(n>--N) break;
            for(i=N;i>=n;i--) ret.add(matrix[i][m]);
            //if(++m>) break;

        }
        return ret;
    }
}

// 055
public class Solution {
    public boolean canJump(int[] nums) {
        int curr=0;
        while(curr<nums.length-1){
            int next=find(nums,curr);
            if(next==curr) return false;
            curr=next;
        }
        return true;
    }
    int find(int[] nums,int pos){
        int step=nums[pos],mpos=pos+step;
        if(nums.length>mpos){
            int max=step+nums[mpos];
            for(int i=1;i<step;i++){
                if(nums[pos+i]+i>max) {
                    mpos=pos+i;
                    max=nums[mpos]+i;
                }
            }
        }
        return mpos;
    }
}

// 056
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ret = new ArrayList<>();
        if(intervals.size()==0) return ret;
        for(Interval tmp:intervals){
            Interval added = null;
            if (ret.size()==0) {
                ret.add(tmp);
                continue;
            }
            while(true){
                boolean merged=false;
                for(Interval curr:ret){
                    if(mergable(curr,tmp)||mergable(tmp,curr)){
                        int start=curr.start<tmp.start?curr.start:tmp.start;
                        int end=curr.end>tmp.end?curr.end:tmp.end;
                        ret.remove(curr);
                        tmp = new Interval(start,end);
                        merged=true;
                        break;
                    }
                }
                if(!merged) break;
            }
            ret.add(tmp);
        }
        return ret;
    }
    boolean mergable(Interval a, Interval b){
        return a.start<=b.start&&b.start<=a.end||a.start<=b.end&&b.end<=a.end;
    }

}

// 058
public class Solution {
    public int lengthOfLastWord(String s) {
        int ret=0,last=s.length()-1;
        while(last>=0&&s.charAt(last)==' ')last--;
        while(last>=0&&s.charAt(last--)!=' ') ret++;
        return ret;
    }
}

// 066
public class Solution {
    public int[] plusOne(int[] digits) {
        for(int i=digits.length-1;i>=0;i--){
            if(digits[i]++<9) return digits;
            digits[i]=0;
        }
        int[] ret = new int[digits.length+1];
        ret[0]=1;
        return ret;
    }
}

// 070
public class Solution {
    public int climbStairs(int n) {
        if(n<=2) return n;
        int a=1,b=2,i=2;
        while(i++<n){
            b=a+b;
            a=b-a;
        }
        return b;
    }
}

// 083
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode curr=head;
        while(curr.next!=null){
            if(curr.next.val==curr.val) curr.next=curr.next.next;
            else curr=curr.next;
        }
        return head;
    }


// 088
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int k=m+n-1;k>=0;k--){
            if(m<=0||n>0&&nums1[m-1]<nums2[n-1]) nums1[k]=nums2[--n];
            else nums1[k]=nums1[--m];
        }

    }
}

// 100
public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null) return true;
        if(p==null||q==null) return false;
        if(p.val!=q.val) return false;
        return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }
}
