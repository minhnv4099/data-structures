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
public class HeapAdaptablePriorityQueue<K,V> extends HeapPriorityQueue<K, V> implements AdaptablePriorityQueue<K, V>{
    protected class AdaptablePQEntry<K,V> extends PQEntry{
        // auxility index refer index in the queue
        private int index;
        public AdaptablePQEntry(K key, V value,int index) {
            super(key, value);
            this.index = index;
        }
        public int getIndex() {return index;}
        public void setIndex(int index) {this.index = index;}
    }   
    
    protected AdaptablePQEntry<K,V> validate(Entry<K,V> entry){
        if(!(entry instanceof AdaptablePQEntry)) throw new IllegalArgumentException("Invalid entry");
        AdaptablePQEntry<K,V> tmp = (AdaptablePQEntry<K,V>) entry;
        // because when removing entry, we just last element of arraylist -> that entry has index attribute equal heap's size at the present
        if(tmp.getIndex() >= heap.size()) throw new IllegalArgumentException("Entry is not in queue");
        return tmp;
    }
    
    protected void swap(int i, int j){
        // just swap 2 entries
        super.swap(i, j);
        // reset index field of 2 entries
        this.validate(heap.get(i)).setIndex(i);
        this.validate(heap.get(j)).setIndex(j);
    }
    /** upheap/downheap */
    protected void bubble(int i){
        if(i > 0 && this.compare(heap.get(i), heap.get(this.parent(i))) < 0 ){
            this.upHeap(i);
        }else{
            this.downHeap(i);
        }
    }
    
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
        this.checKey(key);
        // appent to array list
        AdaptablePQEntry<K,V> newEntry = new AdaptablePQEntry<>(key,value,this.size()-1);
        this.heap.add( newEntry);
        upHeap(this.size()-1);
        return newEntry;
    }

    @Override
    public void remove(Entry<K, V> e) throws IllegalArgumentException {
        AdaptablePQEntry<K,V> tmp = this.validate(e);
        int index = tmp.getIndex();
        if(index == this.size()-1)
            this.heap.remove(this.size()-1);    // do not need to bubble
        else{
            // swap entry at index and last entry
            swap(index,this.size()-1);
            // removing actually happen at last index
            this.heap.remove(this.size()-1);
            // re-constructor heap
            downHeap(index);
        }
    }

    @Override
    public void replaceKey(Entry<K, V> e, K k) throws IllegalArgumentException {
        AdaptablePQEntry<K,V> tmp = this.validate(e);
        checKey(k);
        tmp.setKey(k);
        // because heap compare base on key
        // after set key, need to re-constructor
        bubble(tmp.getIndex());
    }

    @Override
    public void replaceValue(Entry<K, V> e, V v) throws IllegalArgumentException {
        AdaptablePQEntry<K,V> tmp = this.validate(e);
        tmp.setValue(v);
    }
}
