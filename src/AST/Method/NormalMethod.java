package AST.Method;

import AST.Statement;

public class NormalMethod extends  MethodDeclaration
{
    public String nameOfMethod;
    public Statement parameter;
    public Statement bodyOfMethod;


    public NormalMethod(String nameOfMethod,  Statement bodyOfMethod)
    {
        this.nameOfMethod = nameOfMethod;
        this.bodyOfMethod = bodyOfMethod;
    }
    public NormalMethod(String nameOfMethod, Statement parameter, Statement bodyOfMethod) {
        this.nameOfMethod = nameOfMethod;
        this.parameter = parameter;
        this.bodyOfMethod = bodyOfMethod;
    }

    @Override
    public String toString() {
        return String.format("MethodDeclaration, methodType: %s , methodName: %s(parameters: %s) methodBody{\n\n%s}", this.getClass().getSimpleName(), nameOfMethod, parameter,bodyOfMethod);

    }
}
