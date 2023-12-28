package AST.Class;

import AST.Statement;

public class CreateAnObject extends Statement
{

    public String dataType;
    public String nameOfVar;
    Statement bodyOfObject;

    public CreateAnObject(String dataType,String nameOfVar,Statement bodyOfObject) {

        this.dataType = dataType;
        this.nameOfVar = nameOfVar;
        this.bodyOfObject = bodyOfObject;
    }
    @Override
    public String toString()
    {
        // return String.format("Statement: %s , %s %s = %s %s(%s)", this.getClass().getSimpleName(), constKeyWord,nameOfVar,newKeyWord,nameOfClass,literals);
        return String.format("Statement: %s , %s %s = %s", this.getClass().getSimpleName(), dataType,nameOfVar,bodyOfObject);
    }

}
