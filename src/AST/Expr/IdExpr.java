package AST.Expr;

import java.util.ArrayList;
import java.util.LinkedList;

public class IdExpr extends Expr
{
    ArrayList<String> ids ;

    public IdExpr()
    {
        this.ids = new ArrayList<>();
    }
    public void addChild(String element)
    {
        this.ids.add(element);
    }

    @Override
    public String toString() {
        return String.join(", ", ids.stream().map(Object::toString).toArray(String[]::new));
    }

}
