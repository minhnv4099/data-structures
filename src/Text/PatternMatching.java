/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Text;
import MapDSC.ProbeHashMap;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nguyenminh
 */
public class PatternMatching {
    public static int findBruteForce(String text, String pattern){
        char[] textArr = text.toCharArray();
        char[] patArr = pattern.toCharArray();
        int lengthText = textArr.length;
        int lengthPat = patArr.length;               
        // i scan text
        for(int i = 0; i < lengthText - lengthPat + 1; i++){
            // k scan pattern
            int k = 0;
            // loop while match each correspond position
            while(k < lengthPat && textArr[i+k] == patArr[k]){
                k++;
            }
            // matching found
            if(k == lengthPat){
                // return start index 
                return i;
            }
        }
        // not match include pattern longer than text
        return -1;
    }
    
    public static int findBoyerMoore(String text, String pattern){
        char[] textArr = text.toCharArray();
        char[] patArr = pattern.toCharArray();
        int lengthText = textArr.length;
        int lengthPat = patArr.length;
        
        Map<Character,Integer> tmp = new HashMap<>();
        for(int i = 0; i < lengthText;i++){
            // not match first
            tmp.put(textArr[i], -1);
        }
        for(int i = 0; i < lengthPat;i++){
            // last index of char in pattern
            tmp.put(patArr[i], i);
        }
        
        // initally first index in text and pattern
        // scan pattern
        int k = lengthPat-1;
        // scan text
        int i = k;
        // because we scan from end to begin
        // -> i can last index of text
        while(i < lengthText){
            if(textArr[i] == patArr[k]){
                // match pattern
                if(k == 0) return i;
                // continue
                i--;
                k--;
            // mismatch happen
            }else{
                
                k = lengthPat-1;
            }   
        }
        // mismatch char is not in pattern
        return -1;
    }
}
