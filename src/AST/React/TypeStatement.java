package AST.React;

import AST.Statement;

public class TypeStatement extends Statement {

        Statement type;

    public TypeStatement(Statement type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "TypeStatement{" +
                "type=" + type +
                '}';
    }
}
