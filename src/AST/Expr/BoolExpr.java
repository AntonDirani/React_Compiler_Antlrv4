package AST.Expr;

public class BoolExpr extends Expr
{
    public String booleanVal;

    public BoolExpr(String booleanVal) {
        this.booleanVal = booleanVal;
    }

    @Override
    public String toString() {
        return  booleanVal.toString();
    }
}

