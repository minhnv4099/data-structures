/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapDSC;
import ListDSC.Position;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Red Black tree is binary search tree with nodes colored red and black in a way satisfy following properties:
 *      <div><b>Root Property</b>: The root is black.</div>
 *      <div><b>External Property</b>: Every external node is black.</div>
 *      <div><b>Red Property</b>:The children of a red node are black.</div>
 *      <div><b>Depth Property</b>: All external nodes have the same black depth, defined as the number of proper ancestors that are black.</div>
 * @author nguyenminh
 *
 * 
 */
public class RedBlackTreeMap<K,V> extends TreeMap<K, V> {
    
    public RedBlackTreeMap(Comparator<K> comp) {super(comp);}
    public RedBlackTreeMap() {super();}
 
    public int color(Position<Entry<K,V>> p){
        // 0 is red, 1 is black
        return this.tree.getAux(p);
    }
    
    protected boolean isMaintianProperty(Position<Entry<K,V>> p){
        if (this.color(p) == 0){
            Position left = this.tree.leftChild(p);
            Position right = this.tree.rightChild(p);
            return this.color(left)*this.color(right) == 1;
        }
        return true;
    }

    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        while (this.parent(p) != null && this.color(this.parent(p)) == 0){
            Position<Entry<K,V>> p_parent = this.parent(p);
            Position<Entry<K,V>> p_grandpa = this.parent(p_parent);
            System.out.println(p_grandpa.getElement().getKey());
            if(p_parent == this.leftChild(p_grandpa)){
                Position<Entry<K,V>> p_uncle = this.rightChild(p_grandpa);
                if(this.color(p_uncle) == 0){
                    this.tree.setAux(p_uncle, 1);
                    this.tree.setAux(p_parent, 1);
                    this.tree.setAux(p_grandpa, 0);
                    p = p_grandpa;
                }else{
                    if((p == this.rightChild(p_parent))){this.tree.rotate(p);}
                    p = this.leftChild(p);
                    p_parent = this.parent(p);
                    p_grandpa = this.parent(this.parent(p));
                    this.tree.setAux(p_parent, 1);
                    this.tree.setAux(p_grandpa, 0);
                    this.tree.rotate(p_parent);
                }
            }else{
                Position<Entry<K,V>> p_uncle = this.leftChild(p_grandpa);
                if(this.color(p_uncle) == 0){
                    this.tree.setAux(p_uncle, 1);
                    this.tree.setAux(p_parent, 1);
                    this.tree.setAux(p_grandpa, 0);
                    p = p_parent;
                }else{
                    if((p == this.leftChild(p_parent))){this.tree.rotate(p);}
                    p = this.rightChild(p);
                    p_parent = this.parent(p);
                    p_grandpa = this.parent(this.parent(p));
                    this.tree.setAux(p_parent, 1);
                    this.tree.setAux(p_grandpa, 0);
                    this.tree.rotate(p_parent);
                }
            }
        }
        this.tree.setAux(this.root(), 1);
    }

    @Override
    public V get(K key) {
        return super.get(key); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    @Override
    public V put(K key, V value) throws IllegalArgumentException {
        this.checKey(key);
        Position<Entry<K,V>> p = this.searchTree(this.root(), key);
        MapEntry<K,V> newEntry = new MapEntry<>(key,value);
        if(this.isExternal(p)){
            this.expandExternal(p, newEntry);
            rebalanceInsert(p);
            return null;
        }else{
            Entry<K,V> oldEle = p.getElement();
            this.set(p, newEntry);
            return oldEle.getValue();
        }
    }
    @Override
    public V remove(K key) {
        return super.remove(key); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    

   
    
   
    
    @Override
    protected void rebalanceRemove(Position<Entry<K, V>> p) {
        super.rebalanceRemove(p); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void rebalanceAccess(Position<Entry<K, V>> p) {
        super.rebalanceAccess(p); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    
    
    
}
