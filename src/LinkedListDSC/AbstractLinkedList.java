/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LinkedListDSC;

/**
 *
 * @author nguyenminh
 */
public abstract class AbstractLinkedList<E> implements LinkedList<E> {
    protected static class Node<E>{
        private E data;
        private Node<E> nextNode;

        public Node(E data, Node<E> nextNode) {
            this.data = data;
            this.nextNode = nextNode;
        }

        public E getData() {return data;}

        public void setData(E data) {this.data = data;}

        public Node<E> getNextNode() {return nextNode;}

        public void setNextNode(Node<E> nextNode) {this.nextNode = nextNode;}
    }
    protected void checkIndex(int target,int range){
        if(target < 0 || target > range) throw new IndexOutOfBoundsException("Unvalid index: "+target);
    }
  
    @Override
    public boolean isEmpty() {return this.size() == 0;}
    
}
