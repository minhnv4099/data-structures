/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TreeDSC;
import java.util.Iterator;
import ListDSC.Position;

/**
 *
 * @author nguyenminh
 * Interface support basic operations on tree
 * implement Iterable
 * can see tree like iterable to traverse
 */
public interface Tree<E> extends Iterable<E>{
    
    /**
     * @return position of root in tree, null if empty
     */
    Position<E> root();
    
    /**
     * @return parent position of positions p, null if root 
     * @throws IllegalArgumentException if p invalid
     */
    Position<E> parent(Position p) throws IllegalArgumentException;
    
    /**
     * @return iterable of collection children of position p
     * @throws IllegalArgumentException if p invalid
     */
    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;
    
    /**
     * @return number of children of position p
     * @throws IllegalArgumentException if p invalid
     */
    int countChildren(Position<E> p) throws IllegalArgumentException;
    
    /**
     * check whether position p is external
     * @return true / false
     * @throws IllegalArgumentException if p invalid
     */
    boolean isExternal(Position<E> p) throws IllegalArgumentException;
        
    /**
     * check whether position p is internal
     * @return true / false
     * @throws IllegalArgumentException if p invalid
     */
    boolean isInternal(Position<E> p) throws IllegalArgumentException;
    
    /**
     * check whether position p is root
     * @return true / false
     * @throws IllegalArgumentException if p invalid
     */
    boolean isRoot(Position<E> p) throws IllegalArgumentException;
    
    /**
     * number of element(nodes) in tree
     * 
     */
    int size();
    
    /**
     * check whether tree contains any element
     * @return true if no any element
     */
    boolean isEmpty();
    
    /**
     * @return iterator of all positions of tree
     */
    Iterable<Position<E>> positions();
    
}
