package AST.React;

import AST.Statement;

public class ClickHandlerStatement extends Statement {

    Statement arrowFuncion;

    public ClickHandlerStatement(Statement arrowFunction) {
        this.arrowFuncion = arrowFunction;
    }


    @Override
    public String toString() {
        return "ClickHandlerStatement{" +
                "arrowFuncion=" + arrowFuncion +
                '}';
    }
}
