package Visitor;
import AST.*;
import AST.Expr.Expr;
import AST.Expr.FloatExpr;
import AST.Expr.IdExpr;
import AST.Expr.IntExpr;
import AST.Function.BlockOfFunction;
import AST.Function.FunctionDeclaration;
import AST.Function.ParametersOfFunction;
import AST.Literal.*;
import grammar.ParserGram;
import grammar.ParserGramBaseVisitor;

import org.antlr.v4.runtime.tree.ParseTree;

public class MyVisitor extends ParserGramBaseVisitor
{


    @Override
    public Program visitProgram(ParserGram.ProgramContext ctx) {
        Program program = new Program();
        for(int i = 0; i < ctx.statement().size() ; i++)
        {
            //  program.addChild(visitStatement(ctx.statement().get(i)));
            Statement node = (Statement) visitStatement(ctx.statement(i));
            program.addChild(node);
        }
        return program;
    }


    @Override
    public Statement visitStatement(ParserGram.StatementContext ctx)
    {

        Statement statement;
        if(ctx.variableDeclaration() != null)
        {
            statement = (Statement) visit(ctx.variableDeclaration());
        }
        else if(ctx.function() != null)
        {
            statement = (Statement) visit(ctx.function());
        }else if(ctx.returnStatement()!= null)
        {
            statement = (Statement) visit(ctx.returnStatement());
        }
        else
        {
            statement = (Statement) visit(ctx.printOrLogStatement());
        }
        //return super.visitStatement(ctx);
        //  return visit(ctx.variableDeclaration());
        // return visit(ctx.function());
        return statement;
    }

    @Override
    public Statement visitVariableDeclaration(ParserGram.VariableDeclarationContext ctx)
    {

        String type = String.valueOf(visitDataType(ctx.dataType()));
        String variableName = ctx.ID().getText();
        return new VariableDeclarationStatement(type, variableName, (Statement) visit(ctx.variableValues()));
        // return  (Statement) visit(ctx.variableValues());
    }

    @Override
    public Object visitDataType(ParserGram.DataTypeContext ctx) {
        String type = "null";
        if (ctx.CONST() != null) {
            type = "Const";
        } else if (ctx.LET() != null) {
            type = "Let";
        } else if (ctx.VAR() != null) {
            type = "Var";
        }
        return type;
    }
    @Override
    public Statement visitVariableValues(ParserGram.VariableValuesContext ctx)
    {

        return (Statement) visit(ctx.literal());

    }
    ////literal
    @Override
    public Statement visitIntegerLiteral(ParserGram.IntegerLiteralContext ctx)
    {
        IntegerLiteral integerLiteral = new IntegerLiteral();

        for( int i = 0 ; i < ctx.children.size();i++)
        {
            integerLiteral.addChild(Integer.parseInt(ctx.INTEGER().getText()));
        }
        return integerLiteral;
    }
    @Override
    public Literal visitFloatLiteral(ParserGram.FloatLiteralContext ctx)
    {
        FloatLiteral floatLiteral = new FloatLiteral();

        for( int i = 0 ; i < ctx.children.size();i++)
        {
            floatLiteral.addChild(Float.parseFloat(ctx.FLOAT().getText()));
        }
        return  floatLiteral;
    }
    @Override
    public Literal visitStringLiteral(ParserGram.StringLiteralContext ctx)
    {
        StringLiteral stringLiteral = new StringLiteral();
        for( int i = 0 ; i < ctx.children.size();i++)
        {
            stringLiteral.addChild(ctx.StringLiteral().getText());

        }
        return stringLiteral;
    }
    @Override
    public Literal visitBoolLiteral(ParserGram.BoolLiteralContext ctx)
    {
        BoolLiteral boolLiteral = new BoolLiteral();

        for( int i = 0 ; i < ctx.children.size();i++)
        {
            boolLiteral.addChild(Boolean.parseBoolean(ctx.BOOL().getText()));
        }
        return boolLiteral;
    }
    @Override
    public Object visitNull(ParserGram.NullContext ctx) {
        return super.visitNull(ctx);
    }


    @Override
    public Statement visitFunction(ParserGram.FunctionContext ctx)
    {
        return (Statement) visit(ctx.functionExpr());
    }

    @Override
    public Statement visitFunctionExpr(ParserGram.FunctionExprContext ctx) {
        return (Statement) visit(ctx.functionDeclaration());
    }
    @Override
    public Statement visitFunctionDeclaration(ParserGram.FunctionDeclarationContext ctx)
    {String function = ctx.FUNCTION().getText();
        //return visitParameters(ctx.parameters());
        // return  visitBlock(ctx.block());
        return new FunctionDeclaration(function,visitParameters(ctx.parameters()),visitBlock(ctx.block()));
    }
    /* @Override
     public Statement visitParameters(ParserGram.ParametersContext ctx) {
         ParametersOfFunction parameters = new ParametersOfFunction();
         for (int i = 0; i < ctx.children.size(); i++) {
             //parameters.addChild(ctx.ID().get(i).toString());
             if(ctx.ID() != null)
                 parameters.addChild(ctx.ID().get(i).toString());
             else
                parameters.addChild(ctx.ID(i).getText());
         }
         return parameters;
     }*/
    @Override
    public Statement visitParameters(ParserGram.ParametersContext ctx) {
        ParametersOfFunction parameters = new ParametersOfFunction();
        for (int i = 0; i < ctx.ID().size(); i++) {
            parameters.addChild(ctx.ID(i).getText());
        }
        return parameters;
    }

    @Override
    public Statement visitBlock(ParserGram.BlockContext ctx)
    {
        Statement s = visitReturnStatement(ctx.returnStatement());
        return new BlockOfFunction(s);
    }
    @Override
    public Statement visitReturnStatement(ParserGram.ReturnStatementContext ctx) {
        // return super.visitReturnStatement(ctx);
        ReturnStatement returnStatement = new ReturnStatement();
        if (ctx.ID() != null) {
            returnStatement.addChild(ctx.ID().getText());
        } else if (ctx.literal() != null) {
            returnStatement.addChild(ctx.literal().getText());
        } else {
            returnStatement.addChild(ctx.getText());
        }

        return returnStatement;
    }

    @Override
    public Statement visitPrintOrLogStatement(ParserGram.PrintOrLogStatementContext ctx) {
        // return (Statement) visit(ctx.expr()); // will return the expr
        String consoleKeyWord = ctx.CONSOLE().getText();
        String logKeyWord = ctx.LOG().getText();
        //Statement expr = (Statement) visit(ctx.expr());
        //Statement StringLiteral = (Statement) visit(ctx.StringLiteral());
        Statement value;
        if(ctx.expr() != null)
        {
            value = (Statement) visit(ctx.expr());
        }
        else if(ctx.literal() != null)
        {
            value = (Statement) visit(ctx.literal());
        }
        else
        {
            value = (Statement) visit(ctx.accessMethodInLogStatement());
        }

        return new PrintOrLogStatement(consoleKeyWord,logKeyWord,value);

    }


    //Expr
    @Override
    public Expr visitIdExpr(ParserGram.IdExprContext ctx)
    {
        IdExpr idExpr = new IdExpr();
        for( int i = 0 ; i < ctx.children.size();i++)
        {
            idExpr.addChild(ctx.ID().getText());

        }
        return idExpr;
    }
    @Override
    public Expr visitIntExpr(ParserGram.IntExprContext ctx)
    {
        IntExpr intExpr = new IntExpr();
        for( int i = 0 ; i < ctx.children.size();i++)
        {
            intExpr.addChild(Integer.parseInt(ctx.INTEGER().getText()));
        }
        return intExpr;
    }
    @Override

    public Expr visitFloatExpr(ParserGram.FloatExprContext ctx) {
        FloatExpr floatExpr = new FloatExpr();

        for( int i = 0 ; i < ctx.children.size();i++)
        {
            floatExpr.addChild(Float.parseFloat(ctx.FLOAT().getText()));
        }
        return floatExpr;
    }

}

