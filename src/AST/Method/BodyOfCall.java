package AST.Method;

import AST.Statement;

public class BodyOfCall extends Statement
{
    public String nameOfMethod ;
    public Statement parameters;

    public BodyOfCall(String nameOfMethod) {
        this.nameOfMethod = nameOfMethod;

    } public BodyOfCall(String nameOfMethod, Statement parameters) {
    this.nameOfMethod = nameOfMethod;
    this.parameters = parameters;
}

    @Override
    public String toString() {
        return  String.format("funcOrMethodName: %s, %s(parameters: %s)",nameOfMethod,nameOfMethod, parameters);
    }
}
