
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinarySearchTreeDSC;

import ListDSC.Position;
import MapDSC.Entry;
import java.util.Comparator;
import MapDSC.TreeMap;

/**
 *
 * @author nguyenminh
 * extends TreeMapp and implement re-balance method
 * every node n, sub-tree rooted by n is balanced
 */

public class AVLTree<K,V> extends TreeMap<K,V>{
    public AVLTree(Comparator<K> comp) {super(comp);}
    public AVLTree() {super();}
    
    protected int height(Position<Entry<K,V>> p){ return this.tree.getAux(p);}
    // height of p is computed base on height of its children
    protected void recomputeHeight(Position<Entry<K,V>> p){
        this.tree.setAux(p,1+Math.max(this.height(leftChild(p)), this.height(rightChild(p))));
    }

    protected boolean isBalance(Position<Entry<K,V>> p){
        return Math.abs(height(leftChild(p)) - height(rightChild(p))) <= 1;
    }
    
    protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> p){
        if(height(leftChild(p)) > height(rightChild(p))) return leftChild(p);
        if(height(leftChild(p)) < height(rightChild(p))) return rightChild(p);
        // 2 childs has the same height
        if(isRoot(p)) return leftChild(p);                     // irrelevant
        // zig-zig we only need single rotation
        if(p == leftChild(parent(p))) return leftChild(p);   // return alinged child
        else return rightChild(p);                             
    }
    @Override
    public V get(K key) {
        this.checKey(key);
        Position<Entry<K,V>> p = this.searchTree(this.root(), key);
        if(this.isExternal(p)) return null;
        return p.getElement().getValue();
    }

    @Override
    public V put(K key, V value) throws IllegalArgumentException {
        this.checKey(key);
        Position<Entry<K,V>> p = this.searchTree(this.root(), key);
        //create new entry
        MapEntry<K,V> newEntry = new MapEntry<>(key,value);
        // is not on tree
        if(this.isExternal(p)){                
            super.expandExternal(p, newEntry);
            // when a new node added so only its ancestor change balance-property
            this.rebalanceInsert(p);
            return null;
        // already in on tree
        }else{
            V oldData = p.getElement().getValue();
            this.set(p, newEntry);
            this.rebalanceAccess(p);
            return oldData;
        }
    }

    @Override
    public V remove(K key) {
        this.checKey(key);
        Position<Entry<K,V>> p = this.searchTree(this.root(), key);
        if(this.isExternal(p)){     // not found
            rebalanceAccess(p);
            return null;
        }
        // get tree at removed node
        V oldData = p.getElement().getValue();
        // p has 2 children (all child is internal)
        if(this.isInternal(leftChild(p)) && this.isInternal(rightChild(p))){
            // determine replaced node(predecessor) for removed node:
            // 1. min node of right-subtree
            // 2. max node of left-subtree 
            Position<Entry<K,V>> predecessor = this.treeMax(leftChild(p));
            // up-ward replacement to removed node(set value at p with value at r) -> keep ralationship ancestor-descendant
            this.set(p, predecessor.getElement());
            // delete predecessor node instead
            // predecessor has at most left child
            p = predecessor;
        }
        
        Position<Entry<K,V>> leaf = (this.isExternal(leftChild(p)) ?  leftChild(p) : rightChild(p));
        // p is replaced by sib
        Position<Entry<K,V>> sib = this.tree.sibling(leaf);
        // make sure p has at most only one child(not null)
        this.tree.remove(leaf);
        // remove desired node
        this.tree.remove(p);
        // rebalance
        rebalanceRemove(sib);
        
        return oldData;
    }
    
    @Override
    protected void rebalanceRemove(Position<Entry<K, V>> p) {
        // because p is replacemet so sub-tree rooted by p is balanced -> just rebalance parent of p
        if(!this.isRoot(p))
            rebalance(parent(p));
    }
    
    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> p) {rebalance(p);}   
    
    private void rebalance(Position<Entry<K,V>> p){
        int oldHeight, newHeight;
        do{
            // height of p before restructure
            // if p is new node -> old = 0
            oldHeight = height(p);
            
            if(!isBalance(p)){
                // p is unbalanced -> some of its descendant are unbalanced
                // to balance p we need to balance its descendant(level 3), p's level is 1
                p = restructure(tallerChild(tallerChild(p)));
                // recompute height of p's child after restructure
                recomputeHeight(leftChild(p));
                recomputeHeight(rightChild(p));
            }
            // recompute height of p
            // recompute height of new node p -> height = 1
            recomputeHeight(p);
            // height of p after restructure(if)
            newHeight = height(p);
            p = parent(p);
            
            // if oldHeight == newHeight ->  a new node is added into sub-tree(balance previously) in bottom level
            // p == null -> no any node
        }while(oldHeight != newHeight && p != null);
    }
}