package AST.Expr;

import java.util.ArrayList;

public class FloatExpr extends  Expr
{
    ArrayList<Float> floats ;

    public FloatExpr()
    {
        this.floats = new ArrayList<>();
    }
    public void addChild(Float element)
    {
        this.floats.add(element);
    }

    @Override
    public String toString() {
        return String.join(", ", floats.stream().map(Object::toString).toArray(String[]::new));

    }
}

