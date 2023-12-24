package AST.Function;

import AST.Statement;

public class FunctionDeclaration extends AST.Function.Function
{
    public  String funcKeyWord;
    public Statement block;
    public Statement parameters;

    public FunctionDeclaration( String funcKeyWord,Statement block)
    {
        this.funcKeyWord = funcKeyWord;
        this.block = block;

    } public FunctionDeclaration( String funcKeyWord,Statement parameters,Statement block)
{
    this.funcKeyWord = funcKeyWord;
    this.block = block;
    this.parameters = parameters;
}

    @Override
    public String toString() {
        return String.format("Statement: %s , funcKeyWord: %s , parameters: %s , block: %s", this.getClass().getSimpleName(),funcKeyWord,parameters,block);

    }
}

