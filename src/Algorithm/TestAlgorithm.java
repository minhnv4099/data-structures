/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algorithm;
import Algorithm.SortArray;
import QueuDSC.LinkedQueue;
import java.lang.reflect.Method;
import java.util.Random;
import QueuDSC.Queue;

/**
 *
 * @author nguyenminh
 */
public class TestAlgorithm {
    public static void main(String[] args) throws Exception{
        
        int MAX = 1;
        int[] arr = new int[MAX];
        Integer[] arr2 = {2,6,1,7,-12,99,34,25,-1,-5,5,19,-24};
        int[] arr3 = {2,6,1,7,-12,99,34,25,-1,-5,5,19,24};
        System.out.println(SearchArray.linearSearch(arr2, -24));
        
    }
}
