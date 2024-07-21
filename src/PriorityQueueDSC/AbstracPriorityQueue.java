/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PriorityQueueDSC;
import MapDSC.Entry;
import MapDSC.DefaultComparator;
import java.util.Comparator;

/**
 *
 * @author nguyenminh
 */
public abstract class AbstracPriorityQueue<K, V> implements PriorityQueue<K, V>{
    /** actual entry in the priority queue*/
    protected class PQEntry<K, V> implements Entry<K, V>{
        private K key;
        private V value;

        public PQEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        /* implement methods on Entry interface*/
        public V getValue() {return value;}
        public K getKey() {return key;}
        /* methods of PQEntry*/
        public void setKey(K key) {this.key = key;}
        public void setValue(V value) {this.value = value;}
    }
    /** mechanism to arrange priority(key)*/
    private Comparator<K> comp;
    /** create priority queue with given comparator to order keys*/
    public AbstracPriorityQueue(Comparator<K> c){
        this.comp = c;
    }
    /** create priority queue orders keys with natural order base object key*/
    public AbstracPriorityQueue(){
        this(new DefaultComparator<>());
    }
    
    protected int compare(Entry<K,V> e1, Entry<K,V> e2){
        checKey(e1.getKey());
        checKey(e2.getKey());
        return this.comp.compare(e1.getKey(), e2.getKey());
    }
    
    protected boolean checKey(K key) throws IllegalArgumentException{
        try{
            /* check key with itself */
            return this.comp.compare(key, key) == 0; 
        }catch(ClassCastException e){
            /** key can not be compared, key not implement interface Comparable */
            throw new IllegalArgumentException("Impropreated key");
        }
    }
    @Override
    public boolean isEmpty() {return this.size() == 0;}
}
