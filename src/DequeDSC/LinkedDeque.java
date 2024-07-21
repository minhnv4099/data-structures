/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DequeDSC;
import LinkedListDSC.DoublyLinkedList;

/**
 *
 * @author nguyenminh
 */
public class LinkedDeque<E> implements Deque<E> {
    private DoublyLinkedList<E> data;

    public LinkedDeque() {
        this.data = new DoublyLinkedList();
    }

    @Override
    public int size() {return this.data.size();}

    @Override
    public boolean isEmpty() {return this.size() == 0;}
    
    @Override
    public E fisrt() {
        return this.data.getFirst();
    }

    @Override
    public E last() {
        return this.data.getLast();
    }
    
    @Override
    public void addFirst(E ele) {this.data.addFirst(ele);}

    @Override
    public void addLast(E ele) {this.data.addLast(ele);}
    
    @Override
    public E removeFirst() {
        return this.data.removeFirst();
    }

    @Override
    public E removeLast() {
        return this.data.removeLast();
    }
}
