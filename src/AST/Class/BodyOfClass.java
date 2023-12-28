package AST.Class;

import AST.Method.MethodDeclaration;
import AST.Statement;

import java.util.ArrayList;

public class BodyOfClass extends ClassDeclaration
{

    ArrayList<MethodDeclaration> methods;

    public BodyOfClass()
    {
        this.methods = new ArrayList<>();
    }
    public  void addChild(MethodDeclaration method)
    {
        this.methods.add(method);
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Statement children : this.methods){
            stringBuilder.append(children);
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }

}

