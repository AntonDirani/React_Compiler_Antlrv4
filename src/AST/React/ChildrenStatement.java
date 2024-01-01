package AST.React;

import AST.Statement;

import java.util.ArrayList;
import java.util.List;

public class ChildrenStatement extends Statement {

    ArrayList<Statement> createElementStatements;
    String string;

    public ChildrenStatement(ArrayList<Statement> createElementStatements, String string) {
        this.createElementStatements = createElementStatements;
        this.string = string;
    }

    public  void addChild(Statement statement)
    {
        this.createElementStatements.add(statement);
    }


    @Override
    public String toString() {
        return "ChildrenStatement{" +
                "createElementStatements=" + createElementStatements +
                ", string='" + string + '\'' +
                '}';
    }
}
