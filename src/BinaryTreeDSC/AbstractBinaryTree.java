/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeDSC;

import ListDSC.Position;

/**
 *
 * @author nguyenminh
 * As tree is a abstract structure
 */
public abstract class AbstractBinaryTree<E> implements BinaryTree<E>{
    /** 
     * protected inner class for implementation like node on tree
     * implement interface Position, user just can access element(data) outside
     */
    protected class Node<E> implements Position<E>{
        private E ele;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        /** every node has been created have no left and right child, only parent and element(data)*/
        public Node(E ele, Node<E> parent) {
            this(ele,parent,null,null);
        }

        public Node(E ele, Node<E> parent, Node<E> left, Node<E> right) {
            this.ele = ele;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
        

        public E getEle() {return ele;}
        public void setEle(E ele) {this.ele = ele;}
        public Node<E> getParent() {return parent;}
        public void setParent(Node<E> parent) {this.parent = parent;}
        public Node<E> getLeft() {return left;}
        public void setLeft(Node<E> left) {this.left = left;}
        public Node<E> getRight() {return right;}
        public void setRight(Node<E> right) {this.right = right;}
        @Override
        public E getElement(){return this.getEle();}
    }
    
    protected int size;
    
    @Override
    public int size(){return this.size;}
    
    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return this.countChildren(p) == 0;
    }

    @Override
    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return this.countChildren(p) > 0;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
}
