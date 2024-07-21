/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapDSC;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author nguyenminh
 * Using ArrayList to store table(entry)
 * Use integer index
 */
public class UnsortedTableMap<K,V> extends AbstractMap<K, V> {
    
    public UnsortedTableMap() {}

    private int findIndex(K key){
        int index = -1;
        for(Entry<K,V> ele : this.table){
            index++;
            if(ele.getKey().equals(key)){
                return index;
            }
        }
        return -1;
    }
    @Override
    public int size() {return this.table.size();}
    
    @Override
    public V get(K key) {
        int index = this.findIndex(key);
        if(index == -1) return null;
        return this.table.get(index).getValue();
    }

    @Override
    public V put(K key, V value) throws IllegalArgumentException {
        int index = this.findIndex(key);
        /** check key whether exist*/
        if(index == -1){
            this.table.add(new MapEntry<>(key,value));
            return null;
        }
        return this.table.get(index).setValue(value);
    }

    @Override
    public V remove(K key) {
        int index = this.findIndex(key);
        if(index == -1) return null;
        return this.table.remove(index).getValue();
    }

    private class EntryIterator implements Iterator<Entry<K,V>>{
        private Iterator<MapEntry<K,V>> iter = UnsortedTableMap.this.table.iterator();
        private K recentKey = null;
        public boolean hasNext() {return this.iter.hasNext();}
        public Entry<K, V> next() {
            Entry<K,V> answer = this.iter.next();
            this.recentKey = answer.getKey();
            return answer;
        }
        @Override
        public void remove() {
            if(this.recentKey == null) throw new NoSuchElementException("No element is called");
            UnsortedTableMap.this.remove(recentKey);
            recentKey = null;
        }
    }
    
    private class EntryIterable implements Iterable<Entry<K,V>>{
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }   
    }
    @Override
    public Iterable<Entry<K, V>> entrySet() {return new EntryIterable();}   
}
