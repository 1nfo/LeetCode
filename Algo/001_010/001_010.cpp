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
