/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedListDSC;
import java.util.ArrayList;

/**
 *
 * @author nguyenminh
 * Advance operations for linked list
 */
public interface AdvancedLinkedList<E> {
       
    /**
     * get element(data) at the specific index in range[0,size-1] without remove it
     * @return element at specific index
     * @throw error if index out range[0,size-1]
     */
    E get(int index) throws IllegalStateException,IndexOutOfBoundsException;
    
    /**
     * add new element to specific position in range[0,size]
     * new element will have specific index
     * @throws IndexOutOfBoundsException if index out range
     */
    void add(int index,E data) throws IndexOutOfBoundsException;
    
    
    /**
     * @return element at the tail and remove it
     * @throws IllegalStateException if empty
     */
    E removeLast() throws IllegalStateException;
    
    /**
     * @return element at the specific index in range[0,size-1] and remove it
     * @throws IllegalStateException if empty
     * @throws IndexOutOfBoundsException if index out range
     */
    E remove(int index) throws IllegalStateException, IndexOutOfBoundsException;
    
    
    /**
     * shift the cursor to left/right n units
     * @throws IllegalStateException if empty
     */
    void rotate(int unit) throws IllegalStateException;
    
    /**
     * reverse the entire list
     * @throws IllegalStateException if empty
     */
    void revserse() throws IllegalStateException;
    
    /**
     * search input element in the list
     * @return true if occur, otherwise false
     */
    boolean contain(E data);
    
    /**
     * remove element by its data if that element exist
     * @return that data
     * @return null if not exist
     */
    E removeData(E data) throws IllegalStateException;
}
