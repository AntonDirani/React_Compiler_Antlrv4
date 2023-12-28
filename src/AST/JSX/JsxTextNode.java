package AST.JSX;
import AST.Node;
import AST.Statement;
import AST.StatementNode;


public class JsxTextNode extends Statement {
    public String text;

    public JsxTextNode(String text) {
        this.text = text;
    }
//
//    @Override
//    protected String nodeInfo() {
//        return String.format("%s|%s", this.getClass().getSimpleName(),"JsxText: " + text);
//    }

    @Override
    public String toString() {
        return String.format("\n%s|%s}", this.getClass().getSimpleName(),"{text: " + text);
    }


}
