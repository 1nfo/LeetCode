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

// 113
public class Solution {
    private List<List<Integer>> ret = new ArrayList<>();
    private List<Integer> list = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root!=null) help(root,sum);
        return ret;
    }

    void help(TreeNode node, int target){
        list.add(node.val);
        if(node.left==null&&node.right==null){
            if(target==node.val) ret.add(new ArrayList<>(list));
        } else {
            if(node.left!=null)help(node.left,target-node.val);
            if(node.right!=null)help(node.right,target-node.val);
        }
        list.remove(list.size()-1);
    }
}

// 114
public class Solution {
    public void flatten(TreeNode root) {
        if(root != null) help(root);
    }
    TreeNode help(TreeNode node){
        if(node.left==null&&node.right==null) return node;
        TreeNode sortedL=null,sortedR=null;
        if(node.left!=null) sortedL= help(node.left);
        if(node.right!=null) sortedR = help(node.right);
        if(sortedL!=null){
            sortedL.right=node.right;
            node.right=node.left;
            node.left=null;
        }
        return sortedR!=null?sortedR:sortedL;
    }
}

// 115
public class Solution {
    public int numDistinct(String s, String t) {
        int m = t.length(),n=s.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<=n;i++) dp[0][i] = 1;
        for(int i=1;i<=m;i++) dp[i][0] = 0;
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++)
                dp[i][j] = dp[i][j-1]+(t.charAt(i-1)==s.charAt(j-1)?dp[i-1][j-1]:0);
        }
        return dp[m][n];
    }
}

// 116
public class Solution {
    public void connect(TreeLinkNode root) {
        help(root);
    }
    void help(TreeLinkNode node){
        if(node == null) return;
        if(node.left!=null)node.left.next=node.right;
        if(node.right!=null&&node.next!=null)node.right.next=node.next.left;
        help(node.left);
        help(node.right);
    }
}
// 116 _ 2
public class Solution {
    public void connect(TreeLinkNode root) {
        while(root!=null){
            TreeLinkNode curr = root;
            while(curr!=null){
                if(curr.left!=null){
                    curr.left.next=curr.right;
                    if (curr.next!=null) curr.right.next=curr.next.left;
                }
                curr=curr.next;
            }
            root=root.left;
        }
    }
}
// 117 _ forward linking
public class Solution {
    public void connect(TreeLinkNode root) {
        while(root!=null){
            TreeLinkNode curr=root,start=null;
            while(curr!=null){
                if(curr.left!=null){
                    if(start==null) start=curr.left;
                    if(curr.right!=null) curr.left.next=curr.right;
                    else linkNext(curr.left,curr.next);
                }
                if(curr.right!=null){
                    if(start==null) start=curr.right;
                    linkNext(curr.right,curr.next);
                }
                curr=curr.next;
            }
            root=start;
        }
    }
    void linkNext(TreeLinkNode prev,TreeLinkNode next){
        while(next!=null){
            if(next.left!=null){
                prev.next=next.left;
                break;
            }
            else if(next.right!=null){
                prev.next=next.right;
                break;
            }
            next=next.next;
        }
    }
}

// 117 _ 2 _ backward linking
public class Solution {
    public void connect(TreeLinkNode root) {
        while(root!=null){
            TreeLinkNode curr=root,start=null,prev=null;
            while(curr!=null){
                if(curr.left!=null){
                    if(prev==null) start=curr.left;
                    else prev.next=curr.left;
                    prev=curr.left;
                }
                if(curr.right!=null){
                    if(prev==null) start=curr.right;
                    else prev.next=curr.right;
                    prev=curr.right;
                }
                curr = curr.next;
            }
            root=start;
        }
    }
}
// 117 _ 3 _ backward linking with default head
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode curr=root;
        while(curr!=null){
            TreeLinkNode start=new TreeLinkNode(0),prev=start;
            while(curr!=null){
                if(curr.left!=null) prev.next = prev = curr.left;
                if(curr.right!=null) prev.next = prev= curr.right;
                curr = curr.next;
            }
            curr=start.next;
        }
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

// 120
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[][] dp = new int[size][size];
        dp[0][0] = triangle.get(0).get(0);
        for(int i=0;i<size-1;i++){
            dp[i+1][0]+=dp[i][0];
            for(int j=1;j<=i;j++){
                dp[i+1][j]+=Math.min(dp[i][j],dp[i][j-1]);
            }
            dp[i+1][i+1]+=dp[i][i];
            for(int j=0;j<=i+1;j++){
                dp[i+1][j] += triangle.get(i+1).get(j);
            }
        }
        int ret = dp[size-1][0];
        for(int i=1;i<size;i++){
            if(ret>dp[size-1][i]) ret=dp[size-1][i];
        }
        return ret;
    }
}

// 121
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

// 122
public class Solution {
    public int maxProfit(int[] prices) {
        int ret = 0;
        for(int i = 1;i<prices.length;i++){
            int diff = prices[i]-prices[i-1];
            if(diff>0) ret+=diff;
        }
        return ret;
    }
}

// 123
public class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length==0) return 0;
        int b1=prices[0],p1=0,b2=b1,p2=0;
        for(int price:prices){
            b1=Math.min(b1,price);
            p1=Math.max(p1,price-b1);
            b2=Math.min(b2,price-p1);
            p2=Math.max(p2,price-b2);
        }
        return p2;
    }
}

// 124
public class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        int ret = help(root);
        return Math.max(max,ret);
    }
    int help(TreeNode root){
        if(root==null) return Integer.MIN_VALUE;
        int c = root.val;
        int a = help(root.left), b = help(root.right), m = Math.max(a,b);
        if(c>=0||c+m>0||c>m) {
            this.max = Math.max(this.max,Math.max(0,a)+Math.max(0,b)+c);
            return Math.max(0,m)+c;
        }
        this.max = Math.max(this.max,m);
        return Integer.MIN_VALUE;
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

// 127
public class Solution {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        int ret=1;
        Set<String> bSet = new HashSet<>(),eSet = new HashSet<>(),tmp;

        bSet.add(beginWord);
        eSet.add(endWord);
        wordList.add(endWord);

        while(!bSet.isEmpty()&&!eSet.isEmpty()){
            if(bSet.size()>eSet.size()) {
                tmp = bSet;
                bSet = eSet;
                eSet = tmp;
            }
            tmp = new HashSet<>();
            for(String s:bSet){
                for(int j=0;j<s.length();j++){
                    char [] arr = s.toCharArray();
                    for(char a='a';a<='z';a++){
                        arr[j]=a;
                        String built = new String(arr);
                        if(eSet.contains(built)) return ret+1;
                        if(wordList.contains(built)){
                            tmp.add(built);
                            wordList.remove(built);
                        }
                    }
                }
            }
            bSet = tmp;
            ret++;
        }
        return 0;
    }
}

// 128
public class Solution {
    public int longestConsecutive(int[] nums) {
        int ret = 0;
        Map<Integer, Integer> m = new HashMap<>();
        for(int i : nums){
            if(m.containsKey(i)) continue;
            int a = (m.containsKey(i-1)?m.get(i-1):0), b = (m.containsKey(i+1)?m.get(i+1):0);
            int t = a+b+1;
            m.put(i-a,t);
            m.put(i+b,t);
            m.put(i,t);
            ret = Math.max(ret,t);
        }
        return ret;
    }
}

// 129
public class Solution {
    private int ret = 0;

    public int sumNumbers(TreeNode root) {
        help(root,0,0);
        return ret;
    }

    void help(TreeNode node,int sum,int lv){
        if(node == null) return;
        sum*=10;
        sum+=node.val;
        if(node.left==null&&node.right==null) ret+=sum;
        help(node.left,sum,lv+1);
        help(node.right,sum,lv+1);
    }
}

// 130
public class Solution {
    public void solve(char[][] board) {
        int m = board.length;
        if(m==0) return;
        int n = board[0].length;

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0||j==0||i==m-1||j==n-1) queue.add(i*n+j);
            }
        }

        while (!queue.isEmpty()){
            int p = queue.remove();
            int x=p/n,y=p%n;
            if(board[x][y]!='X'){
                board[x][y]='.';
                int c=0,xx,yy;
                while(c++<4){
                    xx=x+(c==1?-1:0)+(c==2?1:0);
                    yy=y+(c==3?-1:0)+(c==4?1:0);
                    if(xx>=0&&yy>=0&&xx<m&&yy<n&&board[xx][yy]=='O'){
                        queue.add(xx*n+yy);
                        board[xx][yy]='.';
                    }
                }
            }

        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='.') board[i][j]='O';
                else board[i][j]='X';
            }
        }
    }
}

// 131
public class Solution {
    private List<List<String>> ret = new ArrayList<>();
    private List<String> list = new ArrayList<>();

    public List<List<String>> partition(String s) {
        help(s.toCharArray(),0);
        return ret;
    }

    void help(char[] arr,int pos){
        if(pos==arr.length) ret.add(new ArrayList<>(list));
        for(int i=pos;i<arr.length;i++){
            boolean isP= true;
            for(int j=pos;j<i+1;j++){
                if(arr[j]!=arr[pos+i-j]){
                    isP = false;
                    break;
                }
            }
            if(isP) {
                list.add(new String(Arrays.copyOfRange(arr, pos, i+1)));
                help(arr,i+1);
                list.remove(list.size()-1);
            }
        }
    }
}

// 133
public class Solution {
    private Map<UndirectedGraphNode,UndirectedGraphNode> map = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null) return null;
        UndirectedGraphNode ret = new UndirectedGraphNode(node.label);
        if(!map.containsKey(node)){
            map.put(node,ret);
            for(UndirectedGraphNode subNode:node.neighbors){
                ret.neighbors.add(cloneGraph(subNode));
            }
        }
        return map.get(node);
    }
}

// 134
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int ret=0,rem=0,tot=0;
        for(int i=0;i<gas.length;i++){
            rem+=gas[i]-cost[i];
            tot+=gas[i]-cost[i];
            if(rem<0) {ret=i+1;rem=0;}
        }
        if(tot>=0) return ret;
        return -1;
    }
}

// 135
public class Solution {
    public int candy(int[] ratings) {
        if(ratings.length<2) return ratings.length;
        int ret=0;
        int[] c = new int[ratings.length];
        Arrays.fill(c,1);
        for(int i=1;i<c.length;i++) if(ratings[i]>ratings[i-1]) c[i]=c[i-1]+1;
        for(int i=c.length-2;i>=0;i--) if(ratings[i]>ratings[i+1]&&c[i]<=c[i+1]) c[i]=c[i+1]+1;
        for(int i:c) ret+=i;
        return ret;
    }
}

// 136
public class Solution {
    public int singleNumber(int[] nums) {
        int ret = 0;
        for(int i:nums) ret^=i;
        return ret;
    }
}

// 137
public class Solution {
    public int singleNumber(int[] nums) {
        int a=0,b=0;
        for(int c:nums){
            int ta = a;
            a = (a^b)&~(b^c);
            b = (~ta)&(b^c);
        }
        return a|b;
    }
}

// 138
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode ret = new RandomListNode(0),op=head,cp=ret;
        Map<RandomListNode,RandomListNode> m = new HashMap<>();
        while(op!=null){
            cp.next = new RandomListNode(op.label);
            cp=cp.next;
            m.put(op,cp);
            op=op.next;
        }
        op=head;
        cp=ret.next;
        while(op!=null){
            cp.random = m.get(op.random);
            cp=cp.next;
            op=op.next;
        }

        return ret.next;
    }
}

// 139
public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        if(s==null) return true;
        boolean[] dp = new boolean[s.length()+1];
        dp[0]=true;

        for(int start=0;start<s.length();start++){
            if(dp[start]){
                for(String d:wordDict){
                    int i=start,j=0;
                    while(i<s.length()&&j<d.length()&&s.charAt(i)==d.charAt(j)){
                        i++;
                        j++;
                    }
                    if(j==d.length()) dp[start+j]=true;
                }
            }

        }

        return dp[s.length()];
    }
}

// 140
public class Solution {
    private Map<String,List<String>> m = new HashMap<>();
    private List<String> wordDict;

    public List<String> wordBreak(String s, List<String> wordDict) {
        this.wordDict = wordDict;
        return help(s);
    }

    List<String> help(String s){
        if(m.containsKey(s)) return m.get(s);
        List<String> ret = new LinkedList<>();
        if(s.length()==0){
            ret.add("");
            return ret;
        }
        for(String word:wordDict){
            int wLen = word.length();
            if(s.startsWith(word)) for(String sub:help(s.substring(wLen))) ret.add(word+(sub.isEmpty()?"":" "+sub));
        }
        m.put(s,ret);
        return ret;
    }
}

// 141
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

// 142
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head==null) return null;
        ListNode s=head,f=head;
        while(f!=null&&f.next!=null){
            s=s.next;
            f=f.next.next;
            if(s==f) break;
        }

        if(f==null||f.next==null) return null;
        while(head!=s){
            head=head.next;
            s=s.next;
        }
        return head;
    }
}

// 143
public class Solution {
    public void reorderList(ListNode head) {
        if(head==null||head.next==null) return;
        ListNode s=head,f=head;
        while(f!=null&&f.next!=null){
            s=s.next;
            f=f.next.next;
        }

        ListNode prev=null,curr=s.next,next;
        s.next=null;

        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }

        ListNode h1=head,h2=prev,n1,n2;

        while(h2!=null){
            n1=h1.next;
            n2=h2.next;
            h1.next=h2;
            h2.next=n1;
            h1=n1;
            h2=n2;
        }

    }
}

// 144
public class Solution {
    private List<Integer> ret = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        help(root);
        return ret;
    }

    void help(TreeNode node){
        if(node==null) return;
        ret.add(node.val);
        help(node.left);
        help(node.right);
    }
}

// 147
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode ret = new ListNode(0),curr=head,prev,next;
        ret.next=head;
        while(curr.next!=null){
            next=curr.next;
            if(curr.val>next.val){
                prev=ret;
                while(prev.next.val<=next.val){
                    prev=prev.next;
                }
                curr.next=next.next;
                next.next=prev.next;
                prev.next=next;
            }
            else{
                curr=curr.next;
            }
        }
        return ret.next;
    }
}

// 151
public class Solution {
    public String reverseWords(String s) {
        if(s==null||s.length()==0) return s;
        String[] arr = s.trim().split("\\s+");
        if(arr.length==0) return "";
        StringBuilder ret = new StringBuilder();
        for(int i=arr.length-1;i>=0;i--){
            ret.append(arr[i]+" ");
        }
        ret.deleteCharAt(ret.length()-1);
        return ret.toString();
    }
}

// 152
public class Solution {
    public int maxProduct(int[] nums) {
        int max=1,min=1,ret = nums[0];
        for(int i:nums){
            int tmax = Math.max(max*i,min*i), tmin = Math.min(max*i,min*i);
            max = Math.max(tmax,i);
            min = Math.min(tmin,i);
            ret = Math.max(ret,max);
        }
        return ret;
    }
}

// 153
public class Solution {
    public int findMin(int[] nums) {
        return help(nums,0,nums.length);
    }

    int help(int[] nums,int start, int end){
        if(nums[start]<=nums[end-1]) return nums[start];
        return Math.min(help(nums,0,(start+end)/2),help(nums,(start+end)/2,end));
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

 // 162
 public class Solution {
    public int findPeakElement(int[] nums) {
        for(int i=0;i<nums.length;i++){
            if((i==0||nums[i]>nums[i-1])&&(i==nums.length-1||nums[i]>nums[i+1])) return i;
        }
        return -1;
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

// 166
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        return help((long)numerator,(long)denominator);
    }

    public String help(long numerator, long denominator){
        if(numerator<0&&denominator>0||numerator>0&&denominator<0) return "-"+help(-numerator,denominator);
        long r = numerator%denominator;
        StringBuilder str = new StringBuilder();
        str.append(numerator/denominator);
        if(r==0) return str.toString();
        str.append(".");
        Map<Long,Integer> map = new HashMap<>();
        while(r!=0&&!map.containsKey(r)){
            map.put(r,str.length());
            str.append(r*10/denominator);
            r=r*10%denominator;
        }
        if(r!=0) {str.insert((int)map.get(r),'(');str.append(')');}
        return str.toString();
    }
}

// 167
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] ret = {-1,-1};
        int i=0,j=numbers.length-1;
        while(i<j){
            if(numbers[i]+numbers[j]>target)  j--;
            else if(numbers[i]+numbers[j]<target) i++;
            else {
                ret[0]=i+1;
                ret[1]=j+1;
                return ret;
            }
        }
        return ret;

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

// 172
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

// 173
public class BSTIterator {
    private Stack<TreeNode> cache = new Stack<>();

    public BSTIterator(TreeNode root) {
        while(root!=null) {
            cache.push(root);
            root=root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !cache.empty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode ret = cache.pop(),tmp;
        if(ret.right!=null)
        {
            tmp = ret.right;
            while(tmp!=null){
                cache.push(tmp);
                tmp=tmp.left;
            }
        }
        return ret.val;
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

// 199
public class Solution {
    private List<Integer> ret = new ArrayList<>();
    private Deque<TreeNode> deque =  new LinkedList<>();

    public List<Integer> rightSideView(TreeNode root) {
        if(root!=null){
            deque.offerLast(root);
            TreeNode last = root;
            int lv = 0;
            while(!deque.isEmpty()){
                TreeNode node = deque.pollFirst();
                if(node.left!=null) deque.offerLast(node.left);
                if(node.right!=null) deque.offerLast(node.right);
                if(node==last){
                    lv++;
                    last = deque.peekLast();
                }
                if(ret.size()<lv) ret.add(node.val);

            }
        }
        return ret;
    }
}

// 200
public class Solution {
    private boolean[][] used;
    private char[][] grid;
    private int m,n;
    private Queue<Integer> queue = new LinkedList<>();

    public int numIslands(char[][] grid) {
        this.m=grid.length;
        if(m==0) return 0;
        this.n=grid[0].length;
        this.grid = grid;
        this.used = new boolean[m][n];

        int ret = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(valid(i,j)){
                    ret++;
                    bfs(i,j);
                }
            }
        }
        return ret;
    }

    void bfs(int xx, int yy){
        offer(xx,yy);
        used[xx][yy]=true;
        while(!queue.isEmpty()){
            int p=queue.poll();
            int x = p/n, y = p%n;
            offer(x-1,y);
            offer(x+1,y);
            offer(x,y-1);
            offer(x,y+1);
        }
    }

    boolean valid(int x,int y){
        return x>=0&&y>=0&&x<m&&y<n&&!used[x][y]&&grid[x][y]=='1';
    }

    void offer(int x, int y){
        if(valid(x,y)){
            int p = x*n+y;
            used[x][y]=true;
            queue.offer(p);
        }
    }
}
