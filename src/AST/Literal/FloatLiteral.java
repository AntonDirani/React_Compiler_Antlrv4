package AST.Literal;

import java.util.ArrayList;

public class FloatLiteral extends Literal
{
    ArrayList<Float> floats ;

    public FloatLiteral()
    {
        this.floats = new ArrayList<>();
    }
    public void addChild(Float element)
    {
        this.floats.add(element);
    }

    @Override
    public String toString() {
        //return "FLOAT:" + floats ;
        return String.join(", ", floats.stream().map(Object::toString).toArray(String[]::new));

    }
}

