package Visitor;
import AST.*;
import AST.Class.*;

import AST.ComparisonExpr.GreaterThan;
import AST.ComparisonExpr.GreaterThanOrEqual;
import AST.ComparisonExpr.LessThan;
import AST.ComparisonExpr.LessThanOrEqual;
import AST.Expr.*;
import AST.LogicExpr.And;
import AST.LogicExpr.EqualEqual;
import AST.LogicExpr.NotEqual;
import AST.LogicExpr.Or;
import AST.Function.*;
import AST.JSX.*;
import AST.Literal.*;
import AST.Loop.*;
import AST.Method.*;
import AST.OperationExpr.*;
import AST.React.ClickHandlerStatement;
import AST.React.ReactHooks.*;
import AST.Variables.AssignmentStatement;
import AST.Variables.VariableDeclarationStatement;
import SymbolTable.SymbolInfo;
import SymbolTable.SymbolTable;
import grammar.ParserGram;
import grammar.ParserGramBaseVisitor;


import java.util.LinkedList;

public class MyVisitor extends ParserGramBaseVisitor
{

    SymbolTable symbolTable = new SymbolTable();


    @Override
    public Program visitProgram(ParserGram.ProgramContext ctx) {
        Program program = new Program();
        for(int i = 0; i < ctx.statement().size() ; i++)
        {

            //  program.addChild(visitStatement(ctx.statement().get(i)));
            Statement node = (Statement) visitStatement(ctx.statement(i));
            program.addChild(node);
        }
        this.symbolTable.print();

        return program;
    }


    @Override
    public Statement visitStatement(ParserGram.StatementContext ctx)
    {

        Statement statement;

        if(ctx.classDeclaration()!= null)
        {
            statement = (Statement) visit(ctx.classDeclaration());
        }
        else if(ctx.inheritsClassDeclaration() != null)
        {
            statement = (Statement) visit(ctx.inheritsClassDeclaration());
        }else if(ctx.importStatement() != null)
        {
            statement = (Statement) visit(ctx.importStatement());
        }
        else if(ctx.variableDeclaration() != null)
        {
            statement = (Statement) visit(ctx.variableDeclaration());
        }else if(ctx.assignment()!= null)
        {
            statement = (Statement) visit(ctx.assignment());
        }
        else if(ctx.function() != null)
        {
            statement = (Statement) visit(ctx.function());
        }
        else if(ctx.callStatement() != null)
        {
            statement = (Statement) visit(ctx.callStatement());
        }else if(ctx.ifStatement()!= null)
        {
            statement = (Statement) visit(ctx.ifStatement());
        }else if(ctx.ifElseStatement()!= null)
        {
            statement = (Statement) visit(ctx.ifElseStatement());
        }else if(ctx.multiIfElseStatement()!= null)
        {
            statement = (Statement) visit(ctx.multiIfElseStatement());
        }else if(ctx.forStatement()!= null)
        {
            statement = (Statement) visit(ctx.forStatement());
        }else if(ctx.whileStatement()!= null)
        {
            statement = (Statement) visit(ctx.whileStatement());
        }
        else if(ctx.createAnObjectStatement() != null)
        {
            statement = (Statement) visit(ctx.createAnObjectStatement());
        }
        else if(ctx.stringInterpolationStatement()!= null)
        {
            statement = (Statement) visit(ctx.stringInterpolationStatement());
        }
        else if(ctx.returnStatement()!= null)
        {
            statement = (Statement) visit(ctx.returnStatement());
        }
        //export
        else if(ctx.jsxBlock() != null)
        {
            statement = (Statement) visit(ctx.jsxBlock());
        }
        else if(ctx.assignment()!= null)
        {
            statement = (Statement) visit(ctx.assignment());
        }
//        else if(ctx.useRef()!= null)
//        {
//            statement = (Statement) visit(ctx.useRef());
//        }
        else
        {
            statement = (Statement) visit(ctx.printOrLogStatement());
        }
        return statement;
    }

    ///class & method

    @Override
    public Statement visitClassDeclaration(ParserGram.ClassDeclarationContext ctx) {
        String classKeyWord = ctx.CLASS().getText();
        String  nameOfClass = ctx.ID().getText();
        Statement bodyOfClass = visitBodyOfClass(ctx.bodyOfClass());
        Statement statement ;
        if(ctx.bodyOfClass() != null)
        {
            statement = new NormalClass(classKeyWord,nameOfClass,bodyOfClass);
        }
        else
        {
            statement = new NormalClass(classKeyWord,nameOfClass);
        }

        SymbolInfo classInfo = new SymbolInfo();
        classInfo.setType(classKeyWord);
        classInfo.setName(nameOfClass);
        symbolTable.getRow().add(classInfo);
        return statement;
    }

    @Override
    public Statement visitInheritsClassDeclaration(ParserGram.InheritsClassDeclarationContext ctx) {
        String classKeyWord = ctx.CLASS().getText();
        Statement bodyOfClass = visitBodyOfClass(ctx.bodyOfClass());
        Statement idExtendsId = visitIdExtendsId(ctx.idExtendsId());
        Statement statement ;

        if(ctx.bodyOfClass() != null)
        {
            statement = new InheritsClass(classKeyWord,idExtendsId,bodyOfClass);
        }
        else
        {
            statement = new InheritsClass(classKeyWord,idExtendsId);
        }

        SymbolInfo classInfo = new SymbolInfo();
        classInfo.setType(classKeyWord);
        classInfo.setName(String.valueOf(idExtendsId));
        symbolTable.getRow().add(classInfo);

        return statement;
    }

    @Override
    public Statement visitIdExtendsId(ParserGram.IdExtendsIdContext ctx)
    {
        ExtendsId id = new ExtendsId();
        for (int i = 0; i < ctx.ID().size(); i++)
        {
            id.addChild(ctx.ID(i).getText());
        }
        return  id;
    }

    @Override
    public Statement visitBodyOfClass(ParserGram.BodyOfClassContext ctx)
    {
        BodyOfClass bodyOfClass = new BodyOfClass();
        for(int i = 0; i < ctx.methodDeclaration().size() ; i++)
        {
            MethodDeclaration methodDeclaration = (MethodDeclaration) visitMethodDeclaration(ctx.methodDeclaration(i));
            bodyOfClass.addChild(methodDeclaration);
        }
        return bodyOfClass;
    }

    @Override
    public Statement visitMethodDeclaration(ParserGram.MethodDeclarationContext ctx) {
        Statement statement;
        if (ctx.method() != null) {
            statement = visitMethod(ctx.method());

        } else if (ctx.staticMethod() != null)
        {
            statement = visitStaticMethod(ctx.staticMethod());
        }else
        {
            statement = visitConstructorMethod(ctx.constructorMethod());
        }

        return statement;
    }
    @Override
    public Statement visitMethod(ParserGram.MethodContext ctx) {
        String nameOfMethod = ctx.ID().getText();
        Statement parameters =visitParameters(ctx.parameters());
        Statement bodyOfMethod = visitBodyOfMethod(ctx.bodyOfMethod());

        if(ctx.parameters() != null) {
            return new NormalMethod(nameOfMethod, parameters, bodyOfMethod);
        }
        else
        {
            return  new NormalMethod(nameOfMethod,bodyOfMethod);

        }
    }

    @Override
    public Statement visitStaticMethod(ParserGram.StaticMethodContext ctx) {

        String staticKeyWord = ctx.STATIC().getText();
        Statement method =  visitMethod(ctx.method());
        return new StaticMethod(staticKeyWord,method);
    }
    @Override
    public Statement visitBodyOfMethod(ParserGram.BodyOfMethodContext ctx)
    {
        BodyOfMethod bodyOfMethod = new BodyOfMethod();
        for(int i = 0; i < ctx.statement().size() ; i++)
        {
            Statement s =  visitStatement(ctx.statement(i));

            bodyOfMethod.addChild(s);
        }
        return bodyOfMethod;

    }

    @Override
    public Statement visitConstructorMethod(ParserGram.ConstructorMethodContext ctx)
    {
        String constructorKeyWord = ctx.CONSTRUCTOR().getText();
        Statement parameter = visitParameters(ctx.parameters());
        Statement bodyOfConstructor =  visitBodyOfConstructor(ctx.bodyOfConstructor());

        return new ConstructorMethod(constructorKeyWord,parameter,bodyOfConstructor);
    }

    @Override
    public Statement visitBodyOfConstructor(ParserGram.BodyOfConstructorContext ctx)
    {

        String thisKeyWord = ctx.THIS().toString();
        ConstructorBody id = new ConstructorBody();
        for (int i = 0; i < ctx.ID().size(); i++) {
            id.addChild(ctx.ID(i).getText());
        }
        return id;
    }

    @Override
    public Statement visitCreateAnObjectStatement(ParserGram.CreateAnObjectStatementContext ctx)
    {
        String type = String.valueOf(visitDataType(ctx.dataType()));
        String var = ctx.ID().getText();
        Statement body = visitBodyOfObject(ctx.bodyOfObject());
        return new CreateAnObject(type,var,body);
    }

    @Override
    public Statement visitBodyOfObject(ParserGram.BodyOfObjectContext ctx)
    {

        String newKeyWord = ctx.NEW().getText();
        String nameOfClass = ctx.ID().getText();
        Literal literals = visitLiteralOrMore(ctx.literalOrMore());
        return new BodyOfObject(newKeyWord,nameOfClass,literals);
    }

    @Override
    public Literal visitLiteralOrMore(ParserGram.LiteralOrMoreContext ctx) {
        Literal literal = new Literal();
        for(int i = 0; i < ctx.literal().size() ; i++)
        {
            Literal l= (Literal) visit(ctx.literal(i));
            literal.addChild(l);
        }
        return literal;
    }



 /// variable & assignment

    @Override
    public Statement visitVariableDeclaration(ParserGram.VariableDeclarationContext ctx)
    {
        Statement values = null;
        String type = String.valueOf(visitDataType(ctx.dataType()));
        String variableName = ctx.ID().getText();
        if(ctx.variableValues() != null)
        {
            values = (Statement) visit(ctx.variableValues());
        }
        VariableDeclarationStatement var =  new VariableDeclarationStatement(type, variableName,values );

        SymbolInfo info = new SymbolInfo();
        String type1 = "Variable: "+type;
        String value2 = "value is  "+values;
        info.setType(type1);
        info.setName(variableName);
        info.setValue(value2);
        symbolTable.getRow().add(info);

        return var;
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
        else if (ctx.hook()!= null)
        {
            statement =(Statement) visit(ctx.hook());
        }
        else if (ctx.arrowFunction()!= null)
        {
            statement =(Statement) visit(ctx.arrowFunction());
        }
        else
        {
            statement =(Statement) visit(ctx.expr());
        }
        return statement;

    }

    ///import
    @Override
    public Statement visitImportStatement(ParserGram.ImportStatementContext ctx) {
        String importPath = ctx.StringLiteral().getText();
        String libraryName = "";
        if (ctx.ID()!=null){
            libraryName = ctx.ID().getText();
        }
        if (ctx.REACT()!=null){
            libraryName = ctx.REACT().getText();
        }
        //TODO: call hook here


        return new ImportStatement(importPath,libraryName);
    }

    ///literal
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

    ///function
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

    @Override
    public Statement visitAnonymousFunction(ParserGram.AnonymousFunctionContext ctx)
    {
        String dataType = String.valueOf(visitDataType( ctx.dataType()));
        String nameOfFunction =ctx.ID().getText();
        Statement funcDecStatement = (Statement) visit(ctx.functionDeclaration());


        SymbolInfo funcInfo = new SymbolInfo();
        String s = "Function :  "+dataType;
        funcInfo.setType(s);
        funcInfo.setName(nameOfFunction);
        symbolTable.getRow().add(funcInfo);

        return new AnonymousFunction(dataType,nameOfFunction,funcDecStatement);

    }


    @Override
    public Statement visitArrowFunction(ParserGram.ArrowFunctionContext ctx)
    {
        String dataType = String.valueOf(visitDataType( ctx.dataType()));
        String nameOfFunction =ctx.ID().getText();
        Statement block = visitBlock(ctx.block());
        Statement parameter = visitParameters(ctx.parameters());
        Statement statement ;
        if(ctx.parameters() != null)
        {
            statement = new ArrowFunction(dataType,nameOfFunction,parameter,block);
        }
        else
        {
            statement = new ArrowFunction(dataType,nameOfFunction,block);
        }

        SymbolInfo funcInfo = new SymbolInfo();
        String s = "function :  "+dataType;
        String s1 = "parameters is  "+parameter;
        funcInfo.setType(s);
        funcInfo.setName(nameOfFunction);
        funcInfo.setValue(s1);
        symbolTable.getRow().add(funcInfo);
        return statement;
    }
    @Override
    public Statement visitFunctionDeclaration(ParserGram.FunctionDeclarationContext ctx)
    {
        String function = ctx.FUNCTION().getText();
        String nameOfFunction = null;
        Statement parameter = null;
        Statement block = null;
        Statement statement;


        if(ctx.ID() != null)
        {
            nameOfFunction = ctx.ID().getText();
        }

        if(ctx.parameters() != null)
        {
            parameter = visitParameters(ctx.parameters());
        }
        if(ctx.block() != null)
        {
            block = visitBlock(ctx.block());
        }
        statement = new FunctionDeclaration(function,nameOfFunction,parameter,block);

        SymbolInfo func = new SymbolInfo();

        func.setType(function);
        func.setName(nameOfFunction);
        String s = "parameters is "+parameter;
        func.setValue(s);
        symbolTable.getRow().add(func);

        return statement;
    }


    /*@Override
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

    }*/

    @Override
    public Statement visitCallStatement(ParserGram.CallStatementContext ctx)
    {
        Statement statement;
        if(ctx.callFunction() != null)
        {
            statement =  visitCallFunction(ctx.callFunction());
        }
        else
        {
            statement = visitCallMethod(ctx.callMethod());
        }
        return statement;
    }
    @Override
    public Statement visitCallFunction(ParserGram.CallFunctionContext ctx)
    {

        Statement statement =  visitBodyOfCall(ctx.bodyOfCall());
        return new CallFunction(statement);

    }
    @Override
    public Statement visitCallMethod(ParserGram.CallMethodContext ctx) {
        String nameOfObj = ctx.ID().getText();
        Statement statement = visitBodyOfCall(ctx.bodyOfCall());
        return new CallMethod(nameOfObj,statement);
    }


    @Override
    public Statement visitBodyOfCall(ParserGram.BodyOfCallContext ctx)
    {
        String name = ctx.ID().getText();
        Statement parameter = visitParameters(ctx.parameters());
        if(ctx.parameters() != null)
        {
            return new BodyOfCall(name,parameter);
        }
        else{
            return new BodyOfCall(name);
        }
    }

    @Override
    public Statement visitAccessMethodInLogStatement(ParserGram.AccessMethodInLogStatementContext ctx) {
        String id = ctx.ID().getText();
        Statement statement = visitBodyOfCall(ctx.bodyOfCall());
        if(ctx.ID() != null)
        {
            return new AccessMethodInLogStatement(id,statement);
        }
        else
        {
            return new AccessMethodInLogStatement(statement);

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
        else if(ctx.variableDeclaration() != null) {
            statement = visitVariableDeclaration(ctx.variableDeclaration());
        }
        else{
            //بدو تعديل
            statement = (Statement) visitHook(ctx.hook());
        }
        return new BlockOfFunction(statement);
    }

    ///return
    @Override
    public Statement visitReturnStatement(ParserGram.ReturnStatementContext ctx) {
        // return super.visitReturnStatement(ctx);
        ReturnStatement returnStatement = new ReturnStatement();
        if (ctx.ID() != null) {
            returnStatement.addChild(ctx.ID().getText());
        } else if (ctx.literal() != null) {
            returnStatement.addChild(ctx.literal().getText());
        } else if (ctx.arrowFunction() != null)
        {
            returnStatement.addChild(ctx.arrowFunction().getText());
        }else {
            returnStatement.addChild(ctx.getText());
        }

        return returnStatement;
    }

    @Override
    public Statement visitPrintOrLogStatement(ParserGram.PrintOrLogStatementContext ctx) {
        // return (Statement) visit(ctx.expr()); // will return the expr
        String consoleKeyWord = ctx.CONSOLE().getText();
        String logKeyWord = ctx.LOG().getText();
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


    @Override
    public Statement visitStringInterpolationStatement(ParserGram.StringInterpolationStatementContext ctx)
    {
        String sDollar = ctx.SDOLLAR().getText();
        String thisKeyWord = ctx.THIS().getText();
        String dot = ctx.DOT().getText();
        String id = ctx.ID().getText();
        //if(ctx.THIS() != null)
        //{
        return new StringInterpolation(sDollar,thisKeyWord,dot,id);

       /* } else
        {
            return new StringInterpolation(sDollar,id);
        }*/
    }

    ///Expr
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

    @Override
    public Expr visitBoolExpr(ParserGram.BoolExprContext ctx)
    {
        String booleanVal ="";
        if(ctx.BOOL()!= null){
            booleanVal = ctx.BOOL().getText();
        }
        return new BoolExpr(booleanVal);
    }


    ///operationExpr

    @Override
    public Expr visitAdd(ParserGram.AddContext ctx) {

        Expr left = (Expr) visit(ctx.getChild(0));
        Expr right = (Expr) visit(ctx.getChild(2));
        return new Add(left,right);
    }

    @Override
    public Expr visitMin(ParserGram.MinContext ctx) {

        Expr left = (Expr) visit(ctx.getChild(0));
        Expr right = (Expr) visit(ctx.getChild(2));
        return new Min(left,right);
    }

    @Override
    public Expr visitMul(ParserGram.MulContext ctx) {

        Expr left = (Expr) visit(ctx.getChild(0));
        Expr right = (Expr) visit(ctx.getChild(2));
        return new Mul(left,right);    }

    @Override
    public Expr visitDiv(ParserGram.DivContext ctx) {

        Expr left = (Expr) visit(ctx.getChild(0));
        Expr right = (Expr) visit(ctx.getChild(2));
        return new Div(left,right);
    }

    @Override
    public Expr visitIncrement(ParserGram.IncrementContext ctx) {
        Expr left = (Expr) visit(ctx.getChild(0));
        return new Increment(left);    }

    @Override
    public Expr visitDecrement(ParserGram.DecrementContext ctx) {
        Expr left = (Expr) visit(ctx.getChild(0));
        return new Decrement(left);
    }

    ///compExpr

    @Override
    public Expr visitLessThan(ParserGram.LessThanContext ctx)
    {
        Expr left = (Expr) visit(ctx.getChild(0));
        Expr right = (Expr) visit(ctx.getChild(2));
        return new LessThan(left,right);
    }
    @Override
    public Expr visitGreaterThan(ParserGram.GreaterThanContext ctx) {
        Expr left = (Expr) visit(ctx.getChild(0));
        Expr right = (Expr) visit(ctx.getChild(2));
        return new GreaterThan(left,right);
    }

    @Override
    public Expr visitLessThanOrEqual(ParserGram.LessThanOrEqualContext ctx) {
        Expr left = (Expr) visit(ctx.getChild(0));
        Expr right = (Expr) visit(ctx.getChild(2));
        return new LessThanOrEqual(left,right);
    }

    @Override
    public Expr visitGreaterThanOrEqual(ParserGram.GreaterThanOrEqualContext ctx) {
        Expr left = (Expr) visit(ctx.getChild(0));
        Expr right = (Expr) visit(ctx.getChild(2));
        return new GreaterThanOrEqual(left,right);
    }

    ///logicExpr

    @Override
    public Expr visitAnd(ParserGram.AndContext ctx) {
        Expr left = (Expr) visit(ctx.getChild(0));
        Expr right = (Expr) visit(ctx.getChild(2));
        return new And(left,right);
    }

    @Override
    public Expr visitOr(ParserGram.OrContext ctx) {
        Expr left = (Expr) visit(ctx.getChild(0));
        Expr right = (Expr) visit(ctx.getChild(2));
        return new Or(left,right);
    }

    @Override
    public Expr visitEqualEqual(ParserGram.EqualEqualContext ctx) {
        Expr left = (Expr) visit(ctx.getChild(0));
        Expr right = (Expr) visit(ctx.getChild(2));
        return new EqualEqual(left,right);    }

    @Override
    public Expr visitNotEqual(ParserGram.NotEqualContext ctx) {
        Expr left = (Expr) visit(ctx.getChild(0));
        Expr right = (Expr) visit(ctx.getChild(2));
        return new NotEqual(left,right);
    }






    @Override
    public Statement visitUseState(ParserGram.UseStateContext ctx) {

        Statement pairValue = new PairValue("","");
        Statement contructorValue = null;

           if (!ctx.pairValue().isEmpty()){

               pairValue = (Statement) visit(ctx.pairValue());

           }
           if (
                   ctx.INTEGER()!=null
           ){
               IntegerLiteral integerLiteral = new IntegerLiteral();
               integerLiteral.addChild(Integer.parseInt(ctx.INTEGER().getText()));
               contructorValue = integerLiteral;
           }
           else if(ctx.parameters()!= null) {
               contructorValue = (Statement) visitParameters(ctx.parameters());
           }
           else {
               contructorValue = (Statement) visitArrowFunction(ctx.arrowFunction());
           }

        return new UseStateStatement(pairValue, contructorValue);

    }

    @Override
    public Object visitUseEffect(ParserGram.UseEffectContext ctx) {
        Statement arrowFunction = null;
        Statement block = null;
        if (ctx.arrowFunction()!=null){
            arrowFunction = (Statement)  visitArrowFunction(ctx.arrowFunction());
        }
        if (ctx.block()!=null){
            block = (Statement) visitBlock(ctx.block());
        }

        return new UseEffectStatement(arrowFunction,block);

    }

    @Override
    public Object visitUseCallback(ParserGram.UseCallbackContext ctx) {
        Statement parameters = null;
        Statement arrowFunction = null;

        if (ctx.parameters()!=null){
            parameters= (Statement) visit(ctx.parameters());
        }
        arrowFunction = (Statement)  visit(ctx.arrowFunction());
        return new UseCallbackStatement(parameters,arrowFunction);

    }

    @Override
    public Object visitHook(ParserGram.HookContext ctx) {
        Statement reactHook = null;
        if (ctx.useState()!=null){
            reactHook = (Statement) visit(ctx.useState());
        }
        else if (ctx.useState()!=null){
            reactHook = (Statement) visit(ctx.useState());
        }
        else if (ctx.useRef()!=null){
            reactHook = (Statement) visit(ctx.useRef());
        }
        else if (ctx.useCallback()!=null){
            reactHook = (Statement) visit(ctx.useCallback());
        }
        else if (ctx.useEffect()!=null){
            reactHook = (Statement) visit(ctx.useEffect());
        }
        else if (ctx.useContext()!=null){
            reactHook = (Statement) visit(ctx.useContext());
        }
        return new HookStatement(reactHook);
    }

    @Override
    public Object visitUseContext(ParserGram.UseContextContext ctx) {
        String context = "";
        if (ctx.ID()!=null){
            context = ctx.ID().getText();
        }
        return new UseContextStatement(context);
    }

    @Override
    public Object visitUseRef(ParserGram.UseRefContext ctx) {
        int param = 0;
        if (ctx.INTEGER()!=null){

            param = Integer.parseInt(ctx.INTEGER().getText());
            return new UseRefStatement(param);
        }
        return new UseRefStatement();

    }

    @Override
    public Object visitClickHandler(ParserGram.ClickHandlerContext ctx) {
        Statement arrowFunction;
        arrowFunction = (Statement) visit(ctx.arrowFunction());
        return new ClickHandlerStatement(arrowFunction);
    }

    @Override
    public Object visitPairValue(ParserGram.PairValueContext ctx) {
        return new PairValue(ctx.ID(0).getText(), ctx.ID(1).getText());
    }

    @Override
    public Object visitJsxBlock(ParserGram.JsxBlockContext ctx) {
        Statement jsxElement = visitJsxElement(ctx.jsxElement());
        return new JsxBlockNode(jsxElement);
    }

    @Override
    public Statement visitJsxElement(ParserGram.JsxElementContext ctx) {
        LinkedList<Statement> jsxChildren = new LinkedList<>();
        String jsxOpenTag = ctx.jsxOpenTag().ID().getText();
        LinkedList<Statement> attributes = new LinkedList<>();
        LinkedList<PropNode> styleProps = new LinkedList<>();
        Statement jsxClasse = null;
        Statement onClick = null;


        for (ParserGram.AttributeContext attributeCtx : ctx.jsxOpenTag().attribute()) {
            Statement attribute = visitAttribute(attributeCtx);
            if (attribute instanceof JsxAttributeNode) {
                attributes.add((Statement) attribute);
            }

        }
        if(ctx.jsxOpenTag().jsxClass() != null){
            jsxClasse = visitJsxClass(ctx.jsxOpenTag().jsxClass());
        }


        if(ctx.jsxOpenTag().attributeClick() != null){
            onClick = visitAttributeClick(ctx.jsxOpenTag().attributeClick());
        }

        if (ctx.jsxOpenTag().style() != null) {
            styleProps = visitStyle(ctx.jsxOpenTag().style());
        }


        for (ParserGram.JsxElementContext childCtx : ctx.jsxChildren().jsxElement()) {
            Statement child = visitJsxElement(childCtx);
            jsxChildren.add(child);
        }

        for (ParserGram.JsxOpenSelfCloseContext childCtx : ctx.jsxChildren().jsxOpenSelfClose()) {
            Statement child = visitJsxOpenSelfClose(childCtx);
            jsxChildren.add(child);
        }

        for (ParserGram.JsxExpreeionContext childCtx : ctx.jsxChildren().jsxExpreeion()) {
            Statement child = visitJsxExpreeion(childCtx);
            jsxChildren.add(child);
        }

        for (ParserGram.ElementJsContext childCtx : ctx.jsxChildren().elementJs()) {
            Statement child = visitElementJs(childCtx);
            jsxChildren.add(child);
        }

        for (ParserGram.JsxTextContext childCtx : ctx.jsxChildren().jsxText()) {
            Statement child = visitJsxText(childCtx);
            jsxChildren.add(child);
        }




        return new JsxElementNode(jsxOpenTag, attributes, jsxClasse, jsxChildren,onClick,styleProps);
    }




    @Override
    public Statement visitJsxOpenTag(ParserGram.JsxOpenTagContext ctx) {
        LinkedList<Statement> attributes = new LinkedList<>();
        Statement jsxClasse = null;


        for (ParserGram.AttributeContext attributeCtx : ctx.attribute()) {
            Statement attribute = visitAttribute(attributeCtx);
            attributes.add(attribute);
        }


        if(ctx.jsxClass() != null){
            jsxClasse = visitJsxClass(ctx.jsxClass());
        }

        return new JsxOpenTagNode(attributes, jsxClasse);

    }


    @Override
    public Statement visitAttribute(ParserGram.AttributeContext ctx) {
        String attributeName = ctx.ID().getText();
        String attributeValue = ctx.StringLiteral().getText();
        return new JsxAttributeNode(attributeName, attributeValue);
    }


    @Override
    public Statement visitJsxClass(ParserGram.JsxClassContext ctx) {
        String className = null ;
        String value = ctx.StringLiteral().getText();
        if(ctx.JSX_CLASS() != null) {
            className = ctx.JSX_CLASS().getText();
        }


        return new JsxClassNode(className, value);
    }


    @Override
    public Statement visitJsxOpenSelfClose(ParserGram.JsxOpenSelfCloseContext ctx) {
        String tagName = ctx.ID().toString();
        LinkedList<Statement> attributes = new LinkedList<>();
        Statement jsxClasse = null;

        if(ctx.jsxClass() != null){
            jsxClasse = visitJsxClass(ctx.jsxClass());
        }

        for (ParserGram.AttributeContext attributeCtx : ctx.attribute()) {
            Statement attribute = visitAttribute(attributeCtx);
            attributes.add(attribute);
        }

        return new JsxOpenSelfCloseNode(tagName, attributes,jsxClasse);
    }





    @Override
    public  Statement visitJsxText(ParserGram.JsxTextContext ctx) {
        String text = ctx.getText();
        return new JsxTextNode(text);
    }


    @Override
    public Statement visitJsxExpreeion(ParserGram.JsxExpreeionContext ctx) {
        String expressionText = ctx.ID().getText();
        return new JsxExpressionNode(expressionText);
    }


    @Override
    public Statement visitElementJs(ParserGram.ElementJsContext ctx) {
        String value = ctx.ID().getText();
        LinkedList<AttributeElementJsNode> attributes = new LinkedList<>();
        for(ParserGram.AttributeElementJsContext AttributeCtx : ctx.attributeElementJs()){

            Statement AttributeElement = visitAttributeElementJs(AttributeCtx);
            attributes.add((AttributeElementJsNode)AttributeElement);}

        return new JsxElementJsNode(value,attributes);
    }


    @Override
    public Statement visitAttributeElementJs(ParserGram.AttributeElementJsContext ctx) {
        String attributeName = ctx.ID().getText();
        String value = ctx.jsxExpreeion().ID().getText();
        return new AttributeElementJsNode(attributeName,value);
    }


    @Override
    public Statement visitAttributeClick(ParserGram.AttributeClickContext ctx) {
        String attributeName = "ON_CLICK";
        String value = ctx.jsxExpreeion().ID().getText();
        return new JsxAttributeClickNode(attributeName, value);
    }


    @Override
    public LinkedList<PropNode> visitStyle(ParserGram.StyleContext ctx) {
        LinkedList<PropNode> styleProps = new LinkedList<>();

        for (ParserGram.PropContext propCtx : ctx.props().prop()) {
            String propName = propCtx.ID().getText();
            String propValue =propCtx.literal().getText() ;
            styleProps.add(new PropNode(propName, propValue));
        }

        return styleProps;
    }

    ///loop

    @Override
    public Statement visitForStatement(ParserGram.ForStatementContext ctx)
    {
        Statement varDeclaration = null;

        Expr comparisonExpr = null;
        Expr exprOperation = null;
        Statement forBodyStatement = null;
        if (ctx.variableDeclaration() != null)
        {
            varDeclaration = visitVariableDeclaration(ctx.variableDeclaration());

        }
        if (ctx.comparisonExpr() != null)
        {
            comparisonExpr = (Expr) visit(ctx.comparisonExpr());
        }
        if (ctx.exprOperation() != null)
        {
            exprOperation = (Expr) visit(ctx.exprOperation());
        } if (ctx.forBodyStatement() != null)
    {
        forBodyStatement = visitForBodyStatement(ctx.forBodyStatement());
    }

        return new ForStatement(varDeclaration,comparisonExpr,exprOperation,forBodyStatement);
    }

    @Override
    public Statement visitForBodyStatement(ParserGram.ForBodyStatementContext ctx)
    {
        Statement statement;
        if(ctx.forStatement() != null)
        {
            statement = visitForStatement(ctx.forStatement());
        }else if(ctx.ifStatement() != null)
        {
            statement = visitIfStatement(ctx.ifStatement());
        }else
        {
            statement = visitPrintOrLogStatement(ctx.printOrLogStatement());
        }
        return statement;
    }

    @Override
    public Statement visitWhileStatement(ParserGram.WhileStatementContext ctx)
    {
        Expr condition;
        if(ctx.comparisonExpr() != null)
        {
            condition = (Expr) visit(ctx.comparisonExpr());
        }
        else
        {
            condition = (Expr) visit(ctx.expr());
        }
        Statement statement = null;
        if(ctx.statement() != null)
        {
            statement = visitStatement(ctx.statement());
        }
        Expr counter = null ;
        if (ctx.exprOperation() != null)
        {
            counter = (Expr) visit(ctx.exprOperation());
        }

        return new WhileStatement(condition,statement,counter);
    }

    @Override
    public Statement visitIfStatement(ParserGram.IfStatementContext ctx)
    {
        Statement statement = null;
        Expr condition ;
        if(ctx.comparisonExpr() != null)
        {
            condition =  (Expr) visit(ctx.comparisonExpr());

        }
        else if(ctx.logicalExpr() != null)
        {
            condition = (Expr) visit(ctx.logicalExpr());

        }
        else
        {
            condition = (Expr) visit(ctx.expr());
        }

        if(ctx.statement() != null)
        {
            statement = visitStatement(ctx.statement());
        }

        return  new IfStatement(condition,statement);
    }

    @Override
    public Statement visitElseStatement(ParserGram.ElseStatementContext ctx) {
        return visitStatement(ctx.statement());
    }

    @Override
    public Statement visitIfElseStatement(ParserGram.IfElseStatementContext ctx)
    {
        Statement s1 = null;//= visitIfStatement(ctx.ifStatement());
        Statement s2 = null;//=visitElseStatement(ctx.elseStatemetn());

        if(ctx.ifStatement() != null)
        {
            s1 = visitIfStatement(ctx.ifStatement());
        }
        if(ctx.elseStatement() != null)
        {
            s2 =visitElseStatement(ctx.elseStatement());

        }
        return new IfElseStatement(s1,s2);
    }

    @Override
    public Statement visitMultiIfElseStatement(ParserGram.MultiIfElseStatementContext ctx)
    {
        Statement s1 = null;
        Statement s2 = null;

        if(ctx.ifElseIfStatement() != null)
        {
            s1 = visitIfElseIfStatement(ctx.ifElseIfStatement());
        }
        if(ctx.elseStatement() != null)
        {
            s2 =visitElseStatement(ctx.elseStatement());

        }
        return new MultiIfElseStatement(s1,s2);
    }

    @Override
    public Statement visitIfElseIfStatement(ParserGram.IfElseIfStatementContext ctx)
    {
        IfElseIfStatement ifElseIf = new IfElseIfStatement();
        for(int i = 0; i < ctx.ifStatement().size() ; i++)
        {
            IfStatement statement = (IfStatement) visitIfStatement(ctx.ifStatement(i));
            ifElseIf.addToList( statement);
        }
        return ifElseIf;

    }
}


