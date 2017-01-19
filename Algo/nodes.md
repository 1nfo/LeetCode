# Leetcode Notes

# Index

### In Sequence<a name="top"></a>
1. [Two Sum](#n1)
2. [Add Two Numbers](#n2)
3. [Longest Substring Without Repeating Characters](#n3)
4. [Median of Two Sorted Arrays](#n4)
5. [Longest Palindromic Substring](#n5)
6. [ZigZag Conversion](#n6)
7. [Reverse Integer](#n7)
8. [String to Integer (atoi)](#n8)
9. [Palindrome Number](#n9)
10. [Regular Expression Matching](#n10)
11. [Container With Most Water](#n11)
12. [Integer to Roman](#n)
13. [Roman to Integer](#n13)
14. [Longest Common Prefix](#n14)
15. [3Sum](#n15)
16. [3Sum Closest](#n16)
17. [Letter Combinations of a Phone Number](#n17)
18. [4Sum](#n18)
19. [Remove Nth Node From End of List](#n19)
20. [Valid Parentheses](#n20)


[Top](#top)

### By Category

#### Two Sum:
two pointers / Hashtable  
find index, find number   
one solution / all solution (duplicated or not)


---

# Problems, Thoughts & Solutions
[Top](#top)
## #1. Two Sum<a name="n1"></a>

#### Problem Statement ([link](https://leetcode.com/problems/two-sum/))
###### TAG [Array] [Hash Table] [Two Sum]
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution.

__Example:__

	Given nums = [2, 7, 11, 15], target = 9,
	
	Because nums[0] + nums[1] = 2 + 7 = 9,
	return [0, 1].
___UPDATE (2016/2/13):___
The return format had been changed to zero-based indices. Please read the above updated description carefully.

#### Thought
#####  - Key Idea 
put (target-nums[i])'s index into hashtable to wait the number which equals it.

#####  - Complexity 
Time: O(n)
Space: O(n)  
#####  - Boundary Conditions
#####  - Further Considerations
what if sorted , two pointers
#####  - Related Problems
1. 3Sum
2. 4Sum
3. Two Sum II - Input array is sorted 
4. Two Sum III - Data structure design

#### Solution
```Java
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target-nums[i],i);
        }
        return new int[2];
    }
}
```
[Top](#top)
## #2. Add Two Numbers<a name="n2"></a>

#### Problem Statement ([link](https://leetcode.com/problems/add-two-numbers/))
###### TAG [Linked List] [Math]
You are given two __non-empty__ linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

__Input:__ (2 -> 4 -> 3) + (5 -> 6 -> 4)
__Output:__ 7 -> 0 -> 8

#### Thought
#####  - Key Idea
Use two pointers to scan and calculate the every digit.
#####  - Complexity 
Time: O(n)  
Space:  O(n)
#####  - Boundary Conditions
1. What if one of lists is shorter than the other?
2. what if sum of two digits is greater than 10
3. "one" is not zero when both lists has been scanned (e.g. 3 -> 7  +  7 -> 2)

#####  - Mistakes  
equals/not equals```l1!=null?l.next:null```

#####  - Further Considerations
reverse, stack (add two number II)
#####  - Related Problems
 (M) Multiply Strings, (E) Add Binary, (E) Sum of Two Integers, (E) Add Strings, (M) Add Two Numbers II


#### Solution
```Java
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ret = new ListNode(0),node=ret;
        int one = 0;
        while(l1!=null || l2!=null){
            int res = (l1!=null?l1.val:0)+(l2!=null?l2.val:0)+one;
            node.next = new ListNode(res%10);
            one = res/10;
            node = node.next;
            l1=(l1!=null?l1.next:null);
            l2=(l2!=null?l2.next:null);
        }
        if(one!=0) node.next = new ListNode(one);
        return ret.next;
    }
}
```
[Top](#top)

## #3. Longest Substring Without Repeating Characters<a name="n3"></a>

#### Problem Statement ([link](https://leetcode.com/problems/longest-substring-without-repeating-characters/))
###### TAG [Hash Table] [Two Pointers] [String] [DP]
Given a string, find the length of the longest substring without repeating characters.

**Examples:**

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
#### Thought
#####  - Key word
#####  - Key Idea
Use hashtable two track most recently char's index; use two pointers as start and end of the substring.
#####  - Complexity 
Time: O(n)
Space:  O(n)
#####  - Boundary Conditions
1. what if no repeated;  
2. what if the current repeated char's index is smaller than the previous repeated char's index  

#####  - Mistakes
```t = m.get(c)+1 // starter should not include the repeated head```
>a __b c a__

```m.get(c)>=t // valid repeated char check range should include the starter```
a <s>b</s> __c d e b__ c

#####  - Further Considerations
#####  - Related Problems
(H) Longest Substring with At Most Two Distinct Characters

#### Solution
```Java
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int ret = 0,t=0;
        Map<Character,Integer> m = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(m.containsKey(c)&&m.get(c)>=t){
                ret = Math.max(ret,i-t);
                t = m.get(c)+1;
            }
            m.put(c,i);
        }
        ret = Math.max(ret,s.length()-t);
        return ret;
    }
}
```
[Top](#top)
## #4. Median of Two Sorted Arrays<a name="n4"></a>

#### Problem Statement ([link](https://leetcode.com/problems/median-of-two-sorted-arrays/))
###### TAG [Binary Search] [Array] [Divide and Conquer]
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

**Example 1:**

	nums1 = [1, 3]
	nums2 = [2]

The median is 2.0  

**Example 2:**

	nums1 = [1, 2]
	nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

#### Thought
#####  - Key word
Sorted
#####  - Key Idea
```K = (nums1.length + nums2.length) / 2```. 
Find the K th number in two arrays if odd, or K th and K+1 divided by two if even. 
Every iteration,  remove k/2 (or depends on smaller array size) from one of arrays, whose k/2 th element is smaller than the other's. 
Until k==1, return the smaller first element out of two arrays.
#####  - Complexity 
Time:  O(log(m+n))  
Space: O(1) 
#####  - Boundary Conditions
Which array is smaller?  
What if one array is empty?  
What if m + n is odd or even?  
What if k is odd or even? (or k == 1)
#####  - Mistakes
#####  - Further Considerations

#### Solution
```Java
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if((m+n)%2==1) return help(nums1, nums2, 0, 0, m, n, (m+n)/2+1);
        return (help(nums1, nums2, 0, 0, m, n, (m+n)/2)+help(nums1, nums2, 0, 0, m, n, (m+n)/2+1))/2.0;
    }
    
    int help(int[] nums1, int[] nums2, int a, int b, int m, int n, int k){
        if(m>n) return help(nums2,nums1,b,a,n,m,k);
        if(m==0) return nums2[b+k-1];
        if(k==1) return Math.min(nums1[a],nums2[b]);
        int ka = Math.min(m,k/2),kb = k-ka;
        if(nums1[a+ka-1]>nums2[b+kb-1]) return help(nums1,nums2,a,b+kb,m,n-kb,k-kb);
        else return help(nums1,nums2,a+ka,b,m-ka,n,k-ka);
    }
}
```
[Top](#top)
## #5. Longest Palindromic Substring <a name="n5"></a>

#### Problem Statement ([link](https://leetcode.com/problems/longest-palindromic-substring/))
###### TAG [String]
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

**Example:**

	Input: "babad"
	
	Output: "bab"

Note: "aba" is also a valid answer.  
**Example:**

	Input: "cbbd"
	
	Output: "bb"

#### Thought
#####  - Key word
palindrome
#####  - Key Idea
Searching palindrome is exhausted by start/end indexes. Smart observation is the center of the palindrome is limited, and there is always only one longest palindrome within the same center group of palindrome.
So 1st loop is for center. Then, each iteration with find the longest one(represented by center(s) )to see if needs to update a new center and length.
#####  - Complexity 
Time:  O(n^2)
Space:  O(1)
#####  - Boundary Conditions
odd: one center or **same centers**, even: two centers
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(H) Shortest Palindrome, (E) Palindrome Permutation, (H) Palindrome Pairs

#### Solution
```Java
public class Solution {
    private int start,mLen;
    private String s;
    public String longestPalindrome(String s) {
        this.s = s;
        for (int i=0;i<s.length();i++){
            help(i,i);
            help(i,i+1);
        }
        return s.substring(start,start+mLen);
    }
    
    void help(int a, int b){
        while(a>=0&&b<s.length()&&s.charAt(a)==s.charAt(b)){
            a--;
            b++;
        }
        if (b-a-1<=mLen) return;
        this.start = a+1;
        this.mLen = b-a-1;
    }
}
```
[Top](#top)
## #6. ZigZag Conversion <a name="n6"></a>

#### Problem Statement ([link](https://leetcode.com/problems/zigzag-conversion/))
###### TAG [String]
The string **"PAYPALISHIRING"** is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

	P   A   H   N
	A P L S I I G
	Y   I   R
And then read line by line: **"PAHNAPLSIIGYIR"**
Write the code that will take a string and make this conversion given a number of rows:

	string convert(string text, int nRows);
**convert("PAYPALISHIRING", 3)** should return **"PAHNAPLSIIGYIR"**.

#### Thought
#####  - Key word
#####  - Key Idea
Multiple passes to scan string, and append to string builder.  
The key is to define the index need to be append for each pass.
#####  - Complexity 
Time:  O(n)
Space:  O(n)
#####  - Boundary Conditions
row number is one.
First and Last row for each cycle.
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems

#### Solution
```Java
public class Solution {
    public String convert(String s, int numRows) {
        if(numRows==1) return s;
        StringBuilder sb = new StringBuilder();
        int n = numRows*2-2;
        for(int k=0;k<numRows;k++){
            for(int i=k,j=n-k;i<s.length();i+=n,j+=n){
                sb.append(s.charAt(i));
                if(k!=0&&k!=numRows-1&&j<s.length()) sb.append(s.charAt(j));
            }
        }
        return sb.toString();
    }
}
```
[Top](#top)
## #7. Reverse Integer <a name="n7"></a>

#### Problem Statement ([link]())
###### TAG 
Reverse digits of an integer.

**Example1:** x = 123, return 321  
**Example2:** x = -123, return -321
#### Thought
#####  - Key word
#####  - Key Idea
#####  - Complexity 
Time:  O(1)
Space: O(1)
#####  - Boundary Conditions
leading zero; minus input; overflow; abs(Integer.MIN_VALUE)>abs(Integer.MAX_VALUE)
#####  - Mistakes
Don't forget return integer; using queue instead of stack
#####  - Further Considerations
#####  - Related Problems

#### Solution
```Java
public class Solution {
    public int reverse(int x) {
        if (x>0) return -reverse(-x);
        int[] arr = new int[10];
        int count=0;
        while(x!=0){
            arr[count++] = x%10;
            x/=10;
        }
        long ret = 0;
        for(int i=0;i<count;i++){
            ret *= 10;
            ret += arr[i];
        }
        if(ret<Integer.MIN_VALUE) return 0;
        return (int)ret;
    }
}
```
[Top](#top)
## #8. String to Integer (atoi) <a name="n8"></a>

#### Problem Statement ([link](https://leetcode.com/problems/string-to-integer-atoi/))
###### TAG 

#### Thought
#####  - Key word
#####  - Key Idea
Lots of Boundary conditions
#####  - Complexity 
Time:  O(n)
Space:  O(1)
#####  - Boundary Conditions
space,+,-, other char in the middle of string, overflow(pos/neg)
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(E) Reverse Integer, (H) Valid Number

#### Solution
```Java
public class Solution {
    public int myAtoi(String str) {
        if(str.length()==0) return 0;
        int curr = 0, sign = 1;
        long ret = 0;
        char c = str.charAt(curr);
        while(c==' ') c = str.charAt(++curr);
        if(c=='+') curr++;
        if(c=='-') {curr++;sign*=-1;}
        while(curr<str.length()){
            c = str.charAt(curr++);
            if(c<'0'||c>'9') return (int) ret;
            ret *= 10;
            ret += (c-'0')*sign;
            if(ret<-2147483648) return -2147483648;
            if(ret>2147483647) return 2147483647;
        }
        return (int) ret;
    }
}
```
[Top](#top)
## #9. Palindrome Number <a name="n9"></a>

#### Problem Statement ([link]())
###### TAG 
Determine whether an integer is a palindrome. Do this without extra space.
#### Thought
#####  - Key Idea
	return (reversed integer == original integer); 

#####  - Complexity 
Time:  O(1)
Space: O(1)
#####  - Boundary Conditions
negative is always false
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(E) Palindrome Linked List

#### Solution
```Java
public class Solution {
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        int origin = x, reverse = 0;
        while(x!=0){
            reverse = reverse*10 + x%10;
            x /= 10;
        }
        return reverse==origin;
    }
}
```
[Top](#top)
## #10. Regular Expression Matching <a name="n10"></a>

#### Problem Statement ([link](https://leetcode.com/problems/regular-expression-matching/))
###### TAG [DP] [Backtracking] [String]
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

	Some examples:
	isMatch("aa","a") → false
	isMatch("aa","aa") → true
	isMatch("aaa","aa") → false
	isMatch("aa", "a*") → true
	isMatch("aa", ".*") → true
	isMatch("ab", ".*") → true
	isMatch("aab", "c*a*b") → true
#### Thought
#####  - Key Idea
DP(m,n) = f(char1[m],char2[n-1],char[n],DP(m,n-2),DP(m-1,n-1),Dp(m-1,n))  
base case: match "" and "a\*b\*c\*..."
last regexp char is a single match or it is a '\*'.
if single match including '.', match dp[m][n]=dp[m-1][n-1]
if "\*", match zero (dp[m][n-2]) or match one/many (characters equaled char[n-1]==char[m] & dp[m-1][n])
#####  - Complexity 
Time:  O(n^2)
Space:  O(n^2)
#####  - Boundary Conditions
s=="" matching "a\*b\*c\*..." **BUT** '*' can't be first in the p  
p=="" by default, all false.

#####  - Mistakes
Out of index
#####  - Further Considerations
#####  - Related Problems
 (H) Wildcard Matching

#### Solution
```Java
public class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0]=true;
        for(int j=0;j<p.length();j++){
            dp[0][j+1] = j>0&&p.charAt(j)=='*'&&dp[0][j-1];
            for(int i=0;i<s.length();i++){
                if(p.charAt(j)=='.'||p.charAt(j)==s.charAt(i)) dp[i+1][j+1] = dp[i][j];
                if(p.charAt(j)=='*') 
                    dp[i+1][j+1] = dp[i+1][j-1]||(p.charAt(j-1)==s.charAt(i)||p.charAt(j-1)=='.')&&dp[i][j+1];
            }
        }
        return dp[s.length()][p.length()];
    }
}
```
[Top](#top)

## #11. Container With Most Water <a name="n11"></a>

#### Problem Statement ([link](https://leetcode.com/problems/container-with-most-water))
###### TAG [Array] [Tow pointers]
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

#### Thought
#####  - Key Idea
two pointers, when Height[a]<Height[b], if we b--, the area can not be enlarged.
#####  - Complexity 
Time:  O(n)  
Space:  O(1)
#####  - Boundary Conditions
height.length<2
#####  - Mistakes
compared indexes instead of height[index]s
#####  - Further Considerations
Why this is an efficient way to search?
#####  - Related Problems
(H) Trapping Rain Water

#### Solution
```Java
public class Solution {
    public int maxArea(int[] height) {
        if(height.length<2) return 0;
        int i=0,j=height.length-1,ret=0;
        while(i<j){
            if(height[i]<height[j]) ret = Math.max(ret,(j-i)*height[i++]);
            else ret = Math.max(ret,(j-i)*height[j--]);
        }
        return ret;
    }
}
```
[Top](#top)

## #12. Integer to Roman <a name="n12"></a>

#### Problem Statement ([link](https://leetcode.com/problems/integer-to-roman))
###### TAG [Math] [String]
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
#### Thought
#####  - Key Idea
digit by digit
#####  - Complexity 
Time:  O(n)
Space:  O(n)
#####  - Boundary Conditions 
9, 4, (6 7 8), 5, (1,2,3), 0
#####  - Mistakes
Reverse the sequence or not
#####  - Further Considerations
Efficiently using space and time
#####  - Related Problems
(E) Roman to Integer (H) Integer to English Words


#### Solution
```Java
public class Solution {
    public String intToRoman(int num) {
        char[][] g = {{'I','V','X'},{'X','L','C'},{'C','D','M'},{'M','\0','\0'}};
        StringBuilder sb = new StringBuilder();
        int count=0;
        while(num>0){
            int r = num%10;
            num /= 10;
            if(r==9){
                sb.append(g[count][2]);
                r-=8;
            }
            else if(r==4){
                sb.append(g[count][1]);
                r-=3;
            }
            while(r%5>0){
                sb.append(g[count][0]);
                r--;
            }
            if(r==5){
                sb.append(g[count][1]);
                r-=5;
            }
            count++;
        }
        return sb.reverse().toString();
    }
}
```
[Top](#top)

## #13. Roman to Integer <a name="n13"></a>

#### Problem Statement ([link](https://leetcode.com/problems/roman-to-integer))
###### TAG [Math] [String]
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
#### Thought
#####  - Key Idea
all number decreasing but digits '4' and '9'
#####  - Complexity 
Time:  O(n)
Space:  O(n)

#####  - Related Problems
 (M) Integer to Roman

#### Solution
```Java
public class Solution {
    public int romanToInt(String s) {
        int ret = 0;
        for(int i=0;i<s.length();i++){
            int v = c2v(s.charAt(i));
            int t = (i+1!=s.length()&&v<c2v(s.charAt(i+1)))?-1:1;
            ret += t*v;
        }
        return ret;
    }
    
    int c2v(char c){
        switch (c){
            case 'M':return 1000;
            case 'D':return 500;
            case 'C':return 100;
            case 'L':return 50;
            case 'X':return 10;
            case 'V':return 5;
            case 'I':return 1;
            default:return 0;
        }
    }
}
```
[Top](#top)

## #14. Longest Common Prefix <a name="n14"></a>

#### Problem Statement ([link](https://leetcode.com/problems/longest-common-prefix))
###### TAG [String]
Write a function to find the longest common prefix string amongst an array of strings.
#### Thought
#####  - Key Idea
two nested loops, break to the outer loop
#####  - Complexity 
Time:  O(n)  
Space:  O(1)
#####  - Boundary Conditions
the last index p is exclusive



#### Solution
```Java
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        int p = 0;
        while(p<strs[0].length()){
            char c = strs[0].charAt(p);
            for(int i=1;i<strs.length;i++){
                String str = strs[i];
                if(p==str.length()||c!=str.charAt(p)) return strs[0].substring(0,p);
            }
            p++;
        }
        return strs[0].substring(0,p);
    }
}
```
[Top](#top)

## #15. 3Sum <a name="n15"></a>

#### Problem Statement ([link](https://leetcode.com/problems/3sum))
###### TAG  [Array] [Two Pointers]

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
#### Thought
#####  - Key word
unique triplets / not contain duplicate triplets
#####  - Key Idea
sort, loop + two pointer
#####  - Complexity 
Time:  O(n^2)  
Space:  O(1)
#####  - Boundary Conditions
length<3, sort first, two-pointer remove duplicated
#####  - Mistakes
how to remove duplicated, if find curr==prev then continue, **BUT** inner loop must check this condition when we find a+b+c==0, otherwise will break two-pointer search.
#####  - Further Considerations
duplicated or not, value or index, one or all solutions
#####  - Related Problems
(E) Two Sum, (M) 3Sum Closest, (M) 4Sum, (M) 3Sum Smaller


#### Solution
```Java
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums.length<3) return ret;
        Arrays.sort(nums);
        int smallest = nums[0] - 1;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] != smallest) {
                smallest = nums[i];
                int l = i + 1, r = nums.length - 1, biggest = nums[nums.length - 1] + 1;
                while (l < r) {
                    if (nums[i] + nums[l] + nums[r] == 0){
                        if (biggest != nums[r]) {
                            ret.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                            biggest = nums[r];
                        }
                        r--;l++;
                    }
                    else if (nums[i] + nums[l] + nums[r] > 0) r--;
                    else l++;
                }
            }
        }
        return ret;
    }
}
```
[Top](#top)

## #16. 3Sum Closest <a name="n16"></a>

#### Problem Statement ([link](https://leetcode.com/problems/3sum-closest))
###### TAG [two pointers] [array]
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

#### Thought
#####  - Key word
#####  - Key Idea
loop + two pointers
#####  - Complexity 
Time:  O(n^2)
Space:  O(1)
#####  - Boundary Conditions
#####  - Mistakes
sort first, out of index
#####  - Further Considerations
#####  - Related Problems
(M) 3Sum, (M) 3Sum Smaller

#### Solution
```Java
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ret = nums[0]+nums[1]+nums[2], prevDiff=target-ret;
        for(int i=0;i<nums.length;i++){
            int j=i+1,k=nums.length-1;
            while(j<k){
                int sum = nums[i]+nums[j]+nums[k], diff = target-sum;
                if(sum>target) k--;
                else if (sum<target) j++;
                else return target;
                if(diff*diff<prevDiff*prevDiff) {
                    prevDiff=diff;
                    ret=sum;
                }
            }
        }
        return ret;
    }
}
```
[Top](#top)

## #17. Letter Combinations of a Phone Number <a name="n17"></a>

#### Problem Statement ([link](https://leetcode.com/problems/letter-combinations-of-a-phone-number/))
###### TAG [backtracking] [string]
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

	Input: Digit string "23"
	Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
#### Thought
#####  - Key Idea
digit by digit. each iteration, use and replace the former result.
#####  - Complexity 
Time:  O(n^2)
Space:  O(n^2)
#####  - Boundary Conditions
first loop needs an initial result "";  
empty input
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(M) Generate Parentheses, (M) Combination Sum (E), Binary Watch

#### Solution
```Java
public class Solution {
    List<String> ret = new ArrayList<>();
    char[][] map = {{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
    public List<String> letterCombinations(String digits) {
        if(digits.length()==0) return ret;
        ret.add("");
        for(char digit:digits.toCharArray()){
            List<String> tmp = new ArrayList<>();
            for(char c:map[digit-'2']){
                for(String str:ret){
                    tmp.add(str+c);
                }
            }
            ret=tmp;
        }
        return ret;
    }
}
```
[Top](#top)




## # <a name="n"></a>

#### Problem Statement ([link]())
###### TAG 

#### Thought
#####  - Key word
#####  - Key Idea
#####  - Complexity 
Time:  
Space:  
#####  - Boundary Conditions
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems

#### Solution
```Java

```
[Top](#top)


