//package AST.JSX;
//
//import java.util.LinkedList;
//import old.Node;
//public class JsxElementNode extends Node {
//    private String tagName;
//    private LinkedList<Node> children = new LinkedList<>();
//
//    public JsxElementNode(String tagName, LinkedList<Node> children) {
//        this.tagName = tagName;
//        this.children = children;
//    }
//
//    @Override
//    protected String nodeInfo() {
//        return String.format("JsxElementNode | Tag: %s", tagName);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(super.toString()); // معلومات الأب
//        stringBuilder.append(String.format("Tag: %s\n", tagName));
//
//        for (Node child : children) {
//            stringBuilder.append(child.toString());
//        }
//
//        return stringBuilder.toString();
//    }
//}
//
//
//




package AST.JSX;

import AST.Statement;

import java.util.LinkedList;

public class JsxElementNode extends Statement {
    private String tagName;
    private Statement onClick;
    private LinkedList<Statement> children = new LinkedList<>();
    private LinkedList<Statement> attributes = new LinkedList<>();
    private LinkedList<Statement> jsxClasses = new LinkedList<>();
    private LinkedList<PropNode> styleProps = new LinkedList<>();


    public JsxElementNode(String tagName, LinkedList<Statement> attributes, LinkedList<Statement> jsxClasses, LinkedList<Statement> children,Statement onClick,LinkedList<PropNode> styleProps) {
        this.tagName = tagName;
        this.attributes = attributes;
        this.jsxClasses = jsxClasses;
        this.children = children;
        this.onClick  = onClick;
        this.styleProps = styleProps;
    }
//
//    @Override
//    protected String nodeInfo() {
//        return String.format("JsxElementNode | Tag: %s", tagName);
//    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append(String.format("\nJsxElementNode|{TagName: %s}    ", tagName));


        for (Statement attribute : attributes) {
            stringBuilder.append(attribute.toString());
        }


        for (Statement jsxClass : jsxClasses) {
            stringBuilder.append(jsxClass.toString());
        }
        if (onClick != null) {
            stringBuilder.append(String.format(onClick.toString()));
        }
        for (PropNode styleProp : styleProps) {
            stringBuilder.append(styleProp.toString());
        }
        for (Statement child : children) {
            stringBuilder.append(child.toString());
        }

        return stringBuilder.toString();
    }
}

