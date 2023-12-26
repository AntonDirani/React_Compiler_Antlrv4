package AST.React.ReactHooks;

import AST.Statement;

public class UseContextStatement extends Statement {


    String context;


    public UseContextStatement(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "UseContext{" +
                "context='" + context + '\'' +
                '}';
    }
}
