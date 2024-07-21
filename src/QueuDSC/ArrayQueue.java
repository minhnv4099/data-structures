/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QueuDSC;
import QueuDSC.Queue;
/**
 *
 * @author nguyenminh
 */
public class ArrayQueue<E> implements Queue<E>{
    private static final int MAX = 100;
    
    private E[] dataArray;
    private int capacity;
    private int size = 0;
    private int front = 0;

    public ArrayQueue() {
        this(MAX);
    }

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.dataArray = (E[]) new Object[this.capacity];   // safe cast
    }

    @Override
    public int size() {return this.size;}
    @Override
    public boolean isEmpty(){return this.size == 0;}
    public boolean isFull(){return this.size == this.capacity;}

    @Override
    public void enqueu(E data) {
        if(this.isFull()) throw new IllegalStateException("Queue is full");
        int rear = (front + this.size) % this.capacity;
        this.dataArray[rear] = data;
        this.size++;
    }

    @Override
    public E first() {
        if(this.isEmpty()) throw new IllegalStateException("Queue is empty");
        return this.dataArray[this.front];
    }

    @Override
    public E dequeu() {
        if(this.isEmpty()) throw new IllegalStateException("Queue is empty");
        E result = this.dataArray[front];
        this.dataArray[front] = null;
        front = (front + 1) % this.capacity;
        this.size--;
        return result;
    }
}
