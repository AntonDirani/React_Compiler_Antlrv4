package AST.Function;

import AST.Statement;

import java.util.ArrayList;

public class BlockOfFunction extends Statement
{
    public ArrayList<Statement> statements;


    public BlockOfFunction(ArrayList<Statement> statement) {
        this.statements = statement;
    }


    @Override
    public String toString() {
        return String.format(" %s ",statements);

    }
}

