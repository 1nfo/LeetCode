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

        for(Interval x:intervals){
            if(ret.size() == 0) ret.add(x);
            else{
                boolean iter=true;
                while(iter){
                    iter=false;
                    for(Interval y:ret){
                        if(mergable(x,y)||mergable(y,x)){
                            int start = x.start>y.start?y.start:x.start;
                            int end = x.end>y.end?x.end:y.end;
                            ret.remove(y);
                            x = new Interval(start,end);
                            iter=true;
                            break;
                        }
                    }
                }
                ret.add(x);
            }
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

//061
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null) return head;
        int count=0;
        ListNode p=new ListNode(0),newTail=p,newHead,tmp;
        p.next=head;
        tmp=p;
        while(tmp.next!=null) {tmp=tmp.next;count++;}
        k%=count;
        while(p.next!=null){
            p=p.next;
            count--;
            if(count==k) newTail=p;
        }
        if(k==0) return head;
        p.next=head;
        newHead=newTail.next;
        newTail.next=null;
        return newHead;
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

// 065
public class Solution {
    public boolean isNumber(String s) {
        if (s.trim().length()==0) return false;
        String regexp = "^(\\+|-)?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)(e(\\+|-)?[0-9]+)?$";
        return s.trim().replaceAll(regexp,"").length()==0;
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

// 067
public class Solution {
    public String addBinary(String a, String b) {
        if(a.length()<b.length()) return addBinary(b,a);
        StringBuilder ret = new StringBuilder();
        int x = a.length()-1,y=b.length()-1;
        int one = 0;
        while(y>=0){
            int res = a.charAt(x--) - '0' + (b.charAt(y--)-'0') + one;
            ret.append(res%2);
            one = res/2;
        }
        while(x>=0){
            int res = a.charAt(x--) - '0' + one;
            ret.append(res%2);
            one=res/2;
        }
        if (one>0) ret.append(1);
        return ret.reverse().toString();
    }
}

// 068
public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ret = new ArrayList<>();
        int start=0,end=0,occupied=0,length=0;
        for(;end<words.length;end++){
            int l = words[end].length();
            if(occupied+l>maxWidth){
                // add this l will overflow so this l is the next start
                ret.add(packWords(Arrays.copyOfRange(words,start,end),maxWidth,length));
                start=end;
                occupied=0;
                length=0;
            }
            occupied+=l+1;
            length+=l;
        }
        ret.add(packWordsNoEven(Arrays.copyOfRange(words,start,end),maxWidth));
        return ret;
    }

    String packWords(String[] words, int maxWidth, int length){
        StringBuilder ret = new StringBuilder();
        if(words.length==1){
            ret.append(words[0]);
            int times = maxWidth-length;
            for(int i=0;i<times;i++) ret.append(' ');
            return ret.toString();
        }
        int spaceM = words.length-1,spaceN = maxWidth-length;
        int gap = spaceN/spaceM;
        int i;
        for(i=0;i<spaceM;i++){
            ret.append(words[i]);
            int times = gap + (i<spaceN%spaceM?1:0);
            for(int j=0;j<times;j++) ret.append(' ');
        }
        ret.append(words[i]);
        return ret.toString();
    }

    String packWordsNoEven(String[] words,int maxWidth){
        StringBuilder ret = new StringBuilder();
        int length = 0;
        for(String word:words){
            ret.append(word+" ");
            length+=word.length()+1;
        }
        ret.deleteCharAt(--length);
        while(length++<maxWidth) ret.append(" ");
        return ret.toString();
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

// 071
public class Solution {
    public String simplifyPath(String path) {
        Stack<String> s = new Stack<>(),ss=new Stack<>();
        StringBuilder ret = new StringBuilder();
        String[] paths = path.split("\\/+");
        for (String p:paths){
            switch (p){
                case "":;
                case ".":break;
                case "..":if(!s.empty()) s.pop();break;
                default:s.push(p);
            }
        }
        while(!s.empty()) ss.push(s.pop());
        while(!ss.empty()) ret.append("/"+ss.pop());
        if(ret.length()==0) ret.append("/");
        return ret.toString();
    }
}

// 072
public class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int i=0;i<word1.length();i++) dp[i+1][0]=i+1;
        for(int i=0;i<word2.length();i++) dp[0][i+1]=i+1;
        for(int i=0;i<word1.length();i++){
            for(int j=0;j<word2.length();j++){
                if(word1.charAt(i)==word2.charAt(j)) dp[i+1][j+1]=dp[i][j];
                else dp[i+1][j+1]=Math.min(dp[i][j],Math.min(dp[i][j+1],dp[i+1][j]))+1;
            }
        }
        return dp[word1.length()][word2.length()];
    }
}

// 073
public class Solution {
    public void setZeroes(int[][] matrix) {
        boolean[] row = new boolean[matrix.length],col = new boolean[matrix[0].length];
        for(int i=0;i<matrix.length;i++)
            for(int j=0;j<matrix[0].length;j++)
                if(matrix[i][j]==0)
                    row[i]=col[j]=true;
        for(int i=0;i<matrix.length;i++)
            for(int j=0;j<matrix[0].length;j++)
                if(col[j]||row[i])
                    matrix[i][j]=0;

    }
}

// 074
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n=matrix[0].length,i;
        if(matrix[0][0]>target) return false;
        for(i=0;i<m-1;i++)
           if(matrix[i+1][0]>target) break;
        for(int j=0;j<n;j++)
            if(matrix[i][j]==target) return true;
        return false;
    }
}

// 075
public class Solution {
    public void sortColors(int[] nums) {
        for(int i=2,j=nums.length-1;i>0;i--)
            for(int k=0;k<j;k++){
                int c = nums[k];
                if(c==i) {
                    while(j>0&&nums[j]>=i) j--;
                    if(k<j){
                        nums[k]=nums[j];
                        nums[j--]=c;
                    }
                }
            }
    }
}

// 076
public class Solution {
    public String minWindow(String s, String t) {
        Map<Character,Integer> map = new HashMap<>();
        int k=0;
        int minL = s.length()+1,minStart=0,minEnd=0;
        for(char c:t.toCharArray()){
            if (!map.containsKey(c)){
                map.put(c,1);
                k++;
            }
            else map.put(c,map.get(c)+1);
        }
        for(int start=0, end=0;start<s.length();start++){
            // find enough chars
            for(;k>0 && end<s.length();end++){
                char c = s.charAt(end);
                if(map.containsKey(c)){
                    int count = map.get(c);
                    map.put(c,count-1);
                    if(count==1){
                        k--;
                    }
                }
            }
            if(k>0) break;
            // refine start
            for(;start<s.length();start++){
                char c = s.charAt(start);
                if(map.containsKey(c)){
                    int count = map.get(c);
                    map.put(c,count+1);
                    if(count==0) {
                        k++;
                        break;
                    }
                }
            }
            if(minL>end-start){
                minL=end-start;
                minStart=start;
                minEnd=end;
            }
        }
        return s.substring(minStart,minEnd);
    }
}

// 077
public class Solution {
    private List<List<Integer>> ret = new ArrayList<>();
    private List<Integer> list = new ArrayList<>();
    private int n;
    private int k;
    private boolean[] used;
    public List<List<Integer>> combine(int n, int k) {
        this.n=n;
        this.k=k;
        this.used = new boolean[n];
        help(0);
        return ret;
    }

    void help(int j){
        for(int i=0;i<n;i++){
            if(used[i]||i<j) continue;
            list.add(i+1);
            used[i]=true;
            if(list.size()==k) ret.add(new ArrayList(list));
            else help(i);
            list.remove(list.size()-1);
            used[i]=false;
        }
    }
}

// 078
public class Solution {
    private List<List<Integer>> ret = new ArrayList<>();
    private List<Integer> list = new ArrayList<>();
    private boolean [] used;

    public List<List<Integer>> subsets(int[] nums) {
        this.used = new boolean[nums.length];
        this.ret.add(this.list);
        help(nums,0);
        return this.ret;
    }

    void help(int[] nums,int j){
        for(int i=0;i<nums.length;i++){
            if(used[i]||i<j) continue;
            list.add(nums[i]);
            used[i]=true;
            ret.add(new ArrayList(list));
            help(nums,i);
            list.remove(list.size()-1);
            used[i]=false;
        }
    }
}

//079
public class Solution {
    private int i,j,m,n;
    private boolean[][] used;
    private char[][] board;
    private String word;

    public boolean exist(char[][] board, String word) {
       this.board=board;
       this.word=word;
       this.m=board.length;
       this.n=board[0].length;
       used = new boolean[m][n];
       for(this.i=0;i<m;i++){
            for(this.j=0;j<n;j++){
                if(board[i][j]==word.charAt(0)){
                    used[i][j]=true;
                    if(searchAround(1)) return true;
                    used[i][j]=false;
                }
            }
        }
        return false;
    }
    boolean searchAround(int pos){
        if(pos==word.length()) return true;
        if(i>0&&check(word.charAt(pos),i-1,j)){
            used[--i][j]=true;
            if(searchAround(pos+1)) return true;
            used[i++][j]=false;
        }  if(i<m-1&&check(word.charAt(pos),i+1,j)){
            used[++i][j]=true;
            if(searchAround(pos+1)) return true;
            used[i--][j]=false;
        }  if(j>0&&check(word.charAt(pos),i,j-1)){
            used[i][--j]=true;
            if(searchAround(pos+1)) return true;
            used[i][j++]=false;
        }  if(j<n-1&&check(word.charAt(pos),i,j+1)){
            used[i][++j]=true;
            if(searchAround(pos+1)) return true;
            used[i][j--]=false;
        }
        return false;
    }
    boolean check(char c,int x, int y){
        return board[x][y]==c&&!used[x][y];
    }
}

// 081
public class Solution {
    public boolean search(int[] nums, int target) {
        if(nums.length==0) return false;
        if(nums.length==1) return nums[0]==target;
        int i=0,j=nums.length-1;
        while(i<j){//----------------------<=
            int mid = (i+j)/2;//----------
            if(nums[mid]==target) return true;
            if(nums[i]==target) return true;
            if(nums[j]==target) return true;
            if(nums[mid]>nums[i]){
                if(target>nums[mid]) i=mid+1;
                else if(target>=nums[i]) j=mid-1;
                else i=mid+1;
            }
            else if(nums[mid]<nums[i]){
                if(target<nums[mid]) j=mid-1;
                else if(target<=nums[j]) i=mid+1;
                else j=mid-1;
            }
            else{
                if(nums[j]!=nums[i]) i=mid+1;
                else {i++;j--;}
            }
        }
        return false;
    }
}

// 082
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode ret = new ListNode(head.val-1),prev=head,curr=head,curr_=ret,prev_;
        ret.next = head;
        while(prev!=null){
            if(curr!=null) curr=curr.next;
            if(curr==null||curr.val!=prev.val){
                prev_ = curr_;
                curr_= prev;
                prev_.next=curr_;
            }
            else{
                while(curr!=null&&curr.val==prev.val){
                    prev=curr;
                    curr=curr.next;
                }
            }
            prev=curr;
        }
        curr_.next=prev;
        return ret.next;
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

// 084
public class Solution {
    public int largestRectangleArea(int[] heights) {
        int ret = 0;
        Stack<Integer> s = new Stack<>();
        for(int i=0;i<=heights.length;i++){
            int h = (i==heights.length?0:heights[i]);
            while(!s.empty()&&h<heights[s.peek()]){
                ret = Math.max(ret, heights[s.pop()] * (s.empty()?i:i-1-s.peek()) );
            }
            if(i+2>heights.length||heights[i]!=heights[i+1]) s.push(i);
        }
        return ret;
    }
}

// 085
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m==0) return 0;
        int n = matrix[0].length, ret = 0;
        int[] h = new int[n+1];
        for(int row = 0; row <m ; row++){
            Stack<Integer> s = new Stack<>();
            for(int i = 0;i<=n;i++){
                h[i] = (i!=n&&matrix[row][i] == '1'?h[i]+1:0);
                int t = (i==n?0:h[i]);
                while(!s.empty()&&h[s.peek()]>t){
                    int p = s.pop();
                    ret = Math.max(ret,h[p] * (s.empty()?i:i-1-s.peek()));
                }
                s.push(i);
            }
        }
        return ret;
    }
}

// 086
public class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode ret=new ListNode(0),curr=ret,last,prev;
        ret.next=head;
        while(curr.next!=null){
            if(curr.next.val>=x) break;
            curr=curr.next;
        }
        last = curr;
        prev=curr;
        curr=curr.next;
        while(curr!=null){
            if(curr.val<x){
                prev.next=curr.next;
                curr.next=last.next;
                last.next=curr;
                last=last.next;
                curr=prev.next;
            }
            else{
                prev=curr;
                curr=curr.next;
            }
        }
        return ret.next;
    }
}

// 087
public class Solution {
    private int len;
    private boolean[][][] dp;
    private boolean[][][] visited;
    private String s1,s2;

    public boolean isScramble(String s1, String s2) {
        this.s1=s1;
        this.s2=s2;
        this.len = s1.length();
        this.dp = new boolean[len][len][len+1];
        this.visited = new boolean[len][len][len+1];
        return  help(0,0,len);
    }

    boolean help(int i,int j,int l){
        if(!visited[i][j][l]) {
            boolean res = false;
            if(l==0) res = true;
            else if(l==1){
                res = s1.charAt(i)==s2.charAt(j);
            }
            else{
                for(int t = 1,s=l-1;t<l&&res==false;t++,s--){
                    res = help(i,j,t) && help(i+t,j+t,s) || help(i,j+s,t) && help(i+t,j,s);
                }
            }
            dp[i][j][l] = res;
            visited[i][j][l] = true;
        }
        return dp[i][j][l];
    }
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

// 089
public class Solution {
    public List<Integer> grayCode(int n) {
        int m = pow2(n),end=1;

        List<Integer> ret = new ArrayList<>();
        ret.add(0);
        for(int i=0;i<n;i++){
                for(int k=end-1;k>=0;k--){
                    ret.add(pow2(i)+ret.get(k));
                }
                end*=2;
        }
        return ret;
    }
    int pow2(int p){
        int ret = 1;
        while(p-->0) ret*=2;
        return ret;
    }
}

// 090
public class Solution {
    private List<List<Integer>> ret;
    private List<Integer> list;
    private int n;
    private boolean[] used;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        this.ret = new ArrayList<>();
        this.list = new ArrayList<>();
        this.n = nums.length;
        if(n==0) return ret;
        this.used = new boolean[n];
        Arrays.sort(nums);
        help(nums,0);
        return this.ret;
    }

    void help(int[] nums,int pos){
        int prev = nums[0]-1;
        ret.add(new ArrayList<>(list));
        for(int i=pos;i<n;i++){
            if(used[i] || prev==nums[i]) continue;
            prev=nums[i];
            used[i]=true;
            list.add(nums[i]);
            help(nums,i+1);
            used[i]=false;
            list.remove(list.size()-1);
        }
    }
}

// 091
public class Solution {
    public int numDecodings(String s) {
        if(s.length()==0||s.charAt(0)=='0') return 0;
        int[] ret = new int[s.length()];
        for(int i=0;i<s.length();i++){
            int c = s.charAt(i)-48;
            // current number
            ret[i]=c==0?0:(i==0?1:ret[i-1]);
            // current + previous
            ret[i]+=(i==0||s.charAt(i-1)-48==0||(s.charAt(i-1)-48)*10+c>26)?0:(i==1?1:ret[i-2]);

        }
        return ret[s.length()-1];
    }
}

// 092
public class Solution {
    private List<String> ret;
    private StringBuilder sb = new StringBuilder();

    public List<String> restoreIpAddresses(String s) {
        ret = new ArrayList<>();
        help(s,0,0);
        return this.ret;
    }

    void help(String s,int pos,int lv){
        if(lv==4){
            if(s.length()!=pos)return;
            else ret.add(sb.toString());
        }
        for(int i=pos+1;i<=s.length()&&i<=pos+3;i++){
            if(i>pos+1&&s.charAt(pos)=='0') return;
            String sub = s.substring(pos,i);
            int num = Integer.parseInt(sub);
            if(num>=0&&num<256){
                sb.append(sub+(lv==3?"":"."));
                help(s,i,lv+1);
                sb.delete(sb.length()-sub.length()-(lv==3?0:1),sb.length());
            }
        }
    }
}

// 095
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        if(n==0) return new ArrayList<>();
        return help(0,n);
    }
    List<TreeNode> help(int start,int end){
        List<TreeNode> ret = new ArrayList<>(),left,right;
        if(start==end) {
            ret.add(null);
            return ret;
        }
        for(int i=start;i<end;i++){
            TreeNode node = new TreeNode(i+1);
            left = help(start,i);
            right = help(i+1,end);
            for(TreeNode l:left){
                for(TreeNode r:right){
                    node.left=l;
                    node.right=r;
                    ret.add(clone(node));
                }
            }
        }
        return ret;
    }

    TreeNode clone(TreeNode n){
        if(n==null) return null;
        TreeNode node = new TreeNode(n.val);
        node.left = clone(n.left);
        node.right = clone(n.right);
        return node;
    }
}

// 096
public class Solution {
    public int numTrees(int n) {
        int [] ret = new int[n+1];
        ret[0]=1;
        ret[1]=1;
        for(int i=2;i<=n;i++){
            for(int j=0;j<i;j++){
                ret[i]+=ret[j]*ret[i-1-j];
            }
        }
        return ret[n];
    }
}

// 097
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()) return false;
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;
        for(int t = 1;t <= s1.length(); t++){dp[t][0] = dp[t-1][0] & (s1.charAt(t-1) == s3.charAt(t-1));}
        for(int t = 1;t <= s2.length(); t++){dp[0][t] = dp[0][t-1] & (s2.charAt(t-1) == s3.charAt(t-1));}
        for(int t = 2;t <= s3.length(); t++){
            for(int i=1,j=t-i;i<t;i++,j--){
                if(i<=s1.length()&&j<=s2.length()){
                    dp[i][j] = dp[i-1][j]&&s1.charAt(i-1)==s3.charAt(t-1)
                             ||dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(t-1);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}

// 098
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isValidBST(TreeNode root) {
        return help(root,(long) Integer.MIN_VALUE-1,(long)Integer.MAX_VALUE+1);
        // if(root==null) return true;
        // if(isValidBST(root.left)&&isValidBST(root.right))
        //     if(root.left==null||big(root.left)<root.val)
        //         if(root.right==null||small(root.right)>root.val)
        //             return true;
        // return false;
    }

    boolean help(TreeNode root, long lb, long ub){
        if(root==null) return true;
        if(root.val>lb&&root.val<ub) return help(root.left,lb,root.val)&&help(root.right,root.val,ub);
        return false;
    }

    int big(TreeNode node){
        if(node.right==null) return node.val;
        else return big(node.right);
    }

    int small(TreeNode node){
        if(node.left==null) return node.val;
        else return small(node.left);
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
