package AST.Variables;

import AST.Statement;

public class AssignmentStatement extends Statement
{
    String variableName;
    Statement variableValue;

    public AssignmentStatement(String variableName, Statement variableValue) {
        this.variableName = variableName;
        this.variableValue = variableValue;
    }

    @Override
    public String toString() {
        return String.format("Statement: %s ,variableName: %s, variableValue: %s", this.getClass().getSimpleName(), variableName, variableValue);

    }
}

