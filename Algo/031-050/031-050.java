// 031
public class Solution {
    public void nextPermutation(int[] nums) {
        int lastPeak=0;
        for (int i=1;i<nums.length;i++){
            if (nums[i]>nums[i-1]) lastPeak=i;
        }
        if (lastPeak!=0) {
            int target=lastPeak;
            for (int i=lastPeak+1;i<nums.length;i++){
                if(nums[i]>nums[lastPeak-1] && nums[i]<nums[target]) target=i;
            }
            swap(nums,target,lastPeak-1);
        }
        sort(nums,lastPeak);
    }

    void sort(int[] nums, int start){
        if (start>=nums.length-1) return;
        for(int j=start;j<nums.length-1;j++){
            int min=j;
            for(int i=j+1;i<nums.length;i++){
                if(nums[i]<nums[min]) min=i;
            }
            if (j==min) continue;
            swap(nums,min,j);
        }
    }
    void swap(int[] nums,int i,int j){
        nums[j]^=nums[i];
        nums[i]^=nums[j];
        nums[j]^=nums[i];
    }
}
// 032
public class Solution {
    public int longestValidParentheses(String s) {
        int [] h = new int[s.length()];
        int count=0,res=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='(') count++;
            else if(count>0){
                h[i]=2+h[i-1];
                if(i>h[i]) h[i]+=h[i-h[i]];
                count--;
            }
            res=res>h[i]?res:h[i];
        }
        return res;
    }
}

// 033
public class Solution {
    public int search(int[] nums, int target) {
        if(nums.length==0) return -1;
        if(nums.length==1){
            if(nums[0]==target) return 0;
            return -1;
        }
        int a=0,b=nums.length,border=0,res=0;
        if(nums[0]<nums[nums.length-1]) return bs(nums,target,0,nums.length);
        if(target<nums[0]&&target>nums[nums.length-1]) return -1;
        while(a<b){
            border = (a+b)/2;
            if(nums[border-1]>nums[border]) break;
            if(nums[border]>nums[a]) a=border;
            else if(nums[border]<nums[b-1]) b=border;
        }
        if((target<=nums[nums.length-1])) res = bs(nums,target,border,nums.length);
        if(target>=nums[0]) res = bs(nums,target,0,border);
        return res;
    }
    int bs(int[] nums,int target,int l, int r){
        while(l<r){
            int mid = (l+r)/2;
            if(target==nums[mid]) return mid;
            else if(target<nums[mid]) r=mid;
            else if(target>nums[mid]) l=mid+1;
        }
        return -1;
    }
}

// 034
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int [] ret = {-1,-1};
        if (nums.length==0) return ret;
        int down=0,up=nums.length-1,p=(up+down)/2;
        while(nums[p]!=target){
            if (down>=up) return ret;
            if (nums[p]>target) up=p;
            else down=(p==down)?down+1:p;
            p=(up+down)/2;
        }
        int q=p;
        while(p>0 && nums[p-1]==target) p--;
        while(q<nums.length-1 && nums[q+1]==target) q++;
        ret[0]=p;
        ret[1]=q;
        return ret;
    }
}

// 035
public class Solution {
    public int searchInsert(int[] nums, int target) {
        if (target<=nums[0]) return 0;
        for (int i=1;i<nums.length;i++){
            if (target<=nums[i]) return i;
        }
        return nums.length;
    }
}

// 036
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i=0;i<9;i++){
            if (checkNot(board[i])) return false;
            char[] col=new char[9];
            for(int j=0;j<9;j++){
                col[j]=board[j][i];
            }
            if (checkNot(col)) return false;

        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                char[] sq = {board[i*3][j*3],board[i*3][j*3+1],board[i*3][j*3+2],
                            board[i*3+1][j*3],board[i*3+1][j*3+1],board[i*3+1][j*3+2],
                            board[i*3+2][j*3],board[i*3+2][j*3+1],board[i*3+2][j*3+2]};
                if (checkNot(sq)) return false;
            }
        }
        return true;
    }

    boolean checkNot(char[] nums){
        Set<Character> set = new HashSet<>();
        for(char c:nums){
            if (c=='.') continue;
            if (set.contains(c)) return true;
            set.add(c);
        }
        return false;
    }
}

// 037
public class Solution {
    public void solveSudoku(char[][] board) {
        if(board==null) return;
        solve(board);
    }
    boolean solve(char[][] board){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    for(int k=0;k<9;k++){
                        char chk = (char)('1'+k);
                        if(checkRow(board,i,chk)&&checkCol(board,j,chk)&&checkRnd(board,i,j,chk)){
                            board[i][j]=chk;
                            if(solve(board)) return true;
                            board[i][j]='.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    boolean checkRow(char [][] b,int row,char chk){
        for(char c :b[row]) if(c==chk) return false;
        return true;
    }
    boolean checkCol(char [][] b,int col, char chk){
        for(int i=0;i<9;i++) if(b[i][col]== chk) return false;
        return true;
    }
    boolean checkRnd(char [][] b,int x,int y,char chk){
        x/=3;
        y/=3;
        x*=3;
        y*=3;
        for(int xx=0;xx<3;xx++)
            for(int yy=0;yy<3;yy++)
                if(b[xx+x][yy+y]==chk) return false;
        return true;
    }
}

// 038
public class Solution {
    public String countAndSay(int n) {
        if (n==1) return "1";
        String ret="11";
        for(int i=2;i<n;i++){
            ret=help(ret);
        }
        return ret;

    }
    public String help(String n){
        StringBuilder ret = new StringBuilder();
        char prev = n.charAt(0),r;
        int count=1;
        for (int i=1;i<n.length();i++){
            r = n.charAt(i);
            if (r==prev) count++;
            else{
                ret.append(count);
                ret.append(prev);
                prev=r;
                count=1;
            }
        }
        ret.append(count);
        ret.append(prev);
        return ret.toString();
    }
}

//039
public class Solution {
    private List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        helper(candidates,target,0,new ArrayList<>());
        return this.ret;
    }

    private void helper(int[] c, int t, int start, List<Integer> list){
        if (t<0) return;
        if (t>0) {
            for(int i=start;i<c.length;i++){
                list.add(c[i]);
                helper(c,t-c[i],i,list);
                list.remove(list.size()-1);
            }
        }
        else{
            this.ret.add(new ArrayList(list));
        }
    }
}

// 040
public class Solution {
    private List<List<Integer>> ret = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        helper(candidates,target,0,new ArrayList<>());
        return this.ret;
    }
    public void helper(int[] c,int t,int start, List<Integer> list){
        //System.out.println(start);
        if (t<0) return;
        if (t>0){
            for(int i = start;i<c.length;i++){
                if(i>0&&c[i]==c[i-1]&&i>start) continue;
                list.add(c[i]);
                helper(c,t-c[i],i+1,list);
                list.remove(list.size()-1);
            }
        }
        else{
            this.ret.add(new ArrayList(list));
        }
    }
}

// 041
public class Solution {
    public int firstMissingPositive(int[] nums) {
        for(int i=0;i<nums.length;){
            if(nums[i]<=0||nums[i]>nums.length||nums[nums[i]-1]==nums[i]) i++;
            else if(nums[nums[i]-1]!=nums[i]) swap(nums, nums[i]-1,i);
        }
        int i;
        for(i=0;i<nums.length;i++){
            if(nums[i]!=i+1) break;
        }
        return i+1;
    }
    void swap(int[] A,int i,int j){
        A[i]^=A[j];
        A[j]^=A[i];
        A[i]^=A[j];
    }
}

// 042
public class Solution {
    public int trap(int[] height) {
        int begin=0,end=height.length-1,level=0;
        int total=0,block=0;
        while(begin<end){
            if(height[begin]<height[end]){
                level=level>height[begin]?level:height[begin];
                block+=height[begin++];
            }else{
                level=level>height[end]?level:height[end];
                block+=height[end--];
            }
            total+=level;
        }
        return total-block;
    }
}

//043
public class Solution {
    public String multiply(String num1, String num2) {
        int a = num1.length(),b=num2.length(), l = a+b-1;
        if (num1.equals("0")||num2.equals("0")) return "0";
        int [] p = new int[l];
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                p[i+j]+=((num1.charAt(i)-'0')*(num2.charAt(j)-'0'));
            }
        }
        if(l==1) return Integer.toString(p[0]);
        StringBuilder ret = new StringBuilder();
        int r=0;
        for(int i=l-1;i>=0;i--){
            r+=p[i];
            ret.append(r%10);
            r/=10;
        }
        if(r>0) ret.append(r);
        return ret.reverse().toString();
    }
}

//044
public class Solution {
    public boolean isMatch(String s, String p) {
        int m=s.length(),n=p.length();
        int i=0,j=0,a=-1,b=-1;
        if(n==0&&m==0) return true;
        if(n==0) return false;
        while(i<m){
            if(j<n&&(p.charAt(j)=='?'||p.charAt(j)==s.charAt(i))) {i++;j++;}
            else if(j<n&&p.charAt(j)=='*'){
                a=i+1;b=j++;
            }
            else if(a>=0){
                i=a;j=b;
            }
            else return false;
        }
        while(j<n) if(p.charAt(j++)!='*') return false;
        return true;
    }
}

//045
public class Solution {
    public int jump(int[] nums) {
        int reach = 0, count = 0,lastStep = 0;
        for(int i=0;i<nums.length-1;i++){
            reach = reach>i+nums[i]?reach:(nums[i]+i);
            if(i==lastStep) {count++;lastStep=reach;}
        }
        return count;
    }
}

// 046
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>>  ret = new ArrayList<>();
        for(int i=0;i<nums.length;i++) ret = helper(ret,nums[i]);
        return ret;
    }
    List<List<Integer>> helper(List<List<Integer>> param,int n){
        List<List<Integer>> ret = new ArrayList<>();
        if(param.size()==0){
            List<Integer> one=new ArrayList<>();
            one.add(n);
            ret.add(one);
            return ret;
        }
        for (int i=0;i<param.size();i++){
            List<Integer> inserted=param.get(i);
            for(int j=0;j<inserted.size()+1;j++){
                ret.add(insert(inserted,j,n));
            }
        }
        return ret;
    }
    List<Integer> insert(List<Integer> inserted, int pos, int n){
        List<Integer> ret = new ArrayList<>();
        for(int i=0,j=0;j<inserted.size()+1;j++){
            if(j==pos){
                ret.add(n);
            }
            else{
                ret.add(inserted.get(i++));
            }
        }
        return ret;
    }
}

// 047
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        boolean [] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtracing(nums,used,list,ret);
        return ret;
    }
    void backtracing(int[] nums,boolean[] used,List<Integer> list, List<List<Integer>> ret){
        if(list.size()==nums.length){
            ret.add(new ArrayList(list));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(used[i]||i>0&&nums[i]==nums[i-1]&&!used[i-1])continue;
            list.add(nums[i]);
            used[i]=true;
            backtracing(nums,used,list,ret);
            list.remove(list.size()-1);
            used[i]=false;
        }
    }
}
