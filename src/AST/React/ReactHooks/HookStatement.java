package AST.React.ReactHooks;

import AST.Statement;

public class HookStatement extends Statement {

    Statement reactHook;


    public HookStatement(Statement reactHook) {
        this.reactHook = reactHook;
    }

    @Override
    public String toString() {
        return "HookStatement{" +
                "reactHook=" + reactHook +
                '}';
    }
}
