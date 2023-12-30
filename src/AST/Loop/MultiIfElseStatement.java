package AST.Loop;


import AST.Statement;

public class MultiIfElseStatement extends Statement
{
    Statement ifElseIfStatement;
    Statement elseStatement;

    public MultiIfElseStatement(Statement ifElseIfStatement, Statement elseStatement) {
        this.ifElseIfStatement = ifElseIfStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        return String.format("%s :  %s else {\n %s \n}", this.getClass().getSimpleName(),ifElseIfStatement,elseStatement);


    }
}
