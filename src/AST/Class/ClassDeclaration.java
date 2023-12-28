/*package AST.Class;

import AST.Statement;

import java.util.ArrayList;

public class ClassDeclaration extends Statement
{

    ArrayList<NormalClass> child;
    ArrayList<InheritsClass> child1;

    public ClassDeclaration()
    {
        this.child = new ArrayList<>();

    }
    public void addChild(NormalClass normalClass)
    {
        this.child.add(normalClass);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Statement children : this.child){
            stringBuilder.append(children);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}*/
package AST.Class;

import AST.Statement;

import java.util.ArrayList;

public class ClassDeclaration extends Statement {

    private ArrayList<NormalClass> normalClasses;
    private ArrayList<InheritsClass> inheritsClasses;

    public ClassDeclaration() {
        this.normalClasses = new ArrayList<>();
        this.inheritsClasses = new ArrayList<>();
    }

    public void addNormalClass(NormalClass normalClass) {
        this.normalClasses.add(normalClass);
    }

    public void addInheritsClass(InheritsClass inheritsClass) {
        this.inheritsClasses.add(inheritsClass);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (NormalClass normalClass : this.normalClasses) {
            stringBuilder.append(normalClass);
            stringBuilder.append("\n");
        }
        for (InheritsClass inheritsClass : this.inheritsClasses) {
            stringBuilder.append(inheritsClass);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
