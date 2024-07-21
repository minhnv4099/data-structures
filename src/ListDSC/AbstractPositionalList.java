/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ListDSC;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *
 * @author nguyenminh
 */
public abstract class AbstractPositionalList<E> implements PositionalList<E> {
    /**
     * Abstract class Node to store data
     * implement interface Position
     * Node is private/ abstracts class to maintain proper encapsulation
     * Users only know the position not node
     * So that they can call only one getElement method from outside
     * Node are positions
     * Use Position seem as integer index
     */
    protected class Node<E> implements Position<E>{
        private Node<E> preNode;
        private Node<E> nextNode;
        private E data;
        
        public Node(E data, Node<E> preNode, Node<E> nextNode) {
            this.preNode = preNode;
            this.nextNode = nextNode;
            this.data = data;
        }

        public Node<E> getPreNode() {return preNode;}
        public void setPreNode(Node<E> preNode) {this.preNode = preNode;}
        public Node<E> getNextNode() {return nextNode;}
        public void setNextNode(Node<E> nextNode) {this.nextNode = nextNode;}
        public void setElement(E data) {this.data = data;}

        @Override
        public E getElement() {return this.data;}
    }

    @Override
    public boolean isEmpty() {return this.size() == 0;}
    
    private class PositionIterator implements Iterator<Position<E>>{
        private Position<E> cur = AbstractPositionalList.this.first();
        private Position<E> recent = null;
        @Override
        public boolean hasNext() {return cur != null;}
        
        @Override
        public Position<E> next() {
            if(!this.hasNext()) throw new NoSuchElementException("Stop iteration");
            Position<E> answer = cur;
            recent = answer;
            cur = after(cur);
            return answer;
        }

        @Override
        public void remove() {
            if(recent == null) throw new NoSuchElementException("No element called");
            AbstractPositionalList.this.remove(recent);
            recent = null;
        }
    }
    
    private class PositionIterable implements Iterable<Position<E>>{
        @Override
        public Iterator<Position<E>> iterator() {return new PositionIterator();}   
    }
    
    private class ElementIterator implements Iterator<E>{
        Iterator<Position<E>> posIterator = new PositionIterator();
        @Override
        public boolean hasNext() {return this.posIterator.hasNext();}
        @Override
        public E next() {return posIterator.next().getElement();}
        @Override
        public void remove() {posIterator.remove();}
    }
    
    private class ElementIterable implements Iterable<E>{
        public Iterator<E> iterator(){return new ElementIterator();}
    }
    // iteration of element/position
    public ElementIterable EleIterable(){return new ElementIterable();}
    public ElementIterator EleIterator(){return new ElementIterator();}
    public PositionIterable PosIterable(){return new PositionIterable();}
    public PositionIterator PosIterator(){return new PositionIterator();}
}
