package AST.Function;

import AST.Statement;

public class BlockOfFunction extends Statement
{
    public Statement statement;

    public BlockOfFunction(Statement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return String.format(" %s ",statement);

    }
}

