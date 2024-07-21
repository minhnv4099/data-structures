/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QueuDSC;

/**
 *
 * @author nguyenminh
 */
public interface CircularQueue<E> extends Queue<E>{
    
    /*
        rotate front of the queue
        left shift the front
    */
    void rotate();
    
}
