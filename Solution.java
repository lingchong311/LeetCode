import java.util.*;

public class Solution {

    public static void main(String[] args) {
       String s = "abc";
       String p = "a*a";

       List<String> list = new ArrayList<>();
       list.add("po");
       list.add("zsz");

        System.out.println(isPalindrome(s));
    }

    public static String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] arr = s.toCharArray();

        for (char item : arr
        ) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (a, b) -> (b.getValue() - a.getValue()));


        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> item : list
        ) {
            for (int i = 0; i < item.getValue(); i++) {
                result.append(item.getKey());
            }
        }

        return result.toString();
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows)
            return s;

        StringBuilder[] stringBuilder = new StringBuilder[numRows];

        for (int i = 0; i < numRows; i++) {
            stringBuilder[i] = new StringBuilder();
        }

        int index = 0;
        int direction = 1;

        for (char item : s.toCharArray()
        ) {
            stringBuilder[index].append(item);
            if (index == 0)
                direction = 1;
            else if (index == numRows - 1)
                direction = -1;
            index += direction;
        }

        StringBuilder result = new StringBuilder();

        for (StringBuilder item : stringBuilder
        ) {
            result.append(item);
        }

        return result.toString();
    }

    public static int numSquares(int n) {

        int[] dp = new int[n + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int m = 1; m * m < i; m++) {
                dp[i] = Math.min(dp[i], dp[i - m * m] + 1);
            }
        }

        return dp[n];
    }

    public static String reverseWords(String s) {
        String[] strArray = s.trim().split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = strArray.length - 1; i > 0; i--) {
            result = result.append(strArray[i]).append(" ");
        }

        return result.append(strArray[0]).toString();
    }


    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int leftProduct = 1;
        int rightProduct = 1;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            //get left production
            result[i] = leftProduct;
            leftProduct = leftProduct * nums[i]; //当对于第n个元素时， result[n] = product * nums[n-1]
        }

        for (int i = n - 1; i >= 0; i--) {
            result[i] = result[i] * rightProduct;
            rightProduct = rightProduct * nums[i];
        }

        return result;
    }

    public static boolean increasingTriplet(int[] nums) {
        int temp1 = Integer.MAX_VALUE;
        int temp2 = Integer.MAX_VALUE;

        for (int item : nums
        ) {
            if (item <= temp1) {
                temp1 = item;
            } else if (item <= temp2) {
                temp2 = item;
            } else
                return true;
        }

        return false;
    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int maxLen = 0;
        int maxIndex = 0;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }

        List<Integer> list = new ArrayList<>();
        int currLen = maxLen;
        int currVal = nums[maxIndex];

        for (int i = maxIndex; i >= 0; i--) {
            if (currVal % nums[i] == 0 && dp[i] == currLen) {
                list.add(nums[i]);
                currVal = nums[i];
                currLen--;
            }
        }
        return list;
    }

    public static boolean isMatch(String s, String p) {
        int sPsition = 0;
        int pPosition = 0;
        int star = -1;
        int match = 0;

        while(sPsition < s.length()){
            if (pPosition < p.length() && (p.charAt(pPosition) == s.charAt(sPsition) || p.charAt(pPosition) == '?')){
                pPosition ++;
                sPsition ++;
            } else if (pPosition < p.length() && p.charAt(pPosition) == '*') {
                match = sPsition;
                star = pPosition;
                pPosition ++;
            } else if (star != -1) {
                pPosition = star + 1;
                sPsition = match;
                match ++;
            }
            else
                return false;
        }

        while (pPosition < p.length() && p.charAt(pPosition) == '*'){
            pPosition ++;
        }

        return pPosition == p.length();
    }

    public static boolean comparison(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;  // 初始化字符串和模式的指针，以及匹配状态和上一个星号的位置

        while (s < str.length()) {  // 遍历字符串 str
            // 检查当前字符是否匹配
            if (p < pattern.length() && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))) {
                s++;  // 匹配，字符串指针后移
                p++;  // 模式指针后移
            } else if (p < pattern.length() && pattern.charAt(p) == '*') {  // 模式中遇到了星号
                starIdx = p;  // 记录星号的位置
                match = s;    // 记录当前匹配位置
                p++;          // 模式指针后移
            } else if (starIdx != -1) {  // 前一个字符不是星号
                p = starIdx + 1;  // 模式指针移动到星号后一位
                match++;          // 匹配位置后移一位
                s = match;        // 字符串指针回到匹配位置后一位
            } else {  // 字符不匹配，且前一个字符也不是星号
                return false;  // 返回 false
            }
        }

        // 检查是否还有剩余的星号
        while (p < pattern.length() && pattern.charAt(p) == '*') {
            p++;  // 模式指针后移，直到最后一个星号
        }

        return p == pattern.length();  // 返回是否模式指针移动到了模式末尾
    }

    public static Boolean isMatch1(String s, String p){
        int starIndex = p.indexOf('*');

        String p_prefix = p.substring(0,starIndex);
        String p_suffix = p.substring(starIndex + 1);

        if(!s.startsWith(p_prefix)){
            return false;
        }

        int s_suffixLength = s.length() - p_prefix.length();

        int pIndex = p_suffix.length() - 1;
        int sIndex = s.length() - 1;

        if(p_suffix.length() > s_suffixLength)
            return false;



        while( pIndex > 0 ){
            if(s.charAt(sIndex) == p_suffix.charAt(pIndex)){
                pIndex --;
                sIndex --;
            } else
                return false;
        }


        return true;


    }

    public static String firstPalindrome(String[] words) {
        for (String word: words
             ) {
            if(isPalindrome(word)){
                return word;
            }
        }

        return null;
    }

    public static Boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;

        while(left < word.length() && right > 0){
            if(word.charAt(left) != word.charAt(right)){
                return false;
            }
            left ++;
            right --;
        }

        return true;
    }

    public int findDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i ++){
            if (map.containsKey(nums[i])){
                return nums[i];
            }
            map.put(nums[i], i);
        }
        return 0;
    }

    public boolean isPowerOfTwo(int n) {
        if ( n == 0 )
            return false;

        while( n > 0 ){
            if (n % 2 != 0){
                break;
            }
            if ( n == 1)
                return true;
            n = n / 2;
        }
        return false;
    }

    public int longestStrChain(String[] words) {
        Map<String, Integer> map = new HashMap<>();

        Arrays.sort(words, Comparator.comparingInt(String::length));

        int res = 0;

        for (String s: words
             ) {
            map.put(s, 1);
            for (int i = 0; i < s.length(); i++){
                StringBuilder stringBuilder = new StringBuilder(s);
                String next = stringBuilder.deleteCharAt(i).toString();

                if(map.containsKey(next)){
                    map.put(s, Math.max(map.get(s), map.get(next) + 1));
                }
            }
            res = Math.max(res, map.get(s));
        }

        return res;

    }

//    public boolean find132pattern(int[] nums) {
//
//        int i = 0;
//
//        while(i < nums.length){
//            int j = i + 1;
//            if(nums[i] >= nums[j]){
//                j ++;
//            } else {
//            while( j < nums.length){
//                int k = j + 1;
//                if （nums(k) <= nums(i) || nums(k) >= nums[j]){
//
//                }
//            }
//        }
//        }
//
//    }

    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        
        if (nums.length == 0)
            return -1;
        else if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i ++){
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for(int i = 1; i < n; i ++){
            dp[0][i] = 1;
        }
        for(int i = 1; i < m; i ++){
            dp[i][0] = 1;
        }

        for (int i = 0; i < m; i ++){
            for(int j = 0; j < n; j ++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];

    }




}
