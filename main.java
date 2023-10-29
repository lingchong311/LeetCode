import java.util.HashMap;

public class main {
    public static void main (String[] args){
        int[] twoSumArray = {2,7,11,15};
        int twoSumTraget = 9;
        int[] twoSumResult = twoSum(twoSumArray, twoSumTraget);
    }

    /*
    Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.
*/
    public static int[] twoSum(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
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



}
