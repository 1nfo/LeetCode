# Leetcode Notes

# Index

### In Sequence
1. [Two Sum]()

### By Category

---

# Problems, Thoughts & Solutions

## # 1. Two Sum

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

## # 2. Add Two Numbers

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
## # 3. Longest Substring Without Repeating Characters

#### Problem Statement ([link](https://leetcode.com/problems/longest-substring-without-repeating-characters/))
###### TAG [Hash Table] [Two Pointers] [String] [DP]

#### Thought
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



## # ...

#### Problem Statement ([link]())
###### TAG 

#### Thought
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