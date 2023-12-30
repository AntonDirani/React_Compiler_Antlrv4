package AST.ComparisonExpr;

import AST.Expr.Expr;

public class LessThan extends Expr
{
    Expr left;
    Expr right;

    public LessThan(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return  left.toString() +" < "+right.toString();
    }
}
