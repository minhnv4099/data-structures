/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedListDSC;

/**
 *
 * @author nguyenminh
 */
public class TestLinkedList {
    public static void main(String[] argv) throws CloneNotSupportedException, Exception{
        SinglyLinkedList mySingLinked = new SinglyLinkedList();
        
        CircularLinkedList myCirLinked = new CircularLinkedList();
        //SinglyLinkedList mySingLinked1 = new SinglyLinkedList();
        
        DoublyLinkedList myDouLinked = new DoublyLinkedList();
        
        
        
        /**
        myDouLinked.addHead("one");
        myDouLinked.addHead("two");
        myDouLinked.addTail("three");
        myDouLinked.addHead("four");
        **/
        /**
        System.out.println(myDouLinked.popHead());
        System.out.println(myDouLinked.popHead());
        System.out.println(myDouLinked.popHead());
        System.out.println(myDouLinked.popTail());
        **/
        //mySingLinked1.addTail("one");
        //mySingLinked1.addHead("two");
        
        
      
        //SinglyLinkedList mySingLinked2 = (SinglyLinkedList)mySingLinked.clone();
        
        /*
        mySingLinked.addFirst(1);
        mySingLinked.addFirst(2);
        mySingLinked.addFirst(3);
        mySingLinked.addFirst(4);
        mySingLinked.addLast(5);
        mySingLinked.addFirst(6);
        mySingLinked.addLast(7);
        mySingLinked.addFirst(8);
        mySingLinked.addLast(9);
        mySingLinked.add(9, 10);
        
        mySingLinked.traversal();
        mySingLinked.revserse();
        mySingLinked.traversal();
        
        
       
        //System.out.println(mySingLinked.search(null));
       // System.out.println(mySingLinked.dataAtIndex(8));
        //System.out.println(mySingLinked.search(4));
        //System.out.println(mySingLinked.indexOf(4));
        /**
        System.out.println(mySingLinked.equals(mySingLinked2));
        System.out.println(mySingLinked.hashCode());
        System.out.println(mySingLinked2.hashCode());
        
        **/
        
        //System.out.println(mySingLinked==mySingLinked2);
        
        //System.out.println(myCirLinked.search(1));
        
        
        
        myCirLinked.addFirst(1);
        myCirLinked.addLast(2);
        myCirLinked.addFirst(3);
        myCirLinked.addLast(4);
        myCirLinked.add(4,2);
        myCirLinked.addFirst(6);
        myCirLinked.add(4,5);
        myCirLinked.addFirst(8);
        myCirLinked.addFirst(9);
        myCirLinked.add(9, 10);
        
        myCirLinked.traversal();
        myCirLinked.revserse();
        
        myCirLinked.traversal();
        

        
        
        
        
        //myCirLinked.reverse();
        //myCirLinked.traversal();
        //System.out.println("");
        

        /**
        
        
        **/
        
        /*
        myDouLinked.addLast(1);
        myDouLinked.addFirst(2);
        myDouLinked.addFirst(3);
        myDouLinked.addLast(4);
        myDouLinked.addLast(5);
        myDouLinked.addLast(6);
        myDouLinked.addFirst(7);
        myDouLinked.addFirst(9);
        myDouLinked.add(8, 10);
        
        myDouLinked.traversal();
        //myDouLinked.rotate(-1);
        //myDouLinked.traversal();
        
        System.out.println(myDouLinked.contain(5));
       /**
        
       7 4 2 1 8 3 5 8 6 9
        
        **/
        

    }
}
