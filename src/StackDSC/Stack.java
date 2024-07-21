/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StackDSC;

import java.util.EmptyStackException;

/**
 *
 * @author nguyenminh
 */
public interface Stack<E>{
    
    /**
     * Return number of elements in the stack
     * @return number of elements
     */
    int size();
    
    /**
     * Check whether the stack is empty
     * @return true if empty, false otherwise
     */
    boolean isEmpty();
    
    /**
     * Check whether the stack is full
     * @return true if full, false otherwise
     */
    boolean isFull();
    
    /**
     * Add a element on the top in stack
     */
    void push(E data);
    
    /**
     * Return element on the top
     * @return element on the top without removing
     * @throws EmptyStackException if empty
     */
    
    E top() throws EmptyStackException;
    
    /**
     * Return element on the top
     * @return element on the top and remove it
     * @throws EmptyStackException if empty
     */
    E pop() throws EmptyStackException;
}
