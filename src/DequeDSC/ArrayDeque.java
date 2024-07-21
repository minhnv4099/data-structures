         /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DequeDSC;

/**
 *
 * @author nguyenminh
 */
public class ArrayDeque<E> implements Deque<E> {
    private static final int MAX = 100;
    
    private E[] data;
    private int front = 1;
    private int size = 0;
    private int capacity;
   
    public ArrayDeque() {
        this(MAX);
    }

    public ArrayDeque(int capacity) {
        this.capacity = capacity;
        this.data = (E[]) new Object[this.capacity];
    }

    @Override
    public int size() {return this.size;}

    @Override
    public boolean isEmpty() {return this.size() == 0;}

    @Override
    public void addFirst(E ele) {
        this.front = (this.front - 1 + this.capacity) % this.capacity;
        this.data[this.front] = ele;
        this.size++;
    }

    @Override
    public void addLast(E ele) {
        int rear = (this.front + this.size) % this.capacity;
        this.data[rear] = ele;
        this.size++;
    }
    
    @Override
    public E fisrt() {
        if(this.isEmpty()) return null;
        return this.data[this.front];
    }

    @Override
    public E last() {
        if(this.isEmpty()) return null;
        return this.data[(this.front + this.size -1)%this.capacity];
    }

    @Override
    public E removeFirst() {
        if(this.isEmpty()) return null;
        E answer = this.data[this.front];
        // garbage collector
        this.data[front] = null;
        this.front = (this.front + 1) % this.capacity;
        this.size--;
        return answer;
    }

    @Override
    public E removeLast() {
        if(this.isEmpty()) return null;
        int rear = (this.front + this.size - 1)% this.capacity;
        E answer = this.data[rear];
        // garbage collector
        this.data[rear] = null;
        this.size--;
        return answer;
    }
}
