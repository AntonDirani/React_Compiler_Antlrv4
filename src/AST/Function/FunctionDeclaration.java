package AST.Function;

import AST.Statement;

public class FunctionDeclaration extends AST.Function.Function
{
    public  String funcKeyWord;
    public  String nameOfFunction;
    public Statement block;
    public Statement parameters;

    public FunctionDeclaration(String funcKeyWord, String nameOfFunction,Statement parameters, Statement block)
    {
        this.funcKeyWord = funcKeyWord;
        this.nameOfFunction = nameOfFunction;
        this.parameters = parameters;
        this.block = block;
    }

    @Override
    public String toString()
    {
        if(nameOfFunction != null && parameters != null)
        {
            return String.format("TypeOfFunction: %s , funcKeyWord: %s , functionName: %s(parameters: %s) { block{ %s }}", this.getClass().getSimpleName(), funcKeyWord,nameOfFunction, parameters, block);
        }
        else if(nameOfFunction != null)
        {

            return String.format("TypeOfFunction: %s , funcKeyWord: %s , functionName: %s(parameters: %s) { block{ %s }}", this.getClass().getSimpleName(), funcKeyWord,nameOfFunction, parameters, block);
        }
        else if(parameters != null)
        {
            return String.format("TypeOfFunction: %s , funcKeyWord: %s (parameters: %s) { block{ %s }}", this.getClass().getSimpleName(), funcKeyWord, parameters, block);
        }
        else
        {
            return String.format("TypeOfFunction: %s , funcKeyWord: %s(){ block{ %s }}", this.getClass().getSimpleName(),funcKeyWord,block);

        }
    }
}

