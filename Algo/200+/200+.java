// 202
public class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while(n!=1){
            set.add(n);
            int tmp=0;
            while(n>0){
                int rem = (n%10);
                tmp+=rem*rem;
                n/=10;
            }
            if(set.contains(tmp)) return false;
            n=tmp;
        }
        return true;
    }
}

//203
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode ret = new ListNode(0),p=ret;
        ret.next=head;
        while(p.next!=null){
            if(p.next.val==val) p.next=p.next.next;
            else p=p.next;
        }
        return ret.next;
    }
}

//204
public class Solution {
    public int countPrimes(int n) {
        if(n<3) return 0;
        boolean[] notPrimes = new boolean[n];
        int count=n/2;
        for(int p=3;p*p<n;p+=2){
            if(notPrimes[p]) continue;
            for(int i=p*p;i<n;i+=p*2){
                if(!notPrimes[i]){
                    notPrimes[i]=true;
                    count--;
                }
            }
        }
        return count;
    }
}
