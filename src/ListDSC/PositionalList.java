/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListDSC;

/**
 *
 * @author nguyenminh
 * @param <E>
 * Array list use index to identify an element in sequence
 * Positional list use position to identify an element
 * Positional implement like doublyLinkedList
 * Advanced implement of list
 */
public interface PositionalList<E> extends Iterable<E>{
    
    /**
     * return number of elements in the list
     * @return number of elements
     */
    @Complexity
    int size();
    
    /**
     * check the list whether empty
     * @return true if empty, otherwise false
     */
    @Complexity
    boolean isEmpty();
    
    /**
     * return position of the first element
     * @return position of the first element. Null if list is empty
     */
    @Complexity
    Position<E> first();
    
    
    /**
     * return position of the last element
     * @return position of the last element. Null if list is empty
     */
    @Complexity
    Position<E> last();
    
    
    /**
     * return the position before the specific position
     * @return position before the specific position
     * @throws IllegalStateException if invalid p
     */
    @Complexity
    Position<E> before(Position<E> refPos) throws IllegalStateException;
    
    /**
     * return the position after the specific position
     * @return position after the specific position
     * @throws IllegalStateException if invalid p
     */
    @Complexity
    Position<E> after(Position<E> refPos) throws IllegalStateException;
    
    /**
     * insert new element at the head the list
     * @return position of the new element
     */
    @Complexity
    Position<E> addFirst(E ele);
    
    /**
     * insert new element at the tail the list
     * @return position of the new element
     */
    @Complexity
    Position<E> addLast(E ele);
    
    /**
     * insert new element before position p
     * @return position of the new element
     * @throws IllegalStateException if invalid p
     */
    @Complexity
    Position<E> addBefore(Position p,E ele) throws IllegalArgumentException;;
    
    /**
     * insert new element after position p
     * @return position of the new element
     * @throws IllegalStateException if invalid p
     */
    @Complexity
    Position<E> addAfter(Position p,E ele) throws IllegalArgumentException;;  
    
    /**
     * replace new element at position p
     * @return formerly element
     * @throws IllegalStateException if invalid p
     */
    @Complexity
    E set(Position<E> p,E ele) throws IllegalArgumentException;
    
    /**
     * remove element at the position p
     * @return removed element
     * @throws IllegalStateException if invalid p
     */
    @Complexity
    E remove(Position<E> p) throws IllegalArgumentException;
}
