# 001
class Solution(object):
    def twoSum(self, nums, target):
        maps={}
        for i in xrange(len(nums)):
            if nums[i] in maps:return [maps[nums[i]],i]
            maps[target-nums[i]]=i

    def dummy(nums,target):
        for j in xrange(len(nums)):
            for i in xrange(j):
                if nums[i]+nums[j]==target:
                    return [i,j]

# 002

class Solution(object):
    def addTwoNumbers(self, l1, l2):
        s = l1.val+l2.val
        one=s/10
        curr = head = ListNode(s%10)
        while l1.next or l2.next:
            if l1.next is None:l1.next=ListNode(0)
            if l2.next is None:l2.next=ListNode(0)
            l1,l2=l1.next,l2.next
            s = l1.val+l2.val+one
            one=s/10
            curr.next=ListNode(s%10)
            curr=curr.next
        if one:curr.next=ListNode(one)
        return head

# 003
class Solution(object):
    def lengthOfLongestSubstring(self, s):
        map,ret,start={},0,0
        for i,v in enumerate(s):
            if v in map and map[v]>=start:
                t = i-start
                ret=t if t>ret else ret
                start=map[v]+1
            map[v]=i
        ret=len(s)-start if len(s)-start>ret else ret
        return ret

# 004
class Solution(object):
    def findMedianSortedArrays(self, nums1, nums2):
        m,n=map(len,[nums1,nums2])
        k=(m+n)/2
        if (n+m)%2:
            return self.helper(nums1,nums2,m,n,0,0,k+1)
        else:
            return (self.helper(nums1,nums2,m,n,0,0,k)+self.helper(nums1,nums2,m,n,0,0,k+1))/2.0
    def helper(self,nums1,nums2,m,n,a,b,k):
        if m>n:
            return self.helper(nums2,nums1,n,m,b,a,k)
        if m==0:
            return nums2[b+k-1]
        if k==1:
            return nums1[a] if nums1[a]<nums2[b] else nums2[b]
        ka=k/2 if k/2<m else m
        kb=k-ka
        if nums1[a+ka-1]>nums2[b+kb-1]:
            return self.helper(nums1,nums2,m,n-kb,a,b+kb,k-kb)
        else:
            return self.helper(nums1,nums2,m-ka,n,a+ka,b,k-ka)

# 005
class Solution(object):
    def longestPalindrome(self, s):
        length=center=0
        for i in xrange(1,len(s)+1):
            tempC=((len(s)-1)>>1)+(-(i>>1) if i&1 else i>>1)
            if tempC-((length-1)>>1)<0 or tempC+(length>>1)>=len(s):break
            r=0
            while tempC-r-1>=0 and tempC+r+1<len(s) and s[tempC+r+1]==s[tempC-r-1]:
                r+=1
            if length<(r<<1)+1:
                length,center=(r<<1)+1,tempC
            r=0
            while tempC-r>=0 and tempC+r+1<len(s) and s[tempC+r+1]==s[tempC-r]:
                r+=1
            if length<(r<<1):
                length,center=r<<1,tempC
        return s[center-((length-1)>>1):center+(length>>1)+1]

