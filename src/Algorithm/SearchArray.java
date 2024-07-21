/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algorithm;

import java.util.Comparator;

/**
 *
 * @author nguyenminh
 */
public class SearchArray {
    
    @SearchingArray
    public static <E> boolean linearSearch(E[] arr, E target){
       for(E ele: arr){
           if(ele.equals(target)){
               return true;
           }
       }
       return false;
    }
    
    @SearchingArray(status = "good")
    public static <E> boolean binarySearch(E[] arr,E target){
        return binarySearch(arr, target, (o1,o2) -> ((Comparable)o1).compareTo((Comparable)o2));
    }
    
    @SearchingArray(status = "good")
    public static <E> boolean binarySearch(E[] arr,E target,Comparator<E> comp,int...slice){
        //arr = SortArray.mergesort(arr);
        if(slice.length == 0){
                slice = new int[2];
                slice[0] = 0;
                slice[1] = arr.length - 1;
        }
        if(slice[0] > slice[1]){return false;}
        else{
            int mid = (slice[0]+slice[1])/2;
            //System.out.println(slice[0] +" "+mid+" "+slice[1]);
            int c = comp.compare(target, arr[mid]);
            if(c == 0){return true;}
            else if(c < 0) slice[1] = mid-1;
            else slice[0] = mid+1;
            return binarySearch(arr, target, comp,slice);    
        }
    }
}
