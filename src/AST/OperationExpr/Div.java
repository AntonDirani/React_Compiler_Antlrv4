package AST.OperationExpr;

import AST.Expr.Expr;

public class Div extends Expr
{
    Expr left;
    Expr right;

    public Div(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return  left.toString() +"/"+right.toString();
    }

}
