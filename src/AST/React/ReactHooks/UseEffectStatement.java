package AST.React.ReactHooks;

import AST.Statement;

public class UseEffectStatement extends Statement {

    Statement arrowFunction;
    Statement block;

    public UseEffectStatement(Statement arrowFunction, Statement block) {
        this.arrowFunction = arrowFunction;
        this.block = block;
    }

    @Override
    public String toString() {
        return "UseEffectStatement{" +
                "arrowFunction=" + arrowFunction +
                ", block=" + block +
                '}';
    }
}
