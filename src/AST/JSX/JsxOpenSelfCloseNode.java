package AST.JSX;


import AST.Statement;


import java.util.LinkedList;
public class JsxOpenSelfCloseNode extends Statement {
    private String tagName;
    private Statement jsxClasse ;
    private LinkedList<Statement> attributes;

    public JsxOpenSelfCloseNode(String tagName, LinkedList<Statement> attributes, Statement jsxClasse) {
        this.tagName = tagName;
        this.jsxClasse = jsxClasse;
        this.attributes = attributes;
    }


    public String getTagName() {
        return tagName;
    }

    public LinkedList<Statement> getAttributes() {
        return attributes;
    }


    @Override
    public String toString() {
        return "\nJsxOpenSelfClose|{" +
                "tagName='" + tagName + '\'' +
                ", attributes=" + '\'' + attributes +jsxClasse.toString()+
                '}';
    }
}
