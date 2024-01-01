package AST.React;

import AST.JSX.PropStatement;
import AST.Statement;

import java.util.ArrayList;


public class PropsStatement extends Statement {


        ArrayList<PropStatement> propsList;


    public PropsStatement(ArrayList<PropStatement> propsList) {
        this.propsList = propsList;
    }

    @Override
    public String toString() {
        return "PropsStatement{" +
                "propsList=" + propsList +
                '}';
    }
}
