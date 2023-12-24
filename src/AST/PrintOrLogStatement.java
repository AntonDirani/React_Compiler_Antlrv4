package AST;

public class PrintOrLogStatement extends Statement
{
    public Statement expr;
    public PrintOrLogStatement(Statement expr)
    {
        this.expr = expr;
    }

    @Override
    public String toString() {
        return "PrintOrLogStatement{" +
                "expr:" + expr +
                '}';
    }
}

