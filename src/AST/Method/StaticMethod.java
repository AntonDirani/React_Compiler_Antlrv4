package AST.Method;

import AST.Statement;

public class StaticMethod extends  MethodDeclaration
{

    public String staticKeyWord;
    public Statement method;


    public StaticMethod(String staticKeyWord,Statement method)
    {
        this.staticKeyWord = staticKeyWord;
        this.method = method;
    }

    @Override
    public String toString() {
        return String.format("MethodDeclaration, methodType: %s { staticKeyWord: %s  %s}", this.getClass().getSimpleName(), staticKeyWord ,method);

    }
}

