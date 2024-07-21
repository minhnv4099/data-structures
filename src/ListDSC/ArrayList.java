/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListDSC;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author nguyenminh
 * Implementation on List using array to store data
 * 
 */
public class ArrayList<E> implements List<E>{
   
    private static final int MAX = 100;
    
    private E[] data;
    private int size;

    public ArrayList() {this(MAX);}
    
    public ArrayList(int capacity){
        this.data = (E[]) new Object[capacity]; // safe casting
    }
    
    /**
     * @param index
     * @param range
     * @throw error if out range, otherwise skip
     */
    private void checkIndex(int index, int range){
        if(index < 0 || index > range)
            throw new IndexOutOfBoundsException("Invalid index: "+index);
    }
    
    /**
     * that method is called when add and list is full
     * when size = capacity
     * resize internals 2 times current capacity 
     * Amortized performance
     */
    private void resize(int newCap){
        E[] tmp = (E[]) new Object[newCap];
        for(int i = 0; i < this.size;i++){
            tmp[i] = this.data[i];
        }
        this.data = tmp;
    }

    @Override
    @Complexity
    public int size() {return this.size;}

    @Override
    @Complexity
    public boolean isEmpty() {return this.size() == 0;}

    @Override
    @Complexity
    public E get(int index) throws IndexOutOfBoundsException {
        if(this.isEmpty()) throw new IllegalStateException("The list is empty");
        this.checkIndex(index, this.size-1);
        return this.data[index];
    }

    @Override
    @Complexity
    public E set(int index, E ele) throws IndexOutOfBoundsException {
        if(this.isEmpty()) throw new IllegalStateException("The list is empty");
        this.checkIndex(index, this.size-1);
        E old = this.data[index];
        this.data[index] = ele;
        return old;
    }

    @Override
    @Complexity(runningTime = "O(n)")
    public void add(int index, E ele) throws IndexOutOfBoundsException {
        if(this.size() == this.data.length) this.resize(this.size*2);
        this.checkIndex(index,this.size());
        for(int i = this.size(); i > index;i--){
            this.data[i] = this.data[i-1];
        }
        this.data[index] = ele;
        this.size++;
    }
    
    public void add(E ele){
        this.add(this.size,ele);
    }

    @Override
    @Complexity(runningTime = "O(n)")
    public E remove(int index) throws IndexOutOfBoundsException {
        if(this.isEmpty()) throw new IllegalStateException("The list if empty");
        this.checkIndex(index, this.size()-1);
        E answer = this.data[index];
        for(int i = index; i < size-1;i++){
            this.data[i] = this.data[i+1];
        }
        this.data[this.size() -1] = null;
        this.size--;
        return answer;
    }   
    
    private class ArrayIterator implements Iterator<E>{
        private int curIndex = 0;   // Index of  next element
        boolean removal = false;
        
        @Override
        public boolean hasNext() {return this.curIndex < ArrayList.this.size;}
        @Override
        public E next() {
            if(!this.hasNext()) throw new NoSuchElementException("Stop iteration");
            this.removal = true;
            return data[this.curIndex++];
        }

        @Override
        public void remove() {
            if(!this.removal) throw new NoSuchElementException("No element is caledd");
            // must call next method before remove
            ArrayList.this.remove(--curIndex);
            // shift rest of list to left 1 unit;
            this.removal = false;
        }   
    }
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }
}
