import java.util.ArrayList;
import java.util.List;

public class Leetcode {
     public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode current = head;

         for (int i = 1; i <6 ; i++) {
             current.next = new ListNode(i);
             current = current.next;
         }

         ListNode temp = head.next;
         while(head != null){
             System.out.println(head.val + " ");

             head = head.next;
         }




         Leetcode leetcode = new Leetcode();
         System.out.println(leetcode.removeNthFromEnd(temp, 2));
    }

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


}


