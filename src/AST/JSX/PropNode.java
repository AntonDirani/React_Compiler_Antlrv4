package AST.JSX;

import AST.Node;
import AST.Statement;
import AST.StatementNode;

public class PropNode extends Statement {
    private String propName;
    private String propValue;

    public PropNode(String propName, String propValue) {
        this.propName = propName;
        this.propValue = propValue;
    }


    @Override
    public String toString() {
        return "   STYLE|{" +
                "propName='" + propName + '\'' +
                ", propValue='" + propValue + '\'' +
                '}';
    }
}
