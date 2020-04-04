# Dynamic Program (DP)
Ref: [geeksforgeeks](https://www.geeksforgeeks.org/dynamic-programming/)

## Idea
The idea is to simply store the results of *subproblems*, so that we do not have to re-compute them when needed later.

### Keys
1. initial state
2. connection between problem n & n+1 
    - Assuming there is an answer to problem `n`, how to solve `n+1`
3. Sometimes, the original problem is not DP, need decomposite it into DP problem
    - 1 dim or more ?
4. Sometimes, result doesn't need to store in array, 
    - usually because only need to memorize the last result

## Examples

### maximum subarray
[leetcode](https://leetcode.com/problems/maximum-subarray/)
```
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
```

#### solution:
key: two pointers, DP, must include right pointer

1. how to connect to subproblem
2. n^2 space: any array between index x, y  
   => 
   n * 1 dim dp: 0..y for y in range(n)

```python
def max_subarray(nums):
    # ans to subproblem 0 is nums[0]
    dp = nums[:1]
    for i in nums[1:]:
        # ans to subproblem 0..i must include i as the last element
        dp.append(max(dp[-1] + i, i)) 
    return max(dp)
```
