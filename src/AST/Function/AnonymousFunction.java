package AST.Function;

import AST.Statement;

public class AnonymousFunction extends  Function
{
    public String nameOfFunc;
    public String dataType;
    public Statement bodyOfFunc;

    public AnonymousFunction(String dataType,String nameOfFunc ,Statement bodyOfFunc)
    {
        this.nameOfFunc = nameOfFunc;
        this.dataType = dataType;
        this.bodyOfFunc =bodyOfFunc;
    }

    @Override
    public String toString() {

        return String.format("TypeOfFunction: %s , dataType: %s , functionName: %s , block{ %s } ", this.getClass().getSimpleName(), dataType, nameOfFunc,bodyOfFunc);

    }
}

