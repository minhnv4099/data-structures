/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QueuDSC;

/**
 *
 * @author nguyenminh
 */


public interface Queue<E> {
    
    /**
     * Return number of elements in queue
     * @return size
     */
    int size();
    
    /**
    * Check the queue whether is empty
    * @return true if true, false otherwise
    */
    boolean isEmpty();
    
    
    /**
    * Add new element to the rear the queue
    * @param data new element
    */
    void enqueu(E data);
    
    /**
    * Return element in front the queue
    * without removing
    * @thorw IllegalStateException if empty
    */
    E first();
    
    /**
    * Return element in front the queue
    * and remove it
    * @thorw IllegalStateException if empty
    */
    E dequeu();
  
}
