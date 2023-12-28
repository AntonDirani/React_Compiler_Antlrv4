package AST.Function;

import AST.Statement;

public class CallMethod extends  Function
{
    public String nameOfObj;
    public Statement statement;

    public CallMethod(String nameOfObj)
    {
        this.nameOfObj = nameOfObj;
    }
    public CallMethod(String nameOfObj, Statement statement)
    {
        this.nameOfObj = nameOfObj;
        this.statement = statement;
    }
    @Override
    public String toString()
    {
        return String.format("TypeOfCall: %s ,objName: %s.{ %s}", this.getClass().getSimpleName(),nameOfObj, statement);
    }
}

