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

//205
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length()) return false;
        Map<Character,Character> map1 = new HashMap<>(),map2 = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char c1 = s.charAt(i),c2=t.charAt(i);
            if(!map1.containsKey(c1)) map1.put(c1,c2);
            if(!map2.containsKey(c2)) map2.put(c2,c1);
            if(!map1.get(c1).equals(c2)||!map2.get(c2).equals(c1)) return false;
        }
        return true;
    }
}

//206
public class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode curr=head,next=head.next,prev;
        head.next=null;
        while(next!=null){
            prev=curr;
            curr=next;
            next=next.next;
            curr.next=prev;
        }
        return curr;
    }
}

//217
public class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> m = new HashSet<>();
        for(int i:nums){
            if(!m.add(i)) return true;
        }
        return false;
    }
}

//219
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> m = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(!m.containsKey(nums[i])||m.get(nums[i])+k<i) m.put(nums[i],i);
            else return true;
        }
        return false;
    }
}

//223
public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int res = area(A,B,C,D)+area(E,F,G,H);
        if(E>=C||F>=D||G<=A||H<=B) return res;
        else return res-area(Math.max(A,E),Math.max(B,F),Math.min(C,G),Math.min(D,H));
    }
    int area(int a,int b,int c,int d){
        return Math.abs((a-c)*(b-d));
    }
}


//225
class MyStack {
    private Queue<Integer> q = new LinkedList<>();
    // Push element x onto stack.
    public void push(int x) {
        Queue<Integer> p = new LinkedList<>();
        p.offer(x);
        while(!empty()) p.offer(q.poll());
        q=p;
    }

    // Removes the element on top of the stack.
    public void pop() {
        q.poll();
    }

    // Get the top element.
    public int top() {
        return q.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q.isEmpty();
    }
}

//226
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root!=null){
            invertTree(root.left);
            invertTree(root.right);
            TreeNode tmp=root.left;
            root.left=root.right;
            root.right=tmp;
        }
        return root;
    }public class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n<=0) return false;
        long i=1;
        while(n>1){
            if((n&1)>0)return false;
            n>>=1;
        }
        return true;
    }
}
}
//231
public class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n<=0) return false;
        long i=1;
        while(n>1){
            if((n&1)>0)return false;
            n>>=1;
        }
        return true;
    }
}

//232
class MyQueue {
    private Stack<Integer> q = new Stack<>();
    // Push element x to the back of queue.
    public void push(int x) {
        Stack<Integer> tmp = new Stack<>();
        while(!empty()) tmp.push(q.pop());
        q.push(x);
        while(!tmp.isEmpty())q.push(tmp.pop());
    }

    // Removes the element from in front of queue.
    public void pop() {
        q.pop();
    }

    // Get the front element.
    public int peek() {
        return q.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return q.isEmpty();
    }
}

//234
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null||head.next==null) return true;
        ListNode curr=head.next,next=curr.next,prev=head,fast=curr.next;
        int n=1;
        while(fast!=null&&fast.next!=null) {
            fast=fast.next.next;++n;
            curr.next=prev;
            prev=curr;
            curr=next;
            next=next.next;
        }

        if(fast!=null) curr=next;
        for(int i=0;i<n;i++){
            if(curr.val!=prev.val) return false;
            curr=curr.next;
            prev=prev.next;
        }
        return true;
    }
}

//235
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;
        if(p.val>q.val) return lowestCommonAncestor(root,q,p);
        if(root.val>=p.val&&root.val<=q.val) return root;
        if(q.val<root.val) return lowestCommonAncestor(root.left,p,q);
        return lowestCommonAncestor(root.right,p,q);

    }
}

//237
public class Solution {
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        node.next=node.next.next;
    }
}

//242
public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s==null&&t==null) return true;
        if(s==null||t==null) return false;
        int[] m = new int[256];
        for(char c:s.toCharArray()){
            m[c-0]++;
        }
        //System.out.print(m);
        for(char c:t.toCharArray()){
           if(m[c-0]--==0) return false;
        }
        for(int i:m){
            if(i!=0)return false;
        }
        return true;
    }
}

//257
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        if(root!=null) help(root,ret,Integer.toString(root.val));
        return ret;
    }
    void help(TreeNode node, List<String> ret,String s){
        if(node.left!=null) help(node.left,ret,s+"->"+node.left.val);
        if(node.right!=null) help(node.right,ret,s+"->"+node.right.val);
        if(node.left==null&&node.right==null) ret.add(s);
    }
}

//258
public class Solution {
    public int addDigits(int num) {
        while(num>9){
            int tmp=num,res=0;
            while(tmp>0){
                res+=tmp%10;
                tmp/=10;
            }
            num=res;
        }
        return num;
    }
}

//263
public class Solution {
    public boolean isUgly(int num) {
        if(num<1) return false;
        while(num%2==0) num/=2;
        while(num%5==0) num/=5;
        while(num%3==0) num/=3;
        return num==1;
    }
}

// 278
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        long a=1,b=n;
        while(a<b) {
            long t = (a+b)/2;
            if(isBadVersion((int)t)) b=t;
            else a=t+1;
        }
        return (int)a;
    }
}

// 283
public class Solution {
    public void moveZeroes(int[] nums) {
        int z=0;
        for(int i=0;i<nums.length+z;i++){
            if(i<nums.length){
                if(nums[i]==0) z++;
                else nums[i-z]=nums[i];
            }
            else nums[i-z]=0;
        }
    }
}

//290
public class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> m = new HashMap<>();
        int i=0,j=0;
        char key;
        String val;
        while(i<pattern.length()&&j<str.length()){
            key=pattern.charAt(i++);
            int end=j+1;
            while(end<str.length()&&str.charAt(end)!=' ') end++;
            val=str.substring(j,end);
            j=end+1;
            if(!m.containsKey(key)&&!m.containsValue(val)) m.put(key,val);
            else if(m.containsKey(key)&&!m.get(key).equals(val)||!m.containsKey(key)) return false;
        }
        if(i<pattern.length()||j<str.length()) return false;
        return true;
    }
}

//299
public class Solution {
    public String getHint(String secret, String guess) {
        if(secret==null) return "0A0B";
        int a=0,b=0;
        int[] m = new int[10];
        for(int i=0;i<secret.length();i++){
            char c = guess.charAt(i);
            if(secret.charAt(i)==c) a++;
            else m[secret.charAt(i)-'0']++;
        }
        for(int i=0;i<secret.length();i++){
            char c = guess.charAt(i);
            if(secret.charAt(i)!=c&&m[c-'0']-->0) b++;
        }

        return a+"A"+b+"B";
    }
}
