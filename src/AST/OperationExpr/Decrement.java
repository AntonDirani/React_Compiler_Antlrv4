package AST.OperationExpr;

import AST.Expr.Expr;

public class Decrement extends Expr {
    Expr left;


    public Decrement(Expr left) {
        this.left = left;

    }

    @Override
    public String toString() {
        return  left.toString() +"--";
    }

}
