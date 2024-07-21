/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PriorityQueueDSC;

import MapDSC.Entry;
/**
 *
 * @author nguyenminh
 * Interface for priority queue
 * We can use priority queue to sort collections of elements
 * with key in priority as element
 */
public interface PriorityQueue<K,V> {
    /**
     * @return number of entry of queue
     */
    int size();
    
    /**
     * check the queue whether is empty
     * @return true/false
     */
    boolean isEmpty();
    
    /**
     * Create an entry with key and value
     * @return inserted entry
     * @throws IllegalArgumentException if invalid key
     */
    Entry<K,V> insert(K key, V value) throws IllegalArgumentException;
    
    /**
     * @return entry has minimal key, without removing, {@code null} if queue empty
     */
    Entry<K, V> min();
    
    /**
     * remove entry has minimal key from the queue, {@code null} if empty
     * @return removed entry
     */
    Entry<K, V> removeMin();
}
