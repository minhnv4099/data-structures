/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algorithm;
import QueuDSC.LinkedQueue;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import QueuDSC.Queue;
import MapDSC.Map;
import MapDSC.UnsortedTableMap;

/**
 *
 * @author nguyenminh
 */
public class SortArray {
    @SortingArray(status = "BAD")
    public static void selectSort(int[] arr){
        for(int i = 0; i < arr.length-1;i++){
            int smallest = i;
            for(int j = i+1;j < arr.length;j++){
                if(arr[smallest] > arr[j])
                    smallest = j;
            }
            int tmp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = tmp;
        }
    }
    
    @SortingArray(status = "BAD")
    public static void bubbleSort(int[] arr){
        for(int i = arr.length-1; i > 1;i--){
            for(int j = 0;j < i;j++){
                if(arr[i] > arr[j]){
                    if(arr[j] > arr[j+1]){
                        int tmp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = tmp;
                    }
                }
            }
        }
    }
    
    @SortingArray(status = "Bad")
    public static void insertSort(int[] arr){
        for(int i = 1; i < arr.length ;i++){
            int tmp = arr[i];
            int j;
            for(j = i-1;j > -1;j--){
                if(arr[j] > tmp){
                    arr[j+1] = arr[j];
                }else{
                    break;
                }
            }
            arr[j+1] = tmp;
        }
    }
    
    public static <K> void mergeSort(K[] s){
        mergeSort(s, (o1, o2) -> {
            return ((Comparable)o1).compareTo((Comparable)(o2));
        });
    }
    @SortingArray(status = "GOOD")
    public static <K> void mergeSort(K[] s, Comparator<K> comp){
        int n = s.length;
        // no need sorting
        if(n < 2) return;
        // devide
        K[] s1 = Arrays.copyOfRange(s, 0, n/2);
        K[] s2 = Arrays.copyOfRange(s, n/2, n);
        // sort each subsequence
        mergeSort(s1, comp);
        mergeSort(s2, comp);
        // merge 2 subsequences
        merge(s1, s2, s, comp);
    }
    
    public static <K> void merge(K[] s1,K[] s2, K[] s, Comparator<K> comp){
        int i = 0, j = 0;
        while(i+j < s.length){
            if(j == s2.length || (i < s1.length && comp.compare(s1[i], s2[j]) < 0))
                s[i+j] = s1[i++];
            else
                s[i+j] = s2[j++];
        }
    }
    public static <K> void mergeSort(Queue<K> q){
        mergeSort(q, (o1, o2) -> {
            return ((Comparable)o1).compareTo((Comparable)(o2));
        });
    }
    @SortingArray(status = "GOOD")
    public static <K> void mergeSort(Queue<K> q, Comparator<K> comp){
        int n = q.size();
        if(n < 2) return;
        Queue<K> q1 = new LinkedQueue<>();
        Queue<K> q2 = new LinkedQueue<>();
        while(q1.size() < n/2)
            q1.enqueu(q.dequeu());
        while(!q.isEmpty())
            q2.enqueu(q.dequeu());
        mergeSort(q1, comp);
        mergeSort(q2, comp);
        merge(q1, q2, q, comp);
    }
    public static <K> void merge(Queue<K> q1, Queue<K> q2, Queue<K> q, Comparator<K> comp){
        while(!(q1.isEmpty() || q2.isEmpty())){
            int c = comp.compare(q1.first(), q2.first());
            if(c < 0)
                q.enqueu(q1.dequeu());
            else
                q.enqueu(q2.dequeu());
        }
        while(!q1.isEmpty())
            q.enqueu(q1.dequeu());
        while(!q2.isEmpty())
            q.enqueu(q2.dequeu());
    }
    
    
    public static <K> void quickSort(Queue<K> q){
        quickSort(q, (o1,o2) -> ((Comparable)o1).compareTo((Comparable)o2));
    }
    
    @SortingArray(status = "Good")
    public static <K> void quickSort(Queue<K> q, Comparator<K> comp){
        if(q.size() < 2) return;
        Queue<K> L = new LinkedQueue<>();
        Queue<K> E = new LinkedQueue<>();
        Queue<K> G = new LinkedQueue<>();
        K pivot = q.first();
        while(!q.isEmpty()){
            K e = q.dequeu();
            int c = comp.compare(e, pivot);
            if(c < 0)
                L.enqueu(e);
            else if(c == 0)
                E.enqueu(e);
            else
                G.enqueu(e);
        }
        quickSort(L, comp);
        quickSort(G, comp);
        while(!L.isEmpty())
            q.enqueu(L.dequeu());
        while(!E.isEmpty())
            q.enqueu(E.dequeu());
        while(!G.isEmpty())
            q.enqueu(G.dequeu());
    }
}
