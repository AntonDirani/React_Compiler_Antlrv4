package AST.JSX;

import AST.Node;
import AST.Statement;
import AST.StatementNode;

public class AttributeElementJsNode extends Statement {
    private String attributeName;
    private String attributeValue;

    public AttributeElementJsNode(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    @Override
    public String toString() {
        return "      JsxAttributeElementJs|{" +
                "attributeName='" + attributeName + '\'' +
                ", attributeValue='" + attributeValue + '\'' +
                '}';
    }


}
