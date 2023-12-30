package AST.ComparisonExpr;

import AST.Expr.Expr;

public class GreaterThan extends Expr{

    Expr left;
    Expr right;

    public GreaterThan(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return  left.toString() +" > "+right.toString();
    }
}
