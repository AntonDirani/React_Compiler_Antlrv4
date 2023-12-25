package AST;

public class PrintOrLogStatement extends Statement
{
    public String consoleKeyWord;
    public String logKeyWord;
    public Statement expr;

    public PrintOrLogStatement(String consoleKeyWord,String logKeyWord,Statement expr)
    {
        this.consoleKeyWord =consoleKeyWord;
        this.logKeyWord =logKeyWord;
        this.expr = expr;
    }

    @Override
    public String toString() {
       /* return "PrintOrLogStatement{" +
                "expr:" + expr +
                '}';*/
        // return + String.join(", ", val.stream().map(Object::toString).toArray(String[]::new));
        return String.format("Statement: %s , %s.%s(%s)", this.getClass().getSimpleName(), consoleKeyWord, logKeyWord,expr);


    }
}
