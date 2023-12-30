package AST.OperationExpr;

import AST.Expr.Expr;

public class Add extends Expr
{
    Expr left;
    Expr right;

    public Add(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return  left.toString() +" + "+right.toString();
    }

}
