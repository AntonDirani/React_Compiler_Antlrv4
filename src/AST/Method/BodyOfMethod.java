package AST.Method;

import AST.Statement;

import java.util.ArrayList;

public class BodyOfMethod extends  MethodDeclaration {
    ArrayList<Statement> children;

    public BodyOfMethod()
    {
        this.children = new ArrayList<>();
    }
    public void addChild(Statement statement)
    {
        this.children.add(statement);
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Statement child : this.children){
            stringBuilder.append(child);
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }
}
