/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedListDSC;
import java.util.EmptyStackException;
import java.util.Deque;
import java.util.NoSuchElementException;
/**
 *
 * @author nguyenminh
 */
public class CircularLinkedList<E> extends AbstractLinkedList<E> implements AdvancedLinkedList<E>{
    private Node<E> tail;
    private int size;

    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }
    
    @Override
    public int size() {return this.size;}  
    
    @Override
    public E getFirst() {
        if(isEmpty()) return null;
        return this.tail.getNextNode().getData();
    }

    @Override
    public E getLast(){
        if(isEmpty()) return null;
        return this.tail.getData();
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if(isEmpty()) return null;
        this.checkIndex(index, this.size()-1);
        Node<E> cur = this.tail.getNextNode();
        for(int step = 0; step < index; step++, cur=cur.getNextNode()){}
        return cur.getData();
    }

    @Override
    public void addFirst(E data) {
        if(this.isEmpty()){
            this.tail = new Node(data,null);
            this.tail.setNextNode(this.tail);
        }else{
            Node<E> newNode = new Node(data, this.tail.getNextNode());
            this.tail.setNextNode(newNode);
        }
        this.size++;
    }

    @Override
    public void addLast(E data) {
        this.addFirst(data);
        this.tail = this.tail.getNextNode();
    }

    @Override
    public void add(int index, E data) throws IndexOutOfBoundsException {
        if(this.isEmpty() || index == 0){
            this.addFirst(data);
        }else{
            if(index == this.size()){
                this.addLast(data);
            }else{
                this.checkIndex(index, this.size());
                Node<E> cur = this.tail.getNextNode();
                for(int step = 1;step < index;cur=cur.getNextNode(),step++){}////////
                Node<E> newNode = new Node(data,cur.getNextNode());///////
                cur.setNextNode(newNode);
                this.size++;
            }
        }
    }

    @Override
    public E removeFirst(){
        if(isEmpty()) return null;
        Node<E> answer = this.tail.getNextNode();
        this.tail.setNextNode(answer.getNextNode());
        this.size--;
        return answer.getData();
    }

    @Override
    public E removeLast(){
        if(isEmpty()) return null;
        Node<E> cur = this.tail.getNextNode();
        for(;cur.getNextNode() != this.tail;cur=cur.getNextNode()){}
        Node<E> answer = this.tail;
        cur.setNextNode(this.tail.getNextNode());
        this.tail = cur;
        this.size--;
        return answer.getData();
    }

    @Override
    public E remove(int index){
        if(isEmpty()) return null;
        this.checkIndex(index, this.size()-1);
        if(index == 0) return this.removeFirst();
        if(index == this.size()-1) return this.removeLast();
        Node<E> cur = this.tail.getNextNode();
        for(int step = 1;step < index; step++,cur=cur.getNextNode()){}
        Node<E> answer = cur.getNextNode();
        cur.setNextNode(answer.getNextNode());
        this.size--;
        return answer.getData();
    }

    @Override
    public void traversal() {
        int curIndex = 0;
        for(Node<E> cur = this.tail.getNextNode();curIndex < this.size();cur=cur.getNextNode(),curIndex++){
            System.out.println("Node "+curIndex+": data="+cur.getData()+", next="+(cur.getNextNode()==this.tail.getNextNode()? "0": String.valueOf(curIndex+1)));
        }
    }

    @Override
    public void clear() {
        this.tail = null;
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
        Node<E> firstNode = this.tail.getNextNode();
        Node<E> secondNode = firstNode.getNextNode();
        while(firstNode!=this.tail){
            Node<E> tmpNode = secondNode.getNextNode();
            secondNode.setNextNode(firstNode);
            firstNode = secondNode;
            secondNode = tmpNode;
        }
        this.tail = secondNode;
        this.tail.setNextNode(firstNode);
    }

    @Override
    public boolean contain(E data) {
        if(this.isEmpty()) return false;
        Node<E> cur = this.tail.getNextNode();
        for(;cur != this.tail && !cur.getData().equals(data);cur=cur.getNextNode()){}
        return cur.getData().equals(data);
    }

    @Override
    public E removeData(E data) throws IllegalStateException {
        int curIndex = 0;
        for(Node<E> cur = this.tail.getNextNode();curIndex <= this.size && !cur.getData().equals(data); cur=cur.getNextNode(),curIndex++){}
        if(curIndex >= this.size) throw new NoSuchElementException("Data is not in list");
        return this.remove(curIndex);
    }
}