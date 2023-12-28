package AST.Function;

import AST.Statement;

public class CallFunction extends Function
{
    public Statement statement;

    public CallFunction(Statement st)
    {
        this.statement =st;

    }

    @Override
    public String toString()
    {
        return String.format("TypeOfCall: %s , %s", this.getClass().getSimpleName(), statement);
    }
}
