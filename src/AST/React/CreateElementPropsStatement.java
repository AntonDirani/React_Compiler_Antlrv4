package AST.React;

import AST.Statement;

public class CreateElementPropsStatement extends Statement {

        Statement props;


    public CreateElementPropsStatement(Statement props) {
        this.props = props;

    }

    @Override
    public String toString() {
        return "CreateElementPropsStatement{" +
                "props=" + props +
                '}';
    }
}
