package AST.OperationExpr;

import AST.Expr.Expr;

public class Mul extends Expr
{
    Expr left;
    Expr right;

    public Mul(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return  left.toString() +" * "+right.toString();
    }

}
