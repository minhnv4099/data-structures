/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapDSC;

import java.io.Serializable;

/**
 *
 * @author nguyenminh
 */
public interface Map<K,V>{
    /**
     * @return number of entry of map
     */
    int size();
    
    /**
     * check the map whether is empty
     * @return {@code true/false}
     */
    boolean isempty();
    
    /**
     * get value of entry has key equals {@code key}
     * @param key given key
     * @return associated value, {@code null} if {@code key} not exist
     * @throws IllegalArgumentException if invalid {@code key}
     */
    V get(K key) throws IllegalArgumentException;
    
    /**
     * If entry has {@code key} exist, replace old value with {@code value}, return old value
     * @param key key of entry
     * @param value associated value of entry
     * @return null if entry with key not in map, old if key existed
     * @throws IllegalArgumentException in {@code key} is not {@code Comparable}
     */
    V put(K key, V value) throws IllegalArgumentException;
    
    /**
     * remove entry has key equals {@code key}
     * @param key given key
     * @param key key of entry
     * @return value of entry has key, null if {@code key} is not map
     * @throws IllegalArgumentException if invalid {@code key}
     */
    V remove(K key) throws IllegalArgumentException;
    
    /**
     * @return iteration of all keys of map
     */
    Iterable<K> keySet();
    
    /**
     * @return iteration of all values of map
     */
    Iterable<V> values();
    /**
     * @return iteration of entries of map
     */
    Iterable<Entry<K,V>> entrySet();
}
