/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListDSC;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import java.util.Objects;

/**
 *
 * @author nguyenminh
 */

class Person{
    
}

class Student extends  Person{
    
}

class Child extends  Student{
    
}
public class TestList {
    public static void main(String[] args) {
        
        LinkedPositionalList<Integer> list = new LinkedPositionalList<>();
        Position<Integer> one = list.addLast(1);
        Position<Integer> two = list.addLast(2);
        Position<Integer> three = list.addFirst(3);
        Position<Integer> four = list.addBefore(two,4);
        Iterator<Position<Integer>> iter = list.PosIterator();
        Iterable<Position<Integer>> iter1 = list.PosIterable();
        
        for(Position<Integer> p : iter1){
            System.out.println(p.getElement());
        }
        
        
        //aaa.forEach((item)-> System.out.println(item.getElement()));
        //bbb.forEach((item)-> System.out.println(item));
        
        /*
        for(Method method: PositionalListADT.class.getMethods()){
            System.out.println(method.getName());
            System.out.println(method.getParameterCount());
            System.out.println(method.getReturnType());
            System.out.println(method.getAnnotation(Complexity.class).runningTime());
            System.out.println();
        }
        */
    }
   
}
