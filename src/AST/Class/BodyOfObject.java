package AST.Class;

import AST.Literal.Literal;
import AST.Statement;


public class BodyOfObject extends Statement
{
    public String newKeyWord;
    public String nameOfClass;
    public Literal literals;

    public BodyOfObject(String newKeyWord, String nameOfClass, Literal literals) {
        this.newKeyWord = newKeyWord;
        this.nameOfClass = nameOfClass;
        this.literals = literals;
    }

    @Override
    public String toString() {
        return String.format("%s %s(%s)", newKeyWord,nameOfClass,literals);

    }
}
