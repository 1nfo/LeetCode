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
21. [Merge Two Sorted Lists](#21)
22. [Generate Parentheses](#22)
23. [Merge k Sorted Lists](#23)
24. [Swap Nodes in Pairs](#24)
25. [Reverse Nodes in k-Group](#25)
26. [Remove Duplicates from Sorted Array](#26)
27. [Remove Element](#27)
28. [Implement strStr()](#28)
29. [Divide Two Integers](#29)
30. [Substring with Concatenation of All Words](#30)


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

## #18. 4Sum <a name="n18"></a>

#### Problem Statement ([link](https://leetcode.com/problems/4sum/))
###### TAG [Array] [Hash Table] [Two pointer]

#### Thought
#####  - Key word
#####  - Key Idea
loop * 2 + two pointers
#####  - Complexity 
Time:  O(n^3)
Space:  O(1)
#####  - Boundary Conditions
#####  - Mistakes
out of index
#####  - Further Considerations
Efficient improvement:

1. use index to check duplicated value
2. max\*4<target || min\*4>target

#####  - Related Problems
(E) Two Sum, (M) 3Sum, (M) 4Sum II

#### Solution
```Java
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        if(nums.length<4) return ret;
        Arrays.sort(nums);
        if(nums[nums.length-1]*4<target||nums[0]*4>target) return ret;
        for(int i=0;i<nums.length-3;i++){
            if(i>0&&nums[i-1]==nums[i]) continue;
            for(int j=i+1,prevJ=nums[j]-1;j<nums.length-2;j++){
                if(j>i+1&&nums[j-1]==nums[j]) continue;
                int l=j+1,r=nums.length-1;
                while(l<r){
                    int sum = nums[i]+nums[j]+nums[l]+nums[r];
                    if(sum>target) while(l<r&&nums[r]==nums[--r]) ;
                    else if(sum<target) while(l<r&&nums[l]==nums[++l]);
                    else{
                            ret.add(new ArrayList(Arrays.asList(nums[i],nums[j],nums[l],nums[r])));
                            while(l<r&&nums[r]==nums[--r]);
                            while(l<r&&nums[l]==nums[++l]);
                    }
                }
            }
        }
        return ret;
    }
}
```
[Top](#top)

## #19 Remove Nth Node From End of List  <a name="n19"></a>

#### Problem Statement ([link](https://leetcode.com/problems/remove-nth-node-from-end-of-list/))
###### TAG  [Linked List] [Two Pointers]

Given a linked list, remove the nth node from the end of list and return its head.

For example,

	Given linked list: 1->2->3->4->5, and n = 2.
	
	After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
#### Thought
#####  - Key Idea
two pointers, one move first, the other follows from(empty node, here serve as prev since curr is redundent) after n steps. Then when first reaches null, second is the node need to
#####  - Complexity 
Time:  O(n)
Space:  O(1)
#####  - Boundary Conditions
empty node before head(in case head is null)
#####  - Mistakes
#####  - Further Considerations

#### Solution
```Java
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode ret = new ListNode(0),prev=ret,ahead=head;
        ret.next=head;
        for(int i=0;i<n;i++){
            ahead=ahead.next;
        }
        while(ahead!=null){
            ahead=ahead.next;
            prev=prev.next;
        }
        prev.next=prev.next.next;
        return ret.next;
    }
}
```
[Top](#top)

## #20. Valid Parentheses <a name="n20"></a>

#### Problem Statement ([link](https://leetcode.com/problems/valid-parentheses/))
###### TAG [stack] [string]
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
#### Thought
#####  - Key Idea
#####  - Complexity 
Time:  O(n)
Space:  O(n)
#####  - Boundary Conditions

#####  - Mistakes
condition: two sides
#####  - Further Considerations
#####  - Related Problems
(M) Generate Parentheses, (H) Longest Valid Parentheses, (H) Remove Invalid Parentheses

#### Solution
```Java
public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c:s.toCharArray()){
            if(c=='}'||c==']'||c==')'){
                if(stack.isEmpty()) return false;
                char last = stack.pop();
                if(c=='}'&&last=='{'||c==']'&&last=='['||c==')'&&last=='('){
                    continue;
                }
                else{return false;}
            }
            else stack.push(c);
        }
        return stack.isEmpty();
    }
}
```
[Top](#top)

## #21. Merge Two Sorted Lists <a name="n21"></a>

#### Problem Statement ([link](https://leetcode.com/problems/merge-two-sorted-lists/))
###### TAG 
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
#### Thought
#####  - Key word
#####  - Key Idea
#####  - Complexity 
Time:  n  
Space:  1 or n
#####  - Boundary Conditions
#####  - Mistakes
#####  - Further Considerations
deep copy or not?
#####  - Related Problems
(H) Merge k Sorted Lists, (E) Merge Sorted Array, (M) Sort List, (M) Shortest Word Distance II

#### Solution
```Java
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode ret = new ListNode(0),curr=ret;
        while(l1!=null&&l2!=null){
            if(l1.val>l2.val){
                curr.next=new ListNode(l2.val);
                curr=curr.next;
                l2=l2.next;
            }
            else{
                curr.next=new ListNode(l1.val);
                curr=curr.next;
                l1=l1.next;
            }
        }
        while(l1!=null){
            curr.next=new ListNode(l1.val);
            curr=curr.next;
            l1=l1.next;
        }
        while(l2!=null){
            curr.next=new ListNode(l2.val);
            curr=curr.next;
            l2=l2.next;
        }
        return ret.next;
    }
}
```
[Top](#top)

## # <a name="n22"></a>

#### Problem Statement ([link]())
###### TAG [String] [backtracking] 

#### Thought
#####  - Key word
#####  - Key Idea
**dp**: ```dp(n) = ∑( "(" + (element in dp[i]) + ")" + (element in dp[n-1-i]))```  
4 nested loops

**backtracking**:
try string+"(" and string+")" when they are valid to do so.
#####  - Complexity 
Time:  O(2^n)?  
Space:  O(n^2)?
#####  - Boundary Conditions
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(M) Letter Combinations of a Phone Number, (E) Valid Parentheses

#### Solution
##### DP
```Java
public class Solution {
    public List<String> generateParenthesis(int n) {
        Map<Integer,List<String>> dp = new HashMap<>();
        dp.put(0,new ArrayList<>(Arrays.asList("")));
        for(int i=0;i<n;i++){
            List<String> list = new ArrayList<>();
            for(int j=0;j<=i;j++){
                for(String a:dp.get(j)){
                    for(String b:dp.get(i-j)){
                        list.add("("+a+")"+b);
                    }
                }
            }
            dp.put(i+1,list);
        }
        return dp.get(n);
    }
}
```
##### backtracking
```Java
public class Solution {
    private List<String> ret = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        backtracking("",0,0,n);
        return ret;
    }
    void backtracking(String attempt,int l,int r,int n){
        if(r==n) ret.add(attempt);
        else{
            if(n>l) backtracking(attempt+"(",l+1,r,n);
            if(l>r) backtracking(attempt+")",l,r+1,n);
        }
    }
}
```
[Top](#top)

## #23. Merge k Sorted Lists <a name="n23"></a>

#### Problem Statement ([link](https://leetcode.com/problems/merge-k-sorted-lists/))
###### TAG [Divide and Conquer] [Linked List] [Heap]
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.


#### Thought
#####  - Key word
#####  - Key Idea
merge sort
#####  - Complexity 
Time:  nlogn
Space:  n
#####  - Boundary Conditions
odd/even
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(E) Merge Two Sorted Lists, (M) Ugly Number II

#### Solution
```Java
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0) return null;
        if(lists.length==1) return lists[0];
        int len = lists.length;
        while(len>1){
            for(int i=0;i<len/2;i++){
                lists[i] = merge2(lists[i*2],lists[i*2+1]);
            }
            if(len%2==1) lists[len/2]=lists[len-1];
            len++;
            len/=2;
        }
        return lists[0];
    }
    
    ListNode merge2(ListNode a, ListNode b){
        ListNode ret = new ListNode(0), curr=ret;
        while(a!=null&&b!=null){
            if(a.val>b.val){curr.next = new ListNode(b.val);b=b.next;}
            else{curr.next = new ListNode(a.val);a=a.next;}
            curr=curr.next;
        }
        if(a==null) curr.next=b;
        else curr.next=a;
        return ret.next;
    }
}
```
[Top](#top)

## #24. Swap Nodes in Pairs <a name="n24"></a>

#### Problem Statement ([link](https://leetcode.com/problems/swap-nodes-in-pairs/))
###### TAG 
Given a linked list, swap every two adjacent nodes and return its head.

**For example**,
Given ```1->2->3->4```, you should return the list as ```2->1->4->3```.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
#### Thought
#####  - Key word
#####  - Key Idea
if the rest of chain has two nodes, exchange them
#####  - Complexity 
Time:  O(n)
Space:  O(1)
#####  - Boundary Conditions
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(H) Reverse Nodes in k-Group

#### Solution
```Java
public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode ret = new ListNode(0),prev=ret,curr=head;
        ret.next = head;
        while(curr!=null&&curr.next!=null){
            prev.next=curr.next;
            curr.next=curr.next.next;
            prev.next.next=curr;
            prev=curr;
            curr=curr.next;
        }
        return ret.next;
    }
}
```
[Top](#top)


## #25. Reverse Nodes in k-Group <a name="n25"></a>

#### Problem Statement ([link](https://leetcode.com/problems/reverse-nodes-in-k-group/))
###### TAG [linked list]

#### Thought
#####  - Key word
#####  - Key Idea
go k pointers first
#####  - Complexity 
Time: O(n) 
Space:  O(1)
#####  - Boundary Conditions
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(E) Swap Nodes in Pairs

#### Solution
```Java
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(k==1) return head;
        ListNode ret = new ListNode(0),last = ret,p=head;
        ret.next = head;
        while(p!=null){
            int count=0;
            while(count++<k){
                if(p==null) return ret.next;
                p=p.next;
            }
            
            ListNode prev = last.next, curr = prev.next;
            while(curr!=p){
                ListNode next=curr.next;
                curr.next=prev;
                prev=curr;
                curr=next;
            }
            last.next.next=p;
            curr=last.next;
            last.next = prev;
            last=curr;
        }
        return ret.next;
    }
}
```
[Top](#top)

## #26. Remove Duplicates from Sorted Array <a name="n26"></a>

#### Problem Statement ([link](https://leetcode.com/problems/remove-duplicates-from-sorted-array/))
###### TAG [two pointers]

#### Thought
#####  - Key word
#####  - Key Idea
#####  - Complexity 
Time:  O(n)
Space:  O(1)
#####  - Boundary Conditions
length==0
#####  - Mistakes
arr update with if block.
#####  - Further Considerations
#####  - Related Problems
(E) Remove Element

#### Solution
```Java
public class Solution {
    public int removeDuplicates(int[] nums) {
       if(nums.length==0) return 0;
       int p=1;
       for(int i=1;i<nums.length;i++){
           if(nums[i-1]!=nums[i])
            nums[p++]=nums[i];
       }
       return p;
    }
}
```
[Top](#top)

## #27. Remove Element <a name="n27"></a>

#### Problem Statement ([link](https://leetcode.com/problems/remove-element/))
###### TAG [two pointers]
Given an array and a value, remove all instances of that value in place and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

**Example**:
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.
#### Thought
#####  - Key word
#####  - Key Idea
#####  - Complexity 
Time:  n
Space:  1
#####  - Boundary Conditions
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(E) Remove Duplicates from Sorted Array, (E) Remove Linked List Elements, (E) Move Zeroes

#### Solution
```Java
public class Solution {
    public int removeElement(int[] nums, int val) {
        int p=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=val)nums[p++]=nums[i];
        }
        return p;
    }
}
```
[Top](#top)


## #28. Implement strStr() <a name="n"></a>

#### Problem Statement ([link]())
###### TAG [two pointers]
Implement strStr().

Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
#### Thought
#####  - Key word
#####  - Key Idea
#####  - Complexity 
Time:  m*n
Space:  1
#####  - Boundary Conditions
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(H) Shortest Palindrome, (E) Repeated Substring Pattern

#### Solution
```Java
public class Solution {
    public int strStr(String haystack, String needle) {
        int p=0,q=0;
        outer:
        for(int i=0;i<haystack.length()-needle.length()+1;i++){
            for(int j=0;j<needle.length();j++){
                if(haystack.charAt(i+j)!=needle.charAt(j)) continue outer;
            }
            return i;
        }
        return -1;
    }
}
```
[Top](#top)

## #29. Divide Two Integers <a name="n29"></a>

#### Problem Statement ([link](https://leetcode.com/problems/divide-two-integers/))
###### TAG [Math] [Binary Search]
Divide two integers without using multiplication, division and mod operator.

If it is overflow, return MAX_INT.
#### Thought
#####  - Key word
#####  - Key Idea
Log(n), double divisor first;
#####  - Complexity 
Time: log(n) 
Space:  1
#####  - Boundary Conditions
overflow, pos/neg
#####  - Mistakes
int a =  (-1<<31)  
a==-a&&a!=0;

#####  - Further Considerations
#####  - Related Problems

#### Solution
```Java
public class Solution {
    public int divide(int dividend, int divisor) {
        if(dividend==0) return 0;
        boolean neg = dividend>0&&divisor<0||dividend<0&&divisor>0;
        long a = dividend, b = divisor,count = 0,m=1,b2=Math.abs(b);
        a = Math.abs(a);
        b = b2;
        while(a>b){
            b<<=1;
            m<<=1;
        }
        while(b>=b2){
            if(a>=b){
                a-=b;
                count+=m;
                
            }
            b>>=1;
            m>>=1;
        }
        return (int)(neg?Math.max(Integer.MIN_VALUE,-count):Math.min(Integer.MAX_VALUE,count));
    }
}
```
[Top](#top)

## #30. Substring with Concatenation of All Words <a name="n30"></a>

#### Problem Statement ([link](https://leetcode.com/problems/substring-with-concatenation-of-all-words/))
###### TAG [hash table] [two pointers]
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:

	s: "barfoothefoobarman"
	words: ["foo", "bar"]

You should return the indices: [0,9].  
(order does not matter).
#### Thought
#####  - Key word
#####  - Key Idea
window, counter, count for "out of use" word
#####  - Complexity 
Time:  n
Space:  n
#####  - Boundary Conditions
empty, remove first(one pass, multi solutions), 
#####  - Mistakes

#####  - Further Considerations
#####  - Related Problems
(H) Minimum Window Substring

#### Solution
```Java
public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new ArrayList<>();
        if(s.length()==0||words.length==0) return ret;
        int wL = words[0].length(), winL = words.length*wL;
        Map<String,Integer> m = new HashMap<>();
        for(String word:words) m.put(word,m.getOrDefault(word,0)+1);
        for(int winInit = 0;winInit<wL;winInit++){
            Map<String,Integer> counter = new HashMap<>();
            int count = 0, index=winInit;
            for(int i=winInit;i+wL<=s.length()&&index+winL<=s.length();i+=wL){
                String sub = s.substring(i,i+wL);
                if(!m.containsKey(sub)){
                    index = i+wL;
                    count = 0;
                    counter.clear();
                }
                else{
                    if(index+winL==i){
                        String first = s.substring(index,index+wL);
                        counter.put(first,counter.get(first)-1);
                        index+=wL;
                        if(counter.get(first)>=m.get(first)) count--;
                    }
                    counter.put(sub,counter.getOrDefault(sub,0)+1);
                    if(counter.get(sub)>m.get(sub)) count++;
                    if(count==0&&i+wL==index+winL) ret.add(index);
                }
            }
        }
        return ret;
    }
}
```
[Top](#top)


## #31. Next Permutation <a name="n31"></a>

#### Problem Statement ([link]())
###### TAG [Array]
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
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
public class Solution {
    public void nextPermutation(int[] nums) {
        if(nums.length<2) return;
        int peak=0;
        for(int i=1;i<nums.length;i++) if(nums[i-1]<nums[i]) peak=i;
        if(peak!=0){
            int target = peak;
            for(int i=peak+1;i<nums.length;i++) if(nums[target]>nums[i]&&nums[peak-1]<nums[i]) target=i;
            swap(nums,target,peak-1);
        }
        heapsort(nums,peak);
    }
    void heapsort(int[] nums, int start){
        int len = nums.length-start;
        for(int i=len/2-1;i>=0;i--) heapify(nums,start,i,len+start);
        for(int i=len-1;i>=0;i--){
            swap(nums,start,start+i);
            heapify(nums,start,0,i+start);
        }
    }
    void heapify(int[] nums,int start, int begin,int end){
        int p = start+begin, c = start+begin*2+1;
        while(c<end){
            if(c+1<end&&nums[c+1]>nums[c]) c++;
            if(nums[c]<=nums[p]) break;
            swap(nums,p,c);
            p=c;
            c=p*2+1-start;
        }
    }
    void swap(int[] nums, int a,int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
```
[Top](#top)

## #32. Longest Valid Parentheses <a name="n32"></a>

#### Problem Statement ([link](https://leetcode.com/problems/longest-valid-parentheses/))
###### TAG [dp]
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
#### Thought
#####  - Key word
#####  - Key Idea
count, dp, ret
#####  - Complexity 
Time:  n
Space:  n
#####  - Boundary Conditions
dp[0] = 0
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(E) Valid Parentheses

#### Solution
```Java
public class Solution {
    public int longestValidParentheses(String s) {
        int ret=0,count=0;
        int dp[] = new int[s.length()+1];
        dp[0] = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='(') count++;
            else if(count>0){
                dp[i+1]=dp[i]+2;
                dp[i+1]+=dp[i+1-dp[i+1]];
                count--;
            }
            ret =  Math.max(ret,dp[i+1]);
        }
        return ret;
    }
}
```
[Top](#top)

## #33. Search in Rotated Sorted Array <a name="n"></a>

#### Problem Statement ([link]())
###### TAG 【binay search】
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
#### Thought
#####  - Key word
#####  - Key Idea
divide into rotated arr and normal arr, then recursively solve.
#####  - Complexity 
Time:  log(n)
Space:  1
#####  - Boundary Conditions
#####  - Mistakes
#####  - Further Considerations
all binary search can be recursive? 
#####  - Related Problems
(M) Search in Rotated Sorted Array II, (M) Find Minimum in Rotated Sorted Array

#### Solution
```Java
public class Solution {
    public int search(int[] nums, int target) {
        return searchRotated(nums,0,nums.length,target);
    }
    
    int searchRotated(int[] nums, int start, int end, int target){
        if (start>=end) return -1;
        if(nums[start]<nums[end-1]) return searchNormal(nums, start, end, target);
        int m = (start+end)/2;
        if(nums[m]==target) return m;
        if(nums[start]==target) return start;
        if(nums[start]<nums[m]){
            if(target<nums[m]&&target>nums[start]) return searchNormal(nums, start+1,m,target);
            else return searchRotated(nums,m+1,end,target);
        }
        else{
            if(target>nums[m]&&target<nums[start]) return searchNormal(nums,m+1,end,target);
            else return searchRotated(nums,start+1,m,target);
        }
    }
    
    int searchNormal(int[] nums, int start, int end, int target){
        if(start>=end) return -1;
        int m = (start+end)/2;
        if(nums[m]>target) return searchNormal(nums,start,m,target);
        else if(nums[m]<target) return searchNormal(nums,m+1,end,target);
        else return m;
    }
}
```
[Top](#top)

## #34. Search for a Range <a name="n34"></a>

#### Problem Statement ([link]())
###### TAG [binary search] [array]
Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
#### Thought
#####  - Key word
#####  - Key Idea
find one, then expand the range
#####  - Complexity 
Time:  n
Space:  1
#####  - Boundary Conditions
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(E) First Bad Version

#### Solution
```Java
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = search(nums,0,nums.length,target),end = start;
        if (start==-1) return new int[]{-1,-1};
        while(start>0&&nums[start-1]==nums[start]) start--;
        while(end<nums.length-1&&nums[end]==nums[end+1]) end++;
        return new int[]{start,end};
    }
    int search(int[] nums,int start, int end, int target){
        if(start>=end) return -1;
        int m = (start+end)/2;
        if(nums[m] == target) return m;
        if(nums[m]>target) return search(nums,start,m,target);
        else return search(nums,m+1,end,target);
    }
}
```
[Top](#top)

## #35. Search Insert Position <a name="n35"></a>

#### Problem Statement ([link]())
###### TAG [array] [binary search] 
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
#### Thought
#####  - Key word
#####  - Key Idea
instead of return -1, return index when start==end, that the place algo wants to go on, so it the inserting position.
#####  - Complexity 
Time:  log(n)
Space:  1
#####  - Boundary Conditions
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(E) First Bad Version


#### Solution
```Java
public class Solution {
    public int searchInsert(int[] nums, int target) {
        return bs(nums,0,nums.length,target);
    }
    
    int bs(int [] nums, int start, int end, int target){
        if (start==end) return start;
        int m = (start+end)/2;
        if(nums[m] == target) return m;
        if(nums[m]>target) return bs(nums,start,m,target);
        else return bs(nums,m+1,end,target);
    }
}
```
[Top](#top)

## #36. Valid Sudoku <a name="n"></a>

#### Problem Statement ([link](https://leetcode.com/problems/valid-sudoku/))
###### TAG [hash table]
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

#### Thought
#####  - Key word
#####  - Key Idea
find collision return false;
#####  - Complexity 
Time:  n  
Space:  1
#####  - Boundary Conditions
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(H) Sudoku Solver

#### Solution
```Java
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        return checkRowsAndCols(board)&&checkBlocks(board);
    }
    
    boolean checkRowsAndCols(char[][] board){
        for(int i=0;i<9;i++){
            int hashRow = 0, hashCol = 0;
            for(int j=0;j<9;j++){
                char cRow = board[i][j], cCol = board[j][i];
                if(cRow!='.'){
                    int bit = 1<<(cRow-'1');
                    if((bit&hashRow)>0) return false;
                    hashRow |= bit;
                }
                if(cCol!='.'){
                    int bit = 1<<(cCol-'1');
                    if((bit&hashCol)>0) return false;
                    hashCol |= bit;
                }
            }
        }
        return true;
    }
    
    boolean checkBlocks(char[][] board){
        for(int i=0;i<9;i++){
            int hashBlock = 0;
            for(int j=0;j<9;j++){
                char c = board[(i/3)*3+j/3][(i%3)*3+j%3];
                if(c!='.'){
                    int bit = 1<<(c-'1');
                    if((bit&hashBlock)>0) return false;
                    hashBlock |= bit;
                }
            }
        }
        return true;
    }
}
```
[Top](#top)



## #37. Sudoku Solver <a name="n"></a>

#### Problem Statement ([link](https://leetcode.com/problems/sudoku-solver/))
###### TAG [hash table] [backtracking]

#### Thought
#####  - Key word
#####  - Key Idea
#####  - Complexity 
Time:  n^2
Space:  1
#####  - Boundary Conditions
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(E) Valid Sudoku

#### Solution
```Java
public class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    
    boolean solve(char[][] board){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]!='.') continue;
                for(int d=1;d<10;d++){
                    if(validToPut(board,i,j,d)){
                        board[i][j] = (char)(d+'0');
                        if(solve(board)) return true;
                        board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }
    
    boolean validToPut(char[][] board, int i, int j, int d){
        int hashR = 0, hashC = 0, hashB = 0;
        for(int k=0;k<9;k++){
            char r = board[i][k],c = board[k][j],b=board[i/3*3+k/3][(j/3*3+k%3)];
            if(r!='.'&&r-'0'== d||c!='.'&&c-'0'== d||b!='.'&&b-'0'== d) return false;
        }
        return true;
    }
}
```
[Top](#top)

## #38. Count and Say <a name="n"></a>

#### Problem Statement ([link](https://leetcode.com/problems/count-and-say/))
###### TAG [String]
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
#### Thought
#####  - Key word
#####  - Key Idea
#####  - Complexity 
Time:  n^2
Space:  n
#####  - Boundary Conditions
#####  - Mistakes
#####  - Further Considerations
#####  - Related Problems
(M) Encode and Decode Strings

#### Solution
```Java
public class Solution {
    public String countAndSay(int n) {
        if(n<2) return "1";
        String ret = "11";
        for(int i=2;i<n;i++){
            ret = help(ret);
        }
        return ret;
    }
    
    String help(String s){
        StringBuilder sb = new StringBuilder();
        char prev = s.charAt(0);
        int count=1;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==prev) count++;
            else{
                sb.append(count);
                sb.append(prev);
                count=1;
                prev = s.charAt(i);
            }
        }
        sb.append(count);
        sb.append(prev);
        return sb.toString();
    }
}
```
[Top](#top)

## #39. Combination Sum <a name="n"></a>

#### Problem Statement ([link](https://leetcode.com/problems/combination-sum/))
###### TAG [backtracking]
Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.  

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.    
The solution set must not contain duplicate combinations.  

For example, given candidate set [2, 3, 6, 7] and target 7,  
 
A solution set is: 

	[
	  [7],
	  [2, 2, 3]
	]
#### Thought
#####  - Key word
#####  - Key Idea
#####  - Complexity 
Time:  n^2
Space:  n^2
#####  - Boundary Conditions
#####  - Mistakes
duplicated solutions
#####  - Further Considerations
#####  - Related Problems
(M) Letter Combinations of a Phone Number, (M) Combination Sum II, (M) Combinations, (M) Combination Sum III, (M) Factor Combinations, (M) Combination Sum IV


#### Solution
```Java
public class Solution {
    private List<List<Integer>> ret = new ArrayList<>();
    private List<Integer> list = new ArrayList<>();
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        help(candidates,target,0);
        return ret;
    }
    
    void help(int[] candidates, int target,int start){
        if(target==0) ret.add(new ArrayList<>(list));
        for(int i=start;i<candidates.length;i++){
            if(candidates[i]<=target){
                list.add(candidates[i]);
                help(candidates,target-candidates[i],i);
                list.remove(list.size()-1);
            } 
        }
    }
}
```
[Top](#top)

## #40. Combination Sum II <a name="n40"></a>

#### Problem Statement ([link](https://leetcode.com/problems/combination-sum-ii/))
###### TAG [array][backtracking]
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.  
The solution set must not contain duplicate combinations.  
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,   
A solution set is: 
	
	[
	  [1, 7],
	  [1, 2, 5],
	  [2, 6],
	  [1, 1, 6]
	]
#### Thought
#####  - Key word
#####  - Key Idea
sort, remove duplicated(same number start only once)
#####  - Complexity 
Time:  n^2  
Space:  n^2
#####  - Boundary Conditions
#####  - Mistakes
not only starter changed for next recursive call, but more strict when add solutions to the list.
#####  - Further Considerations
#####  - Related Problems
(M) Combination Sum

#### Solution
```Java
public class Solution {
    private List<List<Integer>> ret = new ArrayList<>();
    private List<Integer> list = new ArrayList<>();
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        help(candidates,target,0);
        return ret;
    }
    
    void help(int[] candidates, int target, int start){
        if(target == 0) ret.add(new ArrayList<>(list));
        for(int i=start;i<candidates.length;i++){
            if(candidates[i]<=target){
                if(i>0&&candidates[i-1]==candidates[i]&&start<i) continue;
                list.add(candidates[i]);
                help(candidates,target-candidates[i],i+1);
                list.remove(list.size()-1);
            }
        }
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



