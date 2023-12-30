package AST.Loop;

import AST.Statement;

import java.util.ArrayList;
import java.util.List;

public class IfElseIfStatement extends Statement {
    List<IfStatement> ifStatementList ;

    public IfElseIfStatement() {
        this.ifStatementList = new ArrayList<>();
    }


    public void addToList(IfStatement ifStatement)
    {
        this.ifStatementList.add(ifStatement);
    }
    @Override
    public String toString() {
        return String.join(" else ", ifStatementList.stream().map(Object::toString).toArray(String[]::new));

    }
}
