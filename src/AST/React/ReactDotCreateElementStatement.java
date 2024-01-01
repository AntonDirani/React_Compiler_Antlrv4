package AST.React;

import AST.Statement;

public class ReactDotCreateElementStatement extends Statement {


    Statement createElement;

    public ReactDotCreateElementStatement(Statement createElement) {
        this.createElement = createElement;
    }

    @Override
    public String toString() {
        return "ReactDotCreateElementStatement{" +
                "createElement=" + createElement +
                '}';
    }
}
