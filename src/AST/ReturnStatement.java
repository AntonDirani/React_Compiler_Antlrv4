package AST;

import java.util.ArrayList;
import java.util.List;

public class ReturnStatement extends Statement
{
    public ArrayList<String> val;

    public ReturnStatement() {
        this.val = new ArrayList<>();
    }
    public void addChild(String id)
    {
        this.val.add(id);
    }

    @Override
    public String toString() {
        return String.format("%s : return  %s", this.getClass().getSimpleName(), String.join(", ", val.stream().map(Object::toString).toArray(String[]::new)));
    }

}

