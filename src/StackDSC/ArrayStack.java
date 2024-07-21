/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StackDSC;

/**
 *
 * @author nguyenminh
 */
public class ArrayStack<E> implements Stack<E>{
    private static final int MAX = 100;
    private int capacity;
    private E[] dataArray;
    private int pointer = -1;
    

    public ArrayStack() {this(MAX);}

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.dataArray = (E[]) new Object[this.capacity];
    }
    
    @Override
    public int size(){return this.pointer+1;}

    @Override
    public boolean isEmpty() {return this.size() == 0;}
    
    @Override
    public boolean isFull(){return this.size() == this.capacity;}
    
    @Override
    public void push(E data) {
        if(this.isFull()) throw new IllegalStateException("Stack is full");
        this.dataArray[++pointer] = data;
    }

    @Override
    public E top() {
        if(this.isEmpty()) throw new IllegalStateException("Stack is empty");
        return this.dataArray[pointer];
    }

    @Override
    public E pop() {
        if(this.isEmpty()) throw new IllegalStateException("Stack is empty");
        E tmp = this.dataArray[pointer];
        this.dataArray[pointer--] = null;  // garbage collections
        return tmp;
    }
}
