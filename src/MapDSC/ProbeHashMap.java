/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MapDSC;
import MapDSC.AbstractMap.MapEntry;
import java.util.ArrayList;
/**
 *
 * @author nguyenminh
 * using technique opening address
 */
public class ProbeHashMap<K,V> extends AbstractHashMap<K, V> {
    private MapEntry<K,V>[] table;                                           // array
    private MapEntry<K,V> DEFUNCT = new MapEntry<>(null,null);      // auxiliary entry

    public ProbeHashMap(int capacity, int seed) {super(capacity, seed);}
    public ProbeHashMap(int cap) {super(cap);}
    public ProbeHashMap() {this(17);}

    @Override
    protected void createTable() {
        this.table = (MapEntry < K, V >[])new MapEntry[this.capacity];  // safe cast
    }
    
    private boolean isAvailable(int index){
        return this.table[index] == null || this.table[index] == this.DEFUNCT;
    }
    
    private int findSlot(int indexBucket, K key){
        int avail = -1;     
        int j = indexBucket;                              // walk index
        do{
            // search until reach null
            if(isAvailable(j)){                      // null or defunct
                if(avail == -1) avail = j;                // record first slot is available
                if(table[j] == null) break;               // bucket is empty(never has entry)
            }else if(table[j].getKey().equals(key))   // bucket is noy empty, check equal key
                return j;                                 // key is in already, index in range[0,...)
            j = (j+1) % capacity;                         // open address
        }while(j != indexBucket);  // if j turn starting point
        // index in range [-size, 0), index must not be 0, because of load factor
        // if key is not in map
        return -(avail+1);                                // return first slot availabled + 1
    }

    @Override
    protected V bucketGet(int indexBucket, K key) {
        int index = findSlot(indexBucket, key);
        if(index < 0) return null;
        return table[index].getValue();
    }

    @Override
    protected V bucketPut(int indexBucket, K key, V value) {
        int index = findSlot(indexBucket, key);
        // key is already in map
        if(index >= 0)
            // replace value
            return table[index].setValue(value);
        // key is not in map
        MapEntry<K,V> newEntry = new MapEntry<>(key,value);
        // descrement index
        table[-(index+1)] = newEntry;
        this.numbers++;
        return null;
    }

    @Override
    protected V bucketRemove(int indexBucket, K key) {
        int index = findSlot(indexBucket, key);
        if(index < 0) return null;
        V answer = table[index].getValue();
        // mark this table slot used to have entry
        // because we might scan over that slot
        table[index] = this.DEFUNCT;
        this.numbers--;
        return answer;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K,V>> answer = new ArrayList<>();
        for(MapEntry<K,V> e: this.table){
            if(e!=null && e != DEFUNCT)
                answer.add(e);
        }
        return answer;
    }
}
