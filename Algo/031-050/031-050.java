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
