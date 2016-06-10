// 001
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int,int> mp;
        for (int i=0;i<nums.size();i++){
            if( mp.count(nums[i])) return vector<int>(2) = {mp[nums[i]],i};
            mp[target-nums[i]]=i;
        }
        return vector<int>();
    }
};

// 002
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        int sum = l1->val+l2->val,one = sum/10;
        ListNode * curr,* head;
        curr=head = new ListNode(sum%10);
        while(l1->next || l2->next){
            if (NULL==l1->next) l1->next=new ListNode(0);
            if (NULL==l2->next) l2->next=new ListNode(0);
            l1=l1->next;
            l2=l2->next;
            sum = l1->val+l2->val+one;
            one = sum/10;
            curr->next=new ListNode(sum%10);
            curr=curr->next;
        }
        if (one>0) curr->next=new ListNode(one);
        return head;
    }
};

// 003
class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        unsigned long i,start=0,ret=0;
        unordered_map<char,int> mp;
        for (i=0;i<s.size();++i){
            if (mp.count(s[i]) && mp[s[i]]>=start){
                ret=max(ret,i-start);
                start=mp[s[i]]+1;
            }
            mp[s[i]]=i;
        }
        ret =max(ret,s.size() - start);
        return (int) ret;
    }
};

// 004
class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int m=nums1.size(),n=nums2.size();
        int k = (m+n)/2;
        if ((m+n)%2) return helper(nums1,nums2,m,n,0,0,k+1);
        else return (helper(nums1,nums2,m,n,0,0,k+1)+helper(nums1,nums2,m,n,0,0,k))/2.0;
    }
    int helper(vector<int>& nums1,vector<int>& nums2, int m, int n, int a, int b, int k){
        if (m>n) return helper(nums2,nums1,n,m,b,a,k);
        if (m==0) return nums2[b+k-1];
        if (k==1) return min(nums1[a],nums2[b]);
        int ka,kb;
        ka=min(k/2,m);
        kb=k-ka;
        if (nums1[a+ka-1]<nums2[b+kb-1]) return helper(nums1,nums2,m-ka,n,a+ka,b,k-ka);
        else return helper(nums1,nums2,m,n-kb,a,b+kb,k-kb);
    }
};

// 005
class Solution {
public:
    string longestPalindrome(string s) {
        int length=0,center=0,r=0,lenS=s.size();
        for (int i=0;i<lenS;i++){
            int tempC = (lenS-1)/2+((i+1)>>1)*(i&1?1:-1);
            if (tempC+(length>>2)>=lenS || tempC-(length-1)/2<0) break;
            r=0;
            while (tempC-r-1>=0 && tempC+r+1<lenS && s[tempC-r-1]==s[tempC+r+1]) r++;
            if (length<(r<<1)+1){
                length=(r<<1)+1;
                center=tempC;
            }
            r=0;
            while(tempC-r>=0 && tempC+r+1<lenS && s[tempC-r]==s[tempC+r+1]) r++;
            if (length<(r<<1)){
                length=r<<1;
                center=tempC;
            }
        }
        return s.substr(center-((length-1)>>1),length);
    }
};

// 006
class Solution {
public:
    string convert(string s, int numRows) {
        if (numRows<2) return s;
        string ret="";
        int c=(numRows-1)<<1;
        for (int r=0;r<numRows;r++){
            for (int i=r,j=c-r;i<s.length();i+=c,j+=c){
                ret+=s[i];
                if(r!=0&&r!=numRows-1&&j<s.length()) ret+=s[j];
            }
        }
        return ret;
    }
    string dummy(string s, int numRows){
        if (numRows<2) return s;
        vector<string> ret(numRows);
        for (int i = 0; i<ret.size(); i++){
            ret[i]="";
        }
        int n=numRows-1,m=n<<1,r;
        for (int i=0;i<s.length();i++){
            r=n-abs(i%(m)-n);
            ret[r].push_back(s[i]);
        }
        string str;
        for (int i = 0; i<ret.size(); i++){
            str+=ret[i];
        }
        return str;
    }
};

// 007

class Solution {
public:
    int reverse(int x) {
        if (x>0) return -reverse(-x);
        long long int ret=0;
        while (x<0){
            ret=ret*10+x%10;
            x/=10;
        }
        if (ret<1<<31) return 0;
        return ret;
    }
};

// 008 class Solution {
public:
    int myAtoi(string str) {
        if (str.length()==0) return 0;
        string l=str.substr(str.find_first_not_of(' '),str.find_last_not_of(" ")-str.find_first_not_of(' ')+1);
        long ret=0;
        if (l.length()==0) return 0;
        int i=0,flag=1;
        if (l[0]=='+') i++;
        else if (l[0]=='-') {i++;flag=-1;}
        while (i<l.length()){
            if (l[i]-'0'<0||l[i]-'0'>=10) break;
            ret=ret*10+(l[i++]-'0');
            if (ret*flag>INT_MAX) return INT_MAX;
            if (ret*flag<INT_MIN) return INT_MIN;
        }
        return (int)ret*flag;
    }
};
 // 009
class Solution {
public:
    bool isPalindrome(int x) {
        int y=0,z=x;
        if (z<0) return false;
        while (z>0){
            y=y*10+z%10;
            z/=10;
        }
        return y==x;
    }
};
