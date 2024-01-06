package AST.React;

import AST.Statement;

public class ReactComponentStatement extends Statement {



    String type;
    Statement parameters;
    Statement body;

    public ReactComponentStatement(String type, Statement parameters, Statement body) {
        this.type = type;
        this.parameters = parameters;
        this.body = body;
    }

    @Override
    public String toString() {
        return "ReactComponentStatement{" + "\n" +
                "type='" + type + '\'' +
                ", parameters=" + parameters +
                ", body=" + body +
                '}';
    }
}
