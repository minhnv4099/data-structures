/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QueuDSC;

import LinkedListDSC.SinglyLinkedList;

/**
 *
 * @author nguyenminh
 */
public class LinkedQueue<E> implements Queue<E>{
    
    private SinglyLinkedList<E> data;

    public LinkedQueue(){  
        this.data = new SinglyLinkedList<>();
    }
    
    @Override
    public int size(){return this.data.size();}

    @Override
    public boolean isEmpty(){return this.size() == 0;}

    @Override
    public void enqueu(E data) {this.data.addLast(data);}

    @Override
    public E first() {
        if(this.isEmpty()) throw new IllegalStateException("Queue is empty");
        return this.data.getFirst();
    }

    @Override
    public E dequeu() {
        if(this.isEmpty()) throw new IllegalStateException("Queue is empty");
        return this.data.removeFirst();
    }
}
