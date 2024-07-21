/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapDSC;
import MapDSC.AbstractSortedMap;
import BinaryTreeDSC.LinkedBinaryTree;
import ListDSC.Position;
import MapDSC.Entry;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author nguyenminh
 * TreeMap can be said that one implement of binary search tree
 * every node on tree is internal node
 * external node is abstract, placeholder
 */
public class TreeMap<K,V> extends AbstractSortedMap<K, V> {
    // Data structure for balance tree
    protected static class BalanceableBinaryTree<K,V> extends LinkedBinaryTree<Entry<K,V>>{
        protected class BSTNode<E> extends Node{
            // field refer height of that node
            private int aux = 0;
            public BSTNode(E ele, Node<E> parent, Node<E> left, Node<E> right) {
                super(ele, parent, left, right);
            }
            public int getAux() {return aux;}
            public void setAux(int aux) {this.aux = aux;}
        }
        @Override
        protected BSTNode<Entry<K,V>> validate(Position<Entry<K,V>> p){
            if(!(p instanceof BSTNode)) throw new IllegalArgumentException("Invalid p");
            BSTNode<Entry<K,V>> tmp = (BSTNode<Entry<K, V>>)p;
            if(tmp != this.root() && tmp.getParent() == null) throw new IllegalArgumentException("p is not in tree");
            return tmp;
        }
        public int getAux(Position<Entry<K,V>> p){return this.validate(p).getAux();}
        public void setAux(Position<Entry<K,V>> p,int aux){this.validate(p).setAux(aux);};

        @Override
        protected BSTNode<Entry<K, V>> createNode(Entry<K, V> ele, Node<Entry<K, V>> parent, Node<Entry<K, V>> left, Node<Entry<K, V>> right) {
            return new BSTNode<>(ele,parent,left,right);
        }
        
        protected void relinks(Node<Entry<K,V>> parent, Node<Entry<K,V>> child, boolean isLeftChild){
            child.setParent(parent);
            if(isLeftChild)
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        
        protected void rotate(Position<Entry<K,V>> p){
            Node<Entry<K,V>> tmpNode = this.validate(p);
            Node<Entry<K,V>> tmpParent = tmpNode.getParent();
            Node<Entry<K,V>> tmpGrand = tmpParent.getParent();
            if(tmpGrand == null){
                this.root = tmpNode;
                this.root.setParent(null);
            }else
                this.relinks(tmpGrand, tmpNode, tmpParent==tmpGrand.getLeft());
            // swap node and its parent
            if(tmpNode == tmpParent.getLeft()){
                relinks(tmpParent, tmpNode.getRight(), true);
                relinks(tmpNode,tmpParent,false);
            }else{
                relinks(tmpParent, tmpNode.getLeft(), false);
                relinks(tmpNode, tmpParent, true);
            }
        }
    }
    
    /** return new root of sub-tree after restructure, support for method re-balancing */
    protected Position<Entry<K,V>> restructure(Position<Entry<K,V>> p){
        Position<Entry<K,V>> parent = this.parent(p);
        Position<Entry<K,V>> grand = this.parent(parent);
        // 3 node in same align -> parent becomes root of sub-tree
        // zig-zig
        if((p == this.leftChild(parent)) == (parent == this.leftChild(grand))){
            this.tree.rotate(parent);
            return parent;
        // 3 node in zig-zag -> rotate 1st -> 3 in same align(p is between), rotate 2nd -> same above ⬆
        // zig-zag
        }else{
            this.tree.rotate(p);
            this.tree.rotate(p);
            return p;
        }
    }
    // actual tree structure
    protected BalanceableBinaryTree<K,V> tree = new BalanceableBinaryTree<>();

    public TreeMap(Comparator<K> comp) {
        super(comp);
        this.addRoot(null);
    }

    public TreeMap() {
        super();
        this.addRoot(null);
    }
    
    public boolean isRoot(Position<Entry<K,V>> p) {return this.tree.isRoot(p);}
    public boolean isExternal(Position<Entry<K,V>> p) {return this.tree.isExternal(p);}
    public boolean isInternal(Position<Entry<K,V>> p) {return this.tree.isInternal(p);}
    protected Position<Entry<K,V>> addRoot(Entry<K,V> ele) {return this.tree.addRoot(ele);}
    public Entry<K,V> set(Position<Entry<K,V>> p, Entry<K,V> ele) {return this.tree.set(p,ele);}
    protected Position<Entry<K,V>> addLeft(Position<Entry<K,V>> p, Entry<K,V> ele) {return this.tree.addLeft(p,ele);}
    protected Position<Entry<K,V>> addRight(Position<Entry<K,V>> p, Entry<K,V> ele) {return this.tree.addRight(p,ele);}
    public Position<Entry<K,V>> root() {return this.tree.root();}
    public Position<Entry<K,V>> parent(Position<Entry<K,V>> p) {return this.tree.parent(p);}
    public Position<Entry<K,V>> leftChild(Position<Entry<K,V>> p) {return this.tree.leftChild(p);}
    public Position<Entry<K,V>> rightChild(Position<Entry<K,V>> p) {return this.tree.rightChild(p);}
    
    // hook rebalancing tree
    /** This is binary search tree -> no need to embody 3 below methods.
    /** called after a new node added */
    protected void rebalanceInsert(Position<Entry<K,V>> p){}
    /** called after accessing a node */
    protected void rebalanceAccess(Position<Entry<K,V>> p){}
    /** called after remove a node */
    protected void rebalanceRemove(Position<Entry<K,V>> p){}
    
    /** set entry at position*/    
    protected void expandExternal(Position<Entry<K,V>> p, Entry<K,V> entry){
        this.set(p, entry);
        /**create new node has element null as left child of p*/
        this.addLeft(p, null);
        /**create new node has element null as right child of p*/
        this.addRight(p, null);
        
        /** -> that make p becomes internal*/
    }
    // search key in sub-tree rooted by pu7
    protected Position<Entry<K,V>> searchTree(Position<Entry<K,V>> p, K key){
        /** p is node has element null */
        if(this.isExternal(p))                                  // -> not found
            return p;
        int result = this.compare(key, p.getElement().getKey());
        if(result == 0)                                         // -> was found, p is internal
            return p;
        else if(result > 0)                                     // -> recurse with p's right subtree
            return searchTree(this.rightChild(p), key);  
        else
            return searchTree(this.leftChild(p), key);   // -> recurse with p's left subtree
    }
    
    public Position<Entry<K,V>> position(K key){return this.searchTree(this.root(), key);}
    
    @Override
    public int size() {return (this.tree.size()-1)/2;}

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
            this.expandExternal(p, newEntry);
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
            // 1. min node of right-subtreef
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
    
    /** @return position of node has key max in sub tree rooted by p*/
    protected Position<Entry<K,V>> treeMax(Position<Entry<K,V>> p){
        Position<Entry<K,V>> tmp = p;
        while(!this.isExternal(tmp)){
            tmp = this.rightChild(tmp);
        }
        return this.parent(tmp);
    }
    /** @return position of node has key min in sub tree rooted by p*/
    protected Position<Entry<K,V>> treeMin(Position<Entry<K,V>> p){
        Position<Entry<K,V>> tmp = p;
        while(!this.isExternal(tmp)){     // when p is not leaf
            tmp = this.leftChild(tmp);
        }
        return this.parent(tmp);
    }

    public Entry<K,V> lastEntry(){
        if(this.isempty()) return null;
        return treeMax(this.root()).getElement();
    }
    
    public Entry<K,V> firstEntry(){
        if(this.isempty()) return null;
        return this.treeMin(this.root()).getElement();
    }
    
    public Entry<K,V> floorEntry(K key){
        this.checKey(key);
        Position<Entry<K,V>> p = this.searchTree(this.root(), key);
        // equal
        if(this.isInternal(p)) return p.getElement();
        while(!this.isRoot(p)){
            if(p == rightChild(parent(p)))
                return parent(p).getElement();
            else
                p = parent(p);
        }
        return null;
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
        this.checKey(key);
        Position<Entry<K,V>> p = this.searchTree(this.root(), key);
        if(this.isInternal(p)) return p.getElement();
        // can floor key
        while(!this.isRoot(p)){
            if(p == leftChild(parent(p)))
                return parent(p).getElement();
            else 
                p = parent(p);
        }
        return null;
    }

    @Override
    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
        this.checKey(key);
        Position<Entry<K,V>> p = searchTree(this.root(), key);
        // if p has right child -> higher entry of p is min entry of right-subtree of p
        if(isInternal(p) && isInternal(rightChild(p))) return treeMin(rightChild(p)).getElement();
        while(!isRoot(p)){
            // higher entry of p is first ancestor has p in left sub-tree
            if(p == leftChild(parent(p)))
                return parent(p).getElement();
            else
                p = parent(p);
        }
        return null;
    }

    @Override
    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
        this.checKey(key);
        Position<Entry<K,V>> p = searchTree(this.root(), key);
        if(isInternal(p) && isInternal(leftChild(p))) return treeMax(leftChild(p)).getElement();
        while(!isRoot(p)){
            // lower entry of p is first ancestor has p in right sub-tree
            if(p == rightChild(parent(p)))
                return parent(p).getElement();
            else
                p = parent(p);
        }
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>();
        for(Position<Entry<K,V>> c : this.tree.positions(3)){
            if(!this.isExternal(c))
                buffer.add(c.getElement());            
        }
        return buffer;
    }
    
    //because all leaves are external node -> height = height - 1
    //public int height(Position<Entry<K,V>> p){return this.tree.height(p) - 1;}
    //public int height(){return this.tree.height() - 1;}
    //public int depth(Position<Entry<K,V>> p){return this.tree.depth(p);}
}