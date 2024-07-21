/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LinkedListDSC;
import java.nio.channels.IllegalSelectorException;
import java.util.NoSuchElementException;

/**
 *
 * @author nguyenminh
 */
public class SinglyLinkedList<E> extends AbstractLinkedList<E> implements AdvancedLinkedList<E>{
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    @Override
    public int size() {return this.size;}
   
    @Override
    public E getFirst() {
        if(isEmpty()) return null;
        return this.head.getData();
    }

    @Override
    public E getLast() {
        if(isEmpty()) return null;
        return this.tail.getData();
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if(isEmpty()) return null;
        this.checkIndex(index, this.size()-1);
        Node<E> cur = this.head;
        for(int step = 0;step < index;step++,cur=cur.getNextNode()){}
        return cur.getData();
    }

    @Override
    public void addFirst(E data) {
        if(this.isEmpty()){
            this.head = this.tail = new Node(data,null);
        }else{
           this.head = new Node(data,this.head);
        }
        this.size++;
    }

    @Override
    public void addLast(E data) {
        if(this.isEmpty()){
            this.head = this.tail = new Node(data,null);
        }else{
            Node<E> newNode = new Node(data,null);
            this.tail.setNextNode(newNode);
            this.tail = newNode;
        }
        this.size++;
    }

    @Override
    public void add(int index, E data) throws IndexOutOfBoundsException{
        if(this.isEmpty() || index == 0){
            this.addFirst(data);
        }else{
            this.checkIndex(index, this.size());
            if(index == this.size){
                this.addLast(data);
            }else{
                Node pre = this.head;
                for(int step = 1;step < index;step++,pre=pre.getNextNode()){}
                Node<E> newNode = new Node(data,pre.getNextNode());
                pre.setNextNode(newNode);
                this.size++;
            }
        }
    }

    @Override
    public E removeFirst() {
        if(isEmpty()) return null;
        Node<E> answer = this.head;
        this.head = this.head.getNextNode();
        this.size--;
        return answer.getData();
    }

    @Override
    public E removeLast() {
        if(isEmpty()) return null;
        Node<E> pre = this.head;
        for(;pre.getNextNode() != this.tail && pre != this.tail;pre=pre.getNextNode()){}
        Node<E> answer = this.tail;
        this.tail = pre;
        this.tail.setNextNode(null);
        this.size--;
        return answer.getData();           
    }

    @Override
    public E remove(int index) throws IllegalStateException {
        if(isEmpty()) return null;
        this.checkIndex(index, this.size()-1);
        if(index == 0)return this.removeFirst();
        if(index == this.size()-1)return this.removeLast();
        Node<E> pre = this.head;
        for(int step = 1; step < index;step++,pre=pre.getNextNode()){}
        Node<E> answer = pre.getNextNode();
        pre.setNextNode(answer.getNextNode());
        this.size--;
        return answer.getData();
    }

    @Override
    public void traversal() {
        int curIndex = 0;
        for(Node<E> cur = this.head; cur != null;cur=cur.getNextNode(),curIndex++){
            System.out.println("Node "+curIndex+": data="+cur.getData()+", next="+(cur.getNextNode()==null? "null": String.valueOf(curIndex+1)));
        }
    }

    @Override
    public void clear() {
        this.head = this.tail = null;
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
        Node<E> firstNode = this.head;
        Node<E> secondNode = firstNode.getNextNode();
        firstNode.setNextNode(null);
        while(secondNode!=null){
            Node<E> tmpNode = secondNode.getNextNode();
            secondNode.setNextNode(firstNode);
            firstNode = secondNode;
            secondNode = tmpNode;
        }
        this.head = firstNode;
    }

    @Override
    public boolean contain(E data) {
        if(this.isEmpty()) return false;
        Node<E> cur = this.head;
        for(;cur != null && !cur.getData().equals(data);cur=cur.getNextNode()){}
        return !(cur == null);
    }

    @Override
    public E removeData(E data) throws IllegalStateException {
        int curIndex = 0;
        for(Node<E> cur = this.head;cur!= null && !cur.getData().equals(data); cur=cur.getNextNode(),curIndex++){}
        if(curIndex >= this.size) throw new NoSuchElementException("Data is not in list");
        return this.remove(curIndex);
    }
    
    
}
    
   
    
    
