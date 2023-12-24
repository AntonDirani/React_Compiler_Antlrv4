/*
package AST.Variables;

import AST.Node;

public class VariableDeclarationNode extends Node {

    public String type;
    public int lineNum;

    public Node initializer;
    public String variableName;

    public VariableDeclarationNode(int lineNum, String type, String variableName, Node initializer) {

        this.type = type;
        this.variableName = variableName;
        this.initializer = initializer;
        this.lineNum = lineNum;

        this.addChild(initializer);
    }

    @Override
    protected String nodeInfo() {
        return String.format("%s|{%s|%s|%s}", this.getClass().getSimpleName(), "Variable Type: " + type, "Name: " + variableName, "Position: " + lineNum);
    }

    @Override
    public String toString() {
        return String.format("%s|{%s|%s|%s}", this.getClass().getSimpleName(), "Variabel Type: " + type, "Name: " + variableName, "Position: " + lineNum);
    }
}*/
