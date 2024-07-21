/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StackDSC;
import LinkedListDSC.SinglyLinkedList;
import java.util.EmptyStackException;
/**
 *
 * @author nguyenminh
 */
public class LinkedStack<E> implements Stack<E>{
    private static final int MAX = 100;
    
    private SinglyLinkedList<E> data = new SinglyLinkedList<>();
    private int capacity;

    public LinkedStack(int cap) {this.capacity = cap;}

    public LinkedStack() {this(MAX);}
    
    @Override
    public int size() {return this.data.size();}

    @Override
    public boolean isEmpty() {return this.data.isEmpty();}

    @Override
    public boolean isFull() {return this.data.size()== this.capacity;}

    @Override
    public void push(E data) {
        if(this.isFull()) throw new IllegalStateException("Stack is full");
        this.data.addFirst(data);
    }

    @Override
    public E top() {
        if(this.isEmpty()) throw new EmptyStackException();
        return this.data.getFirst();
    }
    
    @Override
    public E pop(){
        if(this.isEmpty()) throw new EmptyStackException();
        return this.data.removeFirst();
    }
}
