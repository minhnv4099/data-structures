/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package QueuDSC;

/**
 *
 * @author nguyenminh
 */
public class TestQueu {
    public static void main(String[] args) {
        CircularQueue myQueu = new CircularLinkedQueue();
        
        //String[ ] a1 = {"Alice", "Bob", "Cindy", "Doug", "Ed", "Fred"};
        //String[ ] a2 = {"Gene", "Hope", "Irene", "Jack", "Kim", "Lance"}; 
        //String[ ] a3 = {"Mike", "Roberto"};
        
        //System.out.println("Winner is "+findWinner(buildCircle(a2), 8));
        
        
        
        
        
        myQueu.enqueu(1);
        myQueu.enqueu(2);
        myQueu.enqueu(3);
        myQueu.enqueu(4);
        myQueu.enqueu(5);
        myQueu.enqueu(6);
        myQueu.enqueu(7);
        myQueu.enqueu(8);
        
        myQueu.rotate();
        System.out.println(myQueu.dequeu());
        System.out.println(myQueu.dequeu());
        System.out.println(myQueu.dequeu());
        System.out.println(myQueu.dequeu());
        System.out.println(myQueu.dequeu());
        
        myQueu.enqueu(6);
        myQueu.enqueu(7);
        myQueu.enqueu(8);
        myQueu.enqueu(9);
        myQueu.enqueu(10);
        //myQueu.rotate();
        System.out.println(myQueu.dequeu());
        System.out.println(myQueu.dequeu());
        System.out.println(myQueu.dequeu());
        System.out.println(myQueu.dequeu());
        System.out.println(myQueu.dequeu());
        
        
        
        //System.out.println(myQueu.size());
        
    }
    
    public static <E> E findWinner(CircularLinkedQueue<E> circle,int k){
        while(circle.size() != 1){
            for(int i = 0; i < k;i++){
                circle.rotate();
            }
            System.out.println(circle.dequeu());
        }
        return circle.first();
    }
    
    public static <E> CircularLinkedQueue<E> buildCircle(E[] arr){
        CircularLinkedQueue tmp = new CircularLinkedQueue();
        for(E item : arr){
            tmp.enqueu(item);
        }
        return tmp;
    }
}
