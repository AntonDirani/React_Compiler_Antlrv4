package AST.Function;

import AST.Statement;

public class BlockOfFunction extends Statement
{
    public Statement returnStatement;

    public BlockOfFunction(Statement returnStatement) {
        this.returnStatement = returnStatement;
    }

    @Override
    public String toString() {
        return "returnStatement: return " + returnStatement ;
    }
}

