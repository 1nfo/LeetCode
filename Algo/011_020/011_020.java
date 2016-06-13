// 011
public class Solution {
    public int maxArea(int[] height) {
        int ret=0,i=0,j=height.length-1;
        while (i<j)
            ret=Math.max(ret,(j-i)*(height[i]<height[j]?height[i++]:height[j--]));
        return ret;
    }
}

// 012
public class Solution {
    public String intToRoman(int num) {
        StringBuilder ret = new StringBuilder();
        String CHAR="IVXLCDM__";
        for (int i=3,r;i>=0;num%=Math.pow(10,i--)){
            r=num/(int)Math.pow(10,i);
            if (r%5==4) {
                ret.append(CHAR.charAt(i*2));
                ret.append(CHAR.charAt(i*2+1+r/5));
            }
            else {
                if (r/5==1) ret.append(CHAR.charAt(i*2+1));
                while(r--%5>0) ret.append(CHAR.charAt(i*2));
            }
        }
        return ret.toString();
    }
}

// 013
public class Solution {
    public int romanToInt(String s) {
        char[] K={'I','V','X','L','C','D','M'};
        int[] V={1,5,10,50,100,500,1000};
        HashMap<Character,Integer> M = new HashMap<>();
        for(int i=0;i<K.length;i++) M.put(K[i],V[i]);
        int ret=0,i=0,tmp;
        while (i<s.length()){
            tmp=M.get(s.charAt(i++));
            if (i<s.length()&&M.get(s.charAt(i))>tmp) ret-=tmp;
            else ret+=tmp;
        }
        return ret;
    }
}

//014
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length==0||strs[0].length()==0) return "";
        for (int i=0;i<strs[0].length();i++){
            char c=strs[0].charAt(i);
            for(int j=1;j<strs.length;j++)
                if (strs[j].length()<=i || strs[j].charAt(i)!=c) return strs[0].substring(0,i);
        }
        return strs[0];
    }
}

// 015
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums.length<3) return ret;
        Arrays.sort(nums);
        int smallest = nums[0] - 1;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != smallest) {
                smallest = nums[i];
                int l = i + 1, r = nums.length - 1, biggest = nums[nums.length - 1] + 1;
                while (l < r) {
                    if (nums[i] + nums[l] + nums[r] == 0){
                        if (biggest != nums[r]) {
                            ret.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                            biggest = nums[r];
                        }
                        r--;l++;
                    }
                    else if (nums[i] + nums[l] + nums[r] > 0) r--;
                    else l++;
                }
            }
        }
        return ret;
    }
}

//016
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int ret = nums[0]+nums[1]+nums[2];
        if (target==ret) return target;
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){
            int l=i+1,r=nums.length-1;
            while (l<r){
                int diff=nums[i]+nums[l]+nums[r]-target;
                ret=Math.abs(ret-target)>Math.abs(diff)?nums[i]+nums[l]+nums[r]:ret;
                if (diff<0) l++;
                else if (diff>0) r--;
                else return target;
            }
        }
        return ret;
    }
}

// 017
public class Solution {
    HashMap<Character,String> map=new HashMap<>();
    List<String> ret=new ArrayList<>();
    public Solution(){
        String[] strs={"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        String num = "23456789";
        for (int i=0;i<num.length();i++){
            this.map.put(num.charAt(i),strs[i]);
        }
    }
    void helper(char d){
        String t=map.get(d);
        List<String> ret=new ArrayList<>();
        for (int i=0;i<this.ret.size();i++){
            for (int j=0;j<t.length();j++){
                ret.add(this.ret.get(i)+t.charAt(j));
            }
        }
        this.ret=ret;
    }
    public List<String> letterCombinations(String digits) {
        if ("".equals(digits)) return this.ret;
        ret.add("");
        for(int i=0;i<digits.length();i++){
            this.helper(digits.charAt(i));
        }
        return this.ret;
    }
}

// 018
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret=new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length<4||nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3]+nums[nums.length-4]<target) return ret;
        int prevI=nums[0]-1,prevJ;
        for(int i=0;i<nums.length;i++){
            if (prevI!=nums[i]){
                prevI=nums[i];
                prevJ=nums[0]-1;
                for (int j=i+1;j<nums.length;j++){
                    if(prevJ!=nums[j]){
                        prevJ=nums[j];
                        int l=j+1,r=nums.length-1;
                        while (l<r){
                            int v=nums[i]+nums[j]+nums[l]+nums[r];
                            if (v>target){
                                r--;
                                while (l<r && nums[r]==nums[r+1]) r--;
                            }
                            else if (v<target){
                                l++;
                                while (l<r && nums[l]==nums[l-1]) l++;
                            }
                            else{
                                ret.add(new ArrayList(Arrays.asList(nums[i],nums[j],nums[l++],nums[r--])));
                                while (l<r && nums[r]==nums[r+1]) r--;
                                while (l<r && nums[l]==nums[l-1]) l++;
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
}
