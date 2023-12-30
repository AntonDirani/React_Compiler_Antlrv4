package AST.LogicExpr;

import AST.Expr.Expr;

public class EqualEqual extends Expr
{
    Expr left;
    Expr right;

    public EqualEqual(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return  left.toString() +" == "+right.toString();
    }

}
