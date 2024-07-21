/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algorithm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Calendar;

/**
 *
 * @author nguyenminh
 */
public class ProcessTime {
    public static Number computTime(Method func,Object... para){
        long startTime = Calendar.getInstance().getTimeInMillis();
        try{
            func.invoke(null,para);
        }catch(Exception e){
        }
        long elapseTime = Calendar.getInstance().getTimeInMillis() - startTime;
        return elapseTime/1000.0;
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ComputeFibonacci{
    String status() default "BAD";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface SortingArray{
    String status() default "BAD";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CountingPrime{
    String status() default "BAD";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface SearchingArray{
    String status() default "BAD";
}