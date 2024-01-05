package AST.JSX;
import AST.Statement;

public class JsxBlockNode extends Statement {
    private Statement jsxElement;

    public JsxBlockNode(Statement jsxElement) {
        this.jsxElement = jsxElement;
       // addChild(jsxElement);
    }

    @Override
    public String toString() {
        return "JsxBlockNode{"  + "\n" +
                "jsxElement=" + jsxElement +
                '}';
    }

    /* @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s:Content: jsxElement", this.getClass().getSimpleName()));
        stringBuilder.append(jsxElement.toString());
        return stringBuilder.toString();

    }*/

}
