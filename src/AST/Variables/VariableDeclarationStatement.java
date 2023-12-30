package AST.Variables;

import AST.Statement;

import java.util.ArrayList;

public class VariableDeclarationStatement extends Statement
{

    ArrayList<String> values;
    public  String variableName;
    public  String type;
    public Statement init;

    public VariableDeclarationStatement(){
        this.values = new ArrayList<>();
    }
    public VariableDeclarationStatement(String type,String variableName,Statement init)
    {
        this.type = type;
        this.variableName =variableName;
        this.init =init;


    }

    public void addChild(String statement)
    {
        this.values.add(statement);
    }


    @Override
    public String toString() {
        return String.format("Statement: %s , dataType: %s , variableName: %s , variableValues: %s ", this.getClass().getSimpleName(), type, variableName,init);
    }
}

