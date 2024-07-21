package LinkedListDSC;
import LinkedListDSC.SinglyLinkedList;
import java.util.NoSuchElementException;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nguyenminh
 */
public class DoublyLinkedList<E> extends AbstractLinkedList<E> implements LinkedList<E>,AdvancedLinkedList<E>{
    public static class Node<E>{
        private Node<E> nextNode;
        private Node<E> preNode;
        private E data;

        public Node(E data,Node<E> preNode, Node<E> nextNode) {
            this.nextNode = nextNode;
            this.preNode = preNode;
            this.data = data;
        }

        public Node<E> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }

        public Node<E> getPreNode() {
            return preNode;
        }

        public void setPreNode(Node<E> preNode) {
            this.preNode = preNode;
        }

        public E getData() {
            return data;
        }
    }
    // Sentinels
    private Node<E> header;
    private Node<E> trailer;
    private int size;
    
    public DoublyLinkedList(){
        this.header = new Node<>(null,null,null);
        this.trailer = new Node<>(null,this.header,null);
        this.header.setNextNode(this.trailer);
        this.size = 0;
    }
  
    @Override
    public int size() {return this.size;}

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public E getFirst() {
        if(isEmpty()) return null;
        return this.header.getNextNode().getData();
    }

    @Override
    public E getLast() {
        if(isEmpty()) return null;
        return this.trailer.getPreNode().getData();
    }

    @Override
    public E get(int index)throws IndexOutOfBoundsException {
        if(isEmpty()) return null;
        this.checkIndex(index, this.size()-1);
        Node<E> cur = this.header.getNextNode();
        for(int step = 0; step < index;cur=cur.getNextNode(),step++);
        return cur.getData();
    }

    @Override
    public void addFirst(E data) {
        this.addBetween(data, this.header, this.header.getNextNode());
    }

    @Override
    public void addLast(E data) {
        this.addBetween(data, this.trailer.getPreNode(), this.trailer);
    }

    @Override
    public void add(int index, E data){
        this.checkIndex(index, this.size());
        Node<E> cur = this.header;
        for(int step = 0;step < index;cur=cur.getNextNode(),step++){}
        this.addBetween(data, cur, cur.getNextNode());
    }

    private void addBetween(E data,Node<E> pre,Node<E> next){
        Node<E> newNode = new Node(data,pre,next);
        pre.setNextNode(newNode);
        next.setPreNode(newNode);
        this.size++;
    }
    
    @Override
    public E removeFirst(){
        if(isEmpty()) return null;
        return this.removeNode(this.header.getNextNode());
    }

    @Override
    public E removeLast(){
        if(isEmpty()) return null;
        return this.removeNode(this.trailer.getPreNode());
    }

    @Override
    public E remove(int index) throws IllegalStateException {
        if(isEmpty()) return null;
        this.checkIndex(index, this.size()-1);
        Node<E> cur = this.header.getNextNode();
        for(int step = 0;step < index;cur=cur.getNextNode(),step++){}
        return this.removeNode(cur);
    }
    
    private E removeNode(Node<E> node){
        Node<E> pre = node.getPreNode();
        Node<E> next = node.getNextNode();
        pre.setNextNode(next);
        next.setPreNode(pre);
        this.size--;
        return node.getData();
    }

    @Override
    public void traversal() {
        int curIndex = 0;
        for(Node<E> cur = this.header.getNextNode();cur != this.trailer;cur=cur.getNextNode(),curIndex++){
            System.out.println("Node "+curIndex+": previous="+(cur.getPreNode()==this.header? "header":String.valueOf(curIndex-1))+", data="+cur.getData()+", next="+
                    (cur.getNextNode()==this.trailer? "trailer":String.valueOf(curIndex+1)));
        }
    }

    @Override
    public void clear() {
        this.header.setNextNode(this.trailer);
        this.trailer.setPreNode(this.header);
        this.size = 0;
    }

    @Override
    public void rotate(int unit) throws IllegalStateException {
        int tmpUnit = Math.abs(unit) % this.size();
        if(unit >= 0){
            for(int step = 1;step <= tmpUnit;step++){
                this.addLast(this.removeFirst());
            }
        }else{
            for(int step = 1;step <= tmpUnit;step++){
                this.addFirst(this.removeLast());
            }
        }
    }
    
    @Override
    public void revserse() throws IllegalStateException {
        throw new UnsupportedOperationException();
    }    

    @Override
    public boolean contain(E data) {
        if(this.isEmpty()) return false;
        Node cur = this.header.getNextNode();
        for(;cur != this.trailer && !cur.getData().equals(data);cur=cur.getNextNode()){}
        return !(cur == this.trailer);
    }

    @Override
    public E removeData(E data) throws IllegalStateException {
        int curIndex = 0;
        for(Node<E> cur = this.header.getNextNode();cur!=this.trailer && !cur.getData().equals(data); cur= cur.getNextNode(),curIndex++){}
        if(curIndex == this.size) throw new NoSuchElementException("Data is not in list");
        return this.remove(curIndex);   
    }
}
