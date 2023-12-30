package AST.OperationExpr;

import AST.Expr.Expr;

public class Min extends Expr
{
    Expr left;
    Expr right;

    public Min(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return  left.toString() +" - "+right.toString();
    }

}
