package AST.React;

import AST.Statement;

public class CreateElementStatement extends Statement {

    Statement type;
    Statement props;

    Statement children;

    Statement createElement;

    public CreateElementStatement(Statement type, Statement props, Statement children) {
        this.type = type;
        this.props = props;
        this.children = children;
    }

    public CreateElementStatement(Statement createElement) {
        this.createElement = createElement;
    }

    @Override
    public String toString() {
        return "\nCreateElementStatement{" + "\n" +
                "type=" + type +
                ", props=" + props +
                ", children=" + children +
                "\n" + '}';
    }
}
