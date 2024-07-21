/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Text;

/**
 *
 * @author nguyenminh
 */
public class TestText {
    public static void main(String[] args) {
        String text = "abbbcabcabacbbacbac";
        String patt = "abb";
        System.out.println(PatternMatching.findBruteForce(text, patt));
    }
}
