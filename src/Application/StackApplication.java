/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application;
import StackDSC.LinkedStack;
import java.util.EmptyStackException;
import java.lang.ArithmeticException;

/**
 *
 * @author nguyenminh
 */
public class StackApplication {
    public static void main(String[] args) {
        String expression = "43^~";
        System.out.println(evaluateSuffix("10+"));
        System.out.println(evaluatePrefix("+*-~5~0~78"));
        String a = "125439287348950984723128903498576342731289049284";
        String b = "1241213245345342312456453214564543231245";
        System.out.println(addLargeNumber(a, b));
    }
    
    /**
     * 
     * @param expression
     * check the expression whether true or false
     * @return true if valid, otherwise false
     */
    public static boolean matchExpression(String expression){
        final String openChars = "{[(";
        final String closeChars = "}])";
        LinkedStack<Character> bufferO = new LinkedStack<>();
        for(char c: expression.toCharArray()){
            // begin the expression must not close char
            if(openChars.indexOf(c) != -1){
                bufferO.push(c);
            }else{
                if(closeChars.indexOf(c) != -1){
                    if(bufferO.isEmpty()) return false; // because the expression begin with close char
                    
                    // check first close char and the last open char(before the close char) whether a couple
                    if(openChars.indexOf(bufferO.pop()) != closeChars.indexOf(c))return false;
                }
            }
        }
        return bufferO.isEmpty();
    }
    
    /**
     * 
     * @param num
     * @param base 
     * 
     */
    public static String convertBase(int num,int base){
        LinkedStack<Integer> tmp = new LinkedStack<>();
        StringBuilder result = num > 0? new StringBuilder() : new StringBuilder("-");
        num = Math.abs(num);
        while(num > 0){
            tmp.push(num%base);
            num /= base;
        }
        while(true){
            try{
                result.append(Character.forDigit(tmp.pop(), base));
            }catch(EmptyStackException e){return result.toString();}
        }            
    }
    
    public static double evaluateSuffix(String exp){
        LinkedStack<Double> operands = new LinkedStack<>();
        String num = "0123456789";
        for(char c: exp.toCharArray()){
            if(num.indexOf(c) != -1){
                operands.push(Double.valueOf(String.valueOf(c)));
            }else{
                if(c == '~') {operands.push(-operands.pop());continue;}
                double op1 = operands.pop();
                double op2 = operands.pop();
                if(c == '*') operands.push(op2*op1);
                else if(c == '+') operands.push(op2+op1);
                else if(c == '-') operands.push(op2-op1);
                else if(c == '%') operands.push(op2%op1);
                else if(c == '^') operands.push(Math.pow(op2, op1));
                else if(c == '/'){
                    if(op1==0.0) throw new ArithmeticException("Divide by zero, result in infinity");
                    operands.push(op2/op1);
                }
                else throw new EmptyStackException();
            }
        }        
        if(operands.size() == 1) return operands.pop();
        throw new IllegalStateException(String.format("Unvalid expression: %s",exp));
    }
    
    public static double evaluatePrefix(String exp){
        exp = new StringBuilder(exp).reverse().toString();
        LinkedStack<Double> operands = new LinkedStack<>();
        String num = "0123456789";
        for(char c: exp.toCharArray()){
            if(num.indexOf(c) != -1){
                operands.push(Double.valueOf(String.valueOf(c)));
            }else{
                if(c == '~') {operands.push(-operands.pop());continue;}
                double op2 = operands.pop();
                double op1 = operands.pop();
                if(c == '*') operands.push(op2*op1);
                else if(c == '+') operands.push(op2+op1);
                else if(c == '-') operands.push(op2-op1);
                else if(c == '%') operands.push(op2%op1);
                else if(c == '^') operands.push(Math.pow(op2, op1));
                else if(c == '/') {
                    if(op1==0.0) throw new ArithmeticException("Divide by zero, result in infinity");
                    operands.push(op2/op1);
                }
                else throw new EmptyStackException();
            }
        }
        if(operands.size() == 1) return operands.pop();
        throw new IllegalStateException(String.format("Unvalid expression: %s",exp));
    }
    
    
    public static String addLargeNumber(String num1,String num2){
        String num = "0123456789";
        StringBuffer result = new StringBuffer();
        LinkedStack<Double> tmp = new LinkedStack<>();
        LinkedStack<Double> numStack1 = new LinkedStack<>();
        LinkedStack<Double> numStack2 = new LinkedStack<>();
        double seed = 0.0;
        for(char c: num1.toCharArray()){
            if(num.indexOf(c) != -1){
                numStack1.push(Double.parseDouble(String.valueOf(c)));
            }else{
                throw new IllegalArgumentException("number 1 "+num1+ " unvalid");
            }
        }
        for(char c: num2.toCharArray()){
            if(num.indexOf(c) != -1){
                numStack2.push(Double.parseDouble(String.valueOf(c)));
            }else{
                throw new IllegalArgumentException("number 2 "+num2+ " unvalid");
            }
        }
        
        while(true){
            try{
                tmp.push((numStack1.pop()+numStack2.pop())*Math.pow(10.0, seed++));
            }catch(EmptyStackException e){
                if(numStack1.isEmpty()){
                    while(!numStack2.isEmpty()){
                        tmp.push(numStack2.pop()*Math.pow(10.0, seed++));
                    }
                    break;
                }
            }
        }
       
        return "";
    }
}
