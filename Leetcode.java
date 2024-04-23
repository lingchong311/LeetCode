import com.sun.source.tree.BreakTree;
import org.w3c.dom.NodeList;

import javax.swing.*;
import java.lang.invoke.StringConcatFactory;
import java.lang.reflect.Array;
import java.util.*;

public class Leetcode {

    public ListNode removeNthFromEnd(ListNode head, int n) {
         ListNode dummy = new ListNode(0);
         dummy.next = head;

         ListNode fast = dummy;
         ListNode slow = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }


    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int l = 0; //pointer from left side
        int r = tokens.length - 1;//pointer from right side

        int score = 0;//score
        int maxScore = 0;//max_scroe

        while(l <= r){
            if(tokens[l] <= power){
                power = power - tokens[l];
                l ++;
                score ++;
                maxScore = Math.max(maxScore, score);
            } else if (score > 0) {
                power = power + tokens[r];
                score --;
                r --;
            }else {
                break;
            }
        }

        return maxScore;
    }

    public int minimumLength(String s) {
         int l = 0;
         int r = s.length() - 1;

         while(l < r && s.charAt(l) == s.charAt(r)){
             char c = s.charAt(l);

             while (l <= r && c == s.charAt(l)){
                 l ++;
             }

             while (l <= r && c == s.charAt(r)){
                 r --;
             }
         }

         return r - l + 1;
    }

    public String maximumOddBinaryNumber(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        int count0 = 0 , count1 = 0;
        for (char item : s.toCharArray()
             ) {
            if (item == '0')
                count0 ++;
            else if (item == '1') {
                count1 ++;
            }
        }

        stringBuilder.append("1".repeat(count1 - 1));
        stringBuilder.append("0".repeat(count0));
        stringBuilder.append("1");

        return stringBuilder.toString();

    }

    public int[] sortedSquares(int[] nums) {
        int[] a = new int[nums.length];

        for (int i = 0; i < nums.length; i++){
            a[i] = nums[i] * nums[i];
        }

        Arrays.sort(a);
        return a;
    }

    public int kthFactor(int n, int k) {
         int count = 0;

        for (int i = 1; i <= n; i++){
            if( n % i == 0){
                count ++;
            }
            if(count == k){
                return i;
            }
        }

        return -1;
    }

    public boolean hasCycle(ListNode head) {
         if (head == null || head.next == null)
             return false;

        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && slow.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow)
                return true;
        }

        return false;
    }

    public static int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                left = i;

                while (right >= i) {
                    if (nums[right] == target) {
                        return result = new int[]{left, right};
                    }
                    right --;
                }
            }
        }
        return null;
    }

    public int maxProduct(int[] nums) {
        Arrays.sort(nums);

        int i = nums.length - 1;
        int j = i - 1;

        return (nums[i] - 1)*(nums[j] - 1);
    }

    public static ListNode middleNode(ListNode head) {
        ListNode node = head;
        int count = 0;
        while (node != null){
            node = node.next;
            count ++;
        }
        for (int i = 0; i < count/2; i ++){
                head = head.next;
            }
             return head;
    }
//  dp[m][n]  max(dp[m-1][n], dp[m][n-1]);
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];


        for (int i = 0; i < m; i ++){
            for (int j =0; j < n; j ++){
                if(i == 0 && j == 0){
                    dp[0][0] = grid[0][0];
                } else if (i == 0 && j > 0) {
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                } else if (i > 0 && j == 0) {
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                } else
                dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
            }
        }
        return dp[m - 1][ n - 1];
    }
/*
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]

I: 7 - 2 -> 7 - 2 -2      -> 7-2-2-2
                          -> 7-2-2-3 X

         -> 7 - 2 - 3     -> 7-2-3-2 0
                          -> 7-2-3-3 X

         -> 7 - 2 - 6 X
         -> 7 - 2 - 7 X



* */

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp;

        while(cur != null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;
    }


    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a,b) -> {
            if( a[0] == b [0])
                return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });

        int count = 0;
        int preLast = points[0][1]; //假设第一支箭在第一个点最右边

        for (int i = 1; i < points.length; i++) {
            if (preLast >= points[i][0]){
                if(preLast > points[i][1])
                    preLast = points[i][1];
            } else {
                preLast = points[i][1];
                count ++;
            }
        }
        return count + 1;
    }

    public static int constrainedSubsetSum(int[] nums, int k) {
//        int[] dp = new int[nums.length];
//        int max = nums[0];
//        for (int i = 0; i < dp.length; i++) {
//            dp[i] = Integer.MIN_VALUE;
//        }
//        dp[0] = nums[0];
//
//        for (int i = 1; i < nums.length; i ++){
//            for (int j = 1; j <= k && i - j >= 0; j ++){
//                dp[i] = Math.max(nums[i], Math.max(dp[i], dp[i - j] + nums[i]));
//            }
//            max = Math.max(max, dp[i]);
//        }
//
//        return max;

        Deque<Integer> deque = new ArrayDeque<>();
        int[] dp = new int[nums.length];
        int max = nums[0];

        for (int i = 0; i < nums.length; i ++){
            if (!deque.isEmpty() && deque.peekFirst() < (i - k)){
                deque.pollFirst();
            }
            if (!deque.isEmpty()){
                dp[i] = Math.max(nums[i], nums[i] + dp[deque.peekFirst()]);
            } else {
                dp[i] = nums[i];
            }
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]){
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1){
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }



    public int minMoves(int[] nums) {
        int sum = 0;
        int min = Integer.MAX_VALUE;

        for (int item: nums){
            min = Math.min(min, item);
        }

        for (int item: nums){
            sum = sum + (item - min);
        }

        return sum;


    }


    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode node = list1;
        ListNode temp;

        for (int i = 0; i < a - 1; i ++){
            node = node.next;
        }

        temp = node;

        for (int i = a; i < b + 2 ; i++) {
            node = node.next;
        }

        ListNode tail = node;
        ListNode temp2 = list2;

        while (list2.next != null ){
            list2 = list2.next;
        }

        temp.next = temp2;
        list2.next = tail;

        return list1;


    }

    public boolean isPalindrome(ListNode head) {
        List<Integer> list= new ArrayList<>();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }

        int left = 0;
        int right = list.size() - 1;

        while(left <= right ){
            if (list.get(left) != list.get(right))
                return false;
            left ++;
            right --;
        }
        return true;
    }

    public List<Integer> findDuplicates(int[] nums) {
//        List<Integer> result = new ArrayList<>();
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < nums.length; i ++){
//           if (map.containsKey(nums[i])){
//               map.put(nums[i], map.get(nums[i]) + 1);
//           } else {
//               map.put(nums[i], 1);
//           }
//        }
//        map.forEach((key, value) -> {
//            if (map.get(key) > 1){
//                result.add(key);
//            }
//            });
//        return result;

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int x  = Math.abs(nums[i]);
            if (nums[x - 1] < 0)
                list.add(nums[x - 1]);
            nums[x - 1] = nums[x - 1] * (-1);
        }
        return list;
    }







    public static int pivotInteger(int n) {
        for(int i = 1; i <= n; i++){
            int a = (1 + i) * i/2;
            int b = (i + n) * (n - i + 1)/2;
            if((1 + i) * i / 2 == (i + n) * (n - i + 1) / 2){
                return i;
            }
        }
        return -1;
    }

    public int appendCharacters(String s, String t) {
        int i = 0;
        int j = 0;

        while(i < s.length() && j < t.length()){
            if(s.charAt(i) == t.charAt(j))
                j ++;
            i ++;
        }
        return t.length() - j;
    }

//    HashMap<Integer, Integer> map = new HashMap<>();
    // map <num,frequency>

    public static int maxFrequencyElements(int[] nums) {
        HashMap<Integer, Integer> map =  new HashMap<>();
        int result = 0;
        int count = 0;

        for (int num: nums){
            if(!map.containsKey(num)){
                map.put(num, 1);
            }else {
               map.put(num, map.get(num) + 1);
            }
        }

        for (int value: map.values()){
            result = Math.max(result, value);
        }

        for (int value: map.values()){
            if(value == result)
                count ++;
        }

        return result * count;

    }

    public static int firstMissingPositive(int[] nums) {
        int index = 0;
        while (index < nums.length){
            int correct = nums[index] - 1;
            if (correct != index && nums[index] < nums.length && nums[index] > 0){
                if (nums[index] != nums[correct]){
                    swap(nums, index, correct);
                }
                else 
                    index ++;
            }
            else 
                index ++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }

        return nums.length + 1;
    }

    private static void swap(int[] nums, int index, int correct){
        int temp;

        temp = nums[index];
        nums[index] = nums[correct];
        nums[correct] = temp;
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int result = 0;
        int left = 0;
        int product = 1;

        for (int i = 0; i < n; i ++){
            int count = 0;
            product = product * nums[i];

            while (product >= k && left <= i){
                product = product / nums[left];
                left ++;
            }
            count = i - left + 1;
            result = result + count;
        }
        return result;
    }

    public List<String> buildArray(int[] target, int n) {
        int index = 0;
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i ++){
            if (target[index] == i){
                list.add("Push");
                index ++;
            } else {
                list.add("Push");
                list.add("Pop");
            }
            if (target.length == index)
                break;
        }
        return list;
    }

    // aaabbbbbbccc

    public static int compress(char[] chars) {
        StringBuilder result = new StringBuilder();
        int n = chars.length;
        int count = 1;

        for (int i = 0; i < n; i ++){
            int right = i + 1;
             if (right < n && chars[right] == chars[i]){
                 count ++;
             } else {
                 result.append(chars[i]);
                 if (count > 1)
                     result.append(count);
                 count = 1;
             }
        }

        return result.length();
    }

    public static void moveZeroes(int[] nums) {
        int no0Index = 0;

        for (int i = 0; i < nums.length; i ++){
            if (nums[i] != 0){
                nums[no0Index] = nums[i];
                no0Index ++;
            }
        }

        for (int i = no0Index; i < nums.length; i ++){
            nums[i] = 0;
        }
    }

    public static int maxOperations(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        Arrays.sort(nums);
        int left  = 0;
        int right = n - 1;

        while (left < right){
            if (nums[left] + nums[right] == k){
                count ++;
                left ++;
                right --;
            } else if (nums[left] + nums[right] > k) {
                right --;
            } else
                left ++;
        }

        return count;
    }

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i ++){
            sum = sum + nums[i];
        }

        int maxSum = sum;

        for (int i = k; i < nums.length; i ++){
            sum = sum + nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum/k;
    }

    public int maxVowels(String s, int k) {
        int num = 0;

        for (int i = 0; i < k; i ++){
            if(isVowelLetter(s.charAt(i))){
                num ++;
            }
        }

        int maxNum = num;

        for (int i = k; i < s.length(); i ++){
            if (isVowelLetter(s.charAt(i) )){
                num ++;
            }
            if (isVowelLetter(s.charAt(i - k))){
                num --;
            }

            maxNum = Math.max(maxNum, num);
        }
        return maxNum;
    }

    private boolean isVowelLetter(char a){
        return a =='a' || a == 'e' || a == 'i' || a == 'o' || a == 'u';
    }

    private int numOfVowelLetter(StringBuilder str){
        int count = 0;
        for (int i = 0; i < str.length(); i ++){
            if (str.charAt(i) == 'a'
                    || str.charAt(i) == 'e'
                    || str.charAt(i) == 'i'
                    || str.charAt(i) == 'o'
                    || str.charAt(i) == 'u')
                count ++;
        }

        return count;
    }

    public int lengthOfLastWord(String s) {
        s = s.trim();
        int length = 0;

        for (int i = s.length() - 1; i >= 0; i --){
            if (s.charAt(i) != ' '){
                length ++;
            } else
                break;
        }

        return length;
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;

        HashMap<Character, Character> map = new HashMap<>();
        HashMap<Character, Character> reverseMap = new HashMap<>();

        for (int i = 0; i < s.length(); i ++){
           if (map.containsKey(s.charAt(i))){
               if (map.get(s.charAt(i)) != t.charAt(i)){
                   return false;
               }
           } else {
               if (reverseMap.containsKey(t.charAt(i))){
                   return false;
               }

               map.put(s.charAt(i), t.charAt(i));
               reverseMap.put(t.charAt(i), s.charAt(i));
           }
            }
        return true;
    }

    public  static int getWinner(int[] arr, int k) {
        int count = 0;

        int winner = arr[0];
        int remove = 0;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for (int num : arr ){
            list.add(num);
            map.put(num, 0);
        }

        while (!list.isEmpty()){
            if (list.get(0) > list.get(1)){
                winner = list.get(0);
                remove = list.get(1);
                map.put(winner, map.get(winner) + 1);
                list.remove(1);
                list.add(remove);
                if (map.get(winner) == k)
                    return winner;
            } else {
                winner = list.get(1);
                remove = list.get(0);
                map.put(winner, map.get(winner) + 1);
                list.remove(0);
                list.add(remove);
                if (map.get(winner) == k)
                    return winner;
            }
        }

        return -1;

    }

//    public static boolean exist(char[][] board, String word) {
//        int m = board.length;
//        int n = board[0].length;
//        boolean res = false;
////        boolean[][] maze = new boolean[m][n];
//
//        for (int i = 0; i < m; i ++){
//            for (int j = 0; j < n; j++) {
//                if (board[i][j] == word.charAt(0)){
//                    boolean[][] maze = new boolean[m][n];
//                    res = isMatch(word, board, i, j, 0, maze);
//                    if (res)
//                        return true;
//                }
//
//            }
//        }
//
//        return false;
//    }
// (1)+((2))+(((3)))
    public int maxDepth(String s) {
        int res = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                count ++;
                res = Math.max(count, res);
            } else if (s.charAt(i) == ')'){
                count --;
            }
        }
        return res;
    }
//    "leEeetcode"
    public  static String makeGood(String s) {
        StringBuilder str = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        StringBuilder res = new StringBuilder();

        for (char a : s.toCharArray()){
            if (!stack.isEmpty() && stack.peek() != a && Character.toLowerCase(stack.peek()) == Character.toLowerCase(a)){
                stack.pop();
            } else {
                stack.push(a);
            }
        }

        while (!stack.isEmpty()){
            str.append(stack.pop());
        }

        for (int i = str.length() - 1; i >= 0 ; i--) {
            res.append(str.charAt(i));
        }
        return res.toString();
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0)
                nums[index] = nums[index] * (-1);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i ++){
            if (nums[i] > 0 )
                list.add(i + 1);
        }
        return list;
    }


    public static int countStudents(int[] students, int[] sandwiches) {
//        Stack<Integer> stack = new Stack<>();
//        Arrays.stream(sandwiches).forEach(element -> stack.push(element));
//
//        Queue<Integer> queue = new ArrayDeque<>();
//        Arrays.stream(students).forEach(element -> queue.add(element));
//
//        while (!stack.isEmpty() && !queue.isEmpty()){
//            if (queue.peek() == stack.peek()){
//                queue.poll();
//                stack.pop();
//            } else {
//                int student = queue.poll();
//                queue.add(student);
//            }
//        }
//
//        return queue.size();

        int likeOne = 0;
        int likeZero = 0;

        for (int item : students){
            if (item == 0)
                likeZero ++;
             else if (item == 1)
                likeOne ++;
        }

        for (int item : sandwiches){
            if (item == 0 && likeZero > 0)
                likeZero --;
            else if (item == 1 && likeOne > 0)
                likeOne --;
            else break;
        }

        return likeOne + likeZero;
    }

    public static int timeRequiredToBuy(int[] tickets, int k) {
       Queue<int []> queue = new ArrayDeque<>();
       int time = 0;

        for (int i = 0; i < tickets.length; i++) {
            queue.offer(new int[] {i, tickets[i]});
        }

        while (!queue.isEmpty()){
            int[] current = queue.poll();
            int person = current[0];
            int left = current[1];

            if (left == 0){
                continue;
            }

            time ++;

            left --;

            if (left == 0 && person == k)
                break;

            queue.offer(new int[] {person, left});

        }

        return time;
    }

    private static int[] deckRevealedIncreasing(int[] deck) {
        List<Integer> list = new ArrayList<>();
        int index = 0;
        Arrays.sort(deck);
        list.add(deck[deck.length - 1]);

        for (int i = deck.length - 2; i >= 0; i--) {
                list.add(0, deck[i]);
                index ++;
                int temp = list.get(index);
                list.remove(index);
                list.add( 1, temp);
            }

        return list.stream().mapToInt(Integer::intValue).toArray();
        }

    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (char digit : num.toCharArray()){
            while (!stack.isEmpty() && stack.peek() > digit && k > 0){
                stack.pop();
                k --;
            }

            stack.push(digit);
        }

        while (k > 0 && !stack.isEmpty()){
            stack.pop();
            k --;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()){
            stringBuilder.append(stack.pop());
        }
        stringBuilder = stringBuilder.reverse();
        while( stringBuilder.length() > 0 && stringBuilder.charAt(0) == '0'){
            stringBuilder.deleteCharAt(0);
        }

        return stringBuilder.length() > 0 ? stringBuilder.toString() : "0";
    }

    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        int sum = 0;

        while (left < right){
            if (leftMax <= rightMax){
                sum += (leftMax - height[left]);
                left ++;
                leftMax = Math.max(leftMax, height[left]);
            } else {
                sum += (rightMax - height[right]);
                right --;
                rightMax = Math.max(rightMax, height[right]);
            }
        }

        return sum;
    }

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int currentSum) {
        if (root == null){
            return 0;
        }
        currentSum = currentSum * 10 + root.val;
        if (root.left == null && root.right == null){
            return currentSum;
        }
        return dfs(root.left, currentSum) + dfs(root.right, currentSum);
    }

    public int maxFrequency(int[] nums, int k) {
        int left = 0;
        int right  = 0;
        int sum = 0;
        int maxFreq = 0;

        Arrays.sort(nums);

        while(right < nums.length){
            sum += nums[right];
            while ((right - left + 1) * nums[right] - sum > k ){
                sum -= nums[left];
                left ++;
            }

            maxFreq = Math.max(maxFreq, right - left + 1);
            right ++;
        }
         return maxFreq;
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1){
            TreeNode treeNode = new TreeNode(val);
            treeNode.left = root;
            return treeNode;
        }

        return add(root, val, depth, 1);
    }

    private TreeNode add(TreeNode root, int val, int depth, int cur){
        if (root == null){
            return root;
        }

        if (cur == depth - 1){
            TreeNode leftTemp = root.left;
            TreeNode rightTemp = root.right;
            root.left = new TreeNode(val);
            root.right = new TreeNode(val);
            root.left.left = leftTemp;
            root.right.right = rightTemp;

            return root;
        }

        root.left = add(root.left, val, depth, cur + 1);
        root.right = add(root.right, val, depth, cur + 1);

        return root;
    }

    public int reductionOperations(int[] nums) {
        int count = 1;
        int sum = 0;

       Arrays.sort(nums);

       for (int i = nums.length - 2; i >= 0; i--){
           if (nums[i] == nums[i + 1]){
               count ++;
           } else {
               sum = sum + count;
               count = 1;
           }
       }

       return sum;
    }

    public String smallestFromLeaf(TreeNode root) {
        StringBuilder smallest = new StringBuilder();
        dfsForSmallestFromLeaf(root, new StringBuilder(), smallest);
        return smallest.toString();
    }

    private void dfsForSmallestFromLeaf(TreeNode root, StringBuilder path, StringBuilder smallest) {
        if (root == null)
            return;

        path.append((char) 'a' + root.val);

        if (root.right == null && root.left == null){
            String current = path.reverse().toString();
            if (smallest.length() == 0 || current.compareTo(smallest.toString()) < 0){
                smallest.setLength(0);
                smallest.append(current);
            }
            path.reverse();
        }

        dfsForSmallestFromLeaf(root.left, path, smallest);
        dfsForSmallestFromLeaf(root.right, path, smallest);

        path.deleteCharAt(path.length() - 1);
    }

    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int perimeter = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    perimeter = perimeter + 4;
                    if (i > 0 && grid[i - 1][j] == 1){
                        perimeter = perimeter - 2;
                    }
                    if (j > 0 && grid[i][j - 1] == 1){
                        perimeter = perimeter - 2;
                    }
                }

            }
        }
        return perimeter;
    }

    public int numIslands(char[][] grid) {
        int islandNum = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1'){
                    islandNum ++;
                    dfsNumOfIsland(grid, i, j);
                }
            }
        }
        return islandNum;
    }

    private void dfsNumOfIsland(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1'){
            return;
        }

        grid[i][j] = '0';

        dfsNumOfIsland(grid, i + 1, j);
        dfsNumOfIsland(grid, i - 1, j);
        dfsNumOfIsland(grid, i, j + 1);
        dfsNumOfIsland(grid, i, j - 1);
    }


    public static void main(String[] args) {
      int [][] grid = {{0}};
      new Leetcode().islandPerimeter(grid);
    }

//    17
//    13 17
//    11 13 17 => 11 17 13
//    7 11 17 13 => 7 13 11 17
//    5 7 13 11 17 => 5 17 7 13 11


}


