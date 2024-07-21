/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DequeDSC;

/**
 *
 * @author nguyenminh
 * @param <E>
 */

/*
Deque such like queue, however deque can insert and remove at either front or rear of the queu
*/
public interface Deque<E> {
    
    /**
     * Number of elements in the deque
     * @return number of elements alive
     * Running time: O(1)
     */
    int size();
    
    /**
     * Check the deque whether empty
     * @return true if empty, otherwise false
     * Running time: O(1)
     */
    boolean isEmpty();
    
    /**
     * @return element at the front without removing it, {@code null} if empty
     */
    E fisrt();
    
    /**
     * @return element at the rear without removing it, {@code null} if empty
     */
    E last();
    
     /**
     * Add new element to the deque at the front 
     */
    void addFirst(E ele);
    
    /**
     * Add new element to the deque at the rear
     */
    void addLast(E ele);
    
    /**
     * @return element at the front and remove it, {@code null} if empty
     */
    E removeFirst();
    
    /**
     * @return element at the rear and remove it, {@code null} if empty
     */
    E removeLast();
}
