package AST.Class;

import AST.Statement;

import java.util.ArrayList;

public class ExtendsId extends ClassDeclaration
{
    public ArrayList<String> i;

    public ExtendsId()
    {
        this.i = new ArrayList<>();
    }

    public void addChild(String id)
    {
        this.i.add(id);
    }

    @Override
    public String toString() {
        return String.join(" extends ", i.stream().map(Object::toString).toArray(String[]::new));
    }
}
