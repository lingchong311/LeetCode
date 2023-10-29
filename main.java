import java.util.HashMap;
import java.util.Stack;

public class main {
    public static void main (String[] args){
        //leetcode 1st question two sum
        int[] twoSumArray = {2,7,11,15};
        int twoSumTraget = 9;
        int[] twoSumResult = twoSum(twoSumArray, twoSumTraget);

        //leetcode 20th question valid parentheses
        String parenthesesString = "()[]{}";
        boolean isValidParentheses = isValid(parenthesesString);
    }

    /*
    Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    You can return the answer in any order.
    */
    public static int[] twoSum(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        int[] result = new int[2];
        for (int i = 0; i < length; i++){
            if(map.containsKey(target - nums[i])){
               result[1] = i;
               result[0] = map.get(target - nums[i]);
               break;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    /*Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

    An input string is valid if:

    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Every close bracket has a corresponding open bracket of the same type.
    */
    public static boolean isValid(String s){
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : arr) {
            if(c == '(')
                stack.push(')');
            else if(c == '[')
                stack.push(']');
            else if(c == '{')
                stack.push('}');
            else if(stack.isEmpty() ||stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }



}
