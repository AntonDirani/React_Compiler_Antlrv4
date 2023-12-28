package AST.Class;

import AST.Statement;

public class InheritsClass extends  Statement
{
    public String classKeyWord;
    public Statement nameOfClass;
    public Statement bodyOfClass;

    public InheritsClass(String classKeyWord, Statement nameOfClass) {
        this.classKeyWord = classKeyWord;
        this.nameOfClass = nameOfClass;

    }
    public InheritsClass(String classKeyWord, Statement nameOfClass, Statement bodyOfClass) {
        this.classKeyWord = classKeyWord;
        this.nameOfClass = nameOfClass;
        this.bodyOfClass = bodyOfClass;
    }

    @Override
    public String toString()
    {
        return String.format("Statement: %s , classKeyWord: %s %s{\n\nclassBody{\n\n%s}\n}", this.getClass().getSimpleName(), classKeyWord,nameOfClass,bodyOfClass);

    }
}
