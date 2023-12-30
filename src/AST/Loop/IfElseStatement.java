package AST.Loop;

import AST.Statement;

public class IfElseStatement extends Statement {
    Statement ifStatement;
    Statement elseStatement;

    public IfElseStatement(Statement ifStatement, Statement elseStatement) {
        this.ifStatement = ifStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        return String.format("%s :  %s else {\n %s \n}", this.getClass().getSimpleName(),ifStatement,elseStatement);


    }
}