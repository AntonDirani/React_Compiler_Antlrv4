package AST.Method;

import AST.Statement;

public class AccessMethodInLogStatement extends Statement
{
    public String id;
    public Statement statement;

    public AccessMethodInLogStatement( Statement statement)
    {

        this.statement = statement;
    }
    public AccessMethodInLogStatement(String id, Statement statement)
    {
        this.id = id;
        this.statement = statement;
    }
    public String toString()
    {
        return  String.format(" %s.{%s}",id, statement);
    }
}
