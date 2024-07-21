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
public class SortedTableMap<K,V> extends AbstractSortedMap<K, V>{

    public SortedTableMap() {
        super();
    }
    public  SortedTableMap(Comparator<K> comp) {
        super(comp);
    }
    /** sorted map -> binary search*/
    private int findIndex(K key,int begin, int end){
        this.checKey(key);
        /** when key not in map, end may be -1 or size -1 */
        if(begin > end)
            return end + 1;
        int mid = (begin+end)/2;
        int result = this.compare(key, this.table.get(mid).getKey());
        if(result == 0)
            return mid;
        if(result > 0)
            begin = mid + 1;
        else
            end = mid - 1;
        return findIndex(key, begin, end);
    }
    /** if provide only key parameter*/
    private int findIndex(K key){return this.findIndex(key,0,this.size()-1);}
    
    @Override
    public int size() {return this.table.size();}

    @Override
    public V get(K key) {
        int index = this.findIndex(key);
        // index always >= 0
        if(index < this.size() && this.compare(key, this.table.get(index).getKey()) == 0)
            return this.table.get(index).getValue();
        return null;
    }

    @Override
    public V put(K key, V value) throws IllegalArgumentException {
        int index = this.findIndex(key);
        if(index < this.size() && this.compare(key, this.table.get(index).getKey()) == 0)
            return this.table.get(index).setValue(value);
        this.table.add(index,new MapEntry<>(key,value));
        return null;
    }

    @Override
    public V remove(K key) {
        int index = this.findIndex(key);
        Entry<K,V> answer = this.safeEntry(index);
        if(answer != null)
            return this.table.remove(index).getValue();
        return null;
    }  
    
    private Entry<K,V> safeEntry(int i){
        if(i < 0 || i >= this.size()) return null;
        return this.table.get(i);
    }
    @Override
    public Entry<K,V> firstEntry(){return safeEntry(0);}
    @Override
    public Entry<K,V> lastEntry(){return safeEntry(this.size()-1);}
    @Override
    public Entry<K,V> floorEntry(K key){
        int index = this.findIndex(key);
        /*
        index = size
            -> key > last entry -> return last
        if key < first entry
            -> index-- = -1 -> return null 
        */
        if(index == this.size() || this.compare(key, this.firstEntry().getKey()) < 0){
            index--;
        }
        return safeEntry(index);
    }
    
    @Override
    public Entry<K,V> ceilingEntry(K key){return safeEntry(findIndex(key));}
    @Override
    public Entry<K,V> lowerEntry(K key){return this.safeEntry(findIndex(key)-1);}
    @Override
    public Entry<K,V> higherEntry(K key){
        Entry<K,V> tmpEntry = this.ceilingEntry(key);
        if(this.compare(key, tmpEntry.getKey()) == 0)
            return this.safeEntry(this.findIndex(key)+1);
        return tmpEntry;
    }
  
    private Iterable<Entry<K,V>> snapshot(int index, K stop){
        ArrayList<Entry<K,V>> buffer = new ArrayList<>();
        /** stop can be in or not in map*/
        /** stop = null, for walk whole map -> while loop stop when index == this.size*/
        while(index < this.size() && (stop == null || this.compare(stop, this.table.get(index).getKey()) >= 0)){
            buffer.add(safeEntry(index++));
        }
        return buffer;
    }
    @Override
    public Iterable<Entry<K, V>> entrySet() {return snapshot(0, null);}
    @Override
    public Iterable<Entry<K,V>> subMap(K fromKey, K toKey){return snapshot(this.findIndex(fromKey), toKey);}
}
