/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MapDSC;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author nguyenminh
 * Hash table
 * we can implement table with some map
 * 
 */
public abstract class AbstractHashMap<K,V> extends AbstractMap<K, V> {
    // number of entry in map
    protected int numbers = 0;
    // max number of entry in map
    protected int capacity;
    // factor
    private int scale;
    private int shift;
    private int seed;

    public AbstractHashMap(int capacity, int seed) {
        this.capacity = capacity;
        this.seed = seed;
        Random rand = new Random();
        this.scale = rand.nextInt(seed-1)+1;
        this.shift = rand.nextInt(seed);
        this.createTable();
    }
    // default seed
    public AbstractHashMap(int cap) {this(cap,1093414144);}
    // default capacity
    public AbstractHashMap(){this(17);} 
    // hash function to compute hash value upon on key
    // hash value is that determine bucket
    private int hashValue(K key) {
        /*
           follow MAD method v = ([i*a + b] mod p) mod N
            - i: hash code
            - a: scale in range(0, p-1]
            - b: shift in range[0, p]
            - p: prime
            - N: capacity
        */
        return (int)(Math.abs((key.hashCode()*scale+shift)%seed)%capacity);
    }
    // public method (implemented methods of map)
    public int size() {return this.numbers;}
    @Override
    public V get(K key) throws IllegalArgumentException {return this.bucketGet(hashValue(key), key);}
    @Override
    public V remove(K key) throws IllegalArgumentException {return bucketRemove(hashValue(key), key);}
    @Override
    public V put(K key, V value) throws IllegalArgumentException {
        V data = bucketPut(hashValue(key), key, value);
        // compute load factor
        if(numbers > capacity/2){ // keep load factor <= 0.5
            // resize with capacity*2
            resize(capacity*2);
        }
        return data;
    }
    // entry == DEFUNCT is eliminated
    private void resize(int newCap){
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(numbers);
        for(Entry<K,V> entry: entrySet()){
            buffer.add(entry);
        }
        capacity = newCap;
        // re-assign table
        createTable();
        // recompute number, because table is empty now
        numbers = 0;  
        // put old data to table
        for(Entry<K,V> entry: buffer){
            this.put(entry.getKey(), entry.getValue());
        }
    }
    // re-declare table
    // in particular implement, table can be implemented with specific type
    protected abstract void createTable();
    /** get value of entry has key in bucket at indexBucket*/
    protected abstract V bucketGet(int indexBucket,K key);   
    /** 
     * put new entry (key,value) to bucket at indexBucket
     * @return old value if 
     */
    protected abstract V bucketPut(int indexBucket,K key,V value);
    /** 
     * remove entry has key in bucket at indexBucket
     * @return value of deleted entry
     */
    protected abstract V bucketRemove(int indexBucket,K key);
    
}
