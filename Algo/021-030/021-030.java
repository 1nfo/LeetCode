// 021
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0),curr;
        curr=head;
        while (l1 != null && l2 != null){
            if (l1.val>l2.val) {curr.next=l2;l2=l2.next;}
            else {curr.next=l1;l1=l1.next;}
            curr=curr.next;
        }
        if (l1==null) curr.next=l2;
        else curr.next=l1;
        return head.next;
    }
}

// 022
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<List<String>> ret = new ArrayList<>();
        ret.add(new ArrayList<String>(Arrays.asList("")));
        for(int i=0;i<n;i++){
            List<String> tmp = new ArrayList<>();
            for(int j=0;j<=i;j++){
                for(String a:ret.get(j)){
                    for(String b:ret.get(i-j)){
                        tmp.add("("+a+")"+b);
                    }
                }
            }
            ret.add(tmp);
        }
        return ret.get(n);
    }
}

// 023
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length==0) return null;
        List<ListNode> ret=new ArrayList<>(Arrays.asList(lists));
        while(ret.size()>1){
            List<ListNode> tmp=new ArrayList<>();
            for(int i=0;i<ret.size()/2;i++){
                tmp.add(merge(ret.get(i*2),ret.get(i*2+1)));
            }
            if (ret.size()%2==1) tmp.add(ret.get(ret.size()-1));
            ret=tmp;
        }
        return ret.get(0);
    }
    ListNode merge(ListNode a,ListNode b){
        ListNode head=new ListNode(0),curr;
        curr=head;
        while(a!=null && b!=null){
            if(a.val<b.val){curr.next=a;a=a.next;}
            else{curr.next=b;b=b.next;}
            curr=curr.next;
        }
        if(a==null)curr.next=b;
        else curr.next=a;
        return head.next;
    }
}

// 024
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head==null || head.next==null) return head;
        ListNode ret = new ListNode(0),prev,curr,next;
        ret.next=head;
        prev=ret;
        curr=head;
        while (prev.next!=null){
            if (curr==null || curr.next==null) break;
            next=curr.next;
            curr.next=next.next;
            next.next=curr;
            prev.next=next;
            prev=curr;
            curr=prev.next;
        }
        return ret.next;
    }
}

// 025
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k==1) return head;
        ListNode ret=new ListNode(0), prev, curr, next, tmp, end;
        tmp=ret;
        ret.next=head;
        while (tmp.next != null){
            end=tmp.next;
            for (int i = 0;i<k-1;i++){
                end=end.next;
                if (end == null) return ret.next;
            }
            prev=tmp;
            curr=tmp.next;
            next=tmp.next.next;
            prev.next=end;
            curr.next=end.next;
            tmp=curr;
            for (int i=0;i<k-1;i++){
                prev=curr;
                curr=next;
                next=next.next;
                curr.next=prev;
            }
        }
        return ret.next;
    }
}

// 026
public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length<2) return nums.length;
        int j=0;
        for (int i=1;i<nums.length;i++){
            if (nums[j]!=nums[i]) nums[++j]=nums[i];
        }
        return ++j;
    }
}

//027
public class Solution {
    public int removeElement(int[] nums, int val) {
        int j=0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=val) nums[j++]=nums[i];
        }
        return j;
    }
}

// 028
public class Solution {
    public int strStr(String haystack, String needle) {
        int m=haystack.length(),n=needle.length();
        for (int i=0;i<m-n+1;i++){
            boolean isMatched=true;
            for(int j=0;j<n;j++){
                if (needle.charAt(j)!=haystack.charAt(j+i)) {
                    isMatched=false;
                    break;
                }
            }
            if (isMatched) return i;
        }
        return -1;
    }
}

//029
public class Solution {
    public int divide(int dividend, int divisor) {
        long a=dividend,b=divisor,i=0,c=1,b2;
        boolean pos=dividend>0&&divisor>0 || dividend<0&&divisor<0;
        a=a>0?a:-a;
        b2=b=b>0?b:-b;
        while (a>b){
            b<<=1;
            c<<=1;
        }
        while (b>=b2){
            if (a>=b){
                a-=b;
                i+=c;
            }
            b>>=1;
            c>>=1;
        }
        return (int)(pos?(i<Integer.MAX_VALUE?i:Integer.MAX_VALUE):(-i>Integer.MIN_VALUE?-i:Integer.MIN_VALUE));
    }
}
