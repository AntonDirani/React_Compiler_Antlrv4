package AST.Method;

import AST.Statement;

public class ConstructorMethod extends  MethodDeclaration
{

    public String constructorKeyWord;
    public Statement parameters;
    public Statement bodyOfConstructor;

    public ConstructorMethod(String constructorKeyWord, Statement parameters, Statement bodyOfConstructor) {
        this.constructorKeyWord = constructorKeyWord;
        this.parameters = parameters;
        this.bodyOfConstructor = bodyOfConstructor;
    }

    @Override
    public String toString() {
       /* return "ConstructorMethod{" +
                "constructorKeyWord='" + constructorKeyWord + '\'' +
                ", parameters=" + parameters +
                ", bodyOfConstructor=" + bodyOfConstructor +
                '}';*/
        return String.format("MethodDeclaration, methodType:  %s{ constructorKeyWord: %s(parameters: %s){ constructorBody{ this.%s } }", this.getClass().getSimpleName(),constructorKeyWord, parameters, bodyOfConstructor);

    }
}

