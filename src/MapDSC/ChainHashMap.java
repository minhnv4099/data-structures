/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MapDSC;

import java.util.ArrayList;

/**
 *
 * @author nguyenminh
 * separate chaining
 * 
 */
public class ChainHashMap<K,V> extends AbstractHashMap<K, V> {
    // array of bucket each is unsorted map
    private UnsortedTableMap<K,V>[] table;
    
    public ChainHashMap(int capacity, int seed) {super(capacity, seed);}
    public ChainHashMap(int cap) {super(cap);}
    public ChainHashMap() {super();}

    @Override
    protected void createTable() {
        table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
    }

    @Override
    protected V bucketGet(int indexBucket, K key) {
        Map<K,V> bucket = table[indexBucket];
        if(bucket == null) return null;
        return bucket.get(key);
    }

    @Override
    protected V bucketPut(int indexBucket, K key, V value) {
        Map<K,V> bucket = table[indexBucket];
        // no such bucket
        if(bucket == null){
            // initial bucket
            bucket = table[indexBucket] = new UnsortedTableMap<>();
        }
        // size of bucket before inserting, as size of bucket can be increased or keep
        int oldSize = bucket.size();
        // if key is already in bucket -> size of bucket does not change
        V oldData = bucket.put(key, value);
        numbers += (bucket.size() - oldSize);
        return oldData;
    }

    @Override
    protected V bucketRemove(int indexBucket, K key) {
        Map<K,V> bucket = table[indexBucket];
        // no such bucket
        if(bucket == null) return null;
        // size of bucket before removing
        int oldSize = bucket.size();
        // if key is not in bucket -> size of bucket does not change
        V oldData = bucket.remove(key);
        numbers -= (oldSize - bucket.size());
        return oldData;
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(this.numbers);
        for(Map<K,V> b : table){
            if(b != null){
                for(Entry<K,V> e : b.entrySet()){
                    buffer.add(e);
                }
            }
        }
        return buffer;
    }
}
