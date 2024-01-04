package AST.JSX;
import AST.Statement;

import java.util.LinkedList;

public class JsxElementJsNode extends Statement {
    private String element;
    private LinkedList<AttributeElementJsNode> attributes = new LinkedList<>();

    public JsxElementJsNode(String element,LinkedList<AttributeElementJsNode> attributes) {
        this.attributes = attributes;
        this.element = element;
    }

//    @Override
//    protected String nodeInfo() {
//        return "JsxElementJsNode";
//    }



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("\nJsxElementJsNode|{element: %s}    ", element));


        for (AttributeElementJsNode attribute : attributes) {
            stringBuilder.append(attribute.toString());
        }

       return stringBuilder.toString();}}