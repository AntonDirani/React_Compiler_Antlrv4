package AST.Literal;

import java.util.ArrayList;

import java.util.LinkedList;


public class BoolLiteral extends Literal
{

    LinkedList<Boolean> booleans ;

    public BoolLiteral()
    {
        this.booleans = new LinkedList<>();
    }
    public void addChild(boolean element)
    {
        this.booleans.add(element);
    }

    @Override
    public String toString() {
        //return ""+ booleans ;
        return String.join(", ", booleans.stream().map(Object::toString).toArray(String[]::new));
    }

}

