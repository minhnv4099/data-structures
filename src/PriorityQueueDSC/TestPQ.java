/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PriorityQueueDSC;

import MapDSC.Entry;

/**
 *
 * @author nguyenminh
 */
public class TestPQ {
    public static void main(String[] args) {
        HeapAdaptablePriorityQueue<Integer, Object> queue = new HeapAdaptablePriorityQueue<>();
        Entry<Integer,Object> e1 = queue.insert(10, "10");
        Entry<Integer,Object> e2 = queue.insert(1, "one");
        Entry<Integer,Object> e3 = queue.insert(12, "12");
        Entry<Integer,Object> e4 = queue.insert(6, "six");
        Entry<Integer,Object> e5 = queue.insert(2, "two");
        
        queue.replaceKey(e5, 0);
        
        System.out.println(queue.removeMin().getValue());
        System.out.println(queue.removeMin().getValue());
        System.out.println(queue.removeMin().getValue());
        System.out.println(queue.removeMin().getValue());
        System.out.println(queue.removeMin().getValue());
        
        
        
    }
}
