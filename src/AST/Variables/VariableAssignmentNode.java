package AST.Variables;
import AST.Node;

import AST.ExpressionNode;

public class VariableAssignmentNode extends ExpressionNode {

    public String varName;
    public Node assignment;

    public VariableAssignmentNode(String varName, Node assignment) {
        this.varName = varName;

        this.addChild(assignment);
    }

    protected String nodeInfo() {
        return String.format("%s|%s", this.getClass().getSimpleName(),"Var Name: " + varName);
    }

    @Override
    public String toString() {
        return String.format("%s|%s", this.getClass().getSimpleName(),"Var Name: " + varName);
    }
}
