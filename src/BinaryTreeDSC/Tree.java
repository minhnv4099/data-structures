/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTreeDSC;

import ListDSC.Position;
/**
 *
 * @author nguyenminh
 * basic operations on a general tree
 */
public interface Tree<E> extends Iterable<E>{
    
    /**
     * @return position of root in tree, null if empty
     */
    Position<E> root();
    
    /**
     * @param p position being considered
     * @return parent's position of{@code p}, null if {@code p} is root 
     * @throws IllegalArgumentException if invalid {@code p}
     */
    Position<E> parent(Position p) throws IllegalArgumentException;
    
    /**
     * @param p position being considered
     * @return iterable of collection children of position {@code p}
     * @throws IllegalArgumentException if {@code p} invalid
     */
    Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException;
    
    /**
     * @return number of children of position {@code p}
     * @throws IllegalArgumentException if position {@code p} invalid
     */
    int countChildren(Position<E> p) throws IllegalArgumentException;
    
    /**
     * check whether position p is external
     * @param p
     * @return true / false
     * @throws IllegalArgumentException if invalid {@code p}
     */
    boolean isExternal(Position<E> p) throws IllegalArgumentException;
        
    /**
     * check whether position p is internal
     * @return true / false
     * @throws IllegalArgumentException if invalid {@code p}
     */
    boolean isInternal(Position<E> p) throws IllegalArgumentException;
    
    /**
     * check whether  {@code p} is root
     * @return true / false
     * @throws IllegalArgumentException if invalid {@code p}
     */
    boolean isRoot(Position<E> p) throws IllegalArgumentException;
    
    /**
     * number of element(nodes) in tree
     * @return number of nodes on tree
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
    
    /**
     * 
     * @param p being considered
     * @return depth(level) of {@code p}
     * @throws IllegalArgumentException  if invalid {@code p}
     */
    int depth(Position<E> p) throws IllegalArgumentException;
    
    /**
     * 
     * @param p being considered
     * @return height of sub tree rooted by {@code p}
     * @throws IllegalArgumentException if invalid {@code p}
     */
    int height(Position<E> p) throws IllegalArgumentException;
    
}
