//package AST.JSX;
//
//import java.util.LinkedList;
//import AST.Node;
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
    private Statement jsxClass ;
    private LinkedList<PropStatement> styleProps = new LinkedList<>();


    public JsxElementNode(String tagName, LinkedList<Statement> attributes,Statement jsxClasse, LinkedList<Statement> children,Statement onClick,LinkedList<PropStatement> styleProps) {
        this.tagName = tagName;
        this.attributes = attributes;
        this.jsxClass = jsxClasse;
        this.children = children;
        this.onClick  = onClick;
        this.styleProps = styleProps;
    }

    @Override
    public String toString() {
        return "JsxElementNode{" + "\n" +
                "tagName='" + tagName + '\'' +
                ", onClick=" + onClick +
                ", children=" + children +
                ", attributes=" + attributes +
                ", jsxClass=" + jsxClass +
                ", styleProps=" + styleProps +
                 "\n" +'}';
    }

    /* @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("\nJsxElementNode:TagName: %s    Attributes:", tagName));


        for (Statement attribute : attributes) {
            stringBuilder.append(attribute.toString());
        }


        if( jsxClass != null) {
            stringBuilder.append(jsxClass.toString());
        }
        if (onClick != null) {
            stringBuilder.append(String.format(onClick.toString()));
        }
        for (PropStatement styleProp : styleProps) {
            stringBuilder.append(styleProp.toString());
        }
        for (Statement child : children) {
            stringBuilder.append(child.toString());
        }

        return stringBuilder.toString();
    }*/
}

