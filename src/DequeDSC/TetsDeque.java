/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DequeDSC;

/**
 *
 * @author nguyenminh
 */
public class TetsDeque {
    public static void main(String[] args) {
        LinkedDeque aa = new LinkedDeque();
        
        aa.addFirst(1);
        aa.addFirst(2);
        aa.addLast(3);
        aa.addFirst(4);
        aa.addLast(5);
        aa.addLast(6);
        aa.addFirst(7);
        
        System.out.println(aa.fisrt());
        System.out.println(aa.last());
        
        System.out.println(aa.removeFirst());
        System.out.println(aa.removeLast());
        System.out.println(aa.removeLast());
        System.out.println(aa.removeFirst());
        System.out.println(aa.removeLast());
        System.out.println(aa.removeLast());
        System.out.println(aa.removeFirst());
        
   
        
        
        
        
        
        
      
    }
}
