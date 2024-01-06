package AST.Expr;

import java.util.ArrayList;

public class IntExpr extends  Expr
{
    ArrayList<Integer> integers;

    public IntExpr()
    {
        this.integers = new ArrayList<>();
    }
    public void addChild(int element)
    {
        this.integers.add(element);
    }

    @Override
    public String toString() {
        return String.join(", ", integers.stream().map(Object::toString).toArray(String[]::new));

    }
}

