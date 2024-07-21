/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MapDSC;

/**
 *
 * @author nguyenminh
 */
public interface SortedMap<K,V> extends Map<K, V> {
    /**
     * @return entry having smallest key, {@code null} if empty
     */
    Entry<K, V> firstEntry();
    /**
     * @return entry having smallest key, {@code null} if empty
     */
    Entry<K, V> lastEntry();
    
    /**
     * 
     * @param key given key
     * @return smallest entry has key equals or greater than {@code key}
     * @throws IllegalArgumentException in invalid {@code key}
     */
    Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException;
    
    /**
     * 
     * @param key given key
     * @return largest entry has key lower than or equals {@code key}
     * @throws IllegalArgumentException in invalid {@code key}
     */
    Entry<K, V> floorEntry(K key) throws IllegalArgumentException;
    
    /**
     * 
     * @param key given key
     * @return entry has key higher strictly {@code key}
     * @throws IllegalArgumentException in invalid {@code key}
     */
    Entry<K, V> higherEntry(K key) throws IllegalArgumentException;
    
    /**
     * 
     * @param key given key
     * @return entry has key lower strictly {@code key}
     * @throws IllegalArgumentException in invalid {@code key}
     */
    Entry<K, V> lowerEntry(K key) throws IllegalArgumentException;
    
    /**
     * compute iteration entry in range [fromKey, toKey]
     * @param fromKey beginning key
     * @param toKey ending key
     * @return iterable of entries in range
     * @throws IllegalArgumentException in invalid {@code fromkey} or {@code toKey}
     */
    Iterable<Entry<K,V>> subMap(K fromKey, K toKey) throws IllegalArgumentException;
}
