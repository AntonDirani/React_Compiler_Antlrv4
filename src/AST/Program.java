package AST;

import java.util.ArrayList;

public class Program {
    ArrayList<Statement> children;

    public Program()
    {
        this.children = new ArrayList<>();
    }
    public  void addChild(Statement statement)
    {
        this.children.add(statement);
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Statement child : this.children){
            stringBuilder.append(child);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}

