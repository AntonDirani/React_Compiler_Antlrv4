package AST.React.ReactHooks;

import AST.Statement;

public class UseStateStatement extends Statement {

    Statement pairValue;

    Statement constructorValue;

    public UseStateStatement(Statement pairValue, Statement constructorValue) {
        this.pairValue = pairValue;
        this.constructorValue = constructorValue;
    }

    @Override
    public String toString() {
        return String.format("Statement: %s , Pair Value: %s , Constructor Value: %s ", this.getClass().getSimpleName(), pairValue,constructorValue);
    }
}
