/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListDSC;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author nguyenminh
 */
public class LinkedPositionalList<E> extends AbstractPositionalList<E> {
    
    private Node<E> header;
    private Node<E> trailer;
    private int size;

    public LinkedPositionalList() {
        this.header = new Node(null,null,null);
        this.trailer = new Node(null,this.header,null);
        this.header.setNextNode(this.trailer);
        this.size = 0;   
    }

    @Override
    public int size(){return this.size;}
    
    /** 
     * check position given is on list
     * call any when user send position as parameter
     * @return associated node at position p
     */
    private Node<E> validate(Position<E> p) throws IllegalArgumentException{
        /** check p is Node (interface can be implemented by other classes)*/
        if(!(p instanceof Node)) throw new IllegalArgumentException("Unvalid position(not Node)");
        Node<E> tmpNode = (Node<E>)p;
        /** because no any Node in the list has previous/next node is null, except header and trailer
         * but it is just abstract
         * No way to get position of them
         */
        if(tmpNode.getNextNode() == null) throw new IllegalArgumentException("That position is not in list");
        /** @return associated node*/
        return tmpNode;
    }
    
    /**
     * @param n
     * call any when user want to get position
     * @return associated position
     */
    private Position<E> position(Node<E> n){
        /** header and trailer are like abstract*/
        if(n == this.header || n == this.trailer) return null;
        return n;
    }
    
    @Override
    public Position<E> first(){return this.position(header.getNextNode());}

    @Override
    public Position<E> last(){return this.position(trailer.getPreNode());}
    
    @Override
    public Position<E> before(Position<E> refPos) throws IllegalStateException {
       return this.position(this.validate(refPos).getPreNode());}

    @Override
    public Position<E> after(Position<E> refPos) throws IllegalStateException {
        return this.position(this.validate(refPos).getNextNode());}

    private Position<E> addBetween(E ele, Node<E> pre, Node<E> next){
        Node<E> newNode = new Node<>(ele,pre,next);
        pre.setNextNode(newNode);
        next.setPreNode(newNode);
        this.size++;
        return newNode;
    }
    @Override
    public Position<E> addFirst(E ele) {
        return this.addBetween(ele, this.header, this.header.getNextNode());
    }

    @Override
    public Position<E> addLast(E ele) {
        return this.addBetween(ele, this.trailer.getPreNode(), this.trailer);
    }

    @Override
    public Position<E> addBefore(Position p, E ele) throws IllegalArgumentException {
        Node<E> tmpNode = this.validate(p);
        return this.addBetween(ele, tmpNode.getPreNode(), tmpNode);
    }

    @Override
    public Position<E> addAfter(Position p, E ele) throws IllegalArgumentException{
        Node<E> tmpNode = this.validate(p);
        return this.addBetween(ele, tmpNode, tmpNode.getNextNode());
    }

    @Override
    public E set(Position<E> p, E ele) throws IllegalArgumentException {
        Node<E> curNode = this.validate(p);
        E oldData = curNode.getElement();
        curNode.setElement(ele);
        return oldData;
    }

    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> removedNode = this.validate(p);
        Node<E> pre = removedNode.getPreNode();
        Node<E> next = removedNode.getNextNode();
        pre.setNextNode(next);
        next.setPreNode(pre);
        removedNode.setNextNode(null);
        removedNode.setPreNode(null);
        //desiredNode = null;
        this.size--;
        return removedNode.getElement();
    }

    @Override
    public Iterator<E> iterator() {return this.EleIterator();}
}
