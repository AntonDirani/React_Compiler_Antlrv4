package AST.Loop;

import AST.Expr.Expr;
import AST.Statement;

public class ForStatement extends Statement
{

    Statement varDeclaration;
    Expr comparisonExpr;
    Expr exprOperation;
    Statement forBodyStatement;

    public ForStatement(Statement varDeclaration, Expr comparisonExpr, Expr exprOperation, Statement forBodyStatement) {
        this.varDeclaration = varDeclaration;
        this.comparisonExpr = comparisonExpr;
        this.exprOperation = exprOperation;
        this.forBodyStatement = forBodyStatement;
    }

    @Override
    public String toString() {
        return String.format("%s: for(%s ; %s ; %s){ %s }", this.getClass().getSimpleName(),varDeclaration,comparisonExpr,exprOperation,forBodyStatement);

    }
}

