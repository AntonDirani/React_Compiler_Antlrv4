package AST.ComparisonExpr;

import AST.Expr.Expr;

public class LessThanOrEqual extends Expr {
    Expr left;
    Expr right;

    public LessThanOrEqual(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return  left.toString() +" <= "+right.toString();
    }
}
