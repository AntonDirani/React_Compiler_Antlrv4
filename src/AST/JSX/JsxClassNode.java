package AST.JSX;

import AST.Statement;

public class JsxClassNode extends Statement {
    private String className;
    private String value;

    public JsxClassNode(String className, String value) {
        this.className = className;
        this.value = value;
    }

    public String getClassName() {
        return className;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "\t" +
                "className=" + className +
                ", value=" + value ;
    }
}
