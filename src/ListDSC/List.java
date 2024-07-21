/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListDSC;

/**
 *
 * @author nguyenminh
 * List abstract data type
 * perform on list with generic
 */
public interface List<E> extends Iterable<E>{
    
    /**
     * return number of element in the list
     * @return number of elements
     */
    int size();
    
    /**
     * Check the list whether empty
     * @return true if empty, otherwise false
     */
    boolean isEmpty();
    
    /**
     * get element at specific index in range[0,size-1]
     * @return element in specific index, null if empty
     * @throws IndexOutOfBoundsException if index out range
     */
    E get(int index) throws IndexOutOfBoundsException;
    
    /**
     * update element(data of element) at specific index in range[0,size-1]
     * @return old element 
     * @throws IndexOutOfBoundsException if index out range
     */
    E set(int index,E ele) throws IndexOutOfBoundsException;
    
    /**
     * add element to specific index in range[0,size]
     * @Note: new element will have that index in the list -> index in range[0,size]
     * @throws IndexOutOfBoundsException if index out range
     */
    void add(int index, E ele) throws IndexOutOfBoundsException;
    
    /**
     * return element at specific index in range[0,size-1] and remove it from the list
     * @return element at specific index
     * @throws IndexOutOfBoundsException if index out range
     */
    E remove(int index) throws IndexOutOfBoundsException;
    
    
}
