package Visitor;
import AST.*;
import AST.Expr.Expr;
import AST.Expr.FloatExpr;
import AST.Expr.IdExpr;
import AST.Expr.IntExpr;
import AST.Function.*;
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
        }else if(ctx.assignment()!= null)
        {
            statement = (Statement) visit(ctx.assignment());
        }
        else
        {
            statement = (Statement) visit(ctx.printOrLogStatement());
        }
        // return super.visitStatement(ctx);
        // return visit(ctx.variableDeclaration());
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
    public Statement visitAssignment(ParserGram.AssignmentContext ctx)
    {
        String id = ctx.ID().getText();
        Statement value = (Statement) visit(ctx.variableValues());
        return new AssignmentStatement(id,value);
    }

    @Override
    public Statement visitVariableValues(ParserGram.VariableValuesContext ctx)
    {
        //value of variable is literal or idExpr or hook
        //just add hook
        Statement statement;
        if(ctx.literal() != null )
        {
            statement = (Statement) visit(ctx.literal());
        }
        else
        {
            statement =(Statement) visit(ctx.expr());
        }
        return statement;

    }

    ////id

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
        String exportKeyWord = ctx.EXPORT().toString();
        Statement statement;
        if(ctx.arrowFunction() != null)
        {
            statement = (Statement) visit(ctx.arrowFunction());
        }else if(ctx.anonymousFunction() != null)
        {
            statement = (Statement) visit(ctx.anonymousFunction());
        }
        else{
            statement = (Statement) visit(ctx.functionDeclaration());
        }
        return statement;
    }

    /*@Override
    public Statement visitFunctionExpr(ParserGram.FunctionExprContext ctx)
    {
        String dataType = String.valueOf(visitDataType( ctx.dataType()));
        String nameOfFunction =ctx.ID().getText();
        Statement funcDecStatement = (Statement) visit(ctx.functionDeclaration());
      //  return (Statement) visit(ctx.functionDeclaration());
        return new FunctionExpr(dataType,nameOfFunction,funcDecStatement);
    }*/

    @Override
    public Statement visitAnonymousFunction(ParserGram.AnonymousFunctionContext ctx)
    {
        String dataType = String.valueOf(visitDataType( ctx.dataType()));
        String nameOfFunction =ctx.ID().getText();
        Statement funcDecStatement = (Statement) visit(ctx.functionDeclaration());
        //  return (Statement) visit(ctx.functionDeclaration());
        return new AnonymousFunction(dataType,nameOfFunction,funcDecStatement);

    }

    @Override
    public Statement visitArrowFunction(ParserGram.ArrowFunctionContext ctx)
    {
        String dataType = String.valueOf(visitDataType( ctx.dataType()));
        String nameOfFunction =ctx.ID().getText();
        Statement block = visitBlock(ctx.block());
        Statement parameter = visitParameters(ctx.parameters());
        if(ctx.parameters() != null)
        {
            return new ArrowFunction(dataType,nameOfFunction,parameter,block);
        }
        else
        {
            return new ArrowFunction(dataType,nameOfFunction,block);
        }


    }

    @Override
    public Statement visitFunctionDeclaration(ParserGram.FunctionDeclarationContext ctx)
    {
        String function = ctx.FUNCTION().getText();
        Statement parameter = visitParameters(ctx.parameters());
        Statement block = visitBlock(ctx.block());
        if(ctx.parameters() != null)
        {
            return new FunctionDeclaration(function,parameter,block);
        }else{
            return new FunctionDeclaration(function,block);
        }

    }

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
        Statement statement;
        if(ctx.returnStatement() != null)
        {
            statement = visitReturnStatement(ctx.returnStatement());
        }
        else if(ctx.printOrLogStatement() != null) {
            statement = visitPrintOrLogStatement(ctx.printOrLogStatement());
        }
        else{
            //بدو تعديل
            statement = (Statement) visitHook(ctx.hook());
        }
        return new BlockOfFunction(statement);
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
