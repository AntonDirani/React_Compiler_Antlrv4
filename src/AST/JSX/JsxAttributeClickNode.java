package AST.JSX;

import AST.Node;
import AST.Statement;
import AST.StatementNode;

public class JsxAttributeClickNode extends Statement {
    private String attributeName;
    private String attributeValue;

    public JsxAttributeClickNode(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }


    @Override
    public String toString() {
        return "      JsxAttributeClick|{" +
                "attributeName='" + attributeName + '\'' +
                ", attributeValue='" + attributeValue + '\'' +
                '}';
    }


}
