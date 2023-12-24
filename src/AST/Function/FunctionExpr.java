package AST.Function;

import AST.Statement;

public class FunctionExpr extends AST.Function.Function
{
    public String nameOfFunc;
    public String dataType;
    public Statement bodyOfFunc;

    public FunctionExpr(String nameOfFunc , String dataType,Statement bodyOfFunc)
    {
        this.nameOfFunc = nameOfFunc;
        this.dataType = dataType;
        this.bodyOfFunc =bodyOfFunc;
    }

    @Override
    public String toString() {

        return String.format("TypeOfFunction: %s , dataType: %s , functionName: %s , functionBody: %s ", this.getClass().getSimpleName(), dataType, nameOfFunc,bodyOfFunc);

    }
}

