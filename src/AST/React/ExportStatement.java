package AST.React;

import AST.Statement;

public class ExportStatement extends Statement {

    String reactComponent;

    public ExportStatement(String reactComponent) {
        this.reactComponent = reactComponent;
    }

    @Override
    public String toString() {
        return "ExportStatement{" +
                "reactComponent='" + reactComponent + '\'' +
                '}';
    }
}
