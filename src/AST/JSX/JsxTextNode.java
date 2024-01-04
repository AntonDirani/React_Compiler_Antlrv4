package AST.JSX;
import AST.Statement;


public class JsxTextNode extends Statement {
    public String text;

    public JsxTextNode(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format("\n%s:%s", this.getClass().getSimpleName(),"\tText:"+text);
    }


}
