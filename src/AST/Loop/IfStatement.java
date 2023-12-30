package AST.Loop;

import AST.Expr.Expr;
import AST.Statement;

public class IfStatement extends Statement
{
    Expr condition;
    Statement statement;

    public IfStatement(Expr condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public String toString() {
        return String.format("%s: if(%s){ %s }\n", this.getClass().getSimpleName(),condition,statement);

    }
}
