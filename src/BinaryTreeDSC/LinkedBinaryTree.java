/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeDSC;

import ListDSC.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author nguyenminh
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E>{
    
    
    /** root of the tree, unique and null first*/
    protected Node<E> root;
    /** used to traverse whole the tree */
    private List<Position<E>> traverse;
    
    /** check p whether is position of node on the tree*/
    protected Node<E> validate(Position<E> p){
        /** 
         * Because interface position outside
         * There may be many class implement that interface
         * need to check exactly position of Node
         */
        if(!(p instanceof Node)) throw new IllegalArgumentException("Invalid position: p");
        
        /**
         * safe cast position to node
         * that can access methods in class Node
         */
        Node<E> tmpNode = (Node<E>) p;
        
        /**
         * because every node always element(data) and parent except root
         */
        if(tmpNode != this.root && tmpNode.getParent() == null) throw new IllegalArgumentException("The position is not in the tree");
        return tmpNode;
    }
    
    protected Node<E> createNode(E ele, Node<E> parent, Node<E> left, Node<E> right){
        return new Node<>(ele,parent,left,right);
    }
    
    @Override
    public Position<E> addRoot(E ele) throws IllegalStateException{
        if(!this.isEmpty()) throw new IllegalStateException("The tree has had root already");
        this.root = this.createNode(ele, null, null, null);
        this.size++;
        return this.root;
    }

    @Override
    public Position<E> addLeft(Position<E> p,E ele) throws IllegalArgumentException,IllegalStateException {
        if(this.isEmpty()) return this.addRoot(ele);
        Node<E> tmpNode = this.validate(p);
        if(tmpNode.getLeft() != null) throw new IllegalStateException("P has had already left child");
        tmpNode.setLeft(this.createNode(ele, tmpNode, null, null));
        this.size++;
        return tmpNode.getLeft();
    }

    public Position<E> addRight(Position<E> p,E ele) throws IllegalArgumentException,IllegalStateException {
        if(this.isEmpty()) return this.addRoot(ele);
        Node<E> tmpNode = this.validate(p);
        if(tmpNode.getRight()!= null) throw new IllegalStateException("P has had already right child");
        tmpNode.setRight((this.createNode(ele, tmpNode, null, null)));
        this.size++;
        return tmpNode.getRight();
    }
    
    @Override
    public Position<E> remove(Position<E> p) throws IllegalArgumentException, IllegalStateException {
        if(countChildren(p) == 2) throw new IllegalStateException("It had 2 children");
        Node<E> tmpNode = this.validate(p);
        if(tmpNode == this.root){
            this.root = tmpNode.getLeft() != null? tmpNode.getLeft(): tmpNode.getRight();
            this.root.setParent(null);
        }else{
            Node<E> tmpParent = tmpNode.getParent();
            Node<E> tmpChild = tmpNode.getLeft() == null ? tmpNode.getRight() : tmpNode.getLeft();
            // set parent for child node if and only if tmpNode is not null
            if(tmpChild != null) tmpChild.setParent(tmpParent);
            if(tmpParent.getRight() == tmpNode){
                tmpParent.setRight(tmpChild);
            }else{
                tmpParent.setLeft(tmpChild);
            }
        }
        this.size--;
        // mark that p has not been in tree
        tmpNode.setParent(null);
        return tmpNode;
    }
    
    @Override
    public E set(Position<E> p, E ele) throws IllegalStateException{
        Node<E> tmpNode = this.validate(p);
        E oldEle = tmpNode.getEle();
        tmpNode.setEle(ele);
        return oldEle;
    }
    
    @Override
    public Position<E> leftChild(Position<E> p) throws IllegalArgumentException {return this.validate(p).getLeft();}

    @Override
    public Position<E> rightChild(Position<E> p) throws IllegalArgumentException {return this.validate(p).getRight();}

    @Override
    public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
        Node<E> tmpParent = validate(this.parent(p));
        if(tmpParent.getLeft() == p) return tmpParent.getRight();
        return tmpParent.getLeft();
    }

    @Override
    public Position<E> root() {return this.root;}

    @Override
    public Position<E> parent(Position p) throws IllegalArgumentException {return this.validate(p).getParent();}

    @Override
    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        List<Position<E>> childList = new ArrayList<>(2);
        Node<E> tmpNode = this.validate(p);
        if(tmpNode.getLeft() != null) childList.add(tmpNode.getLeft());
        if(tmpNode.getRight()!= null) childList.add(tmpNode.getRight());
        return childList;
    }
    
    public Iterable<Position<E>> ancestor(Position<E> p){
        List<Position<E>> ancestorList  = new ArrayList<>();
        for(; !isRoot(p); ancestorList.add(p=parent(p)));
        return ancestorList;
    }

    @Override
    public int countChildren(Position<E> p) throws IllegalArgumentException {
        Node<E> tmpNode = this.validate(p);
        int count = 0;
        if(tmpNode.getLeft() != null) count++;
        if(tmpNode.getRight()!= null) count++;
        return count;
    }

    @Override
    public boolean isRoot(Position<E> p) throws IllegalArgumentException {return this.validate(p) == root;}

    private void resetTraverse(){this.traverse = new ArrayList<>(this.size);}
    private void visit(Position<E> p){
        this.traverse.add(this.validate(p));
    }
    
    private void breathTraversal(Position<E> p){
        List<Position<E>> tmp = new ArrayList<>(this.size);
        tmp.add(p);
        while(!tmp.isEmpty()){
            Position<E> curPos = tmp.remove(0);
            this.visit(curPos);
            for(Position<E> c : this.children(curPos)) tmp.add(c);
        }
    }
    
    private void preorderTraversal(Position<E> p){
        this.visit(p);
        for(Position<E> c: this.children(p)) preorderTraversal(c);
    }
    
    private void inorderTraversal(Position<E> p){
        try{
            inorderTraversal(this.leftChild(p));
            this.visit(p);
            inorderTraversal(this.rightChild(p));
        }catch(IllegalArgumentException e){}
    }
    
    private void postorderTraversal(Position<E> p){
        for(Position<E> c : this.children(p)) postorderTraversal(c);
        this.visit(p);
    }
    
    @Override
    public Iterable<Position<E>> positions() {
        return this.positions(3);
    }
    /**
     * 0: prefix order
     * 1: in order
     * 2: suffix order
     * 3: breath first
     * @param type
     * @return iterable position of nodes in the tree upon particular type
     */
    public Iterable<Position<E>> positions(int type) {
        return this.positions(root, type);
    }
    
    /**
     * 0: prefix order
     * 1: in order
     * 2: suffix order
     * 3: breath first
     * @param type
     * @return iterable position of nodes in the tree upon particular type
     */
    public Iterable<Position<E>> positions(Position<E> p,int type) {
        this.resetTraverse();
        switch(type){
            case 3 -> this.breathTraversal(p);
            case 0 -> this.preorderTraversal(p);
            case 1 -> this.inorderTraversal(p);
            case 2 -> this.postorderTraversal(p);
            default -> this.preorderTraversal(p);
        }
        return this.traverse;
    }

    private class BinaryTreeIterator implements Iterator<E>{
        Iterator<Position<E>> tmpIter;
        public BinaryTreeIterator() {
            positions();
            tmpIter = traverse.iterator();
        }
        @Override
        public boolean hasNext() {return tmpIter.hasNext();}
        @Override
        public E next() {return this.tmpIter.next().getElement();}
        
    }
    @Override
    public Iterator<E> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int depth(Position<E> p) throws IllegalArgumentException {
        if(this.isRoot(p))
            return 1;
        else
            return 1 + this.depth(this.parent(p));
    }

    @Override
    public int height(Position<E> p) throws IllegalArgumentException {
        // check postion first
        this.validate(p);
        // every position has min height is 1
        int max = 1;
        for(Position<E> c : this.children(p))
            // height of one node equals 1 plus height of which subtree has larger height
            // if p does not have any child -> max will not be changed
            max = Math.max(max, 1 + this.height(c));
        return max;
    }
    
    public int height(){return this.height(this.root());}
}
