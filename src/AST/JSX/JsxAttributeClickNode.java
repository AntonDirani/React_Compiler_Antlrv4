package AST.JSX;

import AST.Statement;

public class JsxAttributeClickNode extends Statement {
    private String attributeName;
    private String attributeValue;

    public JsxAttributeClickNode(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }


    @Override
    public String toString() {
        return "\t" +
                "attributeName=" + attributeName+
                ", attributeValue=" + attributeValue ;
    }


}
