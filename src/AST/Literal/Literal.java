package AST.Literal;


import AST.Statement;

import java.util.ArrayList;

public class Literal extends Statement
{

    public ArrayList<Literal> literals;

    public Literal() {
        this.literals = new ArrayList<>();
    }
    public  void addChild(Literal literal)
    {
        this.literals.add(literal);
    }

    @Override
    public String toString() {
        return String.join(", ", literals.stream().map(Object::toString).toArray(String[]::new));

    }

}

