

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TreeDSC;
import ListDSC.Position;

/**
 *
 * @author nguyenminh
 */
public class TestTree {
    public static void main(String[] args) {
        BasicTree<String> tree = new BasicTree();
        
        // level 1
        Position<String> alex = tree.addRoot("Alex");
        
        // level2
        Position<String> bill = tree.addChild(alex,"Bill");
        Position<String> jane = tree.addChild(alex,"Jane");
        Position<String> mark = tree.addChild(alex,"Mark");
        
        // level3
        Position<String> john = tree.addChild(bill,"John");
        Position<String> mery = tree.addChild(bill,"Mery");
        Position<String> marry = tree.addChild(bill,"Marry");
        Position<String> haoski = tree.addChild(jane,"Haoski");
        Position<String> tokyo = tree.addChild(jane,"Tokyo");
        Position<String> peter = tree.addChild(mark,"Peter");
        Position<String> parker = tree.addChild(mark,"Paker");
        Position<String> martin = tree.addChild(mark,"Martin");
        
        // level 4
        Position<String> james = tree.addChild(mery,"James");
        Position<String> sam = tree.addChild(marry,"Sam");
        Position<String> roman = tree.addChild(mery,"Roman");
        
        // level 5
        Position<String> naomi = tree.addChild(sam,"Naomi");
        
        tree.children(bill).forEach((t) -> {
            //System.out.println(t.getElement());
        });
        
        tree.ancestor(martin).forEach((t) -> {
            //System.out.println(t.getElement());
        });
        
        tree.positions(3).forEach((ele) -> System.out.println(ele.getElement()));
        //tree.positions().forEach((ele) -> System.out.println(ele.getElement()));
        //System.out.println(tree.depth(naomi));
        //System.out.println(tree.height(alex));
        //System.out.println(tree.isExternal(mery));
        //System.out.println(tree.isInternal(bill));
        
    }
}
