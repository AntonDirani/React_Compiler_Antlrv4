package AST.JSX;
import AST.Node;
import AST.Statement;
import AST.StatementNode;

public class JsxBlockNode extends Statement {
    private Statement jsxElement;

    public JsxBlockNode(Statement jsxElement) {
        this.jsxElement = jsxElement;
       // addChild(jsxElement);
    }
//
//    @Override
//    protected String nodeInfo() {
//        return String.format("%s|%s", this.getClass().getSimpleName(),"Content:JsxElement");
//    }

    @Override
    public String toString() {
        return String.format("%s|{Content:jsxElement}%S", this.getClass().getSimpleName(),jsxElement.toString());
    }
}
