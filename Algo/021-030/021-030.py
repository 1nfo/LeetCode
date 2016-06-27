#021
class Solution(object):
    def mergeTwoLists(self, l1, l2):
        if l1 is None:return l2
        if l2 is None:return l1
        curr=head=ListNode(0)
        while l1 is not None and l2 is not None:
            if l1.val>l2.val:
                curr.next=l2
                l2=l2.next
            else:
                curr.next=l1
                l1=l1.next
            curr=curr.next
        if l1 or l2: curr.next=l1 or l2
        return head.next

# 022
class Solution(object):
    def generateParenthesis(self, n):
        ret=[[""]]
        for i in xrange(n):
            tmp=[]
            for j in xrange(i+1):
                for a in ret[j]:
                    for b in ret[i-j]:
                        tmp.append("("+a+")"+b)
            ret.append(tmp)
        return ret[n]

## 023
class Solution(object):
    def mergeKLists(self, lists):
        single=[]
        for i in xrange(len(lists)-1,-1,-1):
            if not lists[i]:lists.pop(i)
        if len(lists)==0:return None
        while len(lists)>1:
            tmp=[]
            for i in xrange(len(lists)/2):
                tmp.append(self.mergeTwo(lists[i*2],lists[i*2+1]))
            if len(lists)%2:tmp.append(lists[-1])
            lists=tmp
        return lists[0]
    def mergeTwo(self,a,b):
        curr=head=ListNode(0)
        while a and b:
            if a.val>b.val:curr.next,b=b,b.next
            else:curr.next,a=a,a.next
            curr=curr.next
        if a or b:curr.next=a or b
        return head.next

# 024


#  025
class Solution(object):
    def reverseKGroup(self, head, k):
        if k==1 or head is None or head.next is None:return head
        ret=ListNode(0)
        ret.next=head
        tmp=ret
        while tmp.next:
            end=tmp.next
            for i in xrange(k-1):
                end=end.next
                if end is None: return ret.next
            prev,curr,next=tmp,tmp.next,tmp.next.next
            tmp.next=end
            curr.next=end.next
            tmp=curr
            for i in xrange(k-1):
                prev,curr,next=curr,next,next.next
                curr.next=prev
        return ret.next
