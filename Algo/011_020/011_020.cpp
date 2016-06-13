// 011
class Solution {
public:
    int maxArea(vector<int>& height) {
        int ret=0,i=0,j=height.size()-1;
        while (i<j)
            ret=max(ret,(j-i)*(height[i]<height[j]?height[i++]:height[j--]));
        return ret;
    }
};

// 012
class Solution {
public:
    string intToRoman(int num) {
        string ret="",CHAR="IVXLCDM__";
        for(int i=3,r;i>=0;num%=(int)pow(10,i--)){
            r=num/pow(10,i);
            if (r%5==4) {
                ret+=CHAR[i*2];
                ret+=CHAR[i*2+1+r/5];
            }
            else{
                if(r/5) ret+=CHAR[i*2+1];
                cout<<i<<ret<<endl;
                while(r--%5>0) ret+=CHAR[i*2];
            }
        }
        return ret;
    }
};

//013
class Solution {
public:
    int romanToInt(string s) {
        int ret=0,i=0,tmp;
        char K[]={'I','V','X','L','C','D','M'};
        int V[]={1,5,10,50,100,500,1000};
        unordered_map<char,int> M;
        for (int i=0;i<strlen(K);i++) M[K[i]]=V[i];
        while(i<s.length()){
            tmp=M[s[i++]];
            if (i<s.length()&&M[s[i]]>tmp) ret-=tmp;
            else ret+=tmp;
        }
        return ret;
    }
};

// 014
class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if (strs.size()==0 ||strs[0].length()==0) return "";
        for (int i=0;i<strs[0].length();i++){
            auto c=strs[0][i];
            for (int j=1;j<strs.size();j++)
                if (strs[j].length()<=i || strs[j][i]!=c) return strs[0].substr(0,i);
        }
        return strs[0];
    }
};

// 015
class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>> ret;
        if (nums.size()<3) return ret;
        sort(nums.begin(),nums.end());
        for (int i=0;i<nums.size();i++)cout<<nums[i]<<",";
        int S=nums[0]-1;
        for(int i=0;i<nums.size();i++){
            if (nums[i]!=S){
                S=nums[i];
                int l=i+1,r=nums.size()-1,B=nums[nums.size()-1]+1;
                while(l<r){
                    if(nums[i]+nums[l]+nums[r]==0){
                        if (B!=nums[r]){
                            ret.push_back(vector<int>{nums[i],nums[l],nums[r]});
                            B=nums[r];
                        }
                        r--;l++;
                    }
                    else if (nums[i]+nums[l]+nums[r]>0) r--;
                    else l++;
                }
            }
        }
        return ret;
    }
};

// 016
class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        int ret=nums[0]+nums[1]+nums[2];
        if (ret==target) return target;
        sort(nums.begin(),nums.end());
        for (int i=0;i<nums.size();i++){
            int l=i+1,r=nums.size()-1;
            while (l<r){
                int v=nums[i]+nums[l]+nums[r],diff=v-target;
                if(abs(diff)<abs(ret-target)) ret=v;
                if (diff>0) r--;
                else if (diff<0) l++;
                else return target;
            }
        }
        return ret;

    }
};

// 017 runtime err!!!
class Solution {
    unordered_map<char,string> map;
    vector<string> *ret= new vector<string>;
    void helper(char d){
        vector<string> ret;
        for (int i=0;i<this->ret->size();i++){
            for (int j=0;j<this->map[d].length();j++){
                ret.push_back((*(this->ret))[i]+map[d][j]);
            }
        }
        this->ret=&ret;
    }
public:
    Solution(){
        string strs[]={"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        string num="23456789";
        for (int i=0;i<num.length();i++){
            this->map[num[i]]=strs[i];
        }
    }
    vector<string> letterCombinations(string digits) {
        if (digits.length()==0) return *(this->ret);
        this->ret->push_back("");
        for (int i=0;i<digits.length();i++){
            this->helper(digits[i]);
        }
        //cout<<(string)(*(this->ret))[0]<<endl;
        return *(this->ret);
    }
};

// 018
class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        vector<vector<int>> ret;
        sort(nums.begin(),nums.end());
        if (nums.size()<4 || nums[nums.size()-4]+nums[nums.size()-3]+nums[nums.size()-2]+nums[nums.size()-1]<target) return ret;
        int prevI=nums[0]-1,prevJ;
        for(int i=0;i<nums.size();i++){
            if(prevI!=nums[i] && nums[i]*4<=target){
                prevI=nums[i];
                prevJ=nums[0]-1;
                for(int j=i+1;j<nums.size();j++){
                    if (prevJ!=nums[j] && nums[i]+nums[j]*3<=target){
                        prevJ=nums[j];
                        int l=j+1,r=nums.size()-1;
                        while (l<r){
                            //cout<<i<<j<<l<<r<<endl;
                            int v=nums[i]+nums[j]+nums[l]+nums[r];
                            if(v>target)  {
                                r--;
                                while(l<r && nums[r]==nums[r+1]) r--;
                            }
                            else if(v<target) {
                                l++;
                                while(l<r && nums[l]==nums[l-1]) l++;
                            }
                            else{
                                cout<<"_"<<endl;
                                vector<int> tmp {nums[i],nums[j],nums[l++],nums[r--]};
                                ret.push_back(tmp);
                                while(l<r && nums[r]==nums[r+1]) r--;
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
};
