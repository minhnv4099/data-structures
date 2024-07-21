
    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QueuDSC;
import LinkedListDSC.CircularLinkedList;

/**
 *
 * @author nguyenminh
 */
public class CircularLinkedQueue<E> implements CircularQueue<E>{
    
    private CircularLinkedList<E> data;

    public CircularLinkedQueue() {
        this.data = new CircularLinkedList<>();
    }

    @Override
    public int size(){return this.data.size();}

    @Override
    public boolean isEmpty(){return this.size() == 0;}
    
    @Override
    public void rotate(){
        if(this.isEmpty()) throw new IllegalStateException("Queue is empty");
        this.data.rotate(1);
    }
    
    @Override
    public void enqueu(E data) {
        //if(this.isFull()) throw new IllegalStateException("Queue is full");
        this.data.addLast(data);
    }

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

