package AST.React;

import AST.Statement;

public class VariableDeclarationHookStatement extends Statement {

    String type;
    String name;

    Statement hook;

    public VariableDeclarationHookStatement(String type, String name, Statement hook) {
        this.type = type;
        this.name = name;
        this.hook = hook;
    }

    @Override
    public String toString() {
        return "VariableDeclarationHookStatement{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", hook=" + hook +
                '}';
    }
}
