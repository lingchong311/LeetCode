////import javax.swing.tree.TreeNode;
//import java.util.*;
//
//public class main {
//    public static void main (String[] args){
////        //leetcode 1st question two sum
////        int[] twoSumArray = {2,7,11,15};
////        int twoSumTraget = 9;
////        int[] twoSumResult = twoSum(twoSumArray, twoSumTraget);
////
////        //leetcode 20th question valid parentheses
////        String parenthesesString = "()[]{}";
////        boolean isValidParentheses = isValid(parenthesesString);
////
////        //leetcode 125th valid palindrome
////        String validPalindrome = "A man, a plan, a canal: Panama";
////        boolean isValid = isPalindrome(validPalindrome);
////
////        //leetcode 226 invert binary tree
////
////        //leetcode 57 insert intervals
//
//        List<String> list = new ArrayList<>();
//        list.add("abcdefghijklmnopqrstuvwxyz");
//        System.out.println(maxLength(list));
//
//
//    }
//
//    /*
//    Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
//
//    You may assume that each input would have exactly one solution, and you may not use the same element twice.
//
//    You can return the answer in any order.
//    */
//    public static int[] twoSum(int[] nums, int target){
//        HashMap<Integer, Integer> map = new HashMap<>();
//        int length = nums.length;
//        int[] result = new int[2];
//        for (int i = 0; i < length; i++){
//            if(map.containsKey(target - nums[i])){
//               result[1] = i;
//               result[0] = map.get(target - nums[i]);
//               break;
//            }
//            map.put(nums[i], i);
//        }
//        return result;
//    }
//
//    /*Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
//    An input string is valid if:
//
//    Open brackets must be closed by the same type of brackets.
//    Open brackets must be closed in the correct order.
//    Every close bracket has a corresponding open bracket of the same type.
//    */
//    public static boolean isValid(String s){
//        char[] arr = s.toCharArray();
//        Stack<Character> stack = new Stack<>();
//        for (char c : arr) {
//            if(c == '(')
//                stack.push(')');
//            else if(c == '[')
//                stack.push(']');
//            else if(c == '{')
//                stack.push('}');
//            else if(stack.isEmpty() ||stack.pop() != c)
//                return false;
//        }
//        return stack.isEmpty();
//    }
//
//    public static boolean isPalindrome(String s){
//        StringBuilder cleaningString = new StringBuilder();
//        for (char c: s.toCharArray()) {
//            if(Character.isLetterOrDigit(c)){
//                cleaningString.append(Character.toLowerCase(c));
//            }
//        } // Convert original string to a cleaning string
//        String processedString = cleaningString.toString();
//
//        int i = 0; // Set a pointer points first character
//        int j = processedString.length() - 1; // Set a pointer points last character
//        while (i < j){
//            if (processedString.charAt(i) != processedString.charAt(j)){
//                return false;
//            }
//            i++;
//            j--;
//        }
//        return true;
//        }
//
//    /*226. Invert Binary Tree
//    Easy
//    13.3K
//    190
//    Companies
//    Given the root of a binary tree, invert the tree, and return its root.*/
//        public static TreeNode invertTree(TreeNode treeNode){
//            if (treeNode == null){
//                return null;
//            }
//
//            TreeNode temp = treeNode.left;
//            treeNode.left = treeNode.right;
//            treeNode.right = temp;
//
//            invertTree(treeNode.left);
//            invertTree(treeNode.right);
//
//            return treeNode;
//        }
//
//    /*You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
//
//    Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
//
//    Return intervals after the insertion.
//
//    */
//    public static int[][] insert(int[][] intervals, int[] newInterval){
//        List<int[]> list = new ArrayList<>();
//        int n = intervals.length;
//        int i = 0;
//
//        while (i < n && intervals[i][0] <= newInterval[1]){
//            list.add(intervals[i]);
//            i++;
//        }
//
//        while(i < n && intervals[i][0] <= newInterval[1]){
//           newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
//           newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
//           i++;
//        }
//
//        while (i < n){
//            list.add(intervals[i]);
//            i++;
//        }
//
//        return list.toArray(new int[list.size()][2]);
//    }
//
//
//
//        private static int maxLength(String first, int index, List<String> arr) {
//            if(arr.size()<2){
//                if(isUnique(arr.get(0))){
//                    return arr.get(0).length();
//                }
//                else
//                    return 0;
//            }
//
//            int left = 0;
//            int right = left + 1;
//
//            int length = 0;
//            int max = 0;
//
//
//            String str = "";
//
////            while(left < arr.size() && right < arr.size()){
////                while (right < arr.size()){
////                    str = arr.get(right) + arr.get(left);
////                    if(isUnique(str)){
////                        length = str.length();
////                        max = Math.max(length, max);
////                    }
////                    right ++;
////                }
////                left ++;
////            }
//            for (int i = 0; i < arr.size(); i++) {
//                if isUnique(arr.get(i)){
//                    st
//                }
//            }
//            return max;
//        }
//
//        private static Boolean isUnique(String a){
//            Set<Character> set = new HashSet<>();
//            for (char c:a.toCharArray()
//                 ) {
//                if(set.contains(c))
//                    return false;
//                else
//                    set.add(c);
//            }
//            return true;
//        }
//    }
//
//
//
//
