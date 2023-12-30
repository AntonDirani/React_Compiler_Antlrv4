package AST.ComparisonExpr;

import AST.Expr.Expr;

public class GreaterThanOrEqual extends Expr {
    Expr left;
    Expr right;

    public GreaterThanOrEqual(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return  left.toString() +" >= "+right.toString();
    }
}
