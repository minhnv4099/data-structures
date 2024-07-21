/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PriorityQueueDSC;

import MapDSC.Entry;
/**
 *
 * @author nguyenminh
 */
public interface AdaptablePriorityQueue<K,V> {
    /**
     * remove entry e from queue
     * @param e expected moved entry
     * @return deleted entry
     * @throws IllegalArgumentException if invalid e
     */
    void remove(Entry<K,V> e) throws IllegalArgumentException;
    
    /**
     * set key of entry {@code e} with new key {@code k}
     * @param e expected entry
     * @param k new key
     * @throws IllegalArgumentException if invalid {@code e} and/or invalid {@code k}
     */
    void replaceKey(Entry<K,V> e, K k) throws IllegalArgumentException;
    
    /**
     * set value of entry {@code e} with new value {@code v}
     * @param e expected entry
     * @param v new value
     * @throws IllegalArgumentException if invalid {@code e} and/or invalid {@code v}
     */
    void replaceValue(Entry<K,V> e, V v) throws IllegalArgumentException;
}
