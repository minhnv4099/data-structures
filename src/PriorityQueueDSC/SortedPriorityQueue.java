/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PriorityQueueDSC;

import MapDSC.Entry;
import ListDSC.LinkedPositionalList;
import ListDSC.Position;
import ListDSC.PositionalList;
import java.util.Comparator;

/**
 *
 * @author nguyenminh
 * base on level of priority, if some entries has the same priority then follow FIFO
 */
public class SortedPriorityQueue<K,V> extends AbstracPriorityQueue<K, V>{
    private LinkedPositionalList<Entry<K,V>> data = new LinkedPositionalList<>();

    public SortedPriorityQueue(Comparator<K> c) {
        super(c);
    }

    public SortedPriorityQueue() {
        super();
    }

    @Override
    public int size() {return this.data.size();}

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        this.checKey(key);
        Entry<K,V> newEntry = new PQEntry<>(key,value);
        if(this.isEmpty()) return this.data.addFirst(newEntry).getElement();
        Iterable<Position<Entry<K,V>>> iter = this.data.PosIterable();
        for(Position<Entry<K,V>> c: iter){
            // select suitable position for new entry
            if(this.compare(c.getElement(),newEntry) > 0)
                return this.data.addBefore(c, newEntry).getElement();
        }
        return this.data.addLast(newEntry).getElement();
    }

    @Override
    public Entry<K, V> min() {
        if(this.isEmpty()) return null;
        return this.data.first().getElement();
    }

    @Override
    public Entry<K, V> removeMin() {
        if(this.isEmpty()) return null;
        return this.data.remove(this.data.first());
    }
}
