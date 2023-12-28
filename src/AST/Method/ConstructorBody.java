package AST.Method;

import java.util.ArrayList;

public class ConstructorBody extends MethodDeclaration
{
    public String thisKeyWord = "this";
    public ArrayList<String> iD;

    public ConstructorBody()
    {
        this.iD = new ArrayList<>();
    }

    public void addChild(String iD)
    {
        this.iD.add(iD);
    }

    @Override
    public String toString() {
        return String.join(" = ", iD.stream().map(Object::toString).toArray(String[]::new));

    }
}

