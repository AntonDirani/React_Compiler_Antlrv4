package AST.React.ReactHooks;

import AST.Statement;

public class UseRefStatement extends Statement {

    int param;

    public UseRefStatement(int param) {
        this.param = param;
    }

    public UseRefStatement() {
    }

    @Override
    public String toString() {
        return "UseRefStatement{" +
                "param=" + param +
                '}';
    }
}
