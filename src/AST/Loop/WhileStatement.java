package AST.Loop;

import AST.Expr.Expr;
import AST.Statement;

public class WhileStatement extends Statement
{


    Expr condition;
    Statement statement;
    Expr counter;

    public WhileStatement(Expr condition, Statement statement, Expr counter)
    {
        this.condition = condition;
        this.statement = statement;
        this.counter = counter;
    }

    @Override
    public String toString() {
        return String.format("%s: while(%s){\n %s \n %s;\n }\n", this.getClass().getSimpleName(),condition,statement,counter);

    }
}
