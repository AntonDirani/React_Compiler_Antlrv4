package AST.Literal;

import java.util.ArrayList;

public class IntegerLiteral extends Literal
{
    ArrayList<Integer> integers;

    public IntegerLiteral()
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

