package AST.OperationExpr;

import AST.Expr.Expr;

public class Increment extends Expr
{
    Expr left;

    public Increment(Expr left) {
        this.left = left;
    }

    @Override
    public String toString() {
        return  left.toString() +"++";
    }

}
