/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algorithm;

/**
 *
 * @author nguyenminh
 */
public class CountPrimes {
    
    @CountingPrime(status = "bad")
    public static int countPrimeBad(int proceed){
        int count = 0;
        for(int i = 2; i < proceed+1;i++){
            if(isPrime(i)){
                count++;
            }
        }
        return count;
    }
    
    public static boolean isPrime(int num){
        for(int i = 2; i < Math.floor(Math.sqrt(num))+1;i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
    
    @CountingPrime(status = "good")
    public static int countPrimeGood(int proceed){
        boolean[] checkList = new boolean[proceed];
        int count = 0;
        // coutn primes from 2 to proceed(inclusive)
        for(int i = 1; i < proceed;i++){
            if(checkList[i] == false){
                count++;
            }
            for(int j = i; j < proceed;j += (i+1)){
                checkList[j] = true;
            }
        }
        return count;
    }
        
}
/*        
0 1 2 3 4 5 6 7 8
f f f f f f f f f
1 2 3 4 5 6 7 8 9
*/