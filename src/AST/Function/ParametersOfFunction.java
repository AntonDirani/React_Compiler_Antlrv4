package AST.Function;

import AST.Statement;

import java.util.ArrayList;
import java.util.List;

public class ParametersOfFunction extends Statement
{
    public ArrayList<String> id;

    public ParametersOfFunction() {
        this.id = new ArrayList<>();
    }
    public void addChild(String id)
    {
        this.id.add(id);
    }

    @Override
    public String toString() {
        return String.join(", ", id.stream().map(Object::toString).toArray(String[]::new));

    }
}

