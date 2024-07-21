/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StackDSC;


/**
 *
 * @author nguyenminh
 */
public class TestStack {
    public static void main(String[] argv){
        //MyStack myStack = new MyStack(10);
        /**
        MyQueu myQuee = new MyQueu(10);
        
        myQuee.push("one");
        myQuee.push(2);
        myQuee.push(3);
        myQuee.push("four");
        myQuee.push(5);
        myQuee.push("six");
        
        System.out.println(myQuee.pop());
        System.out.println(myQuee.pop());
        System.out.println(myQuee.pop());
      
        MyStack backUp = myQuee.getBackUp();
        System.out.println("Back up");
        
        System.out.println(backUp.pop());
        System.out.println(backUp.pop());
        
        System.out.println(backUp.pop());
        System.out.println(backUp.pop());
        
        **/
        
        ArrayStack myStack1 = new ArrayStack(5);
        LinkedStack myStack2 = new LinkedStack(10);
        /**
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        
        System.out.println(myStack.top());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.size());
        **/
        myStack2.push(1);
        myStack2.push(2);
        myStack2.push(3);
        myStack2.push(4);
        myStack2.push(5);
        
        System.out.println(myStack2.top());
        System.out.println(myStack2.pop());
        System.out.println(myStack2.pop());
        System.out.println(myStack2.pop());
        System.out.println(myStack2.pop());
        System.out.println(myStack2.pop());
        System.out.println(myStack2.pop());
        System.out.println(myStack2.size());
        
        
        
        
    }
}
