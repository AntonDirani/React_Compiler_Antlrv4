package AST.React;

import AST.Statement;

import java.util.ArrayList;

public class ReactComponentBlockStatement extends Statement {

    public ArrayList<Statement> statements;

    public ReactComponentBlockStatement(ArrayList<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public String toString() {
        return "ReactComponentBlockStatement{" +
                "statements=" + statements +
                '}';
    }
}
