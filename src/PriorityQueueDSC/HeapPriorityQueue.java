/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PriorityQueueDSC;

import MapDSC.Entry;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author nguyenminh
 * That heap is complete binary tree
 * Each new node added to tree, will add at the rightmost at the bottom level
 * Or leftmost at the new level
 * seem like in-order traverse.
 */
public class HeapPriorityQueue<K,V> extends AbstracPriorityQueue<K, V>{
    /** use array list to implement heap, heap seem like tree satisfying property-balanced high*/
    protected ArrayList<Entry<K,V>> heap = new ArrayList<>();

    public HeapPriorityQueue(Comparator<K> c) {
        super(c);
    }

    public HeapPriorityQueue() {
        super();
    }
    // root start with 0
    // can compute some example to find
    protected int parent(int index) {return (index-1)/2;}
    protected int left(int index) { return 2*index + 1;}
    protected int right(int index) {return  2*index + 2;}
    protected boolean hasLeft(int index) { return left(index) < this.heap.size(); }
    protected boolean hasRight(int index) { return right(index) < this.heap.size(); }

    @Override
    public int size() {return this.heap.size();}
    
    /** swap nodes to no violation heap-order*/
    protected void swap(int i, int j){
        Entry<K,V> tmp = this.heap.get(i);
        this.heap.set(i, this.heap.get(j));
        this.heap.set(j,tmp);
    }

    /** 
     * compare key its key with its ancestor
     * put it to appropriate position
     * Seem like insertion sort
     * It is called after inserting a new entry
     * @param i index being considered
     */
    protected void upHeap(int i){
        // i == 0 -> root -> has no parent
        while(i > 0){
            /* get index of parent of entry at index i*/
            int p = this.parent(i);
            /* break if key of parent less than key of child*/
            if(this.compare(this.heap.get(p), this.heap.get(i)) < 0) break; 
            // if key of parent greater than key of child -> swap 2 entries and continue with its parent 
            swap(i, p);
            i = p;
        }   
    }
    
    /**
     * compare its key with their descendants 
     * Called after removing one entry
     * @param i index being considered
     */
    protected void downHeap(int i){
        // entry either has no child or has at least one left child
        while(this.hasLeft(i)){
            int leftIndex = this.left(i);
            // let left child is mininal child
            int smallIndex = leftIndex;
            if(this.hasRight(i)){
                int rightIndex = this.right(i);
                /** determine left and right child what is minimal */
                if(this.compare(this.heap.get(leftIndex), this.heap.get(rightIndex)) > 0)
                    smallIndex = rightIndex;
            }
            // break if parent in appropreated position
            if(this.compare(this.heap.get(i), this.heap.get(smallIndex)) < 0)
                break;
            swap(i, smallIndex);
            i = smallIndex;
        }
    }
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        this.checKey(key);
        Entry<K,V> newEntry = new PQEntry<>(key,value);
        /** append new entry to the heap -> possible to determine index of that new entry and keep property*/
        this.heap.add(newEntry);
        /** move new entry to appropriate position with upHead method*/
        this.upHeap(this.size()-1);
        return newEntry;
    }

    @Override
    public Entry<K, V> min() {
        if(this.isEmpty()) return null;
        return this.heap.get(0);
    }

    @Override
    public Entry<K, V> removeMin() {
        if(this.isEmpty()) return null;
        /** swap removed node(root) with last node*/
        this.swap(0, this.size()-1);
        // removing actually happen at last index
        Entry<K,V> removed = this.heap.remove(this.size() - 1);
        /** find node has minimal key, and let it be root for next removing*/
        this.downHeap(0);
        return removed;
    }
}
