/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapDSC;

import java.util.Comparator;

/**
 *
 * @author nguyenminh
 */
public class DefaultComparator<E> implements Comparator<E> {
    public int compare(E o1, E o2) throws ClassCastException{
        return ((Comparable)o1).compareTo((Comparable)o2);
    }   
}
