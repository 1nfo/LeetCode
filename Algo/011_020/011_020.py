# 011
class Solution(object):
    def maxArea(self, height):
        i,j,ret=0,len(height)-1,0
        while i<j:
            a,b=height[i],height[j]
            ret = max([ret,min([a,b])*(j-i)])
            if a<b:i+=1
            else:j-=1
        return ret
    def dummy():
        from collections import defaultdict
        hash = defaultdict(list)
        for i,v in enumerate(height):
              hash[v].append(i)
        ret,lines=0,[]
        for v in sorted(hash.keys(),reverse=True):
            tmp=lines+hash[v]
            lines=[min(tmp),max(tmp)]
            if len(lines)>1:
                new = v*(max(tmp)-min(tmp))
                ret=ret if new<=ret else new
        return ret ;

# 012
class Solution(object):
    def intToRoman(self, num):
        CHAR,ret="IVXLCDM__",""
        for i in xrange(3,-1,-1):
            r,num=num/(10**i),num%10**i
            if r%5==4:ret+=CHAR[i*2]+CHAR[i*2+1+r/5]
            elif r:ret+=CHAR[i*2+1]*(r/5)+CHAR[i*2]*(r%5)
        return ret
# 013
class Solution(object):
    def romanToInt(self, s):
        M={"I":1,"V":5,"X":10,"L":50,"C":100,"D":500,"M":1000}
        ret=i=0
        while i<len(s):
            if i+1<len(s) and M[s[i+1]]>M[s[i]]:ret-=M[s[i]]
            else:ret+=M[s[i]]
            i+=1
        return ret

# 014
class Solution(object):
    def longestCommonPrefix(self, strs):
        if not strs or not strs[0]: return ""
        for i in xrange(len(strs[0])):
            c=strs[0][i]
            for s in strs[1:]:
                if i>=len(s) or c!=s[i]:
                    return strs[0][:i]
        return strs[0]

# 015
class Solution(object):
    def threeSum(self, nums):
        if len(nums)<3:return []
        ret=[]
        nums.sort()
        prev=[]
        for i in xrange(len(nums)):
            if not nums[i] in prev:
                prev.append(nums[i])
                map={}
                for k in xrange(i+1,len(nums)):
                    if nums[k] not in map: map[-nums[i]-nums[k]]=k
                    elif map[nums[k]]==0:continue
                    else:
                        ret.append([nums[i],nums[map[nums[k]]],nums[k]])
                        map[nums[k]]=0
        return ret

# 016
class Solution(object):
    def threeSumClosest(self, nums, target):
        ret=sum(nums[:3])
        if ret==target:return ret;
        nums.sort()
        for i in xrange(len(nums)):
            l,r=i+1,len(nums)-1
            while l<r:
                diff=nums[i]+nums[l]+nums[r]-target
                if abs(ret-target)>abs(diff):ret=nums[i]+nums[l]+nums[r]
                if diff==0:return target
                elif diff<0:l+=1
                else:r-=1
        return ret

# 017
class Solution(object):
    def __init__(self):
        self.map={"2":"abc","3":"def","4":"ghi","5":"jkl","6":"mno","7":"pqrs","8":"tuv","9":"wxyz"}
        self.ret=[""]

    def letterCombinations(self, digits):
        if not digits:return []
        for d in digits:
            self.helper(d)
        return self.ret

    def helper(self,d):
        ret=[]
        for s in self.map[d]:
            for r in self.ret:
                ret.append(r+s)
        self.ret=ret

# 018
class Solution(object):
    def fourSum(self, nums, target):
        if len(nums)<4:return [];
        ret=[]
        nums.sort()
        prevI=nums[0]-1
        for i in xrange(len(nums)):
            if prevI!=nums[i] and nums[i]*4<=target:
                prevI,prevJ=nums[i],nums[i]-1
                for j in xrange(i+1,len(nums)):
                    if prevJ!=nums[j] and nums[i]+nums[j]*3<=target:
                        prevJ=nums[j]
                        map={}
                        for k in xrange(j+1,len(nums)):
                            if nums[k] in map:
                                if map[nums[k]]:
                                    ret.append([nums[i],nums[j],nums[map[nums[k]]],nums[k]])
                                    map[nums[k]]=0
                            else:map[target-nums[k]-nums[i]-nums[j]]=k
        return ret

# 019
class Solution(object):
    def removeNthFromEnd(self, head, n):
        count,tmp=0,head
        while tmp:count,tmp=count+1,tmp.next
        tmp=ListNode(None)
        tmp.next=head
        prev,curr=tmp,head
        while n<count:
            count-=1
            if curr is None:return head
            prev,curr=prev.next,curr.next
        prev.next=curr.next
        return tmp.next;

#  020
class Solution(object):
    def isValid(self, s):
        stack=[]
        IN={"(":1,"[":2,"{":3}
        OUT={")":1,"]":2,"}":3}
        for i in s:
            if i in IN:stack.append(i)
            elif i in OUT:
                if not stack or IN[stack.pop()]!=OUT[i]:return False
        return stack==[]
