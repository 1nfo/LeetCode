//051
public class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ret = new ArrayList<>();
        boolean[][] s=new boolean[n][n];
        boolean [] row=new boolean[n],col=new boolean[n],
                       diaA=new boolean[2*n-1],diaB=new boolean[2*n-1];
        solve(ret,s,0,n,row,col,diaA,diaB);
        return ret;
    }
    List<String> addSolution(boolean[][] s,int n){
        List<String> ret = new ArrayList<>();
        for(int i=0;i<n;i++){
            StringBuilder tmp = new StringBuilder();
            for(int j=0;j<n;j++){
                if(s[i][j]) tmp.append('Q');
                else tmp.append('.');
            }
            ret.add(tmp.toString());
        }
        return ret;
    }
    void solve(List<List<String>> ret,boolean[][] s, int count, int n,
            boolean[] row, boolean[] col, boolean[] diaA, boolean[] diaB){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==count&&!row[i]&&!col[j]&&!diaA[i+j]&&!diaB[n-i+j-1]){
                    s[i][j]=row[i]=col[j]=diaA[i+j]=diaB[n-i+j-1]=true;
                    if(++count>=n) ret.add(addSolution(s,n));
                    solve(ret,s,count--,n,row,col,diaA,diaB);
                    s[i][j]=row[i]=col[j]=diaA[i+j]=diaB[n-i+j-1]=false;
                }
            }
        }
    }
}

//052
public class Solution {
    private int res = 0;
    private int n;
    boolean [] row,col,diaA,diaB;
    public int totalNQueens(int n) {
        this.n=n;
        this.row = new boolean[n];
        this.col = new boolean[n];
        this.diaA = new boolean[2*n-1];
        this.diaB = new boolean[2*n-1];
        solve(0);
        return res;
    }
    void solve(int count){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==count&&!row[i]&&!col[j]&&!diaA[i+j]&&!diaB[n-1-i+j]){
                    row[i]=col[j]=diaA[i+j]=diaB[n-1-i+j]=true;
                    if(++count>=n) ++res;
                    solve(count--);
                    row[i]=col[j]=diaA[i+j]=diaB[n-1-i+j]=false;
                }
            }
        }
    }
}

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

//057
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> ret = new ArrayList<>();
        if(intervals.size()==0) {
            ret.add(newInterval);
            return ret;
        }
        int start=newInterval.start,end=newInterval.end;
        List<Integer> list = new ArrayList<>();
        Interval tmp = new Interval();
        int first=-1,last=-1;
        for(int k=0;k<intervals.size();k++){
            Interval i = intervals.get(k);
            if(start<=i.end&&end>=i.start){
                list.add(k);
                if(first==-1) first=k;
                last=k;
            }
        }
        if(first==-1){
            if(intervals.get(0).start>end) ret.add(new Interval(start,end));
            ret.add(intervals.get(0));
            for(int i=0;intervals.size()>1&&i<intervals.size()-1;i++){
                if(intervals.get(i).end<start&&intervals.get(i+1).start>end)
                    ret.add(new Interval(start,end));
                ret.add(intervals.get(i+1));
            }
            if(intervals.get(intervals.size()-1).end<start) ret.add(new Interval(start,end));
        }else{
            for(int k=0;k<intervals.size();k++){
                Interval i = intervals.get(k);
                if(k<first||k>last) ret.add(i);
                else{
                    if(k==first) tmp.start = start<i.start?start:i.start;
                    if(k==last){
                        tmp.end = end>i.end?end:i.end;
                        ret.add(tmp);
                    }
                }
            }
        }
        return ret;
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

//059
public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];
        int i=0,j=0,count=1;
        int end=n-1,start=0;
        while(count<n*n){
            for(;j<end;j++) ret[i][j]=count++;
            for(;i<end;i++) ret[i][j]=count++;
            for(;j>start;j--) ret[i][j]=count++;
            for(;i>start;i--) ret[i][j]=count++;
            start++;end--;i++;j++;
        }
        if(n%2==1)ret[i][j]=count;
        return ret;
    }
}

//060
public class Solution {
    public String getPermutation(int n, int k) {
        k--;
        StringBuilder ret = new StringBuilder();
        boolean[] used = new boolean[n];
        int [] res = new int[n];
        res[0]=1;
        for(int i=1;i<n;i++) res[i]=res[i-1]*i;
        for(int i=0;i<n;i++){
            int m = res[n-i-1],count=0,j;
            int d = k/m;
            for(j=0;j<n;j++){
                if(!used[j]) count++;
                if(count==d+1) {used[j]=true;break;}
            }
            ret.append((char)('1'+j));
            k%=m;
        }
        return ret.toString();
    }
}

//062
public class Solution {
    public int uniquePaths(int m, int n) {
        int[][] b = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0&&j==0) b[i][j]=1;
                else b[i][j] = (i>0?b[i-1][j]:0)+(j>0?b[i][j-1]:0);
            }
        }
        return b[m-1][n-1];
    }
}

//063
public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length,n=obstacleGrid[0].length;
        int[][] b = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(obstacleGrid[i][j]!=0) b[i][j]=0;
                else if(i==0&&j==0) b[i][j]=1;
                else b[i][j] = (i>0?b[i-1][j]:0)+(j>0?b[i][j-1]:0);
            }
        }
        return b[m-1][n-1];
    }
}

//064
public class Solution {
    public int minPathSum(int[][] grid) {
        int m=grid.length,n=grid[0].length;
        int [][] M = new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0&&j==0) M[i][j]=grid[i][j];
                else if(i==0) M[i][j]=grid[i][j]+M[i][j-1];
                else if(j==0) M[i][j]=grid[i][j]+M[i-1][j];
                else M[i][j]=grid[i][j] + (M[i][j-1]<M[i-1][j]?M[i][j-1]:M[i-1][j]);
            }
        }
        return M[m-1][n-1];
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
