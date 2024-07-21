/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapDSC;

import java.util.Comparator;

/**
 *
 * @author nguyenminh
 * Using array list to store elements
 * extends AbstractMap to re-use keySet and values methods
 */
public abstract class AbstractSortedMap<K,V> extends AbstractMap<K, V> implements SortedMap<K, V>{
    // co che so sanh key
    private Comparator<K> comp;

    public AbstractSortedMap(Comparator<K> comp) {
        this.comp = comp;
    }

    public AbstractSortedMap() {
        // naturual comparasion
        this(new DefaultComparator<>());
    }
    
    protected int compare(K key1, K key2){
        return this.comp.compare(key1, key2);
    }
    /**
     * check key whether is comparable
     * @param key given key
     * @return {@code true/false}
     * @throws IllegalArgumentException if key is not comparable
     */
    protected boolean checKey(K key) throws IllegalArgumentException{
        try{
            /* check key with itself */
            return this.comp.compare(key, key) == 0; 
        }catch(ClassCastException e){
            /** key can not be compared */
            throw new IllegalArgumentException("Impropreated key");
        }
    }
}
