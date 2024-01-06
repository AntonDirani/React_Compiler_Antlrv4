package AST.JSX;


import AST.Statement;

import java.util.LinkedList;
public class JsxChildrenNode extends Statement {
    private LinkedList<Node> children = new LinkedList<>();

    public JsxChildrenNode(LinkedList<Node> children) {
        this.children = children;
     //   addChildren(children);
    }

//    @Override
//    protected String nodeInfo() {
//        return "JsxChildrenNode";
//    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString()); 
        stringBuilder.append("Children:\n");

        for (Node child : children) {
            stringBuilder.append(child.toString());
        }

        return stringBuilder.toString();
    }
}
