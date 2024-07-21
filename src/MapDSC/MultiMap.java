/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapDSC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

/**
 *
 * @author nguyenminh
 */
public class MultiMap<K,V> {
    private Map<K,List<V>> data = new HashMap<>();
    private int total = 0;
    public MultiMap() {
    }
    
    public int size(){return this.total;}
    public boolean isEmpty() { return this.size() == 0;}
    public void put(K key, V value){
        List<V> tmp = this.data.get(key);
        if(tmp == null){
            this.data.put(key, new ArrayList<>());
        }
        tmp.add(value);
        this.total++;
    }
    public Iterable<V> get(K key){
        Iterable<V> answer = this.data.get(key);
        if(answer != null)
            return answer;
        return new ArrayList<>();
    }
    
    public boolean remove(K key, V value){
        List<V> tmp = this.data.get(key);
        boolean wasRemoved = false;
        if(tmp!=null){
            wasRemoved = tmp.remove(value);
            if(wasRemoved) 
                this.total--;
            if(tmp.isEmpty()){
                this.data.remove(key);
            }
        }
        return wasRemoved;
    }
    
    public Iterable<V> removeAll(K key){
        List<V> tmp = this.data.remove(key);
        if(tmp!=null){
            this.total -= tmp.size();
            return tmp;
        }else{
            return new ArrayList<>();
        }
    }
    
    public Iterable<Map.Entry<K,V>> entries(){
        ArrayList<Map.Entry<K,V>> tmp = new ArrayList<>();
        for(Map.Entry<K,List<V>> c : this.data.entrySet()){
            for(V v: c.getValue()){
                tmp.add(new SimpleEntry<>(c.getKey(),v));
            }
        }
        return tmp;
    }
}
