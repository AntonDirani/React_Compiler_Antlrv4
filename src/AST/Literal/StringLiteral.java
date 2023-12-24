package AST.Literal;
import java.util.ArrayList;

public class StringLiteral extends Literal
{
    public ArrayList<String> strings ;

    public StringLiteral()
    {
        this.strings = new ArrayList<>();
    }
    public void addChild(String text)
    {
        this.strings.add(text);
    }

    @Override
    public String toString() {
        // return "stringLiteral:" + strings ;
        return String.join(", ", strings.stream().map(Object::toString).toArray(String[]::new));
    }
}
