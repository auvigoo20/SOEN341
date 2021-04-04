package SourceFiles;

import InterfaceFiles.*;

public class Operand implements IOperand{
    
    private int operandNumber;
    private String operandString;

    public Operand(String operand){
        if(isNumber(operand)){
            this.operandNumber = Integer.parseInt(operand);
            this.operandString = null;
        }
        else{
            this.operandString = operand;
            this.operandNumber = Integer.MAX_VALUE;
        }
    }

    public boolean isOperandNumber(){
        if(operandString != null){
            return false;
        }
        else{
            return true;
        }
    }

    public int getOperandNumber(){
        return this.operandNumber;
    }

    public String getOperandString(){
        return this.operandString;
    }

    private boolean isNumber(String operand){
        char[] chars = operand.toCharArray();
        for(char c : chars){
            if(!Character.isDigit(c) && c != '-'){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        Operand o1 = new Operand("-4");
        System.out.println(o1.getOperandNumber());
        // System.out.println(Integer.parseInt("5"));
    }

}
