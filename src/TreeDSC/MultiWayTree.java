/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TreeDSC;
import MapDSC.Entry;
import MapDSC.SortedTableMap;
import MapDSC.ProbeHashMap;
import MapDSC.Map;
import MapDSC.AbstractSortedMap;
import java.util.Iterator;
import ListDSC.LinkedPositionalList;
import ListDSC.Position;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author nguyenminh
 */
public class MultiWayTree<K,V> extends AbstractSortedMap<K, V> implements Tree<Entry<K,V>>{
    protected class MWTNode<K,V> implements Position<Entry<K,V>>{
        private SortedTableMap<K,Entry<V,MWTNode<K,V>>> data;
        private LinkedPositionalList<MWTNode<K,V>> children;
        private MWTNode<K,V> parent;

        public MWTNode(MWTNode<K, V> parent) {
            this.parent = parent;
            this.data = new SortedTableMap<>();
            this.children = new LinkedPositionalList<>();
        }

        public SortedTableMap<K, Entry<V, MWTNode<K, V>>> getData() {return data;}
        public LinkedPositionalList<MWTNode<K, V>> getChildren() {return children;}
        public void setChildren(LinkedPositionalList<MWTNode<K, V>> children) {this.children = children;}
        public MWTNode<K, V> getParent() {return parent;}
        public void setParent(MWTNode<K, V> parent) {this.parent = parent;}

        @Override
        public Entry<K, V> getElement() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    
    private MWTNode<K,V> root;
    int size;

    public MultiWayTree() {
        super();
        root = new MWTNode<>(null);
        size = 0;
    }

    public MultiWayTree(Comparator<K> comp) {
        super(comp);
        root = new MWTNode<>(null);
        size = 0;
    }

    private void expandExternal(Position<Entry<K,V>> p, K key, V value){
        
    }
    
    protected Position<Entry<K,V>> searchTree(Position<Entry<K,V>> p,K key){
        MWTNode<K,V> tmp = this.validate(p);
        if(isExternal(p))
            return p;
        Entry<K,Entry<V,MWTNode<K,V>>> ceil = tmp.getData().ceilingEntry(key);
        if(this.compare(ceil.getKey(), key) == 0) return ceil.getValue().getValue();
        return searchTree(ceil.getValue().getValue(), key);
    }
    
    private MWTNode<K,V> validate(Position<Entry<K,V>> p){
        if(!(p instanceof MWTNode)) throw new IllegalArgumentException("Invalid p");
        MWTNode<K,V> tmpNode = (MWTNode<K, V>)p;
        if(tmpNode!=root && tmpNode.getParent()==null) throw new IllegalArgumentException("That position is not on tree");
        return tmpNode;
    }

    @Override
    public Position<Entry<K, V>> parent(Position p) throws IllegalArgumentException {
        return this.validate(p).getParent();
    }

    @Override
    public Position<Entry<K, V>> root() {
        return this.root;
    }

    @Override
    public Iterable<Position<Entry<K, V>>> children(Position<Entry<K, V>> p) throws IllegalArgumentException {
        ArrayList<Position<Entry<K,V>>> listChid = new ArrayList<>();
        Iterable<MWTNode<K, V>> iter = this.validate(p).getChildren().EleIterable();
        for(Position<Entry<K,V>> c: iter){
            listChid.add(c);
        }
        return listChid;
    }

    @Override
    public int countChildren(Position<Entry<K, V>> p) throws IllegalArgumentException {
        return this.validate(p).getChildren().size();
    }

    @Override
    public boolean isExternal(Position<Entry<K, V>> p) throws IllegalArgumentException {
        return this.validate(p).getData().size()==0;
    }

    @Override
    public boolean isInternal(Position<Entry<K, V>> p) throws IllegalArgumentException {
        return !this.isExternal(p);
    }

    @Override
    public boolean isRoot(Position<Entry<K, V>> p) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Iterable<Position<Entry<K, V>>> positions() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public V put(K key, V value) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Entry<K, V> firstEntry() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Entry<K, V> lastEntry() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
    
}
