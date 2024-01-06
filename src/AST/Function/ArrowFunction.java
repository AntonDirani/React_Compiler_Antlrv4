package AST.Function;

import AST.Statement;

public class ArrowFunction extends Function
{

    public String dataType;
    public String nameOfFunc;
    public Statement parameters;
    public Statement functionBlock;

    public ArrowFunction( String dataType, String nameOfFunc, Statement block) {

        this.dataType = dataType;
        this.nameOfFunc = nameOfFunc;
        this.functionBlock = block;
    }

    public ArrowFunction( String dataType, String nameOfFunc, Statement parameters, Statement block) {
        this.dataType = dataType;
        this.nameOfFunc = nameOfFunc;
        this.parameters = parameters;
        this.functionBlock = block;
    }

    @Override
    public String toString() {
        return String.format("TypeOfFunction: %s , dataType: %s , functionName: %s , parameters: %s  block{ %s } ", this.getClass().getSimpleName(),dataType, nameOfFunc,parameters, functionBlock);
    }
}

