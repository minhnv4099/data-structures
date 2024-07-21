/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedListDSC;

/**
 *
 * @author nguyenminh
 * ADT of linked list
 * This is basic operations for every linked list
 * We can implement it in particular type(Linked list)
 */
public interface LinkedList<E>{
    
    /**
     * count elements in linked list
     * @return number of elements
     */
    int size();
    
    /**
     * check linked list whether empty
     * @return true if empty, otherwise false
     */
    boolean isEmpty();
    
    /**
     * get element(data) at the head without remove it
     * @return the first element
     */
    E getFirst();
    
    /**
     * get element(data) at the tail without remove it
     * @return the last element
     */
    E getLast();
    
    /**
     * add new element at first of the linked list
     */
    void addFirst(E data);
    
     /**
     * add new element at last of the linked list
     */
    void addLast(E data);
    
    
    /**
     * @return element at the head and remove it, {@code null} if empty
     */
    E removeFirst();
  
    /**
     * traversal the list and do something with each element
     */
    void traversal();
    
    /**
     * clear entry list
     */
    void clear();
}
