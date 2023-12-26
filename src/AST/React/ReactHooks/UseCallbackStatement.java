package AST.React.ReactHooks;

import AST.Statement;

public class UseCallbackStatement extends Statement {

    Statement paramaters;
    Statement arrowFunction;


    public UseCallbackStatement(Statement paramaters, Statement useCalllbackBlock) {
        this.paramaters = paramaters;
        this.arrowFunction = useCalllbackBlock;
    }


    @Override
    public String toString() {
        return "UseCallbackStatement{" +
                "paramaters=" + paramaters +
                ", useCalllbackFunction=" + arrowFunction +
                '}';
    }
}
