/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapDSC;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;


/**
 *
 * @author nguyenminh
 */
public abstract class AbstractMap<K,V> implements Map<K, V>{
    protected class MapEntry<K,V> implements Entry<K, V>{
        private K key;
        private V value;
        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {return key;}
        public void setKey(K key) {this.key = key;}
        public V getValue() {return value;}
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }
    }
    protected ArrayList<MapEntry<K,V>> table = new ArrayList<>();
    
    private class IteratorKeys implements Iterator<K>{
        Iterator<Entry<K,V>> entries = entrySet().iterator();
        @Override
        public boolean hasNext() {return this.entries.hasNext();}
        @Override
        public K next() {return this.entries.next().getKey();}
        @Override
        public void remove() {entries.remove();}   
    }
    private class KeysIterable implements Iterable<K>{
        public Iterator<K> iterator() {
            return new IteratorKeys();
        }
    }
    @Override
    public Iterable<K> keySet() {return new KeysIterable();}
    
    private class IteratorValues implements Iterator<V>{
        Iterator<Entry<K,V>> entries = entrySet().iterator();
        @Override
        public boolean hasNext() {return entries.hasNext();}
        @Override
        public V next() {return entries.next().getValue();}
        @Override
        public void remove() {entries.remove();}
    }
    private class ValuesIterable implements Iterable<V>{
        public Iterator<V> iterator() {
            return new IteratorValues();
        }
    }
    @Override
    public Iterable<V> values() {return new ValuesIterable(); }

    @Override
    public boolean isempty() {return this.size() == 0;}
}
