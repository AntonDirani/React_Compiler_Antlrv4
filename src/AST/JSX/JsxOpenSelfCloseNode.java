package AST.JSX;


import AST.Statement;


import java.util.LinkedList;
public class JsxOpenSelfCloseNode extends Statement {
    private String tagName;
    private Statement jsxClass ;
    private LinkedList<Statement> attributes;

    public JsxOpenSelfCloseNode(String tagName, LinkedList<Statement> attributes, Statement jsxClasse) {
        this.tagName = tagName;
        this.jsxClass = jsxClass;
        this.attributes = attributes;
    }





    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("\nJsxOpenSelfClose :TagName : %s    Attributes:", tagName));

        for (Statement attribute : attributes) {
            stringBuilder.append(attribute.toString());
        }

        if( jsxClass != null) {
            stringBuilder.append(jsxClass.toString());
        }

        return stringBuilder.toString();}
}
