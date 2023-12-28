package AST.Class;

import AST.Statement;

public class NormalClass extends  Statement{

    public String classKeyWord;
    public String nameOfClass;
    public Statement bodyOfClass;

    public NormalClass(String classKeyWord, String nameOfClass) {
        this.classKeyWord = classKeyWord;
        this.nameOfClass = nameOfClass;

    }
    public NormalClass(String classKeyWord, String nameOfClass, Statement bodyOfClass) {
        this.classKeyWord = classKeyWord;
        this.nameOfClass = nameOfClass;
        this.bodyOfClass = bodyOfClass;
    }

    @Override
    public String toString()
    {
        return String.format("Statement: %s ,classKeyWord: %s , className: %s{\n\nclassBody{\n\n%s}\n}", this.getClass().getSimpleName(), classKeyWord, nameOfClass,bodyOfClass);

    }
}
