/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListDSC;

/**
 *
 * @author nguyenminh
 */
@FunctionalInterface
public interface Position<E> {
    /**
     * Get element stored in that position
     * @return element stored at position
     */
    E getElement();
}
