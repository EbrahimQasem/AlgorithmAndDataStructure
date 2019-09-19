/*  Name: <Ebrahim Qasem>
 *  COSC 311  FA19
 *  hw0912
 *  URL:  <your URL>
 *  
 *  NOTE:As instructed, I only added the insert function.
 *  The rest of code is given by instructor. 
 */

public class SinglyLinkedList {

   public class Node {
      int   data;
      Node  next;
      
      public Node () {
         this (0, null);
      }
      
      public Node (int data) {
         this(data, null);
      }
      
      public Node(int data, Node next) {
         this.data = data;
         this.next = next;
      }
   }
   
   Node  head, tail;
   
   public SinglyLinkedList() {
      head = tail = null;
   }
   
   public void insert(int num) {
	   Node newNum = new Node(num);
	   
	   if(head == null || head.data >= num) {
		   newNum.next = head;
		   head = newNum;
	   }
	   else {
		   Node temp;
		   temp = head;
		   while(temp.next != null && temp.next.data < newNum.data)
			   temp = temp.next;
		   newNum.next = temp.next;
		   temp.next = newNum;
	   }
   }
   
   public void delete(int el) {
      if (!isEmpty() )
         if ( head == tail && el == head.data)
            head = tail = null;
         else if (el == head.data )
            head = head.next;
         else {   
            Node p, q;
            for ( p= head, q = head.next; 
                  q != null && !(q.data == el ); 
                  p = p.next, q = q.next) ;
            if (q != null) {
               p.next = q.next;
               if (q == tail )
                  tail = p;
            }
         } 
   }
   
   public boolean isEmpty() {
      return head == null;
   }
   
   public String  toString() {
      String s = "";
      if (head == null) { 
         return "Empty String" ;
      }
      for (Node p = head; p != null; p = p.next) 
         s += p.data + " ";
      return s;
   }
     
   public static void main(String[] args) {
      SinglyLinkedList list = new SinglyLinkedList();
      
      System.out.println("Execution begun");
      System.out.println("initial list: " + list );
      
      // Sample run
      list.insert(10);
      list.insert(9);
      list.insert(8);
      list.insert(7);
      list.insert(13);
      list.insert(6);
      System.out.println("1: " + list);
      
      list.delete(7);
      list.delete(10);
      System.out.println("2: " + list);
      
      list.insert(11);
      list.insert(14);
      System.out.println("3: " + list);
      System.out.println("Execution terminated");
   }
}

/*
 * Sample Output
Execution begun
initial list: Empty String
1: 6 7 8 9 10 13 
2: 6 8 9 13 
3: 6 8 9 11 13 14 
Execution terminated
 */
