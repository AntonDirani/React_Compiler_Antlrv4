package AST.Literal;
import java.util.ArrayList;

public class StringLiteral extends Literal
{
    //public ArrayList<String> strings ;
    String string;

    public StringLiteral(String string) {
        this.string = string;
    }
/*
    public StringLiteral()
    {
        this.strings = new ArrayList<>();
    }
    public void addChild(String text)
    {
        this.strings.add(text);
    }*/

    @Override
    public String toString() {
       return "stringLiteral : " + string ;

    }

}
