package AST.JSX;

import AST.Node;
import AST.Statement;
import AST.StatementNode;

public class JsxExpressionNode extends Statement {
    private String expressionText;

    public JsxExpressionNode(String expressionText) {
        this.expressionText = expressionText;
    }

//    @Override
//    protected String nodeInfo() {
//        return "JsxExpressionNode";
//    }

    @Override
    public String toString() {
        return String.format("\nJsxExpression|{JsxExpression: %s }", expressionText);
    }
}
