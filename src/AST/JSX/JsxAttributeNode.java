package AST.JSX;

import AST.Statement;

public class JsxAttributeNode extends Statement {
    private String attributeName;
    private String attributeValue;

    public JsxAttributeNode(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    @Override
    public String toString() {
        return "\tattributeName=" + attributeName +
                ", attributeValue=" + attributeValue ;
    }
}
