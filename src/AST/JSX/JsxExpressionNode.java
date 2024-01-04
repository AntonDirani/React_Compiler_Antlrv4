package AST.JSX;

import AST.Statement;

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
        return String.format("\nJsxExpression:JsxExpression: %s ", expressionText);
    }
}
