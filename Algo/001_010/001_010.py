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

# 006
class Solution(object):
    def convert(self, s, numRows):
        if numRows<2:return s
        ret=""
        for r in xrange(numRows):
            j=-r
            for i in range(r,len(s),numRows*2-2):
                ret+=s[i]
                j+=numRows*2-2
                if j<len(s) and r not in [0,numRows-1]:
                    ret+=s[j]
        return ret

    def faster4py():
        if numRows<2:return s
        n,m=numRows-1,2*numRows-2
        ret=['']*numRows
        for i in xrange(len(s)):
            r=n-abs(i%m-n)
            ret[r]+=s[i]
        return ''.join(ret)

    def dummy():
        if len(s)==0 or numRows==1:return s
        ret=[]
        seqs = [range(i,len(s),2*numRows-2) for i in range(2*numRows-2)]
        for i in seqs[0]:
            ret.append(s[i])
        for j in range(1,numRows-1):
            m=n=0
            while m<len(seqs[j]) or n<len(seqs[numRows*2-2-j]):
                if m<len(seqs[j]):
                    ret.append(s[seqs[j][m]])
                    m+=1
                if n<len(seqs[numRows*2-2-j]):
                    ret.append(s[seqs[numRows*2-2-j][n]])
                    n+=1
        for i in seqs[numRows-1]:
            ret.append(s[i])
        return ''.join(ret)

# 007
class Solution(object):
    def reverse(self, x):
        if x<0:return -self.reverse(-x)
        ret=0
        while x!=0:
            ret,x=ret*10+x%10,x/10
        if abs(ret) >= (1<<31)-1:return 0
        else:return ret

# 008
class Solution(object):
    def myAtoi(self, s):
        l,ret=s.strip(),0
        if len(l)==0: return 0
        digits = map(str,range(10))
        if l[0] == "-":i,flag=1,-1
        elif l[0] == "+":i,flag=1,1
        else:i,flag=0,1
        while i < len(l):
            if l[i] not in digits:break
            d=int(l[i])
            ret=ret*10+d
            i+=1
        ret*=flag
        if ret>=(1<<31):return (1<<31)-1
        if ret<-(1<<31):return -1<<31
        return ret

# 009
class Solution(object):
    def isPalindrome(self, x):
        if x<0:return False
        y,z=0,x
        while z>0:
            y=y*10+z%10
            z/=10
        return y==x

# 010
class Solution(object):
    def isMatch(self, s, p):
        p=self.opt(p)
        if '.' not in p and '*' not in p:return s==p
        i=j=0
        while i<len(p) and j<=len(s):
            if i+1<len(p) and p[i+1]=='*':
                k=j
                while k<=len(s):
                    if self.isMatch(s[k:],p[i+2:]): return True
                    if s[k:k+1]==p[i] or p[i]==".":k+=1
                    else:break
                return False
            elif p[i] == '.':
                if j>=len(s):return False
                j,i=j+1,i+1
            else:
                if s[j:j+1]!=p[i]:return False
                j,i=j+1,i+1
        if i+1<len(p):return reduce(lambda x,y:x&y,[p[t]=="*" for t in range(i+1,len(p),2)])
        return not(i<len(p) or j<len(s))

    def opt(self,pp):
        p=pp
        for i in xrange(len(p)):
            if p[i]=='*' and i+2<len(p) and p[i+2]=='*' and p[i-1]==p[i+1]:
                p = p[:i-1]+p[i+1:]
                break
        if pp==p: return p
        return self.opt(p)

    def cplusplus():
        p=self.opt(p)
        if len(p)==0: return len(s)==0
        if p[1:2] == '*':
            i=0
            while True:
                if self.isMatch(s[i:],p[2:]):return True
                if i>=len(s) or p[0] != s[i:i+1] and p[0] != '.':break
                i+=1
        elif p[0:1] == s[0:1] or (p[0] == '.' and s):
            return self.isMatch(s[1:],p[1:])
        return False
