/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PriorityQueueDSC;
import MapDSC.Entry;
import ListDSC.Position;
import ListDSC.LinkedPositionalList;
import java.util.Comparator;

/**
 *
 * @author nguyenminh
 * Using positional list to store entry in priority queue, time is O(1)
 * Sort by selection sort
 * Find min and remove
 */
public class UnsortedPriorityQueue<K,V> extends AbstracPriorityQueue<K, V> {
    private LinkedPositionalList<Entry<K,V>> data = new LinkedPositionalList<>();

    public UnsortedPriorityQueue() {super();}

    public UnsortedPriorityQueue(Comparator<K> c) {super(c);}

    @Override
    public int size() {return this.data.size();}

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        /** check key whether is appropriate*/
        this.checKey(key);
        return this.data.addLast(new PQEntry<>(key,value)).getElement();
    }

    private Position<Entry<K,V>> findMin(){
        if(this.isEmpty()) return null;
        /** assume first element to minimal entry*/
        Position<Entry<K,V>> minimalEntry = this.data.first();
        Iterable<Position<Entry<K,V>>> iter = this.data.PosIterable();
        for(Position<Entry<K,V>> c: iter){
            if(this.compare(minimalEntry.getElement(), c.getElement()) > 0)
                minimalEntry = c;
        }
        return minimalEntry;
    }
    @Override
    public Entry<K, V> min() {
        if(this.isEmpty()) return null;
        return this.findMin().getElement();
    }

    @Override
    public Entry<K, V> removeMin() {
        if(this.isEmpty()) return null;
        return this.data.remove(this.findMin());
    }
}
