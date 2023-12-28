package AST.JSX;

import AST.Node;
import AST.Statement;
import AST.StatementNode;

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
