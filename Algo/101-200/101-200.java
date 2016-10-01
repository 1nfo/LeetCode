//101
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        return compare(root.left,root.right);
    }
    boolean compare(TreeNode a,TreeNode b){
        if(a==null&&b==null) return true;
        if(a==null||b==null||a.val!=b.val) return false;
        return compare(a.left,b.right)&&compare(a.right,b.left);
    }
}

// 102
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        travel(root,ret,0);
        return ret;
    }
    void travel(TreeNode node, List<List<Integer>> ret,int level){
        if(node==null) return;
        if(level>=ret.size()) ret.add(new ArrayList<>());
        ret.get(level).add(node.val);
        travel(node.left,ret,level+1);
        travel(node.right,ret,level+1);
    }
}

// 103
public class Solution {
    private List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        add(root,0);
        int lv = 0;
        for(List list:ret){
            if(lv++%2==1) Collections.reverse(list);
        }
        return ret;
    }

    void add(TreeNode node, int level){
        if(node==null) return;
        if(ret.size()==level) ret.add(new ArrayList<>());
        ret.get(level).add(node.val);
        add(node.left,level+1);
        add(node.right,level+1);
    }
}

// 104
public class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        int a=maxDepth(root.left),b=maxDepth(root.right);
        return (a>b?a:b)+1;
    }
}

// 105
public class Solution {
    private int[] pre,in;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        pre = preorder;
        in = inorder;
        return help(0,pre.length,0,in.length);
    }

    TreeNode help(int pStart,int pEnd,int iStart,int iEnd){
        if(pStart==pEnd) return null;
        int target = pre[pStart],offset=0;
        TreeNode node = new TreeNode(target);
        for(int i=iStart;i<iEnd;i++) if(in[i]==target) {offset = i-iStart;break;}
        node.left = help(pStart+1,pStart+1+offset,iStart,iStart+offset);
        node.right = help(pStart+1+offset,pEnd,iStart+offset+1,iEnd);
        return node;
    }
}

// 106
public class Solution {
    private int[] in,post;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        in = inorder;
        post = postorder;
        return help(0,inorder.length,0,postorder.length);
    }

    TreeNode help(int iStart, int iEnd, int pStart, int pEnd){
        if(iStart == iEnd) return null;
        int target = post[pEnd-1],offset=0;
        TreeNode node = new TreeNode(target);
        for(int i=iStart;i<iEnd;i++){
            if(in[i]==target) {offset = i-iStart;break;}
        }
        node.left = help(iStart,iStart+offset,pStart,pStart+offset);
        node.right = help(iStart+offset+1,iEnd,pStart+offset,pEnd-1);
        return node;
    }
}

// 107
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        travel(root,ret,0);
        Collections.reverse(ret);
        return ret;
    }
    void travel(TreeNode node,List<List<Integer>> ret,int level){
        if(node==null) return;
        if(level==ret.size()) ret.add(new ArrayList<>());
        ret.get(level).add(node.val);
        travel(node.left,ret,level+1);
        travel(node.right,ret,level+1);
    }
}

// 108
public class Solution {
    private int[] nums;

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return help(0,nums.length);
    }

    TreeNode help(int start, int end){
        if(start == end) return null;
        int mid = (start+end)/2;
        TreeNode  node = new TreeNode(nums[mid]);
        node.left = help(start,mid);
        node.right = help(mid+1,end);
        return node;

// 109
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        ListNode fast=head,slow=head,prev=null;
        while(fast!=null){
            fast=fast.next;
            if(fast==null) break;
            fast = fast.next;
            prev=slow;
            slow=slow.next;
        }
        if(slow==head) head=null;
        else prev.next=null;
        TreeNode node = new TreeNode(slow.val);
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(slow.next);
        return node;
    }
}

// 110
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root==null) return true;
        return depth(root)!=-1;
    }
    int depth(TreeNode node){
        if(node==null) return 0;
        int a = depth(node.left),b=depth(node.right);
        if (a==-1||b==-1||a-b>1||b-a>1) return -1;
        return a>b?a+1:b+1;
    }
}

// 111
public class Solution {
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        if(root.left==null&&root.right==null) return 1;
        int a=minDepth(root.left)+1,b=minDepth(root.right)+1;
        if(root.left==null) return b;
        if(root.right==null) return a;
        return a>b?b:a;
    }
}

//112
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null) return false;
        sum-=root.val;
        if(root.left==null&&root.right==null) return sum==0;
        boolean l=hasPathSum(root.left,sum),r=hasPathSum(root.right,sum);
        return l||r;
    }
}

// 118
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<>();
        for(int i=0;i<numRows;i++){
            List<Integer> list = new ArrayList<>();
            for(int j=0;j<=i;j++){
                if(j==0||i==j) list.add(1);
                else list.add(ret.get(i-1).get(j)+ret.get(i-1).get(j-1));
            }
            ret.add(list);
        }
        return ret;
    }
}

//119
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<=rowIndex;i++)
            list.add(0);
        list.set(0,1);
        for(int i=0;i<rowIndex;i++){
            for(int j = i;j>=0;j--){
                list.set(j+1,list.get(j)+list.get(j+1));
            }
        }
        return list;
    }
}

//122
public class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length==0) return 0 ;
        int buy=prices[0],profit=0;
        for(int price:prices){
           if(price>buy+profit) profit=price-buy;
           if(price<buy) buy=price;
        }
        return profit;
    }
}

// 125
public class Solution {
    public boolean isPalindrome(String s) {
        int l=0,r=s.length()-1;
        while(l<=r){
            char a = s.charAt(l),b=s.charAt(r);
            if(a<48||a>122||a>90&&a<97||a>57&&a<65){ l++;continue;}
            if(b<48||b>122||b>90&&b<97||b>57&&b<65){ r--;continue;}
            if(a>57&&a!=b&&a!=b+32&&a+32!=b||a<65&&a!=b) return false;
            l++;r--;
        }
        return true;
    }
}

//141
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null) return false;
        ListNode a=head,b=head.next;
        while(b!=null&&b.next!=null){
            if(a==b) return true;
            a=a.next;
            b=b.next.next;
        }
        return false;
    }
}

// 155
public class MinStack {

    Node s,minS;
    /** initialize your data structure here. */
    public MinStack() {
        s =  null;
        minS = null;
    }

    public void push(int x) {
        s = new Node(x,s);
        if(minS!=null && getMin()<x) return;
        minS=new Node(x,minS);
        //System.out.println(s+"--");System.out.println(minS);
    }

    public void pop() {
         int p = s.val;
         s=s.next;
         if (p == getMin()) minS=minS.next;
    }

    public int top() {
        return s.val;
    }

    public int getMin() {
        return minS.val;
    }
}
class Node {
    public Node next;
    public int val;
    public Node(int val) {
        this.val = val;
    }
    public Node(int val,Node next) {
        this.val = val;
        this.next = next;
    }
}

// 160
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a=headA,b=headB;
        int m=0,n=0;
        if(headA==null||headB==null) return null;
        while(a!=null) {m++;a=a.next;}
        while(b!=null) {n++;b=b.next;}
        if(m>n) {
            a=headB;
            b=headA;
            m^=n;
            n^=m;
            m^=n;
        }
        else{
            a=headA;
            b=headB;
        }
        for(int i=0;i<n-m;i++){
            b=b.next;
        }
        while(a!=null) {
            if(a.val==b.val) return a;
            a=a.next;b=b.next;
        }
        return null;
    }
}

// 165
public class Solution {
    int i=0,j=0;
    public int compareVersion(String version1, String version2) {
        int a,b;
        do{
            a=next1(version1);
            b=next2(version2);
        }while(a==b&&(this.i<version1.length()||this.j<version2.length()));
        if(a==b) return 0;
        return a>b?1:-1;
    }

    int next1(String version){
        int sum=0;
        for(;this.i<version.length();this.i++){
            char c = version.charAt(this.i);
            if (c=='.') {this.i++;break;}
            sum*=10;
            sum+=c-48;
        }
        return sum;
    }
    int next2(String version){
        int sum=0;
        for(;this.j<version.length();this.j++){
            char c = version.charAt(this.j);
            if (c=='.') {this.j++;break;}
            sum*=10;
            sum+=c-48;
        }
        return sum;
    }
}

// 168
public class Solution {
    public String convertToTitle(int n) {
        StringBuilder ret = new StringBuilder();
        do{
            ret.append((char)('A'+--n%26));
            n/=26;
        }while(n>0);
        return ret.reverse().toString();
    }
}

//169
public class Solution {
    public int majorityElement(int[] nums) {
        int ret = nums[0], count=0;
        for(int i:nums){
            if(ret==i) count++;
            else if(count==0) {count=1;ret=i;}
            else count--;
        }
        return ret;
    }
}

//171
public class Solution {
    public int titleToNumber(String s) {
        int ret=0;
        for(int i=0;i<s.length();i++){
            ret*=26;
            ret+=s.charAt(i)-'A'+1;
        }
        return ret;
    }
}

//172
public class Solution {
    public int trailingZeroes(int n) {
        int m=0;
        while(n>0){
            n/=5;
            m+=n;
        }
        return m;
    }
}

//189
public class Solution {
    public void rotate(int[] nums, int k) {
        k%=nums.length;
        if(nums.length<=1||k==0) return;
        reverse(nums,0,nums.length-k);
        reverse(nums,nums.length-k,nums.length);
        reverse(nums,0,nums.length);
    }

    void reverse(int[] nums, int a, int b){
        for(int i=0;i<(b-a)/2;i++){
            nums[a+i]^=nums[b-i-1];
            nums[b-i-1]^=nums[a+i];
            nums[a+i]^=nums[b-i-1];
        }
    }
}

// 190
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ret = 0;
        for(int i=0;i<32;i++){
            ret<<=1;
            ret+=n&1;
            n>>=1;
        }
        return ret;
    }
}

//198
public class Solution {
    public int rob(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        int a=nums[0],b=nums[1]>nums[0]?nums[1]:nums[0];
        for(int i=2;i<nums.length;i++){
            int c=nums[i];
            if(c+a>b) {
                c+=a;
                a=b;
                b=c;
            }
            else a=b;
        }
        return b;
    }
}
