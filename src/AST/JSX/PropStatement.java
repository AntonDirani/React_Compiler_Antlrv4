package AST.JSX;

import AST.Statement;

public class PropStatement extends Statement {
    private String propName;
    private String propValue;

    public PropStatement(String propName, String propValue) {
        this.propName = propName;
        this.propValue = propValue;
    }


    @Override
    public String toString() {
        return "PropStatement :" +
                "propName=" + propName +
                ", propValue=" + propValue ;
    }
}
