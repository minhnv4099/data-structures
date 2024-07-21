/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algorithm;
import java.lang.Math;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author nguyenminh
 */
public class ComputeFibo {
    @ComputeFibonacci
    public static void main(String[] agrv) throws Exception{
        Integer n = 1;

        Method test1 = ComputeFibo.class.getDeclaredMethod("fibonacciBad",int.class);
        Method test2 = ComputeFibo.class.getDeclaredMethod("fibonacciGood",int.class);
        ComputeFibonacci anno = test1.getAnnotation(ComputeFibonacci.class);
        for(Method method: ComputeFibo.class.getDeclaredMethods()){
            if(method.getAnnotation(ComputeFibonacci.class).status().equalsIgnoreCase("good")){
                System.out.println(ProcessTime.computTime(method,10000));
            }
        }
        //System.out.println(anno);
        //System.out.println(ProcessTime.computTime(test1, n));
        //System.out.println(ProcessTime.computTime(test2, n));
    }
    @ComputeFibonacci
    public static int fibonacciBad(int n){
        if(n <= 1){
            return n;
        }else{
            return fibonacciBad(n-2) + fibonacciBad(n-1);
        }
    }
    
    @ComputeFibonacci(status = "GOOD")
    public static int[] fibonacciGood(int n){
        if(n <= 1){
            int[] result = {0,n};
            return result;
        }else{
            int[] tmp = fibonacciGood(n-1);
            int[] result = {tmp[1],tmp[0]+tmp[1]};
            return result;
        }
    }
}
    
    
