package Visitor;
import AST.*;
import AST.Expr.Expr;
import AST.Expr.FloatExpr;
import AST.Expr.IdExpr;
import AST.Expr.IntExpr;
import AST.Function.*;
import AST.JSX.*;
import AST.Literal.*;
import AST.React.ClickHandlerStatement;
import AST.React.ReactHooks.*;
import grammar.ParserGram;
import grammar.ParserGramBaseVisitor;

import java.util.LinkedList;

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
        }
        else if(ctx.jsxBlock() != null)
        {
            statement = (Statement) visit(ctx.jsxBlock());
        }
        else if(ctx.importStatement() != null)
        {
            statement = (Statement) visit(ctx.importStatement());
        }else if(ctx.assignment()!= null)
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
        else if (ctx.hook()!= null)
        {
            statement =(Statement) visit(ctx.hook());
        }
        else
        {
            statement =(Statement) visit(ctx.expr());
        }
        return statement;

    }

    ////id
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
    {    String dataType ="null";

        String nameOfFunction = "null";
        if (ctx.dataType()!= null)
        {
           dataType = String.valueOf(visitDataType( ctx.dataType()));
        }
        if (ctx.ID()!=null){
            nameOfFunction =ctx.ID().getText();
        }
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
        LinkedList<Statement> jsxClasses = new LinkedList<>();
        Statement onClick = null;
        LinkedList<PropNode> styleProps = new LinkedList<>();

        for (ParserGram.AttributeContext attributeCtx : ctx.jsxOpenTag().attribute()) {
            Statement attribute = visitAttribute(attributeCtx);
            if (attribute instanceof JsxAttributeNode) {
                attributes.add((Statement) attribute);
            }

        }

        for (ParserGram.JsxClassContext jsxClassCtx : ctx.jsxOpenTag().jsxClass()) {
            Statement jsxClass = visitJsxClass(jsxClassCtx);
            if (jsxClass instanceof JsxClassNode) {
                jsxClasses.add((Statement) jsxClass);
            }
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




        return new JsxElementNode(jsxOpenTag, attributes, jsxClasses, jsxChildren,onClick,styleProps);
    }


    @Override
    public Statement visitJsxOpenTag(ParserGram.JsxOpenTagContext ctx) {
        LinkedList<Statement> attributes = new LinkedList<>();
        LinkedList<Statement> jsxClasses = new LinkedList<>();


        for (ParserGram.AttributeContext attributeCtx : ctx.attribute()) {
            Statement attribute = visitAttribute(attributeCtx);
            attributes.add(attribute);
        }


        for (ParserGram.JsxClassContext jsxClassCtx : ctx.jsxClass()) {
            Statement jsxClass = visitJsxClass(jsxClassCtx);
            jsxClasses.add(jsxClass);
        }

        return new JsxOpenTagNode(attributes, jsxClasses);

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


        for (ParserGram.AttributeContext attributeCtx : ctx.attribute()) {
            Statement attribute = visitAttribute(attributeCtx);
            attributes.add(attribute);
        }

        return new JsxOpenSelfCloseNode(tagName, attributes);
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
    }}


